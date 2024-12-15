package com.servlet;

import com.module.Teacher;
import com.util.DbUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacherDashboard")
public class TeacherDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "viewManageQuestions":
                viewAndManageQuestions(request, response);
                break;
            case "createGraduationQuestion":
                createGraduationQuestion(request, response);
                break;
            case "modifyGraduationQuestion":
                modifyGraduationQuestion(request, response);
                break;
            case "deleteGraduationQuestion":
                deleteGraduationQuestion(request, response);
                break;
            case "auditStudentSelectionRequest":
                auditStudentSelectionRequest(request, response);
                break;
            case "viewPersonalInfo":
                viewPersonalInfo(request, response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Invalid action");
        }
    }

    private void viewAndManageQuestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取题目列表
//        List<Topic> topics = getQuestionsFromDatabase();
//        request.setAttribute("Topic", topics);
//        List<Students> students = getStudentFromDatabase();
//        request.setAttribute("Students",students);
//        List<Teacher> teachers = getTeacherFromDatabase();
//        request.setAttribute("teachers",teachers);
        List<com.module.StudentApplication>studentapplications = getTopicFromDatabase();
        request.setAttribute("Studentapplication",studentapplications);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dynamic/teacherDashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void createGraduationQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转到静态 然后绑定TopicCreateServlet
        response.sendRedirect("/static/Create_a_question.html");
    }

    private void modifyGraduationQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中获取题目ID和更新后的信息
        int id = Integer.parseInt(request.getParameter("topicId"));
        String title = request.getParameter("title");
        String content = request.getParameter("description");

        // 更新数据库中的题目
        updateQuestionInDatabase(id, title, content);

        response.sendRedirect("/WebContent/dynamic/teacherDashboard.jsp/viewManageQuestions.action");
    }

    private void deleteGraduationQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中获取题目ID
        int id = Integer.parseInt(request.getParameter("topicId"));

        // 删除数据库中的题目
        deleteQuestionFromDatabase(id);

        response.sendRedirect("/WebContent/dynamic/teacherDashboard.jsp/viewManageQuestions.action");
    }

    private void auditStudentSelectionRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        boolean approved = Boolean.parseBoolean(request.getParameter("approved"));
        HttpSession session =request.getSession();
        session.setAttribute("requestId",requestId );
        session.setAttribute("approved",approved);
        response.sendRedirect(request.getContextPath()+"/topicApproval");

        /*// 从请求中获取选题申请ID和审核结果
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        boolean approved = Boolean.parseBoolean(request.getParameter("approved"));

        // 更新数据库中的选题申请状态
        updateStudentSelectionRequestStatus(requestId, approved);

        response.sendRedirect("/WebContent/dynamic/teacherDashboard.jsp/auditStudentSelectionRequests.action");*/
    }

    private void viewPersonalInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从数据库获取教师个人信息
        Teacher teacher = getTeacherInfoFromDatabase();

        request.setAttribute("teacher", teacher);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/teacher/personalInfo.jsp");
        dispatcher.forward(request, response);
    }


