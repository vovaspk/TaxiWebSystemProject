<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 18.06.2019
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="locales" />
<html>
<head>
    <title>Cars</title>
    <a href="${pageContext.request.contextPath}/home">Back</a>
    Cars that we have:

    <table border = "1" width = "100%">
        <tr>
            <th>car class</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td><c:out value="${car.carClass}"/></td/>

            </tr>
        </c:forEach>
    </table>

</head>
<body>

</body>
</html>
