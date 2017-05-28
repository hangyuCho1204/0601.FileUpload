<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="http://localhost:8888/0601.FileUpload/">홈으로</a>
	<form method="post" enctype="multipart/form-data">
	<!-- application/x-www-form-urlencoded -->
		<fieldset>
			작성자 : <input type="text" name="writer"/><br>
		</fieldset>
		<fieldset>
			제목 : <input type="text" name="title"/><br>
		</fieldset>
		<fieldset>
			파일 : <input type="file" name="upFile">
		</fieldset>
		<fieldset>
			<input type="submit" value="업로드">
		</fieldset>
	</form>
</body>
</html>