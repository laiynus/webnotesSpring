<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/customstyles.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div id="header">
    <%@ include file="header.jspf" %>
</div>
<br>
<br>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading">Sign Up</div>
                <div class="panel-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger fade in">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                ${error}
                        </div>
                    </c:if>
                    <form:form method="post" class="form-horizontal" role="form" modelAttribute="user" id="registrationForm" name="registrationForm" action="registrationUser">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <form:label path="username" for="username"
                                        class="col-sm-3 control-label">Login</form:label>
                            <div class="col-sm-9">
                                <form:input type="text" class="form-control" id="username" required="true"
                                            placeholder="Login" name="username/>" path="username" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="password" for="password"
                                        class="col-sm-3 control-label">Password</form:label>
                            <div class="col-sm-9">
                                <form:input type="password" class="form-control" id="password" required="true"
                                            placeholder="Password" name="password/>" path="password" />
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" id="submit" name="submit"
                                        class="btn btn-success btn-sm">Sign Up</button>
                                <button type="reset" class="btn btn-default btn-sm">
                                    Reset</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
