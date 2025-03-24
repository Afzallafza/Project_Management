<%--
  Created by IntelliJ IDEA.
  User: afzal
  Date: 3/5/25
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }


        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td {
            color: #333;
        }

        caption {
            font-size: 1.5em;
            font-weight: bold;
            padding: 10px;
        }

        select {
            width: 200px;
            border-radius: 20px;
            padding: 10px;
        }

        button {
            padding: 10px;
            width: 150px;
            border: 1px solid black;
            border-radius: 10px;
        }
        h5{
            background: cornflowerblue;
            color: white;
            font-weight: bold;
            font-size: 18px;
            width: 100px;
            padding: 10px;
            border-radius: 10px;
            margin: 0;
            text-align: center;
        }
    </style>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>User</th>
        <th>Assign Role</th>
        <th>Submit</th>
    </tr>

    <c:forEach var="item" items="${users}">
        <tr>
            <form action="/add-role" method="post">
                <td><h4>${item.name}</h4></td>
                <td>
                    <c:choose>
                        <c:when test="${not empty item.role}">
                            <h5>${item.role}</h5>
                        </c:when>
                        <c:when test="${empty item.role}">
                            <select name="assign" id="">
                                <option selected disabled>Select Role</option>
                                <option value="Project Manager">Project Manager</option>
                                <option value="Team Lead">Team Lead</option>
                                <option value="Developer">Developer</option>
                            </select>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty item.role}">
                            <button value="${item.id}" type="submit" name="id">Add</button>
                        </c:when>
                        <c:when test="${not empty item.role}">
                            <button value="${item.id}" type="submit" name="sth">Change</button>
                        </c:when>
                    </c:choose>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
