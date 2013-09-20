<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Add Computer</h1>

    <c:if test="${ eResult != null && eResult != 0  }">
        <div class="alert alert-danger">
            <strong>Oh snap!</strong> It seems you left some mistakes in our sweet form.
        </div>
    </c:if>

    <form method="post" role="form">
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_NAME\")) != 0 }"> has-error</c:if>">
            <label for="name">Computer name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<c:if test="${ computerNameValue != null }">${ computerNameValue }</c:if>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_NAME\")) != 0 }">
                        Please enter a valid name for the computer
                    </c:when>
                    <c:otherwise>
                        Required
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_INTRODUCED_DATE\")) != 0 }"> has-error</c:if>">
            <label for="dateIntroduced">Date introduced:</label>
            <input type="text" class="form-control" id="dateIntroduced" name="dateIntroduced" value="<c:if test="${dateIntroducedValue != null}">${dateIntroducedValue}</c:if>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_INTRODUCED_DATE\")) != 0}">
                        Please enter a valid date in the following format: YYYY-MM-DD
                    </c:when>
                    <c:otherwise>
                        YYYY-MM-DD
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_DISCONTINUED_DATE\")) != 0 }"> has-error</c:if>">
            <label for="dateDiscontinued">Date discontinued:</label>
            <input type="text" class="form-control" id="dateDiscontinued" name="dateDiscontinued" value="<c:if test="${dateDiscontinuedValue != null}">${dateDiscontinuedValue}</c:if>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_DISCONTINUED_DATE\")) != 0 }">
                        Please enter a valid date in the following format: YYYY-MM-DD
                    </c:when>
                    <c:otherwise>
                        YYYY-MM-DD
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPANY\")) != 0 }"> has-error</c:if>">
            <label for="company">Company name:</label>
            <select name="company" id="company">
                <c:forEach items="${requestScope.companies}" var="company">
                    <option value="${company.id}" <c:if test="${ companyIdValue != null && company.id == companyIdValue }">selected="selected"</c:if>> ${company.name}</option>
                </c:forEach>
            </select>
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPANY\")) != 0 }">
                        Please select a valid company from the list above.
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <button class="btn btn-primary" type="submit">Add</button> or
        <a href="<c:url value="/computer/list"/>" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />