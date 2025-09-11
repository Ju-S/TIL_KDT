<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
<table border="1" align="center" class="mt-5">
    <tr>
        <th colspan="4">출력하기</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.seq}</td>
            <td>${item.writer}</td>
            <td>${item.message}</td>
            <td>
                <form action="/messages/delete">
                    <input type="hidden" name="target" value=${item.seq}>
                    <button>삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4">
            <form action="/messages/update" align="center">
                <input type="text" placeholder="input seq to update" name="seq">
                <br>
                <input type="text" placeholder="input sender to update" name="writer">
                <br>
                <input type="text" placeholder="input message to update" name="message">
                <br>
                <button>수정</button>
            </form>
        </td>
    </tr>
    <tr>
        <th colspan="4"><a href="/">back</a></th>
    </tr>
</table>
</body>
</html>
