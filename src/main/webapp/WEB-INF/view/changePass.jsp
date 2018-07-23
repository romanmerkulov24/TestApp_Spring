<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change Password</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div>
    <%@ include file="jspf/navbar.jspf"%>
</div>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <form:form class="form-horizontal"
                       method="post"
                       role="form"
                       action="${pageContext.request.contextPath}/customer/change">
                <h3 style="padding-bottom: 10px">Change Password</h3>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                         Curent password
                        <input type="password" class="form-control" name="currentPass" id="currentPass" required/>
                    </div>
                    <span class="error-msg"><c:out value="${errorCheckPass}"></c:out></span>
                </div>
                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        New Password
                        <input type="password" class="form-control" name="newPass" id="newPass" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        Confirm New Password
                        <input type="password" class="form-control" name="confirmPass" id="confirmPass" required/>
                    </div>
                    <span class="error-msg"><c:out value="${errorConfirm}"></c:out></span>
                </div>
                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">CHANGE</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
