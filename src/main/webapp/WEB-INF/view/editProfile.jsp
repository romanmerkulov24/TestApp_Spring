<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <title>Admin</title>
</head>
<body>

<div>
    <%@ include file="jspf/navbar.jspf" %>
</div>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <form:form class="form-horizontal"
                       method="post"
                       role="form"
                       action="${pageContext.request.contextPath}/customer/edit">
                <h3 style="padding-bottom: 10px">Edit Profile</h3>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        First Name
                        <input type="text" class="form-control" name="firstName" id="firstName"
                               value="${sessionScope.user.firstName}" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        Last Name
                        <input type="text" class="form-control" name="lastName" id="lastName"
                               value="${sessionScope.user.lastName}" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        Email
                        <input type="text" class="form-control" name="email" id="email" value="${sessionScope.user.email}"
                               required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        Phone Number
                        <input type="text" class="form-control" name="phone" id="phone" value="${sessionScope.user.phone}"
                               required size="13"/>
                    </div>
                </div>

                <input type="hidden" name="id" value="${sessionScope.user.id}">
                <input type="hidden" name="password" value="${sessionScope.user.password}">

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">UPDATE</button>
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