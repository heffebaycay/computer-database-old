<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Edit Computer</h1>

    <c:if test="${ eResult != null }">
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
    <c:if test="${ bAddSuccess != null && bAddSuccess == true}">
        <div class="alert alert-success">
            <strong>Success!</strong> Computer was added successfully. Feel free to take a moment to check for any typo!
        </div>
    </c:if>

    <form method="post" role="form" action="<c:url value="/computer/edit?id=${computer.id}"/>">
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_NAME\")) != 0}"> has-error</c:if>">
            <label for="name">Computer name:</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="<c:choose><c:when test="${computerNameValue != null}">${computerNameValue}</c:when><c:otherwise><c:if test="${computer.name != null}">${computer.name}</c:if></c:otherwise></c:choose>">
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
        <div class="form-group<c:if test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_INTRODUCED_DATE\")) != 0}"> has-error</c:if>">
            <label for="dateIntroduced">Date introduced:</label>
            <input type="text" class="form-control" id="dateIntroduced" name="dateIntroduced"
                   value="<c:choose><c:when test="${dateIntroducedValue != null}">${dateIntroducedValue}</c:when><c:otherwise><c:if test="${ computer.introduced != null}">${epf:formatDate(computer.introduced, "y-MM-dd")}</c:if></c:otherwise></c:choose>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ eResult != null && epf:bwAnd(eResult, epf:eResult(\"INVALID_COMPUTER_INTRODUCED_DATE\")) != 0 }">
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
            <input type="text" class="form-control" id="dateDiscontinued" name="dateDiscontinued"
                   value="<c:choose><c:when test="${ dateDiscontinuedValue != null }">${dateDiscontinuedValue}</c:when><c:otherwise><c:if test="${ computer.discontinued != null}">${epf:formatDate(computer.discontinued, "y-MM-dd")}</c:if></c:otherwise></c:choose>">
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
                    <option value="${company.id}"
                            <c:choose>
                                <c:when test="${ companyIdValue != null }">
                                    <c:if test="${company.id == companyIdValue}">
                                        selected="selected"
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${ computer.company != null && company.id == computer.company.id }">
                                        selected="selected"
                                    </c:if>
                                </c:otherwise>
                            </c:choose>> ${company.name}</option>
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
        <input type="hidden" name="computerId" value="${computer.id}" />
        <button class="btn btn-primary" type="submit">Save changes</button> or
        <a href="<c:url value="/computer/list"/>" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />