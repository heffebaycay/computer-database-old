<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Edit Company</h1>

    <c:if test="${ bEverythingOkay != null}">
        <c:choose>
            <c:when test="${bEverythingOkay == false}">
                <div class="alert alert-danger">
                    <strong>Oh snap!</strong> It seems you left some mistakes in our sweet form.
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success">
                    <strong>Success!</strong> Your modifications were saved successfully
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${bAddSuccess != null && bAddSuccess == true}">
        <div class="alert alert-success">
            <strong>Success!</strong> Company added successfully. You can now fix any typo you could have made!
        </div>
    </c:if>
    
    <form method="post" role="form" action="<c:url value="/company/edit?id=${company.id}" />">
    <input type="hidden" id="companyId" name="companyId" value="${company.id}">
        <div class="form-group">
            <label for="name">New Company Name :</label>
            <input type="text" class="form-control" id="name" name="name" value="${company.name}">
        </div>
        <button class="btn btn-primary" type="submit">Save changes</button> or
        <a href="<c:url value="/company/list" />" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />