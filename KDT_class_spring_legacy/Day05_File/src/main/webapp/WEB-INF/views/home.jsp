<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<fieldset>
    <legend>Upload</legend>
    <form action="/file/upload" method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="아무 텍스트"><br/>
        <input type="file" name="files" multiple>
        <button>전송</button>
    </form>
</fieldset>

<button id="list">파일 목록 가져오기</button>
<fieldset>
    <legend>File List</legend>
    <table border="1" id="files">
    </table>
</fieldset>
<script>
    $("#list").on("click", () => {
        $.ajax({
            url: "/file/list"
        }).done((res) => {
            $("#files").html("<tr> " +
                "<th>seq</th> " +
                "<th>파일 이름</th> " +
                "<th>업로드 날짜</th> " +
                "</tr>");
            for (let item of res) {
                let tr = $("<tr>");
                let seq = $("<td>").html(item.seq);
                let fileLink = $("<a>").attr("href", "/file/download?sysName=" + item.sysName + "&oriName=" + item.oriName).html(item.oriName);
                let oriName = $("<td>");
                let uploadTime = $("<td>").html(item.uploadTime);

                oriName.append(fileLink);

                tr.append(seq);
                tr.append(oriName);
                tr.append(uploadTime);

                $("#files").append(tr);
            }
        })
    })
</script>

</body>
</html>