//    private List<Teacher> getTeacherFromDatabase(){
//        List<Teacher> teachers = new ArrayList<>();
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM teacher");
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Teacher teacher = new Teacher();
//                teacher.setUsername(rs.getString("name"));
//                teacher.setCode(rs.getString("code"));
//                teacher.setGender(rs.getString("gender"));
//                teacher.setPhone(rs.getString("phone"));
//                teacher.setDepartmentName(rs.getString("departmentName"));
//                teacher.setProfessionalTitle(rs.getString("professionalTitle"));
//                teacher.setDetail(rs.getString("detail"));
//                DbUtil.closeConnection(conn);
//            }
//        } catch ( SQLException e) {
//            System.err.println("Error fetching questions: " + e.getMessage());
//            return new ArrayList<>(); // 返回空列表以避免NPE
//        }
//
//        return teachers;
//    }
//
//        private List<Students> getStudentFromDatabase(){
//            List<Students> students = new ArrayList<>();
//            try (Connection conn = DbUtil.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students");
//                 ResultSet rs = stmt.executeQuery()) {
//
//                while (rs.next()) {
//                    Students student = new Students();
//                    student.setUsername(rs.getString("name"));
//                    student.setPhone(rs.getString("phone"));
//                    student.setMajor(rs.getString("major"));
//                    student.setId(rs.getString("id"));
//                    students.add(student);
//                    DbUtil.closeConnection(conn);
//                }
//            } catch ( SQLException e) {
//                System.err.println("Error fetching questions: " + e.getMessage());
//                return new ArrayList<>(); // 返回空列表以避免NPE
//            }
//
//            return students;
//        }
        private List<com.module.StudentApplication> getTopicFromDatabase () {
            // 实际实现数据库查询
            List<com.module.StudentApplication> studentapplications = new ArrayList<>();
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM studentapplications");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    com.module.StudentApplication studentapplication = new com.module.StudentApplication();
                    studentapplication.setTopicId(rs.getInt("topicId"));
                    studentapplication.setStudentId(rs.getString("studentId"));
                    studentapplication.setStudentName(rs.getString("studentName"));
                    studentapplication.setMajor(rs.getString("major"));
                    studentapplication.setPhoneNumber(rs.getString("phoneNumber"));
                    studentapplication.setTopicName(rs.getString("topicName"));
                    studentapplication.setSelectionMethod(rs.getString("selectionMethod"));
                    studentapplication.setTeacherName(rs.getString("teacherName"));
                    studentapplication.setStatus(rs.getString("status"));
                    studentapplication.setApproved(rs.getString("approved"));
//                    Topic topic = new Topic();
//                    topic.setTopicId(rs.getInt("topicId"));
//                    topic.setSelectionMethod(rs.getString("selectionMethod"));
//                    topic.setTopicName(rs.getString("title"));
//                    topic.setTopicDescription(rs.getString("description"));
//                    topic.setStatus(rs.getString("status"));
//                    topics.add(topic);
                    studentapplications.add(studentapplication);
                    DbUtil.closeConnection(conn);
                }
            } catch ( SQLException e) {
                System.err.println("Error fetching questions: " + e.getMessage());
            }
            return studentapplications;

        }

        /*private void addQuestionToDatabase (String title, String content){
            // 实际实现数据库插入
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO questions (title, content) VALUES (?, ?)",
                         Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, title);
                stmt.setString(2, content);
                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        System.out.println("Added question with ID: " + id);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error adding question: " + e.getMessage());
            }
        }
*/
        private void updateQuestionInDatabase( int id, String title, String content){
            // 实际实现数据库更新
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE topic SET title = ?, description = ? WHERE topicId = ?")) {

                stmt.setString(1, title);
                stmt.setString(2, content);
                stmt.setInt(3, id);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Updated " + rowsAffected + " row(s)");
                } else {
                    System.out.println("No rows affected");
                }
            } catch (SQLException e) {
                System.err.println("Error updating question: " + e.getMessage());
            }
        }

        private void deleteQuestionFromDatabase ( int id){
            // 实际实现数据库删除
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM topic WHERE id = ?")) {

                stmt.setInt(1, id);
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Deleted " + rowsDeleted + " row(s)");
                } else {
                    System.out.println("No rows deleted");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting question: " + e.getMessage());
            }
        }

        /*private void updateStudentSelectionRequestStatus ( int requestId, boolean approved){
            // 实际实现数据库更新
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
        }*/


        private Teacher getTeacherInfoFromDatabase () {
            // 实际实现数据库查询
            Teacher teacher = null;
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM teachers WHERE id = ?");
                 ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    teacher.setUsername(rs.getString("name"));
                    teacher.setCode(rs.getString("code"));
                    teacher.setGender(rs.getString("gender"));
                    teacher.setPhone(rs.getString("phone"));
                    teacher.setDepartmentName(rs.getString("departmentName"));
                    teacher.setProfessionalTitle(rs.getString("professionalTitle"));
                    teacher.setDetail(rs.getString("detail"));
                    DbUtil.closeConnection(conn);
                }
            } catch (SQLException e) {
                System.err.println("Error fetching teacher info: " + e.getMessage());
            }
            return teacher;
        }

    }


