<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>${fn:length(companies)} Companies found</h1>

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

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />