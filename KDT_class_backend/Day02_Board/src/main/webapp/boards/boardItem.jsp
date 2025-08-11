<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
    <link rel="stylesheet" href="/boards/boardItem.css">
</head>
<body>
<div class="container">
    <form id="modifyFrm" action="/update.board" method="post">
        <input type="hidden" name="seq" value="${post.seq}">
        <div class="row title">
            <div class="col divTitle">
                ${post.title}
            </div>
            <input type="hidden" name="title">
        </div>
        <hr>
        <div class="row">
            <div class="col writer">
                ${post.writer}
            </div>
        </div>
        <div class="row">
            <div class="col writeDate">
                <fmt:formatDate value="${post.writerDate}"
                                pattern="yyyy-MM-dd hh:mm"/>
                |
                조회: ${post.viewCount}
            </div>
        </div>
        <hr>
        <div class="row contents">
            <div class="col divContents">${post.contents}</div>
            <input type="hidden" name="contents">
        </div>
    </form>
    <hr>
    <div class="row sideMenu m-0 p-0">
        <div class="col m-0 p-0" align="right">
            <%--            <div class="btn-group" role="group">--%>
            <c:if test="${post.writer == sessionScope.loginId}">
                <button id="modifyBtn" type="button" class="btn btn-outline-success">수정</button>

                <button id="deleteBtn" type="button" class="btn btn-danger" data-bs-toggle="modal"
                        data-bs-target="#deletePost">삭제
                </button>

                <div class="modal fade" id="deletePost" tabindex="-1" aria-labelledby="deletePost" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5>정말 삭제 하시겠습니까?</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니요</button>
                                <button type="button" onclick="location.href = '/delete.board?seq=${post.seq}'"
                                        class="btn btn-primary">예
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <button id="confirmBtn" class="btn btn-success d-none">수정완료</button>
                <button id="cancelBtn" class="btn btn-danger d-none">취소</button>

                <script>
                    $("#modifyBtn").on("click", function () {
                        $("#confirmBtn, #cancelBtn").toggleClass("d-none", false);
                        $("#modifyBtn, #deleteBtn, #backBtn").addClass("d-none").removeClass("d-inline-block");
                        $(".divTitle, .divContents").addClass("form-control").attr("contenteditable", true);
                    });

                    $("#cancelBtn, #confirmBtn").on("click", function () {
                        $("#confirmBtn, #cancelBtn").toggleClass("d-none", true);
                        $("#modifyBtn, #deleteBtn, #backBtn").addClass("d-inline-block").removeClass("d-none");
                        $("[name = divTitle], [name = divContents]").removeClass("form-control").attr("contenteditable", false);
                    });

                    $("#cancelBtn").on("click", function () {
                        location.reload();
                    });

                    $("#confirmBtn").on("click", function () {
                        $("[name = title]").val($(".divTitle").html());
                        $("[name = contents]").val($(".divContents").html());
                        $("#modifyFrm").submit();
                    });
                </script>
            </c:if>

            <button id="backBtn" type="button" class="btn btn-secondary"
                    onclick="location.href='/list.board'">목록으로
            </button>
            <%--            </div>--%>
        </div>
    </div>
</div>
</body>
</html>
