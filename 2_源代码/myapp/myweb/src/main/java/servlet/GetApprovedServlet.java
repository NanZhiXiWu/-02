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

@WebServlet("/GetApprovedServlet")
public class GetApprovedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");
            String sql = "SELECT course_name, course_id, selection_method, teacher_name, teacher_contact, course_requirements FROM course WHERE course_status = '已批准'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

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
                jsonArray.add(jsonObject);
            }


            resultSet.close();
            preparedStatement.close();
            connection.close();

            out.println(gson.toJson(jsonArray));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("[]");
        }
    }
}