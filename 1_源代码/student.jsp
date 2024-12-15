<%--
  Created by IntelliJ IDEA.
  User: Xu
  Date: 2024/11/11
  Time: 下午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生界面</title>
    <style>
        /* 设置页面内容居中 */
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .container {
            text-align: center;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 60%;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 30px;
            color: #333;
        }
        button {
            width: 250px;
            height: 50px;
            margin: 15px;
            font-size: 18px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>欢迎来到学生选题系统</h1>

    <!-- 浏览教师发布的毕设题目 -->
    <form action="viewTeacherTopics.jsp" method="get">
        <button type="submit">浏览教师发布的毕设题目</button>
    </form>

    <!-- 提交选题申请 -->
    <form action="submitTopicApplication.jsp" method="get">
        <button type="submit">提交选题申请</button>
    </form>

    <!-- 查看和管理自己的选题状态 -->
    <form action="manageTopicStatus.jsp" method="get">
        <button type="submit">查看和管理选题状态</button>
    </form>
</div>

</body>
</html>
