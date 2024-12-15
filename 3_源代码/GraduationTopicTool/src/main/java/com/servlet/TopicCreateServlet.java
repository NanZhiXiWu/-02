package com.servlet;


import com.util.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/topicCreate")
public class TopicCreateServlet  extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        String teacherId = req.getParameter("teacherId");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String sourceofTopic = req.getParameter("sourceofTopic");
        String researchDirection = req.getParameter("researchDirection");
        String theoryAndTechRequirements = req.getParameter("theoryAndTechRequirements");
        String selectionMethod = req.getParameter("selectionMethod");
        String status = req.getParameter("status");
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "INSERT INTO Topics (topicId,teacherId, title, description, sourceofTopic, researchDirection, theoryAndTechRequirements, selectionMethod, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,topicId);
            pstmt.setString(2, teacherId);
            pstmt.setString(3, title);
            pstmt.setString(4, description);
            pstmt.setString(5, sourceofTopic);
            pstmt.setString(6, researchDirection);
            pstmt.setString(7, theoryAndTechRequirements);
            pstmt.setString(8, selectionMethod);
            pstmt.setString(9,status);

            int row = pstmt.executeUpdate();
            System.out.println(row + "行受影响！");

            DbUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}