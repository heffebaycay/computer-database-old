<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Edit Company</h1>
    
    <form method="post" role="form" action="<c:url value="/company/edit?id=${company.id}" />">
    <input type="hidden" id="companyId" name="companyId" value="${company.id    }">
        <div class="form-group">
            <label for="name">New Company Name :</label>
            <input type="text" class="form-control" id="name" name="name" value="${company.name}">
        </div>
        <button class="btn btn-primary" type="submit">Edit</button> or
        <a href="<c:url value="/company/list" />" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />