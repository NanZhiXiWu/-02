<%--
  Created by IntelliJ IDEA.
  User: Xu
  Date: 2024/11/11
  Time: 下午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教师界面</title>
    <style>
        /* 设置页面内容居中 */
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .container {
            text-align: center;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        button {
            width: 200px;
            height: 40px;
            margin: 10px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>欢迎来到教师界面</h1>
    <p>您好，${sessionScope.username}！</p>
    <form action="person_information.jsp" method="get">
        <button type="submit">修改个人信息</button>
    </form>
    <form action="toptic.jsp" method="get">
        <button type="submit">手工建课题</button>
    </form>
    <form action="importTopic.jsp" method="get">
        <button type="submit">导入课题</button>
    </form>
</div>

</body>
</html>
