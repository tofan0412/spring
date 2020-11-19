<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자 정보</title>

<%@include file="../layout/commonlib.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
// 	var userid = $('#userid').val();

	showMemberInfoAjax("${param.userid}");
	// showMemberInfoAjax("${param.userid}") 라고 표현하는 것이 가능한가? 가능가능..
	
	
	$('#modBtn').on('click', function(){
		$(location).attr('href', '/member/showMemberInfo_u?userid='+userid);
	});

	$("#profileDownBtn").on('click', function(){
		$(location).attr('href', '/member/profileDownload?userid='+ userid);
	})
})

function showMemberInfoAjax(userid){
	$.ajax({
		url : "/member/showMemberInfoAjax",
		data : {userid : userid},
		method : "GET",
		success : function(res){
			var html = res.split("$$$");
			$(".cont").html(html);
		}
	})
}


</script>
</head>

<body>
<%-- 	<input type="text" id="userid" value="${param.userid }" hidden> --%>
	<div class="cont"></div>
</body>
</html>
