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

import Users.Student;
import com.google.gson.Gson;

@WebServlet("/StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 获取从前端传递过来的用户名和密码参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username);
            System.out.println(password);
            try {
                // 连接数据库
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");
                // 根据用户名和密码查询学生信息
                String sql = "SELECT * FROM students WHERE username =? AND password =?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    Student student = new Student();
                    student.setUsername(rs.getString("username"));
                    student.setStudent_number(rs.getString("student_number"));
                    student.setClasses(rs.getString("class"));
                    student.setMajor(rs.getString("major"));
                    student.setCollege(rs.getString("college"));
                    student.setDepartment(rs.getString("department"));
                    System.out.println(rs.getString("department"));
                    Gson gson = new Gson();
                    String json = gson.toJson(student);
                    out.print(json);
                }
                else {
                    String errorJson = new Gson().toJson("{\"error\": \"未找到匹配的学生记录\"}");
                    out.print(errorJson);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}