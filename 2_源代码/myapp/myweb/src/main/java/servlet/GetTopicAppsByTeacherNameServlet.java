package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

import static java.lang.System.out;

@WebServlet("/GetTopicAppsByTeacherNameServlet")
public class GetTopicAppsByTeacherNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 获取教师姓名参数
            String teacherName = request.getParameter("teacherName");

            // 连接数据库（假设使用MySQL，根据实际情况修改）
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");

            // 查询course表中指定教师姓名的题目申请信息
            String sql = "SELECT course_name, course_id, selection_method, teacher_name, teacher_contact, course_requirements, course_status FROM course WHERE teacher_name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacherName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 使用Gson将查询结果转换为JSON格式
            Gson gson = new Gson();
            JsonArray jsonArray = new JsonArray();
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("course_name", resultSet.getString("course_name"));
                jsonObject.addProperty("course_id", resultSet.getString("course_id"));
                jsonObject.addProperty("selection_method", resultSet.getString("selection_method"));
                jsonObject.addProperty("teacher_name", resultSet.getString("teacher_name"));
                jsonObject.addProperty("teacher_contact", resultSet.getString("teacher_contact"));
                jsonObject.addProperty("course_requirements", resultSet.getString("course_requirements"));
                jsonObject.addProperty("course_status", resultSet.getString("course_status"));
                jsonArray.add(jsonObject);
            }

            // 关闭资源
            resultSet.close();
            preparedStatement.close();
            connection.close();

            out.println(gson.toJson(jsonArray));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // 如果出现错误，返回空数组
            out.println("[]");
        }
    }
}