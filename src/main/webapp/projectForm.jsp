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

        select {
            padding: 10px;
            margin-bottom: 20px;
            border: 2px solid black;
            width: 300px;
        }
        h5{
            color: red;
            font-weight: bold;
        }

    </style>
    <title>Title</title>
</head>
<body>
<div>
    <form action="/projects" method="post">
        <input type="text" name="name" placeholder="Enter Project Name">

        <select name="manager">
            <option selected disabled>Choose Project Manager</option>
            
            <c:forEach var="item" items="${managers}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>

        <select name="lead">
            <option selected disabled>Choose Team Lead</option>
            <c:forEach var="item" items="${leads}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Create</button>
    </form>

    <div class="projectList">

    </div>
</div>

</body>
</html>
