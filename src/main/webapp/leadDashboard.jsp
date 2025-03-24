<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            gap: 40px;
        }

        h1 {
            color: #2c3e50;
            font-size: 2em;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }

        input[type="text"], select, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1em;
        }

        textarea {
            resize: vertical;
        }

        button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }

        button:hover {
            background-color: #2980b9;
        }

        .feature {
            flex: 1;
            max-height: 80vh;
            overflow-y: auto;
        }

        .cont {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin: 20px;
        }

        .cont form {
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
    </style>

    <title>Title</title>
</head>
<body>
<div>
    <h1>Create Feature </h1>
    <form method="post">
        <input type="text" name="name" placeholder="Name"/>
        <select name="developer">
            <option selected disabled>Select Developer</option>
            <c:forEach var="item" items="${users}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <textarea name="description" placeholder="Describe Feature..." cols="30" rows="10"></textarea>
        <select name="priority">
            <option selected disabled>Choose Priority</option>
            <option value="HIGH">High</option>
            <option value="MEDIUM">Medium</option>
            <option value="TRIVIAL">Trivial</option>
        </select>
        <button type="submit">Create Feature</button>
    </form>
</div>

<div class="feature">
    <h1>Features</h1>
    <div class="cont">
        <c:forEach var="feature" items="${features}">
            <form method="post" action="/feature-status-update">
                <h3>${feature.name}</h3>
                <p>${feature.description}</p>
                <h4>Assigned to : <span>${feature.developer.name}</span></h4>
                <h5>Priority : <span>${feature.priority}</span></h5>
                <h5>Status : <span>${feature.status}</span></h5>
                <c:if test="${feature.status.ordinal==2}">
                    <textarea name="description" cols="30" rows="10" required></textarea>
                    <button name="reject" type="submit" value="${feature.id}">Reject</button>
                    <button name="accept" type="submit" value="${feature.id}">Accept</button>
                </c:if>
            </form>
        </c:forEach>
    </div>
</div>

</body>
</html>
