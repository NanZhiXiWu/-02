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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userType = request.getParameter("user-type");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 检查用户类型
        if (userType == null || username == null || password == null) {
            response.sendRedirect("error.html");
            return;
        }
        // 登录验证逻辑
        boolean isAuthenticated = authenticateUser(userType, username, password);
        // 根据验证结果进行不同的跳转
        if (isAuthenticated) {
            // 创建 session 并保存用户信息
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            session.setAttribute("username", username);
            // 根据用户类型进行跳转
            switch (userType) {
                case "Student":
                    response.sendRedirect("dynamic/studentDashboard.jsp");
                    break;
                case "Teacher":
                    response.sendRedirect("dynamic/teacherDashboard.jsp");
                    break;
                case "Dean":
                    response.sendRedirect("static/deanDashboard.html");
                    break;
                case "Admin":
                    response.sendRedirect("static/adminHomePage.html");
                    break;
                default:
                    response.sendRedirect("error.html");
            }
        } else {
            response.sendRedirect("login.html?error=invalid_credentials");
        }
    }
    private boolean authenticateUser(String userType, String username, String password) throws ServletException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            String sql = "SELECT * FROM User WHERE username = ? AND password = ? AND role = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, userType);

            rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            // 关闭数据库连接
            DbUtil.closeConnection(conn);
        }

    }
}