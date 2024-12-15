package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型为 HTML
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 获取用户输入的用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username);
            System.out.println(password);

            // 连接数据库并查询管理员信息
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                // 加载数据库驱动（对于 JDBC 4.0 及以上版本，这一步通常是可选的）
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 获取数据库连接
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");
                // 构建 SQL 查询语句
                String sql = "SELECT * FROM admins WHERE username =? AND password =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();


                if (rs.next()) {
                    // 输出 HTML 页面头部和样式
                    out.println("<!DOCTYPE html>");
                    out.println("<html lang=\"zh\">");
                    out.println("<head>");
                    out.println("<meta charset=\"UTF-8\">");
                    out.println("<title>管理员页面</title>");
                    out.println("<style>");
                    out.println(".jz { text-align: center; }");
                    out.println(".button-container { text-align: center; }");
                    out.println(".button { padding: 10px 20px; background-color: white; color: black; border: 2px solid black; border-radius: 5px; cursor: pointer; font-size: 16px; }");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<header>");
                    out.println("<div class=\"jz\">");
                    out.println("<p>学生指导关系管理系统</p>");
                    out.println("</div>");
                    out.println("</header>");
                    out.println("<div class=\"button-container\">");
                    out.println("<a href=\"DataServlet\"><button class=\"button\" type=\"button\">查看总体情况</button></a>");
                    out.println("<button class=\"button\" type=\"button\">查看指导关系进度</button>");
                    out.println("<button class=\"button\" type=\"button\">查看系统情况</button>");
                    out.println("<a href=\"classSearch.html\"><button class=\"button\" type=\"button\">按班级汇总</button></a>");
                    out.println("<a href=\"majorSearch.html\"><button class=\"button\" type=\"button\">按专业汇总</button></a>");
                    out.println("<a href=\"department.html\"><button class=\"button\" type=\"button\">按系部汇总</button></a>");
                    out.println("<a href=\"academy.html\"><button class=\"button\" type=\"button\">按学院汇总</button></a>");
                    out.println("</div>");

                    out.println("<html>");
                    out.println("<head><title>管理员信息</title>");
                    out.println("<style>");
                    // 整体页面布局样式，设置页面为弹性布局，让内容在垂直和水平方向居中
                    out.println("body {");
                    out.println("    display: flex;");
                    out.println("    justify-content: center;");
                    out.println("    align-items: center;");
                    out.println("    min-height: 100vh;");
                    out.println("    margin: 0;");
                    out.println("    background-color: #f4f4f4;");
                    out.println("    font-family: Arial, sans-serif;");
                    out.println("}");

                    // 表格整体样式
                    out.println("table {");
                    out.println("    width: 80%; /* 根据需要调整表格宽度，这里设置为页面宽度的80% */");
                    out.println("    border-collapse: collapse;");
                    out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);");
                    out.println("}");
                    // 表头样式
                    out.println("th {");
                    out.println("    background-color: #3498db;");
                    out.println("    color: white;");
                    out.println("    padding: 15px;");
                    out.println("    text-align: left;");
                    out.println("}");
                    // 表格数据行样式
                    out.println("td {");
                    out.println("    padding: 15px;");
                    out.println("    border-bottom: 1px solid #ddd;");
                    out.println("}");
                    // 行交替颜色
                    out.println("tr:nth-child(even) {");
                    out.println("    background-color: #f9f9f9;");
                    out.println("}");
                    // 鼠标悬停行颜色变化
                    out.println("tr:hover {");
                    out.println("    background-color: #e6e6e6;");
                    out.println("}");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>管理员编号</th>");
                    out.println("<th>姓名</th>");
                    out.println("<td>" + rs.getString("AdminID") + "</td>");
                    out.println("<td>" + rs.getString("username") + "</td>");
                    // 根据实际表字段添加更多数据

                    out.println("</tr>");
                } else {
                    out.println("用户名或密码错误！");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (rs!= null) rs.close();
                    if (pstmt!= null) pstmt.close();
                    if (conn!= null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
