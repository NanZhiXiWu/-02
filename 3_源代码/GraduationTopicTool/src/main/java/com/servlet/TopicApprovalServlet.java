package com.servlet;

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
import java.sql.SQLException;

@WebServlet("/topicApproval")
public class TopicApprovalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        // 从请求中获取选题申请ID和审核结果
        int requestId = (int) session.getAttribute("requestId");
        boolean approved = (boolean)session.getAttribute("approved");

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE topic SET approved = ? WHERE topicId = ?")) {

            stmt.setBoolean(1, approved);
            stmt.setInt(2, requestId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated status of request ID " + requestId);
            } else {
                System.out.println("No rows affected for request ID " + requestId);
            }
        } catch (SQLException e) {
            System.err.println("Error updating request status: " + e.getMessage());
        }
    }
}
