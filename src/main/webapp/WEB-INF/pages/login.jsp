<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WebNotes</title>
</head>
<body>
<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="post" class="form-horizontal">
    <c:if test="${error != null}">
            <p>Invalid username and password.</p>
    </c:if>
    <c:if test="${msg != null}">
            <p>You have been logged out successfully.</p>
    </c:if>
    <div>
        <label for="username">Login</label>
        <input type="text" id="username" placeholder="Enter Username" required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter Password" required>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <input type="submit" value="Log in">
    </div>
</form>
</body>
</html>
