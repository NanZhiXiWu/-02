<%--
  Created by IntelliJ IDEA.
  User: Xu
  Date: 2024/11/11
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
  教师修改个人信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改教师个人信息</title>
    <style>body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    form {
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        max-width: 600px;
        margin: 0 auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    td {
        padding: 10px;
        vertical-align: top;
        border: 1px solid #ddd;
    }

    input[type="text"],
    input[type="password"],
    select,
    textarea {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    textarea {
        resize: vertical; /* Allow vertical resizing only */
    }

    button {
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        padding: 10px 15px;
        cursor: pointer;
        font-size: 16px;
    }

    button:hover {
        background-color: #218838; /* Darker shade on hover */
    }
    .back-button {
        background-color: #007bff;
        margin: 10px 0;
    }

    .back-button:hover {
        background-color: #0056b3;
    }



    </style>>
</head>
<body>
<h1>修改教师个人信息</h1>
<form action="UpdateTeacherInfoServlet" method="POST">
    <table>
        <tr>
            <td>教师号：</td>
            <td><input type="text" name="teacherId" value="${teacher.id}" readonly></td>
            <td>姓名：</td>
            <td><input type="text" name="teacherName" value="${teacher.name}" readonly></td>
        </tr>

        <tr>
            <td>性别：</td>
            <td>
                <select name="teacherGender">
                    <option value="男" ${teacher.gender == '男' ? 'selected' : ''}>男</option>
                    <option value="女" ${teacher.gender == '女' ? 'selected' : ''}>女</option>
                </select>
            <td>系部：</td>
            <td><input type="text" name="department" value="${teacher.department}" readonly></td>
            </td>
        </tr>

        <tr>
            <td>职称：</td>
            <td><input type="text" name="teacherTitle" value="${teacher.title}" readonly></td>
            <td> </td>>
            <td> </td>>

        </tr>
        <tr>
            <td>修改密码：</td>
            <td><input type="password" name="password" required></td>
            <td> </td>>
            <td> </td>>
        </tr>

        <tr>
            <td>研究方向专业理论技术特长：</td>
            <td><textarea name="technicalExpertise" rows="4" required>${teacher.teacherResearch}</textarea></td>
            <td> </td>>
            <td> </td>>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">提交修改</button>
            </td>
            <td colspan="2"> </td>
        </tr>
    </table>
</form>
<!-- 添加返回按钮 -->
<form action="/teacherHome" method="GET">
    <button type="submit" class="back-button">返回教师界面</button>

</form>
</body>
</html>
