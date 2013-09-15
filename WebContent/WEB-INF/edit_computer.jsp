<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="epf" uri="/WEB-INF/epf-params.tld" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Edit Computer</h1>

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

    <form method="post" role="form" action="<c:url value="/computer/edit?id=${computer.id}"/>">
        <div class="form-group<c:if test="${ bValidComputerName != null && bValidComputerName == false }"> has-error</c:if>">
            <label for="name">Computer name:</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="<c:choose><c:when test="${ bValidComputerName != null && bValidComputerName == false }">${computerNameValue}</c:when><c:otherwise><c:if test="${computer.name != null}">${computer.name}</c:if></c:otherwise></c:choose>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ bValidComputerName != null && bValidComputerName == false }">
                        Please enter a valid name for the computer
                    </c:when>
                    <c:otherwise>
                        Required
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ bValidDateIntroduced != null && bValidDateIntroduced == false }"> has-error</c:if>">
            <label for="dateIntroduced">Date introduced:</label>
            <input type="text" class="form-control" id="dateIntroduced" name="dateIntroduced"
                   value="<c:choose><c:when test="${ bValidDateIntroduced != null && bValidDateIntroduced == false }">${dateIntroducedValue}</c:when><c:otherwise><c:if test="${ computer.introduced != null}">${epf:formatDate(computer.introduced, "y-MM-dd")}</c:if></c:otherwise></c:choose>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ bValidDateIntroduced != null && bValidDateIntroduced == false }">
                        Please enter a valid date in the following format: YYYY-MM-DD
                    </c:when>
                    <c:otherwise>
                        YYYY-MM-DD
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ bValidDateDiscontinued != null && bValidDateDiscontinued == false }"> has-error</c:if>">
            <label for="dateDiscontinued">Date discontinued:</label>
            <input type="text" class="form-control" id="dateDiscontinued" name="dateDiscontinued"
                   value="<c:choose><c:when test="${ bValidDateDiscontinued != null && bValidDateDiscontinued == false }">${dateDiscontinuedValue}</c:when><c:otherwise><c:if test="${ computer.discontinued != null}">${epf:formatDate(computer.discontinued, "y-MM-dd")}</c:if></c:otherwise></c:choose>">
            <span class="help-block">
                <c:choose>
                    <c:when test="${ bValidDateDiscontinued != null && bValidDateDiscontinued == false }">
                        Please enter a valid date in the following format: YYYY-MM-DD
                    </c:when>
                    <c:otherwise>
                        YYYY-MM-DD
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="form-group<c:if test="${ bValidCompany != null && bValidCompany == false }"> has-error</c:if>">
            <label for="company">Company name:</label>
            <select name="company" id="company">
                <c:forEach items="${requestScope.companies}" var="company">
                    <option value="${company.id}"
                            <c:choose>
                                <c:when test="${ bValidCompany != null && bValidCompany == false }">
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
                    <c:when test="${ bValidCompany != null && bValidCompany == false }">
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