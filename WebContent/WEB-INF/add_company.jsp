<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Add Company</h1>

    <c:if test="${ eResult != null && eResult != 0  }">
        <div class="alert alert-danger">
            <strong>Oh snap!</strong> It seems you left some mistakes in our sweet form.
        </div>
    </c:if>

    <form method="post" role="form">
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPANY_NAME\")) != 0 }"> has-error</c:if>">
            <label for="name">Company name :</label>
            <input type="text" class="form-control" id="name" name="name" value="<c:if test="${companyNameValue != null}">${companyNameValue}</c:if>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPANY_NAME\") ) != 0 }">
                        Please enter a valid name for the company
                    </c:when>
                    <c:otherwise>
                        Required
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <button class="btn btn-primary" type="submit">Add</button> or
        <a href="<c:url value="/company/list" />" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />