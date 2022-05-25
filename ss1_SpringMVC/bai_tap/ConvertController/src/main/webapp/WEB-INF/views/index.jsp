<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Convert</title>
</head>
<body>
<form action="/convert" method="post">
    Tỉ giá: <input type="text" name="rate" value="${rate}"><br><br>
    USD: <input type="text" name="usd" value="${usd}"> <input type="submit" name="Convert"><br><br>
    Result: <input value="${vnd}"> VND
</form>
</body>
</html>
