<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 11.06.2019
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/registration" method="post">
    User name:<br>
    <input type="text" name="userName" >
    <br>
    Mail:<br>
    <input type="text" name="mail" >
    <br><br>
    Password:<br>
    <input type="text" name="password" >
    <br><br>
    <input type="submit" value="Submit">

</form>
</body>
</html>
