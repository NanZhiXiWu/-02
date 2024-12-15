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

import Users.Teacher;
import com.google.gson.Gson;

@WebServlet("/TeacherInfoServlet")
public class TeacherInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 获取从前端传递过来的教师用户名和密码参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username);
            System.out.println(password);

            try {
                // 连接数据库
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");

                // 根据用户名和密码查询教师信息
                String sql = "SELECT * FROM teachers WHERE username =? AND password =?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacher_id(rs.getString("teacher_id"));
                    teacher.setTeacher_name(rs.getString("username"));
                    teacher.setTeacher_gender(rs.getString("teacher_gender"));
                    teacher.setTeacher_title(rs.getString("teacher_title"));
                    teacher.setTeacher_phone(rs.getString("teacher_phone"));

                    Gson gson = new Gson();
                    String json = gson.toJson(teacher);
                    out.print(json);
                } else {
                    String errorJson = new Gson().toJson("{\"error\": \"未找到匹配的教师记录\"}");
                    out.print(errorJson);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}