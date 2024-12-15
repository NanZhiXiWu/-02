package com.servlet;

import com.module.*;
import com.util.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TopicApply")
public class TopicApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topicId = request.getParameter("topicId");
        String studentId = request.getParameter("studentId");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Topic> topicList = new ArrayList<>();

        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT t.topicId, t.title, t.description, te.code AS teacherName, da.approvalStatus " +
                    "FROM Topic t " +
                    "LEFT JOIN Teacher te ON t.teacherId = te.id " +
                    "LEFT JOIN DeanApproval da ON t.topicId = da.topicId" +
                    "WHERE topicId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, topicId);
            rs = stmt.executeQuery();
            Topic topic = new Topic();
            Students students = new Students();

            while (rs.next()) {
                String topicName = rs.getString("title");
                String topicDescription = rs.getString("description");
                String sourceOfTopic = rs.getString("sourceOfTopic");

                Teacher teacher = new Teacher();
                teacher.setCode(rs.getString("teacherName"));

                DeanApproval deanApproval = new DeanApproval();
                deanApproval.setStatus(rs.getString("approvalStatus"));

                // 构建 Topic 对象

                topic.setTopicId(Integer.parseInt(topicId));
                topic.setTopicName(topicName);
                topic.setTopicDescription(topicDescription);
                topic.setTeacher(teacher);
                topic.setDeanApproval(deanApproval);
                topicList.add(topic);
            }

            String query = "SELECT s.studentID, s.phone, s.major" +
                    "FROM Students s " +
                    "JOIN User u ON s.studentID = u.studentID " +
                    "WHERE s.studentID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while(rs.next()){
                students.setId(rs.getString("studentId"));
                students.setPhone(rs.getString("phone"));
                students.setMajor(rs.getString("major"));
            }
            StudentApplication studentApplication = new StudentApplication(students,topic);
            request.setAttribute("topic", studentApplication);
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            // 关闭数据库连接
            DbUtil.closeConnection(conn);
        }
    }
}
