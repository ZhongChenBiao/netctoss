<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/5/28
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll">查询所有</a><br>

    <h1>管理员登录</h1>
    <form action="administor/login" method="post">
        姓名：<input name="adminNumber"><br>
        金额：<input name="password"><br>
        <button type="submit">提交</button>
    </form>
    <br>
    <h1>更新用户</h1>
    <form action="account/update" method="get">
        姓名：<input name="name"><br>
        金额：<input name="money"><br>
        <button type="submit">提交</button>
    </form>
    <br>
    <h1><a href="account/findByPage?currentPage=2"> 分页请求</a></h1><br>
    <h1><a href="account/findByPageHelper?currentPage=1"> 使用分页助手请求</a></h1><br>


</body>
</html>
