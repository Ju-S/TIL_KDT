<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Message List</title>
</head>
<body>
<table border="1" align="center">
    <tr>
        <th>Seq</th>
        <th>Writer</th>
        <th>Message</th>
    </tr>
    <%--  Expression Language: EL ${} --%>
    <%--  JSP Standard Tag Library: JSTL  --%>
    <c:foreach var="item" items="${list}">
        <tr>
            <td>${item.seq}</td>
            <td>${item.writer}</td>
            <td>${item.message}</td>
        </tr>
    </c:foreach>
    <tr>
        <td colspan="3">
            <form action="/Delete" method="get">
                <input type="text" name="target" placeholder="input seq to delete">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <form action="/Update" method="get">
                <input type="text" name="target" placeholder="input seq to update" required><br>
                <input type="text" name="writer" placeholder="input writer to update" required><br>
                <input type="text" name="contact" placeholder="input message to update" required><br>
                <input type="submit" value="Update">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center"><a href="/index.html">Back</a></td>
    </tr>
</table>
</body>
</html>
