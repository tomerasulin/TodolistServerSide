<%--
  Created by IntelliJ IDEA.
  User: tomer
  Date: 30/05/2019
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New User</title>
</head>
<body>
<form method="post" action="/todolistcontroller/">
    Enter Your ID: <input type="text" name="id"><br/>
    Enter Your First Name: <input type="text" name="firstName"><br/>
    Enter Your Last Name: <input type="text" name="lastName"><br/>
    Enter Your Password: <input type="text" name="password"><br/>
    <input type="submit"><br/>
</form>
</body>
</html>
