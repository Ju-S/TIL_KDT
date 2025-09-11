<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<form action="/contacts/insert">
    <table align="center" border="1">
        <tr>
            <th>연락처 신규 등록</th>
        </tr>
        <tr>
            <td><input type="text" name="name" placeholder="이름 입력"></td>
        </tr>
        <tr>
            <td><input type="text" name="contact" placeholder="연락처 입력"></td>
        </tr>
        <tr>
            <td>
                <button>등록</button>
            </td>
        </tr>
        <tr>
            <td><a href="/">back</a></td>
        </tr>
    </table>
</form>
</body>
</html>
