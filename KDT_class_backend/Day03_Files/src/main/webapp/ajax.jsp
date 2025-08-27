필요한 jstl

```html
<!-- jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous" type="text/javascript"></script>

<!-- contentType="text/html , pageEncoding="UTF-8" 여기서 타입 지정하기 때문에 한글이 안깨짐 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
```

AJAX (Asynchronous Javascript and XML): 비동기 통신
페이지 전환 없이 request와 response가 오가는 통신 기법 / 멀티쓰레드도 비동기 통신 중 하나
ajax는 응답에 대해서 스트링을 받는거 외에는 아무것도 하지 않음

1. 기본실행

```html
<fieldset>
    <legend>AJAX01 : 기본통신</legend>
    <button id="ajax01">AJAX 실행</button>
</fieldset>

<script>
    /* 01 버튼클릭했을 때 */
    $("#ajax01").on("click", function(){
        $.ajax({ //펑션에 객체 전달
            url: "/exam01.ajax" //anchor태그나 submit처럼 화면전환 없음
        });
    })
</script>

------- 컨트롤러
String cmd = request.getRequestURI();
Gson g =new Gson();

if(cmd.equals("/exam01.ajax")) {
System.out.println("단순 확인");
}
```

1. 파라미터 전송 통신

```html
<fieldset>
    <legend>AJAX02 : 파라미터 전송 통신</legend>
    <button id="ajax02">AJAX 실행</button>
</fieldset>

<script>
    /* 02 버튼 클릭했을때 */
    $("#ajax02").on("click", function(){
        $.ajax({ //펑션에 객체 전달
            url: "/exam02.ajax",
            type : "post",
            data : {
                fruit : "Apple"
            }
        });
    })
</script>

------- 컨트롤러
String cmd = request.getRequestURI();
Gson g =new Gson();

else if (cmd.equals("/exam02.ajax")) {
String fruit = request.getParameter("fruit");
System.out.println(fruit);
}
```

2-1. 특정 데이터 파라미터로 전송하고 싶다면

```html
<fieldset>
    <legend>AJAX02 : 파라미터 전송 특정 값 보내는 통신</legend>
    <input type= "text" id="text">
    <button id="ajax02-1">AJAX 실행</button>
</fieldset>

<script>
    /* 02-1 버튼 클릭했을때 */
    $("#ajax02-1").on("click", function(){
        let text = $("#text").val();

        $.ajax({ //펑션에 객체 전달
            url: "/exam02.ajax",
            type : "post",
            data : {
                text: text
            }
        });
    })
</script>
```

**폼 전송처럼 작동**하기 때문에 특별한 설정 없이도 대부분의 서버에서 잘 받음

2-2. 특정 데이터 배열을 파라미터로 전송하고 싶다면

```html
<h2>Ajax로 배열 보내기</h2>
<input type= "text" class="text">
<input type= "text" class="text">
<input type= "text" class="text">
<button id="sendBtn">보내기</button>

<script>
    $("#sendBtn").on("click", function () {
        let arr = []; // 배열 만들기

        $(".text").each(function() { // 포리치로 돌면서 배열에 넣기
            arr.push($(this).val());
        });

        $.ajax({
            url: "FruitServlet",
            type: "POST",
            traditional: true, // 배열 전송 시 꼭 필요!
            data: { text: arr }
        });
    });
</script>


--- 컨트롤러
// 배열 받기
String[] fruits = request.getParameterValues("text");

for (String fruit : fruits) {
System.out.println("받은 과일: " + fruit);
}

```

2-3. 특정 데이터 객체를 파라미터로 전송하고 싶다면

```html
<fieldset>
    <legend>AJAX02 : 파라미터 전송 특정 값 보내는 통신</legend>
    <input type= "text" class="text">
    <input type= "text" class="text">
    <button id="ajax02-3">AJAX 실행</button>
</fieldset>

<script>
    /* 02-3 버튼 클릭했을때 */
    $("#ajax02-3").on("click", function(){
        let inputs = $(".text");
        let obj = {};   // 객체 만들기

        for (let i = 0; i < inputs.length; i++) {
            // obj라는 객체 안에
            // 0이라는 키값에 : inputs객체의 1번째 밸류인
            // <input type="text" class="text"> 라는 js를 jquery로 감싸고
            // .val() 값을 0이라는 키값의 .val()로 넣어주기
            obj[i] = $(inputs[i]).val();
        }

        $.ajax({
            url: "/exam02.ajax",
            type: "post",
            contentType: "application/json; charset=UTF-8", // JSON 보낸다고 알려줌
            data: JSON.stringify(obj) // 직렬화
        });
    });

</script>

```

let inputs = $(".text"); 여기서 바로 inputs를 못보내는 이유는

```html
{
0: <input type="text" class="text" value="apple">,
1: <input type="text" class="text" value="banana">,
2: <input type="text" class="text" value="cherry">,
length: 3,
jquery: "3.6.0",   // jQuery 버전
... 기타 메서드들 (.each, .val, .css 등)
}
```

이런식으로 태그 자체에 대한 키-밸류 객체로 생성되기 때문이다

따라서 태그의 val()만 담을 객체가 필요함

2-4. 특정 데이터 객체의 배열을 파라미터로 전송하고 싶다면

1. 서버에서 응답 받아보기

```html
<fieldset>
    <legend>AJAX03 : 응답 받아보기</legend>
    <button id="ajax03">AJAX 실행</button>
</fieldset>

<script>
    /*03버튼 눌렀을때*/
    $("#ajax03").on("click", function(){
        $.ajax({
            url: "/exam03.ajax"
        }).done(function(response){ //여기 response에 HelloAJAX가 돌아옴
            //.done() 체이닝 함수 : .ajax request에 의해서 서버가 response를 정상적으로 돌려보냈을때 done이라는 함수가 실행된다
            // response 받은걸로 어떤 작업 하고싶은지 콜백 함수의 파라미터로 넣어줌

            $("#container").empty(); // 컨테이너 안쪽에잇는거 다 지원버리고 아래 내용 실행

            let line= $("<marquee>");
            line.append(response);
            $("#container").append(line);
        });
    })
</script>
```

1. 서버에서 배열로 응답받기

```html
<fieldset>
    <legend>AJAX04 : 서버에서 보내는 응답이 배열일 경우</legend>
    <button id="ajax04">AJAX 실행</button>
</fieldset>

<script>
    $("#ajax04").on("click", function(){
        $.ajax({
            url:"/exam04.ajax",
            dataType :"json" //역직렬화 방법 2:데이터 타입 속성 보내주면 바로 파싱해서 받을 수 있음
        }).done(function(response){
            /* 역직렬화 방법 1 : 역직렬화 함수 .parse
            let result =JSON.parse(response);
            console.log(result[0]);
            console.log(result); */

            // 2가지 방법 같이쓰면 에러 남
            console.log(response);
        })
    })
</script>
```

1. 서버에서 객체로 응답받기

```html
<fieldset>
    <legend>AJAX05 : 서버에서 보내는 응답이 객체일 경우</legend>
    <button id="ajax05">AJAX 실행</button>
</fieldset>

<script>
    /* 05버튼 누를때 */
    $("#ajax05").on("click", function(){
        $.ajax({
            url: "/exam05.ajax"
        }).done(function(response){
            let result =JSON.parse(response);
            console.log(result);
        })
    })
</script>
```

1. 서버에서 객체 배열로 응답받기

```html
<fieldset>
    <legend>AJAX06 : 서버에서 보내는 응답이 객체배열일 경우</legend>
    <button id="ajax06">AJAX 실행</button>
</fieldset>

<script>
    /* 06버튼 누를때 */
    $("#ajax06").on("click", function(){
        $.ajax({
            url: "/exam06.ajax"
        }).done(function(response){
            let result =JSON.parse(response);


            /* result.forEach(function(e) {
                let str = e.seq+":"+e.name +":" + e.price +":"+ e.isIce+"<br>";
                $("#container").append(str);
            }); */

            for(let i =0; i<result.length; i++){
                let str = result[i].seq+":"+result[i].name +":" + result[i].price +":"+result[i].isIce+"<br>";
                $("#container").append(str);
            }
        })
    })
</script>
```

[연습 1](https://www.notion.so/1-257618022e3a80f4870bd8d47c566593?pvs=21)