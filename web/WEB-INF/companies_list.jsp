<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>2 Companies found</h1>

    <div id="actions">
        <form method="get" action="" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="searchbox">Search</label>
                <input type="search" class="form-control" id="searchbox" name="search" value="" placeholder="Search name">
            </div>
            <button type="submit" class="btn btn-primary">Filter by name</button>
        </form>
        <a class="btn btn-success" id="add" href="/company/add">Add Company</a>
    </div>

    <div style="margin-top: 20px;">
        <table class="table table-bordered table-striped" id="table-company">
            <thead>
            <tr>
                <th>#</th>
                <th>Company name</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Apple</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Dell</td>
            </tr>
            </tbody>
        </table>
    </div>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />