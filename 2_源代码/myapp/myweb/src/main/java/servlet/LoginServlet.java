package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 数据库连接信息（请根据您的实际数据库配置进行修改）
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tea_course_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        /*response.setContentType("text/html;charset=UTF-8");*/

        String role = request.getParameter("role");
        System.out.println(role);
       /* PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Data Display</title></head>");
        out.println("<body>");
        out.println("<table border='1'>");
        out.println("<tr><th>Parameter</th><th>Value</th></tr>");
        out.println("<tr><td>Username</td><td>" + username + "</td></tr>");
        out.println("<tr><td>Password</td><td>" + password + "</td></tr>");
        out.println("<tr><td>Role</td><td>" + role + "</td></tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");*/

        // 调用数据库验证方法
        boolean isValid = validateUser(username, password, role);

        if (isValid) {
            System.out.println("选择角色");
            // 根据角色重定向到相应的页面
            String redirectUrl;
            switch (role) {
                case "student":
                    redirectUrl = "student.html";
                    break;
                case "teacher":
                    redirectUrl = "teacher.html";
                    break;
                case "departmentHead":
                    System.out.println("定向系主任");
                    redirectUrl = "director.html";
                    break;
                case "admin":
                    redirectUrl = "administrators.html";

                    break;
                default:
                    redirectUrl = "error.html"; // 未知角色时重定向到错误页面
                    break;
            }
           /* response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>Your Page Title</title></head>");
            out.println("<body>");
 StringBuilder formBuilder = new StringBuilder();
            formBuilder.append("<form id='redirectForm' method='post' action='").append(redirectUrl).append("'>");
            formBuilder.append("<input type='hidden' name='username' value='").append(username).append("'>");
            formBuilder.append("<input type='hidden' name='password' value='").append(password).append("'>");
            formBuilder.append("<input type='hidden' name='role' value='").append(role).append("'>");
            formBuilder.append("</form>");
            formBuilder.append("<script>document.getElementById('redirectForm').submit();</script>");

            response.getWriter().write(formBuilder.toString());
            out.println(formBuilder.toString());
            out.println("</body>");
            out.println("</html>");*/
           /* StringBuilder formBuilder = new StringBuilder();
            formBuilder.append("<form id='redirectForm' method='post' action='").append(redirectUrl).append("'>");
            formBuilder.append("<input type='hidden' name='role' value='").append(role).append("'>");
            formBuilder.append("</form>");
            formBuilder.append("<script>document.getElementById('redirectForm').submit();</script>");

            response.getWriter().write(formBuilder.toString());*/
            response.sendRedirect(redirectUrl+"?username=" + username + "&password=" + password);
        } else {
            System.out.println("验证失败");
            // 验证失败，重定向回登录页面并显示错误消息
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    // 数据库验证方法
    private boolean validateUser(String username, String password, String role) {
        System.out.println("进入方法");
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql;
            if ("admin".equals(role)) {
                sql = "SELECT * FROM admins WHERE username =? AND password =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                System.out.println("admin".equals(role));
                isValid=true;

            } else if ("teacher".equals(role)) {
                sql = "SELECT * FROM teachers WHERE username =? AND password =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                isValid=true;
            } else if("departmentHead".equals(role)){
                System.out.println("进入系主任");
                sql = "SELECT * FROM director WHERE username =? AND password =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                isValid=true;
            }
            else if("student".equals(role)){
                sql = "SELECT * FROM students WHERE username =? AND password =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                isValid=true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(isValid);
        return isValid;
    }
}