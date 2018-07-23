<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body>
<script type="text/javascript">
    window.contextPath = '${pageContext.request.contextPath}';
</script>
<div>
    <%@ include file="jspf/navbar.jspf" %>
</div>

<div class="container">
    <div class="form-horizontal">
        <div class="form-group">
            <span class="pull-left" style="font-weight: bold">Fields</span>
            <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addField">
                <span><i class="glyphicon glyphicon-plus"></i>Add field</span>
            </button>
        </div>
        <hr/>
        <div class="form-group">
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Label</th>
                    <th scope="col">Type</th>
                    <th scope="col">Required</th>
                    <th scope="col">Is Active</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fieldList}" var="field">
                    <tr style="color:black; font-weight: bold">
                        <td>${field.label}</td>

                        <td>${field.type}</td>

                        <td>${field.required}</td>

                        <td>${field.active}</td>

                        <td>
                            <div class="row">
                                <div style="float: left">
                                    <button class="btn btn-default updateBtn"
                                            data-toggle="modal"
                                            data-target="#editField"
                                            value="${field.id}">
                                        <span class="glyphicon glyphicon-edit"></span></button>
                                    <input type="hidden" name="fieldId" value="${field.id}">
                                </div>
                                <div>
                                    <form:form method="post" action="${pageContext.request.contextPath}/fields/delete">
                                        <button class="btn btn-default" type="submit" style="margin-left: 15px">
                                            <span class="glyphicon glyphicon-remove"></span></button>
                                        <input type="hidden" name="fieldId" value="${field.id}">
                                    </form:form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<%--modal for add fields--%>
<div class="modal fade" tabindex="-1" role="dialog" id="addField">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Modal title</h4>
            </div>

            <%--form--%>
            <div class="modal-body">
                <form:form class="form-horizontal"
                           id="newFieldForm"
                           method="post"
                           role="form"
                           action="${pageContext.request.contextPath}/fields/save"
                           modelAttribute="field">
                    <div class="form-group">
                        <label for="label" class="col-sm-2 control-label">Label</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="label" name="label">
                        </div>
                    </div>
                    <%--select--%>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">Type</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="type" name="type">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.htmlName}">${type.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--text--%>
                    <div class="form-group">
                        <label for="options" class="col-sm-2 control-label">Options</label>
                        <div class="col-sm-10">
                            <textarea id="options" class="form-control" name="optionsString"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox col-sm-5">
                                <label>
                                    <input type="checkbox" name="required"> Required
                                </label>
                            </div>
                            <div class="checkbox col-sm-5">
                                <label>
                                    <input type="checkbox" name="active"> Is Active
                                </label>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>


            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="submit" form="newFieldForm" class="btn btn-primary">Save</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" tabindex="-1" role="dialog" id="editField">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Modal title</h4>
            </div>

            <%--form--%>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label for="newLabel" class="col-sm-2 control-label">Label</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="newLabel" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox col-sm-5">
                                <label>
                                    <input type="checkbox" id="required"> Required
                                </label>
                            </div>
                            <div class="checkbox col-sm-5">
                                <label>
                                    <input type="checkbox" id="active"> Is Active
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="saveBtn">Save</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fields.js"></script>
</body>

</html>
