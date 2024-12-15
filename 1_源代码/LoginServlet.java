import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // 数据库连接信息（适用于 SQL Server）
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=project;encrypt=false;trustServerCertificate=true;useUnicode=true&characterEncoding=UTF-8";

    private static final String DB_USER = "sa";  // 根据实际情况修改
    private static final String DB_PASSWORD = "1111"; // 根据实际情况修改

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的信息
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        // 响应字符集设置
        response.setContentType("text/html; charset=UTF-8");

        // 验证用户输入的登录信息
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 编写SQL查询语句
            String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND role = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // 设置查询参数
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, role);

                // 执行查询
                ResultSet resultSet = statement.executeQuery();

                // 判断用户是否存在并且信息是否匹配
                if (resultSet.next()) {
                    // 登录成功，设置session并跳转到主页
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("role", role);
                    session.setAttribute("gender", resultSet.getString("gender"));
                    session.setAttribute("department", resultSet.getString("department"));
                    session.setAttribute("title", resultSet.getString("title"));

                    // 如果是教师角色，查询课题信息
                    if (role.equals("教师")) {
                        String topicSql = "SELECT * FROM Topics WHERE teacherName = ?";
                        try (PreparedStatement topicStmt = connection.prepareStatement(topicSql)) {
                            topicStmt.setString(1, username);
                            ResultSet topicResult = topicStmt.executeQuery();

                            // 将课题信息存入session对象
                            if (topicResult.next()) {
                                session.setAttribute("creator", topicResult.getString("creator"));
                                session.setAttribute("status", topicResult.getString("status"));
                                session.setAttribute("department", topicResult.getString("department"));
                                session.setAttribute("teacherName", topicResult.getString("teacherName"));
                                session.setAttribute("title", topicResult.getString("title"));
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        // 跳转到teacher.jsp页面
                        response.sendRedirect("/untitled2/teacher.jsp");
                        //信息转发到toptic.jsp页面
                    } else if (role.equals("学生")) {
                        response.sendRedirect("/untitled2/student.jsp");
                    } else {
                        response.sendRedirect("admin_home.jsp");  // 其他角色跳转到管理员首页
                    }
                } else {
                        // 用户信息错误，跳转回登录页面并显示错误信息
                        response.getWriter().println("<h3>用户名、密码或角色错误，请重试！</h3>");
                        response.getWriter().println("<a href='login.html'>返回登录页面</a>");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("<h3>数据库连接失败，请稍后再试！</h3>");
            }
        }
    @Override
    public void init() throws ServletException {
        // 初始化数据库驱动，确保数据库连接可用
        try {
            // 使用 SQL Server 的 JDBC 驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("数据库驱动加载失败");
        }
    }
}
