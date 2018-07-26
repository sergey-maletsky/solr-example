<%@ taglib prefix="solr_ex" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<solr_ex:includes/>

<table>
    <caption>Users table</caption>
    <tr>
        <th>##</th>
        <th>Name</th>
        <th>Age</th>
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
            <c:forEach items="${users}" var="user" varStatus="count">
                <tr>
                    <td><a>${count.index + 1}</a></td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<form id="search" class="entity-area" name="search_by_name" action="" method="get">
    <div>Name</div>
    <input id="s_name" name="name" type="text">
    </p>
    <button id="search-submit">Search users</button>
</form>

<div id="error-field" class="red"></div>
</body>
</html>