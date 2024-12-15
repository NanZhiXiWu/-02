package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet("/ApproveTopicServlet")
public class ApproveTopicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // 读取请求体中的JSON数据，设置为UTF-8编码读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String json = reader.lines().collect(Collectors.joining());

            // 使用Gson解析JSON数据
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            String courseId = jsonObject.get("courseId").getAsString();

            try {
                // 连接数据库（假设使用MySQL，根据实际情况修改）
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tea_course_system", "root", "123456");

                // 更新指定课题的状态为"已批准"
                String sql = "UPDATE course SET course_status = '已批准' WHERE course_id =?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, courseId);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    out.println("{\"success\": true}");
                } else {
                    out.println("{\"success\": false, \"error\": \"更新课题状态失败\"}");
                }

                // 关闭资源
                preparedStatement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                out.println("{\"success\": false, \"error\": \"数据库操作错误\"}");
            }
        }
    }
}