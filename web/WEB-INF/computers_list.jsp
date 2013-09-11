<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>456 Computers found</h1>

    <div id="actions">
        <form method="get" action="" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="searchbox">Search</label>
                <input type="search" class="form-control" id="searchbox" name="search" value="" placeholder="Search name">
            </div>
            <button type="submit" class="btn btn-primary">Filter by name</button>
        </form>
        <button class="btn btn-success" id="add" href="">Add Company</button>
    </div>

    <div style="margin-top: 20px;">
        <table class="table table-bordered table-striped computers">
            <thead>
            <tr>
                <th>Computer name</th>
                <th>Date introduced</th>
                <th>Date discontinued</th>
                <th>Company</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.computers}" var="computer">
                <tr>
                    <td>${computer.name}</td>
                    <td>${computer.introduced}</td>
                    <td>${computer.discontinued}</td>
                    <c:choose>
                        <c:when test="${computer.company != null}">
                            <td>${computer.company.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>&nbsp;</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />