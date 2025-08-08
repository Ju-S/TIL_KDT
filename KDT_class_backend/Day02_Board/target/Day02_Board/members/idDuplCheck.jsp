<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
    <link rel="stylesheet" href="/members/idDuplCheck.css">
</head>
<body>
<c:if test="${!idDupl}">
    <div class="container">
        <div class="row text">
            <div class="col col-12">
                사용할 수 있는 아이디 입니다.<br>
                사용하시겠습니까?
            </div>
        </div>
        <div class="row input">
            <div class="col col-6 justify-content-end">
                <input type="button" class="btn btn-primary" id="submitBtn" value="사용">
            </div>
            <div class="col col-6 justify-content-start">
                <input type="button" class="btn btn-outline-primary" id="cancelBtn" value="취소">
            </div>
        </div>
    </div>
    <script>
        $("#cancelBtn").on("click", function () {
            $(window.opener.document.getElementsByName("id").item(0)).val("");
            window.close();
        });

        $("#submitBtn").on("click", function () {
            $(window.opener.document.getElementById("checkIdDupl")).css("display", "none");
            window.opener.setIdDupl(true);
            window.close();
        });
    </script>
</c:if>
<c:if test="${idDupl}">
    <div class="container">
        <div class="row text">
            <div class="col col-12">
                사용중인 아이디 입니다.
            </div>
        </div>
        <div class="row input">
            <div class="col col-12">
                <input type="button" class="btn btn-primary" id="closeBtn" value="닫기">
            </div>
        </div>
    </div>
    <script>
        $("#closeBtn").on("click", function () {
            window.opener.document.getElementsByName("id").item(0).value = "";
            window.close();
        });
    </script>
</c:if>
</body>
</html>
