<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <th>#</th>
                <th>Company name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.companies}" var="company">
                <tr>
                    <td>${company.id}</td>
                    <td>${company.name}</td>
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
                        <c:choose>
                            <c:when test="${ searchQuery != null }" >
                                <li><a href="<c:url value="/company/list?search=${searchQuery}&p=${currentPage - 1}"/>">&laquo;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/company/list?p=${currentPage - 1}"/>">&laquo;</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="${searchQuery != null}">
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="<c:url value="/company/list?search=${searchQuery}&p=${i}"/>">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="<c:url value="/company/list?p=${i}"/>">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${currentPage lt totalPage}">
                        <c:choose>
                            <c:when test="${ searchQuery != null }" >
                                <li><a href="<c:url value="/company/list?search=${searchQuery}&p=${currentPage + 1}"/>">&raquo;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/company/list?p=${currentPage + 1}"/>">&raquo;</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:if>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />