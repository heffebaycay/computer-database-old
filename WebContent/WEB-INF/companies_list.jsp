<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>${totalCount} Companies found</h1>

    <div id="actions">
        <form method="get" action="" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="searchbox">Search</label>
                <input type="search" class="form-control" id="searchbox" name="search" value="" placeholder="Search name">
            </div>
            <button type="submit" class="btn btn-primary">Filter by name</button>
        </form>
        <a class="btn btn-success" id="add" href="<c:url value="/company/add"/>"/>Add Company</a>
    </div>

    <div style="margin-top: 20px;">
        <table class="table table-bordered table-striped" id="table-company">
            <thead>
            <tr>
                <th># <a href="<c:url value="/company/list?${epf:generateGetParams(currentPage, searchQuery, \"id\", \"asc\")}"/>"><span class="glyphicon glyphicon-arrow-up"></span></a> / <a href="<c:url value="/company/list?${epf:generateGetParams(currentPage, searchQuery, \"id\", \"desc\")}"/>"><span class="glyphicon glyphicon-arrow-down"></span></a></th>
                <th>Company name <a href="<c:url value="/company/list?${epf:generateGetParams(currentPage, searchQuery, \"name\", \"asc\")}"/>"><span class="glyphicon glyphicon-arrow-up"></span></a> / <a href="<c:url value="/company/list?${epf:generateGetParams(currentPage, searchQuery, \"name\", \"desc\")}"/>"><span class="glyphicon glyphicon-arrow-down"></span></a></th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.companies}" var="company">
                <tr>
                    <td>${company.id}</td>
                    <td>${company.name}</td>
                    <td><a href="<c:url value="/company/edit?id=${company.id}"/>"><span class="glyphicon glyphicon-edit"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${ currentPage != null }">
        <div class="row">
            <div style="margin-left: 30px;">
                <ul class="pagination">
                    <c:if test="${ currentPage gt 1 }">
                        <li><a href="<c:url value="/company/list?${epf:generateGetParams(currentPage - 1, searchQuery, sortCriterion, sortOrder)}" />">&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <li class="active"><a>${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/company/list?${epf:generateGetParams(i, searchQuery, sortCriterion, sortOrder)}"/>">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt totalPage}">
                        <li><a href="<c:url value="/company/list?${epf:generateGetParams(currentPage +1, searchQuery, sortCriterion, sortOrder)}" />">&raquo;</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:if>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />