<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restarants</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<section>
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <hr>

    <jsp:useBean id="meal" type="com.git.selection.model.Restaurant" scope="request"/>
    <h2><spring:message code="${meal.id==null?'restform.create':'restform.update'}"/></h2>
    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
            <dt><spring:message code="rest.description"/></dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="rest.address"/></dt>
            <dd><input type="text" value="${meal.address}" name="address" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="rest.phone"/></dt>
            <dd><input type="number" value="${meal.phone}" name="phone" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
        <td><a href="meals/update?id=${meal.id}">Update</a></td>
        <td><a href="meals/delete?id=${meal.id}">Delete</a></td>
    </form>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
