<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body onload="connect()">
<script type="text/javascript">
    window.labels = [];
    <c:forEach items="${labels}" var="label">
        labels.push('${label}');
    </c:forEach>
    window.contextPath = '${pageContext.request.contextPath}';
</script>
<div>
    <%@ include file="jspf/navbar.jspf" %>
</div>

<div class="container">
    <div class="form-horizontal">
        <div class="form-group">
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <c:forEach items="${labels}" var="label">
                        <th scope="col">${label}</th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody id="responseTable">
                <c:forEach items="${responses}" var="response">
                    <tr>
                        <c:forEach items="${response}" var="item">
                            <td>${item}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/repsponses.js"></script>
<script src="${pageContext.request.contextPath}/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
</body>


</html>
