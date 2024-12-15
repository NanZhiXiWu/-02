package com.servlet;

import com.module.DeanApproval;
import com.module.Teacher;
import com.module.Topic;
import com.util.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/StudentDashboardServlet")
public class StudentDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话对象
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("studentId");

        // 查询毕设选题信息
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Topic> topicList = new ArrayList<>();

        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT t.topicId, t.title, t.description, te.code AS teacherName, da.approvalStatus " +
                    "FROM Topic t " +
                    "LEFT JOIN Teacher te ON t.teacherId = te.id " +
                    "LEFT JOIN DeanApproval da ON t.topicId = da.topicId";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int topicId = rs.getInt("topicId");
                String topicName = rs.getString("title");
                String topicDescription = rs.getString("description");
                String sourceOfTopic = rs.getString("sourceOfTopic");

                Teacher teacher = new Teacher();
                teacher.setCode(rs.getString("teacherName"));

                DeanApproval deanApproval = new DeanApproval();
                deanApproval.setStatus(rs.getString("approvalStatus"));

                // 构建 Topic 对象
                Topic topic = new Topic();
                topic.setTopicId(topicId);
                topic.setTopicName(topicName);
                topic.setTopicDescription(topicDescription);
                topic.setTeacher(teacher);
                topic.setDeanApproval(deanApproval);
                topicList.add(topic);
            }

            if (topicList.isEmpty()) {
                request.setAttribute("message", "没有毕设题目。");
            } else {
                request.setAttribute("topicList", topicList);
                request.setAttribute("studentId", studentId);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            // 关闭数据库连接
            DbUtil.closeConnection(conn);
        }

        // 转发请求到 JSP 页面
        request.getRequestDispatcher("/studentDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
