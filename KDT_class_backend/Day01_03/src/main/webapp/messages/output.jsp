<%--
  Created by IntelliJ IDEA.
  User: keduit
  Date: 25. 8. 6.
  Time: 오후 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table border="1" align="center">
    <tr>
        <th>번호</th>
        <th>작성자</th>
        <th>내용</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.seq}</td>
            <td>${item.writer}</td>
            <td>${item.message}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3" align="center">
            <form action="/delete.messages" method="get">
                <input type="text" name="target" placeholder="삭제할 데이터 번호">
                <input type="submit" value="삭제">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <form action="/update.messages" method="get">
                <input type="text" name="target" placeholder="수정할 데이터 번호 입력"><br>
                <input type="text" name="writer" placeholder="수정할 작성자"><br>
                <input type="text" name="message" placeholder="수정할 내용"><br>
                <input type="submit" value="수정">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <a href="/">Back</a>
        </td>
    </tr>
</table>
</body>
</html>
