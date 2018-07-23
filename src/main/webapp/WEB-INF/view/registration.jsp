<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <form:form class="form-horizontal"
                       method="post"
                       role="form"
                       action="${pageContext.request.contextPath}/registration/save"
                       modelAttribute="customer">
                <h3 align="center" style="padding-bottom: 10px">Sign Up</h3>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="text" class="form-control" name="firstName" id="firstName"
                               value="${customer.firstName}" placeholder="First Name"/>
                        <span class="help-block"><form:errors path="firstName" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="text" class="form-control" name="lastName" id="lastName"
                               value="${customer.lastName}" placeholder="Last Name"/>
                        <span class="help-block"><form:errors path="password" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="text" class="form-control" name="email" id="email"
                               value="${customer.email}" placeholder="Email"/>
                        <span class="help-block"><form:errors path="email" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="text" class="form-control" name="phone" id="phone"
                               value="${customer.phone}" placeholder="Phone"/>
                        <span class="help-block"><form:errors path="phone" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="password" class="form-control" name="password" id="password"
                               value="${customer.password}" placeholder="Password"/>
                        <span class="help-block"><form:errors path="password" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <input type="password" class="form-control" name="passwordConfirmation"
                               id="passwordConfirmation" value="${customer.passwordConfirmation}"
                               placeholder="Confirm Password"/>
                        <span class="help-block"><form:errors path="passwordConfirmation" cssClass="error"/></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-1">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Sign Up</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group col-md-10 col-md-offset-2">
                        <span>Already have an account?
                            <a href="${pageContext.request.contextPath}/login">Log In</a>
                        </span>
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