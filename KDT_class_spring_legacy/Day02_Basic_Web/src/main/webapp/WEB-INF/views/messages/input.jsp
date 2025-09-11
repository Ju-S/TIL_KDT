<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<table border="1" align="center" class="mt-5">
    <tr>
        <th colspan="3">입력하기</th>
    </tr>
    <tr>
        <form action="/messages/inputproc">
            <td><input type="text" name="writer" placeholder="작성자 이름"></td>
            <td><input type="text" name="message" placeholder="보낼 메세지 내용"></td>
            <td>
                <button>전송</button>
            </td>
        </form>
    </tr>
    <tr>
        <td colspan="3"><a href="/"><button>홈으로</button></a></td>
    </tr>
</table>
</body>
</html>
