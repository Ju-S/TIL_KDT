<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<div class="container w-50">
    <fieldset class="border p-3 m-3">
        <legend>파일 업로드 폼</legend>
        <form action="/upload.files" method="post" enctype="multipart/form-data">
            <input class="form-control" type="text" name="sender" placeholder="업로더명 입력"><br>
            <input class="form-control" type="file" name="files"><br>
            <input class="btn btn-success form-control" type="submit" value="업로드">
        </form>
    </fieldset>

    <button class="btn btn-outline-secondary ms-3" id="listFiles" onclick="location.href = '/list.files'">목록 불러오기
    </button>
    <fieldset class="border p-3 m-3">
        <legend>파일 목록</legend>
        <div id="list"></div>
    </fieldset>
</div>

<script>
    if (${not empty filesList}) {
        for (let file of ${filesList}) {
            let div = $("<div>").addClass("row");

            let seq = $("<div>").addClass("col-1").html(file.seq);

            let oriName = $("<div>").addClass("col-11");
            let sysName = $("<a>").attr("href", "/download.files?sysName=" + file.sysName + "&oriName=" + file.oriName).html(file.oriName);

            oriName.append(sysName);

            div.append(seq);
            div.append(oriName);

            $("#list").append(div);
            $("#list").append($("<hr>").addClass("mb-1 mt-1"));
        }
    }
</script>
</body>
</html>