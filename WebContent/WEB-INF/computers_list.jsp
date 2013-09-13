<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="include/header.jsp" />

<div class="container">
    <h1>${totalCount} Computers found</h1>

    <div id="actions">
        <form method="get" action="<c:url value="/computer/list"/>" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="searchbox">Search</label>
                <input type="search" class="form-control" id="searchbox" name="search" value="" placeholder="Search name">
            </div>
            <button type="submit" class="btn btn-primary">Filter by name</button>
        </form>
        <a class="btn btn-success" id="add" href="<c:url value="/computer/add"/>">Add Computer</a>
    </div>

    <div style="margin-top: 20px;">
        <table class="table table-bordered table-striped computers">
            <thead>
            <tr>
                <th>Computer name</th>
                <th>Date introduced</th>
                <th>Date discontinued</th>
                <th>Company</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.computers}" var="computer">
                <tr>
                    <td>${computer.name}</td>
                    <td>${computer.introduced}</td>
                    <td>${computer.discontinued}</td>
                    <c:choose>
                        <c:when test="${computer.company != null}">
                            <td>${computer.company.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>&nbsp;</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="<c:url value="/computer/edit?id=${computer.id}"/>"><span class="glyphicon glyphicon-edit"></span></a> / <a onclick="showDeleteModal(${computer.id}, '${computer.name}' , '<c:if test="${computer.company != null}">${computer.company.name}</c:if>')"><span class="glyphicon glyphicon-remove"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${ currentPage != null }">
        <div class="row">
            <div style="margin-left: 30px;">
                <ul class="pagination">
                    <c:if test="${ currentPage gt 1 }">
                        <c:choose>
                            <c:when test="${ searchQuery != null }" >
                                <li><a href="<c:url value="/computer/list?search=${searchQuery}&p=${currentPage - 1}"/>">&laquo;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/computer/list?p=${currentPage - 1}"/>">&laquo;</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="${searchQuery != null}">
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="<c:url value="/computer/list?search=${searchQuery}&p=${i}"/>">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="active"><a>${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="<c:url value="/computer/list?p=${i}"/>">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${currentPage lt totalPage}">
                        <c:choose>
                            <c:when test="${ searchQuery != null }" >
                                <li><a href="<c:url value="/computer/list?search=${searchQuery}&p=${currentPage + 1}"/>">&raquo;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/computer/list?p=${currentPage + 1}"/>">&raquo;</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:if>

    <script type="text/javascript">
        function showDeleteModal( computerId, computerName, companyName) {
            $('#removeComputerInput').val( computerId );
            $('#modalComputerName').text(computerName);
            $('#modalComputerCompany').text(companyName);
            $('#deleteModal').modal();
        }

        function deleteComputer() {
            $('#deleteModal').modal('hide');
            var computerId = $('#removeComputerInput').val();
            $.post(
                    "<c:url value="/computer/remove"/>",
                    {id: computerId},
                    function(data) {
                        location.reload();
                    }
            );
        }
    </script>

    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Are you sure you want to remove this computer?</h4>
                </div>
                <div class="modal-body">
                    Computer name: <span id="modalComputerName"></span><br/>
                    Company: <span id="modalComputerCompany"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onClick="deleteComputer();">Delete</button>
                </div>
            </div>
        </div>
    </div>
    <form method="post" role="form" id="removeComputerForm">
        <input type="hidden" name="computerId" id="removeComputerInput" value="-1">
    </form>

</div> <!-- /container -->

<jsp:include page="include/footer.jsp" />