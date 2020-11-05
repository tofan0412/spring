<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${pageContext.request.contextPath}/member/main">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/member/getMemberList">사용자</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/getJobsAll">Jobs</a></li>
	</ul>
</body>
</html>