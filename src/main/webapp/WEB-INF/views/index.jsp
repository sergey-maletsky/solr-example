<%@ taglib prefix="solr_ex" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<solr_ex:includes/>

<table>
    <caption>Users table</caption>
    <tbody id="users_table">
    <tr class="head_tr">
        <th>##</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <c:choose>
        <c:when test="${empty users}">
            <tr class="users_tr">
                <td colspan="6">
                    <p>
                        Data not found.
                    </p>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${users}" var="user" varStatus="count">
                <tr class="users_tr">
                    <td><a>${currentPage * currentPageSize + count.index + 1}</a></td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<div class="users-pages">
    <div class="pages">
        Pages:
            <c:forEach var="i" begin="1" end="${pagesTotal}">
                <spring:url value="/?page=${i - 1}" var="pageUrl" />
                <c:choose>
                    <c:when test="${i - 1 == currentPage}">
                        <div class="users-pages_list-item users-pages_list-item_current"><a href="${pageUrl}">${i}</a></div>
                    </c:when>
                    <c:otherwise>
                        <div class="users-pages_list-item"><a href="${pageUrl}">${i}</a></div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

    </div>

    <p class="count">
        Show from: ${currentPage * currentPageSize + 1} to
        <c:choose>
            <c:when test="${(currentPage + 1) * currentPageSize <= recordsTotal}">
                ${(currentPage + 1) * currentPageSize}
            </c:when>
            <c:otherwise>
                ${recordsTotal}
            </c:otherwise>
        </c:choose>
        Total: ${recordsTotal}
    </p>
</div>

<form id="search" class="entity-area" name="search_by_name" action="" method="get">
    <div>Name</div>
    <input id="s_name" name="name" type="text">
    <p>
    <div>Age</div>
    <input id="s_age" name="age" type="text">
    </p>
    <button id="search-submit">Search users</button>
    <button id="search-clean">Clean</button>
</form>

<form id="user" class="entity-area" name="add_user" action="" method="post">
    <div>Name</div>
    <input id="name" name="name" type="text">
    <p>
    <div>Age</div>
    <input id="age" name="age" type="text">
    </p>
    <button id="user-submit">Add new user</button>
</form>

<form id="generate_users" class="entity-area" name="gen_users" action="" method="post">
    <div>Amount of users</div>
    <input id="amount" name="amount" type="text">
    <button id="generate">Generate users</button>
</form>

<div id="error-field" class="red"></div>
</body>
</html>