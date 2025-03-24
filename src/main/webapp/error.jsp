<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            text-align: center;
            padding: 50px;
        }
        .container {
            display: inline-block;
            padding: 30px;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 2.5em;
            color: #dc3545;
        }
        h4 {
            margin-top: 20px;
            margin-bottom: 30px;
            color: #6c757d;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Access Denied</h1>
    <h4>You do not have permission to access this resource.</h4>
    <button onclick="window.location.href='/'">Return to Home</button>
</div>
</body>
</html>
