<%--
  Created by IntelliJ IDEA.
  User: NST
  Date: 3/1/2025
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }

        form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            padding: 20px;
            border: 2px solid black;
            box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
        }

        input {
            width: 300px;
            padding: 10px;
            margin-bottom: 20px;
        }

        h5 {
            color: red;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1>Log In</h1>
<form action="/login" method="post">
    <input type="text" name="username" placeholder="Enter Username">
    <c:if test="${not empty sessionScope.userError}">
        <h5>${sessionScope.userError}</h5>
    </c:if>
    <input type="password" name="password" placeholder="Enter Password">
    <c:if test="${not empty sessionScope.passError}">
        <h5>${sessionScope.passError}</h5>
    </c:if>
    <button type="submit">Log In</button>
</form>
</body>
</html>
