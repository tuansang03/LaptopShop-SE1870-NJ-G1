<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 Not Found</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .error-container {
            text-align: center;
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .error-container h1 {
            font-size: 80px;
            color: #dc3545;
        }
        .error-container p {
            font-size: 20px;
            color: #343a40;
        }
        .btn-back {
            margin-top: 20px;
        }
    </style>
</head>
<body>
 <div class="error-container">
     <h4>HTTP Error 403 Forbidden</h4>
        <h1>403 Error</h1>
        <p>The page you are trying to access is currently unavailable, you can't access this page with your role ${role}</p>

        <button class="btn btn-danger btn-back" onclick="goBack()">Go Back</button>
    </div>
  <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
