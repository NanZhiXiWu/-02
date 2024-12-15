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

@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // HTML 页面头部和样式
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"zh\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>管理员 UI</title>");
            out.println("<style>");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".status-approved { color: rgb(238, 240, 238); }");
            out.println(".status-denied { color: rgb(247, 245, 245); }");
            out.println(".status-pending { color: rgb(254, 254, 252); }");
            out.println(".jz{ text-align: center; }");
            out.println(".button-container { text-align: center; }");
            out.println(".button { padding: 10px 20px; background-color: white; color:black; border: 2px solid black; border-radius: 5px; cursor: pointer; font-size: 16px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div class=\"jz\">");
            out.println("<p>学生指导关系管理系统</p>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class=\"button-container\">");
            out.println("<a href=\"administrators.html\"><button class=\"button\" type=\"button\">首页</button></a>");
            out.println("<a href=\"whole.html\"><button class=\"button\" type=\"button\">查看总体情况</button></a>");
            out.println("<button class=\"button\" type=\"button\">查看指导关系进度</button>");
            out.println("<button class=\"button\" type=\"button\">查看系统情况</button>");
            out.println("<a href=\"class.html\"><button class=\"button\" type=\"button\">按班级汇总</button></a>");
            out.println("<a href=\"specialty.html\"><button class=\"button\" type=\"button\">按专业汇总</button></a>");
            out.println("<a href=\"department.html\"><button class=\"button\" type=\"button\">按系部汇总</button></a>");
            out.println("<a href=\"academy.html\"><button class=\"button\" type=\"button\">按学院汇总</button></a>");
            out.println("</div>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>题号</th>");
            out.println("<th>题目名称</th>");
            out.println("<th>选题方式</th>");
            out.println("<th>学号</th>");
            out.println("<th>姓名</th>");
            out.println("<th>班级</th>");
            out.println("<th>专业</th>");
            out.println("<th>系部</th>");
            out.println("<th>学院</th>");
            out.println("<th>电话</th>");
            out.println("<th>状态</th>");
            out.println("<th>详情</th>");
            out.println("</tr>");

            // 连接数据库并查询数据
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                // 加载数据库驱动（对于 JDBC 4.0 及以上版本，这一步通常是可选的）
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 获取数据库连接
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");
                // 构建 SQL 查询语句
                String sql = "SELECT question_id, question_name, selection_method, student_id, student_name, class_name, major, department, college, phone_number, status FROM ad_whole";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("question_id") + "</td>");
                    out.println("<td>" + rs.getString("question_name") + "</td>");
                    out.println("<td>" + rs.getString("selection_method") + "</td>");
                    out.println("<td>" + rs.getString("student_id") + "</td>");
                    out.println("<td>" + rs.getString("student_name") + "</td>");
                    out.println("<td>" + rs.getString("class_name") + "</td>");
                    out.println("<td>" + rs.getString("major") + "</td>");
                    out.println("<td>" + rs.getString("department") + "</td>");
                    out.println("<td>" + rs.getString("college") + "</td>");
                    out.println("<td>" + rs.getString("phone_number") + "</td>");
                    out.println("<td>" + rs.getString("status") + "</td>");
                    out.println("<td><a href=\"#\">详情</a></td>");
                    out.println("</tr>");
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

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
