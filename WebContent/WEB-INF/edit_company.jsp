<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Edit Company</h1>

    <c:if test="${eResult != null}">
        <c:choose>
            <c:when test="${ eResult != 0 }">
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
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPANY_NAME\")) != 0  }"> has-error</c:if>">
            <label for="name">New Company Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<c:choose><c:when test="${companyNameValue != null }">${companyNameValue}</c:when><c:otherwise>${company.name}</c:otherwise></c:choose>">
        </div>
        <button class="btn btn-primary" type="submit">Save changes</button> or
        <a href="<c:url value="/company/list" />" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />