<%@ taglib prefix="solr_ex" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<solr_ex:includes/>

<table>
    <caption>Users table</caption>
    <tr>
        <th>##</th>
        <th>First Name</th>
        <th>Last name</th>
        <th>Gender</th>
        <th>E-mail</th>
    </tr>
    <c:choose>
        <c:when test="${empty users}">
            <tr>
                <td colspan="6">
                    <p>
                        Data not found.
                    </p>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${users}" var="study" varStatus="count">
                <tr>
                    <td><a href="${url}">${count.index + 1}</a></td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.gender}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<div id="error-field" class="red"></div>
</body>
</html>