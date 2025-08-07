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
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="stylesheet" href="/common/css/input.css">
    <link rel="stylesheet" href="/members/register.css">
</head>
<body>
<div class="container">
    <div class="row title">
        <div class="col">
            회원 가입 정보 입력
        </div>
    </div>
    <form action="/register.member" method="post" id="registerFrm">
        <div class="row input">
            <div class="col col-2 form-label">ID</div>
            <div class="col col-7">
                <input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요." required>
            </div>
            <div class="col col-3">
                <input type="button" class="btn btn-primary" onclick="duplCheck()" value="중복확인">
            </div>
        </div>
        <div class="row">
            <div class="col col-2"></div>
            <div class="col col-10" id="checkIdDupl">
                아이디 중복확인을 해주세요.
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">PW</div>
            <div class="col col-10">
                <input type="password" class="form-control" name="pw" placeholder="비밀번호를 입력하세요" required>
            </div>
        </div>
        <div class="row">
            <div class="col col-2"></div>
            <div class="col col-10">
                <div id="passwordRule">패스워드는 영문, 숫자, 특수문자를 포함해야합니다.</div>
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">PW확인</div>
            <div class="col col-10">
                <input type="password" class="form-control" name="pwCheck"
                       placeholder="비밀번호를 다시 입력하세요." required>
            </div>
        </div>
        <div class="row">
            <div class="col col-2"></div>
            <div class="col col-10">
                <div id="passwordDuplicate">입력된 패스워드가 서로 다릅니다.</div>
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">이름</div>
            <div class="col col-10">
                <input type="text" class="form-control" name="name" placeholder="이름을 입력하세요.(한글 2-6자)" required>
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">전화번호</div>
            <div class="col col-10">
                <input type="text" class="form-control" name="phone" placeholder="전화번호를 입력하세요." required>
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">이메일</div>
            <div class="col col-10">
                <input type="email" class="form-control" name="email" placeholder="이메일을 입력하세요.">
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">우편번호</div>
            <div class="col col-8">
                <input type="text" class="form-control readonly-disabled" name="zipCode" placeholder="우편번호" readonly>
            </div>
            <div class="col col-2">
                <input type="button" class="btn btn-primary" onclick="searchPostcode()" value="찾기">
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">주소</div>
            <div class="col col-10">
                <input type="text" class="form-control readonly-disabled" name="address1" placeholder="주소" readonly>
            </div>
        </div>
        <div class="row input">
            <div class="col col-2 form-label">상세주소</div>
            <div class="col col-10">
                <input type="text" class="form-control" name="address2" placeholder="상세주소를 입력하세요.">
            </div>
        </div>
        <div class="row regTryBtn">
            <div class="col registerBtn">
                <input type="submit" class="btn btn-primary" value="회원가입">
            </div>
            <div class="col tryAgainBtn">
                <input type="reset" class="btn btn-outline-primary" value="다시입력">
            </div>
        </div>
    </form>
</div>
<script>
    function searchPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                $("[name = zipCode]").val(data.zonecode);
                $("[name = address1]").val(data.address);
            }
        }).open()
    }

    function duplCheck() {
        window.open("/idDuplCheck.member?id=" + $("[name = id]").val() + "", "",
            "width=300, height=200, left=500");
    }

    function beforeSubmit() {
        $("[name = address1]").removeAttr("disabled");
        $("[name = zipCode]").removeAttr("disabled");
    }

    let idRegex = /^[a-z\d_]{4,12}$/;
    let passwordRegex = /([^\w\s])|([a-zA-Z])|(\d)/g;
    let nameRegex = /^[가-힣]{2,6}$/;
    let phoneRegex = /^010(-?\d{4}){2}$/;
    let emailRegex = /^.+@.+(?=\.com|\.co\.kr)$/;

    $("[name = id]").on("keypress", function () {
        $("#checkIdDupl").css("display", "flex");
    });

    $("[name = pwCheck]").on("keyup", function () {
        checkPassword();
    });

    $("[name = pw]").on("keyup", function () {
        checkPassword();
    });

    $("[name = pwCheck], [name = pw]").on("focus", function () {
        document.addEventListener('keydown', handleSpacebar);
    });

    $("[name = pwCheck], [name = pw]").on("blur", function () {
        document.removeEventListener('keydown', handleSpacebar);
    });

    function handleSpacebar(event) {
        if (event.key === ' ' || event.keyCode === 32) {
            event.preventDefault();
        }
    }

    function checkPassword() {
        let passwordVal = $("[name = pw]").val();
        let passwordCheckVal = $("[name = pwCheck]").val();

        if (passwordVal) {
            let temp;
            let tempFlag = [undefined, undefined, undefined];

            while ((temp = passwordRegex.exec(passwordVal)) != null) {
                for (let i = 1; i < temp.length; i++) {
                    if (!tempFlag[i]) {
                        tempFlag[i] = temp[i];
                    }
                }
            }

            if (tempFlag[1] && tempFlag[2] && tempFlag[3]) {
                $("#passwordRule").css("display", "none");
            } else {
                $("#passwordRule").css("display", "block");
                $("#passwordDuplicate").css("display", "none");
                return false;
            }
        }

        if (passwordVal && passwordCheckVal) {
            if (passwordVal != passwordCheckVal) {
                $("#passwordRule").css("display", "none");
                $("#passwordDuplicate").css("display", "block");
                return false;
            } else {
                $("#passwordDuplicate").css("display", "none");
            }
        }

        if (!passwordVal && !passwordCheckVal) {
            $("#passwordRule").css("display", "none");
            $("#passwordDuplicate").css("display", "none");
            return false;
        }
        return true;
    }

    $("#registerFrm").on("submit", function checkValidation() {
        if (idRegex.test($("[name = id]").val()) &&
            checkPassword() &&
            nameRegex.test($("[name = name]").val()) &&
            emailRegex.test($("[name = email]").val()) &&
            phoneRegex.test($("[name = phone]").val())) {
            return true;
        } else {
            alert("입력 정보를 다시 확인해주세요.");
            return false;
        }
    });
</script>
</body>
</html>
