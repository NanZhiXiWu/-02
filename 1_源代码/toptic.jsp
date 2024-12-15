<%--
  Created by IntelliJ IDEA.
  User: Xu
  Date: 2024/11/10
  Time: 下午9:06
  To change this template use File | Settings | File Templates.
  教师出题
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教师发布选题</title>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 30px;
        }

        form {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 700px;
            margin: 0 auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 12px 15px;
            text-align: left;
            vertical-align: top;
            border-bottom: 1px solid #ddd;
        }

        th {
            padding: 12px 15px;
            text-align: left;
            background-color: #f9f9f9;
            font-weight: bold;
        }

        input[type="text"], select, textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }


        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            margin-top: 20px;
        }

        button:hover {
            background-color: #45a049;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

    </style>
</head>
<body>
<h1>教师发布选题</h1>
<form action="/untitled2/PublishTopicServlet" method="POST">
    <table>
        <!-- 课题名称 -->
        <tr>
            <td><label for="topicName">课题名：</label></td>
            <td><input type="text" name="topicName" id="topicName" required></td>
            <td><label for="topicId">课题号：</label></td>
            <td><input type="text" name="topicId" id="topicId" value="${nextTopicId}" readonly></td>
        </tr>

        <!-- 课题创始人 -->
        <tr>
            <td><label for="creator">课题创始人：</label></td>
            <td><input type="text" name="creator" id="creator" value="${sessionScope.username}" readonly></td>
            <!-- 选题类型 -->
            <td><label for="topicType">选题类型：</label></td>
            <td>
                <select name="topicType" id="topicType" readonly>
                    <option value="理论研究型">理论研究型</option>
                    <option value="应用研究型">应用研究型</option>
                    <option value="其他">其他</option>
                </select>
            </td>
        </tr>

        <!-- 课题状态 -->
        <tr>
            <td><label for="status">课题状态：</label></td>
            <td><input type="text" name="status" id="status" value="${sessionScope.status}" readonly></td>
            <!-- 系部 -->
            <td><label for="department">系部：</label></td>
            <td><input type="text" name="department" id="department" value="${sessionScope.department}" readonly></td>
        </tr>
        <!-- 选题来源 -->
        <tr>
            <td><label for="source">选题来源：</label></td>
            <td>
                <select name="source" id="source" readonly>
                    <option value="科研">科研</option>
                    <option value="社会生产实践">社会生产实践</option>
                    <option value="其他">其他</option>
                </select>
            </td>
            <td> </td>
            <td> </td>
        </tr>
        <!-- 课题专业理论及技术要求 -->
        <tr>
            <td><label for="requirements">课题专业理论及技术要求：</label></td>
            <td><textarea name="requirements" id="requirements" rows="4" required></textarea></td>
            <td></td>
            <td></td>
        </tr>
        <!-- 论文研究方向 -->
        <tr>
            <td><label for="researchDirection">论文研究方向：</label></td>
            <td><input type="text" name="researchDirection" id="researchDirection" required></td>
            <td></td>
            <td></td>
        </tr>
        <!-- 教师姓名 -->
        <tr>
            <td><label for="teacherName">教师姓名：</label></td>
            <td><input type="text" name="teacherName" id="teacherName" value="${sessionScope.username}" readonly></td>
            <!-- 教师性别 -->
            <td><label for="teacherGender">教师性别：</label></td>
            <td><input type="text" name="teacherGender" id="teacherGender" value="${sessionScope.gender}" readonly></td>
        </tr>
        <!-- 教师职称 -->
        <tr>
            <td><label for="teacherTitle">教师职称：</label></td>
            <td><input type="text" name="teacherTitle" id="teacherTitle" value="${sessionScope.title}" readonly></td>
            <td></td>
            <td></td>

        </tr>
        <!-- 系主任审批状态 -->
        <tr>
            <td><label for="approvalStatus">系主任审批状态：</label></td>
            <td><input type="text" name="approvalStatus" id="approvalStatus" value="${topic.approvalStatus}" readonly></td>
            <td></td>
            <td></td>

        </tr>
        <!-- 系主任审批意见 -->
        <tr>
            <td><label for="approvalComments">审批意见：</label></td>
            <td><textarea name="approvalComments" id="approvalComments" rows="4" readonly>${topic.approvalComments}</textarea></td>
            <td></td>
            <td></td>
        </tr>
        <!-- 提交按钮 -->
        <tr>
            <td colspan="2">
                <button type="submit">发布选题</button>
            </td>
            <!-- 返回按钮 -->
            <td colspan="2">
                <button type="button" onclick="window.location.assign('/untitled2/teacher.jsp');">返回</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
