<%--
  Created by IntelliJ IDEA.
  User: Xu
  Date: 2024/11/11
  Time: 下午11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系主任选题审批</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .action-btn {
            padding: 8px 16px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }
        .approve-btn {
            background-color: #9daf4c;
            color: white;
        }
        .reject-btn {
            background-color: #f4e436;
            color: white;
        }
        .status-approved {
            color: #9daf4c;
        }
        .status-rejected {
            color: #f4e436;
        }
        .status-pending {
            color: #ff9800;
        }
        textarea {
            width: 100%;
            height: 60px;
            padding: 8px;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<h1>系主任选题审批</h1>
<p>请选择是否同意或回退待审批的选题。</p>

<table>
    <thead>
    <tr>
        <th>出题人</th>
        <th>题目名称</th>
        <th>选题要求</th>
        <th>研究方向</th>
        <th>状态</th>
        <th>审批操作</th>
        <th>审批意见</th>
    </tr>
    </thead>
    <tbody>
    <!-- 使用 JSTL 显示从数据库获取的选题列表 -->
    <c:forEach var="topic" items="${topics}">
        <tr>
            <td>${topic.creator}</td>
            <td>${topic.title}</td>
            <td>${topic.requirements}</td>
            <td>${topic.researchDirection}</td>
            <td>
                        <span class="${topic.status == '未审批' ? 'status-pending' : (topic.status == '已同意' ? 'status-approved' : 'status-rejected')}">
                                ${topic.status}
                        </span>
            </td>
            <td>
                <!-- 同意按钮 -->
                <form action="approveTopic" method="post" style="display:inline-block;">
                    <input type="hidden" name="topicId" value="${topic.id}">
                    <button type="submit" class="action-btn approve-btn" name="action" value="approve">同意</button>
                </form>

                <!-- 回退按钮 -->
                <form action="approveTopic" method="post" style="display:inline-block;">
                    <input type="hidden" name="topicId" value="${topic.id}">
                    <button type="submit" class="action-btn reject-btn" name="action" value="reject">回退</button>
                </form>
            </td>
            <td>
                <!-- 输入审批意见 -->
                <textarea name="approvalComments" placeholder="输入审批意见..."></textarea>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
