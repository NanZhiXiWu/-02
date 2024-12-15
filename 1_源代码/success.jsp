<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="3;url=<%= request.getAttribute("referer") != null ? request.getAttribute("referer") : "index.html" %>">
    <script type="text/javascript">
        // 如果没有 referer，跳转到 index.html
        setTimeout(function() {
            var referer = "<%= request.getAttribute("referer") != null ? request.getAttribute("referer") : "toptic.jsp" %>";
            window.location.href = referer;
        }, 3000); // 3秒后跳转
    </script>
</head>
<body>
<h1>发布主题成功！</h1>
<p>页面将在 3 秒钟后自动跳转回原始页面。如果没有跳转，请点击 <a href="<%= request.getAttribute("referer") != null ? request.getAttribute("referer") : "toptic.html" %>">这里</a>。</p>
</body>
</html>
