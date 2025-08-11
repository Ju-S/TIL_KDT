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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css"
          integrity="sha512-DxV+EoADOkOygM4IR9yXP8Sb2qwgidEmeqAEmDKIOfPRQZOWbXCzLC6vjbZyy0vPisbH2SyW27+ddLVCN+OMzQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="/boards/boardList.css">
</head>
<body>
<div class="container">

    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" aria-current="page" href="/">Home</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/mypage.member">회원 정보</a>
                    </li>
                </ul>
                <form class="d-flex" action="/search.board" method="get">
                    <div class="input-group">
                        <select class="form-select" name="searchOpt">
                            <option value="title" selected>제목</option>
                            <option value="writer">작성자</option>
                        </select>
                        <input class="form-control me-2" name="search" placeholder="검색어 입력" aria-label="Search"/>
                    </div>
                    <button class="btn btn-outline-success"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>
        </div>
    </nav>

    <table class="table">
        <thead>
        <tr align="center">
            <th></th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:choose>
            <c:when test="${empty list}">
                <tr>
                    <td colspan="5" align="center">표시할 내용이 없습니다.</td>
                </tr>
                <c:forEach begin="1" end="9" var="item">
                    <tr>
                        <td colspan="5"><p style="color: transparent"></p></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach begin="0" end="9" var="i">
                    <c:choose>
                        <c:when test="${empty list[i]}">
                            <tr>
                                <td colspan="5"><p style="color: transparent"></p></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr align="center">
                                <td width="10%">${list[i].seq}</td>
                                <td class="title"><a title="${list[i].title}"
                                                     href="/item.board?seq=${list[i].seq}">${list[i].title}</a></td>
                                <td width="20%">${list[i].writer}</td>
                                <td width="30%"><fmt:formatDate value="${list[i].writerDate}"
                                                                pattern="yyyy-MM-dd hh:mm"/></td>
                                <td width="10%">${list[i].viewCount}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="3">
                <c:if test="${maxPage > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <c:choose>
                                    <c:when test="${i == curPage}">
                                        <li class="page-item active"><a class="page-link" href="?page=${i}">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </td>
            <td colspan="2" align="right">
                <div class="btn-group" role="group">
                    <button onclick="location.href='/'" class="btn btn-secondary">뒤로가기</button>
                    <button onclick="location.href='/postingFrm.board'" class="btn btn-primary">작성하기</button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>