
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/PublishTopicServlet")
public class PublishTopicServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=project;encrypt=false;trustServerCertificate=true";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "1111";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topicName = request.getParameter("topicName");
        String topicType = request.getParameter("topicType");
        String source = request.getParameter("source");
        String requirements = request.getParameter("requirements");
        String research_direction = request.getParameter("researchDirection");
        String topicId = request.getParameter("topicId");
        // 检查空值并处理
        if (topicName == null || topicName.trim().isEmpty()) {
            topicName = "默认主题名";  // 设置默认值
        }

        // 获取来源页面的 URL
        String referer = request.getHeader("Referer");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 获取课题详情（creator, status, department, teacherName）
            String sqlSelect = "SELECT creator, status, department, teacherName FROM Topics WHERE topicId = ?";
            pstmt = conn.prepareStatement(sqlSelect);
            pstmt.setString(1, topicId); // 设置查询条件，假设通过 topicId 查询
            rs = pstmt.executeQuery();

            // 如果查询到结果
            if (rs.next()) {
                // 将查询结果存入 request 对象，供 JSP 页面使用
                request.setAttribute("creator", rs.getString("creator"));
                request.setAttribute("status", rs.getString("status"));
                request.setAttribute("department", rs.getString("department"));
                request.setAttribute("teacherName", rs.getString("teacherName"));

            }
            //插入新课题的 SQL 语句
            String sql = "INSERT INTO Topics (topicName, topicType, source, requirements, researchDirection) " +
                    "VALUES (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, topicName);
            pstmt.setString(2, topicType);
            pstmt.setString(3, source);
            pstmt.setString(4, requirements);
            pstmt.setString(5, research_direction);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                // 将来源页面 URL 传递到 success.jsp
                request.setAttribute("referer", referer);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                response.sendRedirect("failure.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().write("<h1>Error: " + e.getMessage() + "</h1>");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

