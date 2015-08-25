<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
<div id="header">
    <%@ include file="header.jspf" %>
</div>
<br>
<br>
<br>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
            </div>
            <div style="padding-top:30px" class="panel-body">
                <c:url value="/login" var="loginUrl" />
                <form name="loginForm" class="form-horizontal" role="form" action="${loginUrl}" method="POST">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Invalid username and password!
                        </div>
                    </c:if>
                    <c:if test="${msg != null}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            You have been logged out successfully.
                        </div>
                    </c:if>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="username" type="text" class="form-control" name="username" value="" required="true"
                               placeholder="username">
                    </div>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" required="true"
                               placeholder="password">
                    </div>
                    <div class="input-group">
                        <div class="checkbox">
                            <label>
                                <input id="rememberMe" type="checkbox" name="rememberMe" value="1"> Remember me
                            </label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div style="margin-top:10px" class="form-group">
                        <div class="col-sm-12 controls">
                            <button type="submit" id="btn-login" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                Don't have an account!
                                <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                    Sign Up Here
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="signupbox" style="display:none; margin-top:50px"
         class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px">
                    <a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
            </div>
            <div class="panel-body">
                <form id="signupform" class="form-horizontal" role="form">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Invalid username and password!
                        </div>
                    </c:if>
                    <c:if test="${msg != null}">
                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            You have been logged out successfully.
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="usernameR" class="col-md-3 control-label">Login</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="usernameR" id="usernameR" required="true" placeholder="Login">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passwordR" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="passwordR" required="true" id="passwordR"
                                   placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmPasswordR" class="col-md-3 control-label">Confirm password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="confirmPasswordR" required="true" id="confirmPasswordR"
                                   placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="button" class="btn btn-default">Sign Up</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
