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

@WebServlet("/MajorSearchServlet")
public class MajorSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 获取从前端传递过来的专业参数
            String selectedMajor = request.getParameter("selectedMajor");

            // 连接数据库并查询数据
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                // 加载数据库驱动（对于JDBC 4.0及以上版本，这一步通常是可选的）
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 获取数据库连接
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");

                // 构建SQL查询语句，根据专业进行筛选
                String sql = "SELECT * FROM ad_whole WHERE major =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, selectedMajor);
                rs = pstmt.executeQuery();

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
                out.println("</table>");
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