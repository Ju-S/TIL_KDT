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
    <link rel="stylesheet" href="/index.css">
    <link rel="stylesheet" href="/common/css/input.css">
</head>
<body>
<div class="container">
    <form action="/" method="get">
        <div class="row title">
            <div class="col col-12">
                Login Box
            </div>
        </div>
        <div class="row idInput input">
            <div class="col col-3 form-label">
                아이디
            </div>
            <div class="col col-8">
                <input type="text" class="form-control" name="id" placeholder="ID">
            </div>
        </div>
        <div class="row pwInput input">
            <div class="col col-3 form-label">
                비밀번호
            </div>
            <div class="col col-8">
                <input type="text" class="form-control" name="pw" placeholder="PW">
            </div>
        </div>
        <div class="row btns">
            <div class="col col-6 loginBtn">
                <input type="submit" class="btn btn-primary" value="로그인">
            </div>
            <div class="col col-6 regBtn">
                <input type="button" class="btn btn-outline-primary" onclick="location.href = '/registerFrm.member'" value="회원가입">
            </div>

        </div>
        <div class="row checkbox">
            <div class="col col-12 form-check">
                <input type="checkbox" class="form-check-input" name="rememberId">
                <div class="form-label">ID기억하기</div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
