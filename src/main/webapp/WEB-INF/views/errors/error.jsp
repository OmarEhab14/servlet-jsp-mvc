<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <style>
        body {
            margin: 0;
            font-family: "Inter", Arial, sans-serif;
            background: #f5f7fb;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }

        .error-container {
            background: #ffffff;
            padding: 40px 50px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            text-align: center;
            max-width: 500px;
            width: 90%;
        }

        .error-code {
            font-size: 64px;
            font-weight: 800;
            color: #e74c3c;
            margin-bottom: 10px;
        }

        .error-title {
            font-size: 22px;
            font-weight: 700;
            margin-bottom: 10px;
        }

        .error-message {
            font-size: 15px;
            color: #666;
            line-height: 1.6;
            margin-bottom: 25px;
        }

        .error-badge {
            display: inline-block;
            padding: 6px 12px;
            background: #fff3f3;
            color: #e74c3c;
            border-radius: 20px;
            font-size: 13px;
            margin-bottom: 15px;
        }

        .btn {
            display: inline-block;
            padding: 10px 18px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            transition: 0.2s;
        }

        .btn:hover {
            background: #43a047;
        }

        .details {
            margin-top: 20px;
            font-size: 13px;
            color: #999;
        }
    </style>
</head>

<body>

<div class="error-container">

    <div class="error-badge">Something went wrong</div>

    <div class="error-code">
        ${error.status()}
    </div>

    <div class="error-title">
        ${error.title()}
    </div>

    <div class="error-message">
        ${error.detail()}
    </div>

    <a href="${pageContext.request.contextPath}/" class="btn">
        Go Home
    </a>

    <div class="details">
        If the problem persists, contact support.
    </div>

</div>

</body>
</html>