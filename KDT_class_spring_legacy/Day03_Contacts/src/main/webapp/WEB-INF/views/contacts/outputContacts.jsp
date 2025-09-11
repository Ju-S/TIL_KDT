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
<table align="center" border="1" id="contactsList">
    <tr>
        <th colspan="2">연락처 목록</th>
    </tr>
    <tr>
        <th>이름</th>
        <th>연락처</th>
    </tr>
</table>
<script>
    let contactsList = ${contactsList};
    $(() => {
        for (let contactItem of contactsList) {
            let tr = $("<tr>");
            let name = $("<td>").html(contactItem.name);
            let contact = $("<td>").html(contactItem.contact);

            tr.append(name);
            tr.append(contact);

            $("#contactsList").append(tr);
        }
        let backButton = $("<tr>")
            .append($("<td colspan='2'>")
                .append($("<a href='/'>").html("back")));
        $("#contactsList").append(backButton);
    });
</script>
</body>
</html>
