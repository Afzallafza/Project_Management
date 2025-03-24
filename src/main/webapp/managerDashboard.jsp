<%--
  Created by IntelliJ IDEA.
  User: afzal
  Date: 3/6/25
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        body {
            font-family: 'verdana', serif;
        ' background-color: #f4f4f9; color: #333; margin: 0; padding: 0;
            display: flex;
        }

        h1 {
            color: #2c3e50;
            font-size: 2em;
            margin-bottom: 20px;
        }

        .container {
            position: fixed;
            left: 0;
            top: 0;
            width: 200px;
            height: 100vh;
            background-color: #fff;
            padding: 20px;
            box-shadow: 2px 0 4px rgba(0, 0, 0, 0.1);
            overflow-y: auto;
        }

        button {
            background-color: lightblue;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            text-align: center;
            width: 100%;
            margin-bottom: 10px;
        }

        button:hover {
            background-color: #2980b9;
        }

        .feature {
            margin-left: 240px;
            padding: 20px;
            flex-grow: 1;
        }

        .cont {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }

        .cont div {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .cont h3 {
            margin-top: 0;
            color: #2c3e50;
        }

        .cont p {
            color: #666;
            line-height: 1.6;
        }

        .cont h4, .cont h5 {
            color: #7f8c8d;
            margin: 10px 0;
        }

        .cont span {
            color: #34495e;
            font-weight: bold;
        }

        .cont div:hover {
            border-left: 4px solid cornflowerblue;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: cornflowerblue;
            color: white;
            font-weight: bold;
        }

        tr {
            border-bottom: 1px solid #ddd;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td {
            color: #333;
        }

        .feature button {
            width: 150px;
            font-weight: bold;
            font-size: 15px;
            background: whitesmoke;
            color: black;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1>All Project</h1>
<form class="container" method="POST">
    <c:forEach var="project" items="${projects}">
        <c:choose>
            <c:when test="${submitted==project.id}">
                <button type="submit" name="submitted" value="${project.id}" class="button${project.id}"
                        style="background:cornflowerblue;color:white"
                >${project.name}</button>
            </c:when>
            <c:otherwise>
                <button type="submit" name="submitted" value="${project.id}" class="button${project.id}"
                >${project.name}</button>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</form>

<form method="post" class="feature">
    <h1>Features</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Assigned to</th>
            <th>Priority</th>
            <th>Status</th>
        </tr>
        <c:forEach var="feature" items="${features}">
            <tr>
                <td>${feature.name}</td>
                <td>${feature.developer.name}</td>
                <td>${feature.priority}</td>
                <td>${feature.status}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
