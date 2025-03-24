<%--
  Created by IntelliJ IDEA.
  User: afzal
  Date: 3/6/25
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
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
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
        }

        h1 {
            color: #2c3e50;
            font-size: 2em;
            margin: 0;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 100%;
        }

        .container {
            display: flex;
            flex: 1;
            gap: 20px;
            padding: 20px;
        }

        .feature {
            width: 30%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .feature button {
            background-color: lightblue;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .feature button:hover {
            background-color: #2980b9;
        }

        .feature button[style*="background:cornflowerblue"] {
            background-color: cornflowerblue;
            color: white;
        }

        .review {
            width: 70%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .review h2 {
            color: #2c3e50;
            margin-top: 0;
        }

        .review p {
            color: #666;
            line-height: 1.6;
        }

        .review h4, .review h5 {
            color: #7f8c8d;
            margin: 10px 0;
        }

        .review span {
            color: #34495e;
            font-weight: bold;
        }

        .review textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1em;
            resize: vertical;
        }

        .review button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }

        .review button:hover {
            background-color: #2980b9;
        }

        h5 > input {
            font-weight: bold;
            text-align: center;
            border: none;
            outline: none;
            width: 100px;
            margin: 0;
        }

        /*
        Timeline
         */
        .timeline {
            width: 25%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow-y: auto;
        }

        .timeline-item {
            position: relative;
            padding-left: 20px;
            margin-bottom: 20px;
        }

        .timeline-item::before {
            content: '';
            position: absolute;
            left: 0;
            top: 0;
            width: 10px;
            height: 10px;
            background-color: #3498db;
            border-radius: 50%;
            border: 2px solid #fff;
            box-shadow: 0 0 0 2px #3498db;
        }

        .timeline-item::after {
            content: '';
            position: absolute;
            left: 4px;
            top: 10px;
            width: 2px;
            height: 100%;
            background-color: #3498db;
        }

        .timeline-item:last-child::after {
            display: none;
        }

        .status {
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 5px;
        }

        .timestamp {
            font-size: 0.9em;
            color: #7f8c8d;
            margin-bottom: 5px;
        }

        .description {
            color: #666;
            line-height: 1.4;
        }
        .feature h4 {
            color: #2c3e50;
            margin-bottom: 6px;
            font-size: 1.1em;
        }

        .inline{
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
            margin-bottom: 10px;
        }

        .inline-group label {
            display: flex;
            align-items: center;
            font-size: 0.9em;
            color: #666;
            cursor: pointer;
            margin: 0;
        }

        .feature input[type="radio"],
        .feature input[type="checkbox"] {
            margin-right: 6px;
            cursor: pointer;
            width: 16px;
            height: 16px;
            accent-color: #3498db;
        }

        .feature input[type="radio"]:focus,
        .feature input[type="checkbox"]:focus {
            outline: 2px solid #3498db;
            outline-offset: 2px;
        }
        .container .exc{
            background: cornflowerblue;
            width: 150px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 25px;
        }
        select{
            padding: 5px;
            width: 200px;
            margin: 10px;
            margin-left: 0;
            font-size: 15px;
            outline: none;
            border:2px solid cornflowerblue;
        }

    </style>
    <title>Title</title>
</head>
<body>
<h1>Features</h1>
<div class="container">
    <form class="feature" method="post">
        <div>
            <h4>Order By Priority</h4>
            <div class="inline">
                <label>High to Trivial</label>
                <input type="radio" name="priorityOrder" value="asc" checked="checked"/>
                <label>Trivial to High</label>
                <input type="radio" name="priorityOrder" value="desc"/>
            </div>
            <h4>Select Status</h4>
            <div class="inline">
                <label>PENDING</label>
                <input type="checkbox" name="selectedStatus" value="PENDING" checked="checked"/>
                <label>ONGOING</label>
                <input type="checkbox" name="selectedStatus" value="ONGOING" checked="checked"/>
                <label>IN_REVIEW</label>
                <input type="checkbox" name="selectedStatus" value="IN_REVIEW" checked="checked"/>
                <label>FINISHED</label>
                <input type="checkbox" name="selectedStatus" value="FINISHED" checked="checked"/>
            </div>
            <button type="submit" name="filter" class="exc">Filter</button>
        </div>

        <c:forEach var="feature" items="${features}">
            <c:choose>
                <c:when test="${not empty submittedFeatureId && submittedFeatureId==feature.id}">
                    <button type="submit" name=
                            lowerblue;color:white"
                    >${feature.name}</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" name="submittedFeatureId" value="${feature.id}" class="button${feature.id}"
                    >${feature.name}</button>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </form>
    <c:if test="${feature.status.ordinal<2}">
        <div class="review">
            <form method="post" action="/feature-status-update">
                <h2>${feature.name}</h2>
                <p>${feature.description}</p>
                <h5>Priority : <span>${feature.priority}</span></h5>
                <select name="status">
                    <option value="" selected disabled>Select Status</option>
                    <option value="PENDING">PENDING</option>
                    <option value="ONGOING">ONGOING</option>
                    <option value="IN_REVIEW">IN REVIEW</option>
                </select>
                <textarea name="description" cols="30" rows="10" placeholder="Describe the changes..."></textarea>
                <button type="submit" name="change" value="${feature.id}">Change Status</button>
            </form>
        </div>
    </c:if>
    <div class="timeline">
        <h2>Timeline of ${feature.name}</h2>
        <c:forEach var="status" items="${featureStatusHistories}">
            <div class="timeline-item">
                <div class="status">${status.status}</div>
                <div class="timestamp">${status.timeOfChange}</div>
                <div class="description">${status.description}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>