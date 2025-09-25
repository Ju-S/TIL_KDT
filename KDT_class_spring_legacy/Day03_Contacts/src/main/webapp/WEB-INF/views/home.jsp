<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<table align="center" border="1">
    <tr>
        <th colspan="2">
            Contact HomePage
        </th>
    </tr>
    <tr>
        <td>
            <a href="/contacts/register">
                <button>신규등록</button>
            </a>
        </td>
        <td>
            <a href="/contacts/list">
                <button>연락처목록</button>
            </a>
        </td>
    </tr>
</table>
</body>
</html>