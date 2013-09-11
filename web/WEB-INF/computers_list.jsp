<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>456 Computers found</h1>

    <div id="actions">
        <form method="get" action="" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="searchbox">Search</label>
                <input type="search" class="form-control" id="searchbox" name="search" value="" placeholder="Search name">
            </div>
            <button type="submit" class="btn btn-primary">Filter by name</button>
        </form>
        <button class="btn btn-success" id="add" href="">Add Company</button>
    </div>

    <div style="margin-top: 20px;">
        <table class="table table-bordered table-striped computers">
            <thead>
            <tr>
                <th>Computer name</th>
                <th>Date introduced</th>
                <th>Date discontinued</th>
                <th>Company</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>ThinkPad T420</td>
                <td>2011-01-01</td>
                <td>2013-03-04</td>
                <td>Lenovo</td>
            </tr>
            <tr>
                <td>Precision 3500</td>
                <td>2010-05-07</td>
                <td>2012-06-01</td>
                <td>Dell</td>
            </tr>
            <tr>
                <td>Mackbook Air</td>
                <td>2005-05-09</td>
                <td>2008-06-06</td>
                <td>Apple</td>
            </tr>
            </tbody>
        </table>
    </div>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />