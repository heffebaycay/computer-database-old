<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container" id="main">
    <h1>Add Computer</h1>

    <form method="post" role="form">
        <div class="form-group">
            <label for="name">Computer name:</label>
            <input type="text" class="form-control" id="name" name="name">
            <span class="help-block">Required</span>
        </div>
        <div class="form-group">
            <label for="dateIntroduced">Date introduced:</label>
            <input type="text" class="form-control" id="dateIntroduced" name="dateIntroduced">
            <span class="help-block">YYYY-MM-DD</span>
        </div>
        <div class="form-group">
            <label for="dateDiscontinued">Date discontinued:</label>
            <input type="text" class="form-control" id="dateDiscontinued" name="dateDiscontinued">
            <span class="help-block">YYYY-MM-DD</span>
        </div>
        <div class="form-group">
            <label for="company">Company name:</label>
            <select name="company" id="company">
                <option value="0">--</option>
                <option value="1">Apple</option>
                <option value="2">Dell</option>
                <option value="3">Lenovo</option>
            </select>
        </div>
        <button class="btn btn-primary" type="submit">Add</button> or
        <a href="/computer/list" class="btn btn-default">Cancel</a>
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />