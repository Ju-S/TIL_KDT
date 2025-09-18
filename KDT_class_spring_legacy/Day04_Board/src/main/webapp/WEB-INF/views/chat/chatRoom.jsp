<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css"
          integrity="sha512-DxV+EoADOkOygM4IR9yXP8Sb2qwgidEmeqAEmDKIOfPRQZOWbXCzLC6vjbZyy0vPisbH2SyW27+ddLVCN+OMzQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="/css/chat/chatRoom.css"/>
</head>
<body>

<div class="container">
    <div class="chatList"></div>
    <div class="emojiContainer" style="display: none;">
        <img src="https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExcHpsaWN1aWN3YXB0bTJsMmdjam5pbHVxdndja2hscWdjdGo1cHhrcCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/33zX3zllJBGY8/giphy.gif"
             alt="">
        <img src="https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExa3k2c3h2dGp1bGRxbndmYzRreG9ocmcyazdsbGJubW1hZ2Z4cWx1ciZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/J2tNIOLEHELkY/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3YWQ0eWNvMnRvYmx6cTFtZWVndXYwY2VtZjdqY29ta2hwOTYyaHZzaCZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/LSFApowZ5ukHXUnbNI/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3dnY4Zm0zejZlcTAydmExNTU2OGtjc2pjZnNsZHQwdDFyYXY0aXltMiZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/rlkpAmX3gaLWE/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3dnY4Zm0zejZlcTAydmExNTU2OGtjc2pjZnNsZHQwdDFyYXY0aXltMiZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/TmVqs1EEKnFjq/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3dnY4Zm0zejZlcTAydmExNTU2OGtjc2pjZnNsZHQwdDFyYXY0aXltMiZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/deOKie3HCHOAo/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3NXppY2o5MXNvdHhmbjBpcHl3d29vZThiY3V0YTJwZHZybmN0ZnlodiZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/Ehl2Haxp48wP6/giphy.gif"
             alt="">
        <img src="https://media.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3d2xpY3dtNXp2ZjZ5enN1bzJyM21ieXloN2ljdWJtOHhrM2Y3d3N5OCZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/Dis5yrKgiBLKE/giphy.gif"
             alt="">
    </div>
    <div class="chatInput">
        <div contenteditable="true" id="chatText"></div>
        <button id="icon"><i class="fa-solid fa-face-smile"></i></button>
    </div>
</div>

<script>
    let ws = new WebSocket("ws://10.5.5.7/chat");

    ws.onmessage = (e) => {
        const data = JSON.parse(e.data);
        if (data.type === "history") {
            for (let item of data.contents) {
                appendMessage(item);
            }
        } else {
            appendMessage(data);
        }
    };

    $("#chatText").on("keydown", function (e) {
        if ((e.keyCode === 13 || e.key === "Enter") && !e.shiftKey) {
            e.preventDefault();

            if($(this).html().replace(/&nbsp;/g, ' ').trim() !== '') {
                ws.send($(this).html());
            }
            $(this).html("");

            return false;
        }
    });

    $("#icon").on("click", function () {
        $(".emojiContainer").fadeToggle(150);
    })

    $("img").on("click", function () {
        $("#chatText").append($(this).clone());
    })

    function appendMessage(data) {
        const timeStamp = new Date(data.sendDate);
        const hours = timeStamp.getHours();
        const minutes = timeStamp.getMinutes().toString().padStart(2, '0');
        const period = hours >= 12 ? "오후" : "오전";
        const hour12 = hours % 12 || 12;  // 0시 → 12시 처리

        const timeStr = period + " " + hour12 + ":" + minutes;

        const sender = data.sender;
        const message = data.message;

        let chatItemDiv = $("<div class='chatItem'>");
        let senderSpan = $("<span class='sender'>").text(sender);
        let messageSpan = $("<span class='message'>").html(message);
        let sendTime = $("<span class='time'>").text(timeStr);

        // 내 메시지인 경우 오른쪽 정렬
        if (sender == "${sessionScope.loginId}") {
            chatItemDiv.addClass("my-message text-end ms-auto");
            messageSpan.addClass("me");
        } else {
            chatItemDiv.addClass("other-message text-start");
            chatItemDiv.append(senderSpan);
            chatItemDiv.append("<br>");
        }

        chatItemDiv.append(messageSpan);
        chatItemDiv.append("<br>");
        chatItemDiv.append(sendTime);

        $(".chatList").append(chatItemDiv);
        $(".chatList").scrollTop($(".chatList")[0].scrollHeight);
    }
</script>
</body>

</html>