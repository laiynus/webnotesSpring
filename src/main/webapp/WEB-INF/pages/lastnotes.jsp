<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/resources/js/operation.js"/>"></script>
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
<br>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">Your note</div>
        </div>
        <div class="input-group">
            <textarea class="form-control custom-control" id="noteText" rows="3" style="resize:vertical" placeholder="Enter your note"></textarea>
            <span class="input-group-addon btn btn-success" id="addNote" name="addNote" onclick="addNote()">Add note</span>
            <span class="input-group-addon btn btn-warning" disabled="true" id="editNote"
                  name="editNote">Edit note</span>
        </div>
    </div>
</div>
</div>
</body>
</html>
