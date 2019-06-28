<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 10.06.2019
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="<%=request.getLocale()%>"/>--%>

<fmt:setBundle basename="locales" />
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <fmt:message key="loginName"/><br>
    <input type="text" name="userName" >
    <br>
    <br>
    <fmt:message key="userPassword"/><br>
    <input type="password" name="password" >
    <br><br>
    <input type="submit" value="Login">
</form>
<form action="${pageContext.request.contextPath}/view/registration.jsp" method="post">
    <input type="submit" value="registration" >
</form>



</body>
</html>
