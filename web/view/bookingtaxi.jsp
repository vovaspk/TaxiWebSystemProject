<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vovas
  Date: 18.06.2019
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>
<body>
Select where to pick up you
<select name="home">

    <c:forEach var="street" items="${streets}">
        <option><c:out value="${street.name}"/></option>
    </c:forEach>
    <%--    <option value="Vokzal">Volvo</option>--%>
    <%--    <option value="Yunosti">Saab</option>--%>
    <%--    <option value="Keletska">Mercedes</option>--%>
    <%--    <option value="Kosmonavtiv">Audi</option>--%>
</select>

Select where do you want to go
<select name="dest">

    <c:forEach var="street" items="${streets}">
        <option><c:out value="${street.name}"/></option>
    </c:forEach>
</select>

Select type of car you want
<%--<select name="cars">--%>
<%--    <c:forEach var="car" items="${carList}">--%>
<%--        <option><c:out value="${car.class}"/></option>--%>
<%--    </c:forEach>--%>
<%--</select>--%>  <%-- MAYBE MAKE TABLE OF AVAILABLE CARS WITH ALL THEIR ATTRIBUTES--%>
</body>
</html>
