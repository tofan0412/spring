<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- client 
				form method : post
				encType : multipart/form-data
		 server 
		 		servlet @MultipartCinfig로 설정해줘야 한다.
		 		spring framework의 경우 : multipartResolver 
	 -->
	<form action="${cp }/fileupload/upload" method="post" encType="multipart/form-data">
		<input type="text" name="userid" value="브라운"><br><br>
		<input type="file" name="file"><br><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>