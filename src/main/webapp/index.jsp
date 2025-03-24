<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
        }

        a {
            margin: 10px;
            border: 3px solid cornflowerblue;
            outline: none;
            font-size: 18px;
            text-align: center;
            font-weight: bold;
            padding: 10px;
            width: 300px;
            border-radius: 10px;
        }
    </style>
    <title>JSP - Hello World</title>
</head>
<body>


<a href="hello-servlet">Hello Servlet</a>
<a href="/dashboard">Dashboard</a>
<a href="/logout">Logout</a>
</body>
</html>