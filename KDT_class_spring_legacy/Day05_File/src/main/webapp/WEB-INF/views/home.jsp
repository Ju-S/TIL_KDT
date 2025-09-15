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

</body>
</html>