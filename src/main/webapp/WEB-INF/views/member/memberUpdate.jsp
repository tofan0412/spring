<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<title>Jsp</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	$('#zipcodeBtn').on('click', function() {
		new daum.Postcode({
			oncomplete : function(data) {
				console.log(data);
				$('#addr1').val(data.roadAddress);
				$('#zipcode').val(data.zonecode);
			}
		}).open();
	});
})
</script>

<%@include file="../layout/commonlib.jsp"%>

</head>

<body>
	<%@include file="../layout/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@include file="../layout/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form id="frm" class="form-horizontal" role="form" method="POST" action="/member/memberUpdate" enctype="multipart/form-data">
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<img src="${cp }/profileImg?userid=${memberVo.userid}"/>
							<input type="file" name="file" />
						</div>
					</div>

					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<!-- placeholder : 사용자가 입력하지 않았을 때 기본적으로 보여주는 내용 -->
							<input type="text" class="form-control" id="userid" name="userid"
								placeholder="사용자 아이디" value="${memberVo.userid }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${memberVo.usernm }">
						</div>
					</div>
					<div class="form-group">
						<label for="alias" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="사용자 별명" value="${memberVo.alias }">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="사용자 비밀번호" value="${memberVo.pass }">
						</div>
					</div>
					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="사용자 주소" readonly value="${memberVo.addr1 }"><br>
							<button id="zipcodeBtn" class="btn btn-default" type="button">우편번호
								찾기</button>
						</div>
					</div>
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="사용자 상세주소" value="${memberVo.addr2 }">
						</div>
					</div>
					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" placeholder="사용자 우편번호" readonly value="${memberVo.zipcode }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="filename" class="col-sm-2 control-label">파일이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="filename"
								name="filename" placeholder="사용자 파일이름" readonly value="${memberVo.filename }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="realfilename" class="col-sm-2 control-label">실제파일이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="realFilename"
								name="realFilename" placeholder="사용자 파일이름" readonly value="${memberVo.realFilename }">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input id="ModBtn" type="submit" class="btn btn-default"/>
						</div>
					</div>
					
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>