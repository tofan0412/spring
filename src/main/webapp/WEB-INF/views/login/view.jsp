<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/signin.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/js.cookie-2.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    
		function getCookieValue(cookieName){
			var cookies = document.cookie.split("; ");	// 브라우저에 저장되어 있는 쿠키 리스트를 변수로 저장
			for (var i = 0 ; i < cookies.length ; i++){
				var cookie = cookies[i].split("=");	// 이 경우 쿠키의 이름과 쿠키의 값을 갖는 배열이 생성된다.
				// cookie 배열은 [쿠키의 이름 , 해당 쿠키의 값]와 같은 형태를 갖는다. 
				if (cookie[0] == cookieName){
					 return cookie[1];	// 리턴으로 하면 바로 끝난다.
				}else{
					return "";	// 해당 쿠키명이 존재하지 않는 경우에는 ""를 반환한다.
				}
			}
		}
		function setCookie(cookieName, cookieValue, expires){
			var today = new Date();
			// 현재 날짜에서 미래로 + expires만큼 한 날짜 구하기
			today.setDate(today.getDate() + expires);
			document.cookie = cookieName + "=" 
							+ cookieValue 
							+ "; path=/; expires=" + today.toGMTString() + ";";
			console.log(document.cookie);
		}
		
		// 해당 쿠키의 expires 속성을 과거 날짜로 변경
		function deleteCookie(cookieName){
			setCookie(cookieName, "", -1);
		}

		$(function(){
			cookieValue = Cookies.get("REMEMBERME");
			if (cookieValue == "Y"){
				$("input[type=email]").val(Cookies.get("USERNM"));
				$("input:checkbox").prop("checked", true);
				// attr("checked", "checked")
				// "input[type=checkbox]" 와 같이 해도 된다.
			}

			$("button[type=submit]").on('click', function(){
				console.log("button_click");	// 클릭 여부를 콘솔창에 전시한다.
				// 아이디 기억 체크박스가 체크되어 있으면
				if ( $("input:checkbox").is(":checked") == true) {
					// is 대신 prop도 사용 가능하다.
					Cookies.set("REMEMBERME", "Y");
					Cookies.set("USERNM", $("input[type=email]").val());
				}
				// 아이디 기억 체크박스가 체크되어 있지 않으면
				if ( $("input:checkbox").is(":checked") == false) {
					Cookies.remove("REMEMBERME");
					Cookies.remove("USERNM");
				}
				$("form").submit();
			})
		})
    </script>
</head>
<body>
	msg : ${msg } <br>
	msg_s : ${msg_s }<br>
	msg_ra : ${msg_ra }
	<c:remove var="msg_s" scope="session"/>
	
	<div class="container">
		<form class="form-signin"
			action="${pageContext.request.contextPath}/login/process" method="POST">
			<h2 class="form-signin-heading"><spring:message code="login.signin"></spring:message></h2>
			<label for="inputEmail" class="sr-only"></label> <input
				type="text" id="userid" name="userid" class="form-control"
				placeholder="Email address" required autofocus value="brown">
			<label for="inputPassword" class="sr-only"><spring:message code="login.password"></spring:message></label> <input
				type="password" id="pass" name="pass"
				class="form-control" placeholder="Password" required
				value="brownPass">
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					<spring:message code="login.rememberme"></spring:message>
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
