<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Add Computer</h1>

    <form method="post" role="form">
        <div class="form-group<c:if test="${ bValidComputerName != null && bValidComputerName == false }"> has-error</c:if>">
            <label for="name">Computer name:</label>
            <input type="text" class="form-control" id="name" name="name">
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
            <input type="text" class="form-control" id="dateIntroduced" name="dateIntroduced">
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
            <input type="text" class="form-control" id="dateDiscontinued" name="dateDiscontinued">
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
	            	<option value="${company.id}" > ${company.name}</option>
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
        <button class="btn btn-primary" type="submit">Add</button> or
        <a href="<c:url value="/computer/list"/>" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />