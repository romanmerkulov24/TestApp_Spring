<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body onload="connect()">
<script type="text/javascript">
    window.fields = [
        <c:forEach items="${field}" var="fieldItem">
        {
            id: "${fieldItem.id}",
            label: "${fieldItem.label}"
        },
        </c:forEach>
    ];
    window.contextPath = '${pageContext.request.contextPath}';
</script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span id="logo">LOGO</span><span id="types">TYPE</span></a>

        </div>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/login">Log In</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="row main" id="userMsg" hidden>
        <div class="well col-md-offset-3 col-md-6">
            <h2 align="center">Thank you!</h2>
            <h4 align="center">Your response was saved</h4>
        </div>
    </div>
    <div class="main-login main-center" id="questionnaire">

        <form:form class="form-horizontal"
                   method="post"
                   action="${pageContext.request.contextPath}/save"
                   role="form">

            <c:forEach items="${field}" var="field">

                <c:if test="${field.type.equals('select')}">
                    <div>
                        <c:out value="${field.label}"/>
                        <p>
                            <select class="form-control" name="${field.id}">
                                <c:forEach items="${field.options}" var="opt">
                                    <option><c:out value="${opt.value}"/></option>
                                </c:forEach>
                            </select>
                        </p>
                    </div>
                </c:if>

                <c:if test="${field.type.equals('text')}">
                    <div>
                        <c:out value="${field.label}"/>
                        <p><label>
                            <input type="text" class="form-control" name="${field.id}">
                        </label></p>
                    </div>
                </c:if>

                <c:if test="${field.type.equals('radio')}">
                    <c:out value="${field.label}"/>
                    <c:forEach items="${field.options}" var="opt">
                        <p>
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="${field.id}">
                            <label class="custom-control-label">
                                <c:out value="${opt.value}"/>
                            </label>
                        </div>
                        </p>
                    </c:forEach>
                </c:if>

                <c:if test="${field.type.equals('date')}">
                    <c:out value="${field.label}"/>
                    <div class="form-group">
                        <label>
                            <input type="date" name="${field.id}">
                        </label>
                    </div>
                </c:if>

                <c:if test="${field.type.equals('textarea')}">
                    <c:out value="${field.label}"/>
                    <p><label>
                        <textarea cols="20" rows="5" name="${field.id}"> </textarea>
                    </label></p>
                </c:if>

                <c:if test="${field.type.equals('checkbox')}">
                    <c:out value="${field.label}"/>
                    <c:forEach items="${field.options}" var="opt">
                        <p>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" name="${field.id}"
                                   value="${opt.value}">
                            <label class="custom-control-label">
                                <c:out value="${opt.value}"/>
                            </label>
                        </div>
                        </p>
                    </c:forEach>
                </c:if>

            </c:forEach>

            <div class="form-group">
                <div class="input-group col-lg-2 col-md-offset-1">
                    <button type="button"
                            class="btn btn-primary btn-lg btn-block login-button"
                            onclick="sendResponse()">
                        SUBMIT
                    </button>
                </div>
            </div>

        </form:form>
    </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/questionnaire.js"></script>
<script src="${pageContext.request.contextPath}/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
</body>
</html>
