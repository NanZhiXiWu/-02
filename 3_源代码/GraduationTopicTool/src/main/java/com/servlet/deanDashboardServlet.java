package com.servlet;

import com.module.DeanApproval;
import com.module.Teacher;
import com.util.DbUtil;
import com.module.Topic;
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

@WebServlet("/DeanDashboardServlet")
public class deanDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话对象
        HttpSession session = request.getSession();
        String deanId = (String) session.getAttribute("deanId");

        if (deanId == null) {
            // 如果系主任未登录，重定向到登录页面
            response.sendRedirect("static/Login.html");
            return;
        }

        // 查询系主任管理的所有毕设选题信息
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Topic> topics = new ArrayList<>();

        try {
            // 获取数据库连接
            conn = DbUtil.getConnection();

            String sql = "SELECT Topic.topicId, Topic.title, Topic.description, Teacher.id, Teacher.username, " +
                    "Teacher.departmentName, DeanApproval.approvalStatus, DeanApproval.approvalId, DeanApproval.approvalStatus, " +
                    "DeanApproval.approvalDate " +
                    "FROM Topic " +
                    "LEFT JOIN Teacher ON Topic.teacherId = Teacher.id " +
                    "LEFT JOIN DeanApproval ON Topic.topicId = DeanApproval.topicId " +
                    "WHERE Teacher.departmentName = '系主任';";
            stmt = conn.prepareStatement(sql);

            // 执行查询
            rs = stmt.executeQuery();

            // 处理结果集
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topicId"));
                topic.setTopicName(rs.getString("title"));
                topic.setTopicDescription(rs.getString("description"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getString("id"));
                teacher.setUsername(rs.getString("username"));
                teacher.setDepartmentName(rs.getString("departmentName"));
                topic.setTeacher(teacher);

                DeanApproval deanApproval = new DeanApproval();
                deanApproval.setStatus(rs.getString("approvalStatus"));
                deanApproval.setApprovalComments(rs.getString("approvalId"));
                topic.setDeanApproval(deanApproval);

                topics.add(topic);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            // 关闭数据库连接
            DbUtil.closeConnection(conn);
        }

        // 将选题信息列表存储到请求属性中
        request.setAttribute("topics", topics);

        // 转发到系主任控制台 JSP 页面
        request.getRequestDispatcher("dynamic/deanDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取审批操作和审批意见
        String topicId = request.getParameter("topicId");
        String approvalAction = request.getParameter("approvalAction");
        String approvalComment = request.getParameter("approvalComment");

        if (topicId == null || approvalAction == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters.");
            return;
        }

        // 更新数据库中的审批状态
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DbUtil.getConnection();
            String sql = "UPDATE DeanApproval SET approvalStatus = ?, approvalDate = ? WHERE topicId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "同意".equals(approvalAction) ? "已同意" : "已退回");
            stmt.setString(2, approvalComment);
            stmt.setString(3, topicId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            // 关闭数据库连接
            DbUtil.closeConnection(conn);
        }

        // 重定向到GET方法以刷新页面
        response.sendRedirect("DeanDashboardServlet");
    }
}
