<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Add Company</h1>

    <form method="post" role="form">
        <div class="form-group">
            <label for="name">Company name :</label>
            <input type="text" class="form-control" id="name" name="name">
            <span class="help-block">Required</span>
        </div>
        <button class="btn btn-primary" type="submit">Add</button> or
        <a href="<c:url value="/company/list" />" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />