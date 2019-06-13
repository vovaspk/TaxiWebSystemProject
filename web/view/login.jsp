<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 10.06.2019
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    Username:<br>
    <input type="text" name="userName" >
    <br>
    <br>
    Password:<br>
    <input type="text" name="password" >
    <br><br>
    <input type="submit" value="Login">
</form>
<form action="${pageContext.request.contextPath}/view/registration.jsp" method="post">
    <input type="submit" value="registration" >
</form>



</body>
</html>
