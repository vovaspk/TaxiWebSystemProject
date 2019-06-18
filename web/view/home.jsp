<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 11.06.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
Hello ${param.userName}
<a href="${pageContext.request.contextPath}/booking">Book a taxi</a>
<a href="${pageContext.request.contextPath}/actions">Check my actions</a>
<a href="${pageContext.request.contextPath}/cars">types of cars</a>

</body>
</html>
