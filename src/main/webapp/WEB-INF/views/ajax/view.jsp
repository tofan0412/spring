<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 예제</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	// 이벤트 등록
	$('#makeJsonBtn').on('click', function(){
		console.log("하이하이")

		// 1. json 객체 만들기
		// 2. 문자열로 변경
		var a = {userid : $("#userid").val(), usernm : $("#usernm").val()};
		$('#jsonString').html(JSON.stringify(a));
	});

	$('#callAjax').on('click', function(){
		// 클릭하지 않아도 이벤트가 강제 실행
		$('#makeJsonBtn').trigger("click");
		$.ajax({
				url : "/ajax/json",
				data : JSON.stringify({userid : $("#userid").val(), usernm : $("#usernm").val()}),
				//String으로 변환하여 data에 저장..
				contentType : "application/json; charset=utf-8",
				// 보내는 것이 json 문자열임을 가리킨다. 
				method : "POST",
				success : function(res){
					console.log(res);
				} 
			});
	});
	$('#callAjax_r').on('click', function(){
		// makeJsonBtn 클릭이벤트 강제 발생
		$('#makeJsonBtn').trigger("click");

		$.ajax({
				url : "/ajax/json_r",
				data : JSON.stringify({userid : $("#userid").val(), usernm : $("#usernm").val()}),
				//String으로 변환하여 data에 저장..
				contentType : "application/json; charset=utf-8",
				// 보내는 것이 json 문자열임을 가리킨다. 
				method : "POST",
				// dataType : 서버로부터 받기 희망하는 type이 json임을 명시한다. 
				dataType : $('#dataType').val(),
				success : function(res){
					console.log(res);

					if ($('#dataType').val() == "json"){
						$("#respJsonString").html(JSON.stringify(res));}
					else{
						$('#respJsonString').html((new XMLSerializer()).serializeToString(res));
					}
					
// 					$('#respJsonString').html("");
// 					$('#respJsonString').html(JSON.stringify(res));
				} 
			});
	});



	
});


</script>

</head>
<body>
	<strong>전송 json</strong><div id="jsonString"></div>
	<strong>응답 json</strong><div id="respJsonString"></div>
	
	userid : <input type="text" id="userid" name="userid" value="brown"/><br>
	usernm : <input type="text" id="usernm" name="usernm" value="브라운"/><br>
	<select id="dataType">
		<option value="json">json</option>
		<option value="xml">xml</option>
	</select>
	<button type="button" id="makeJsonBtn">json문자열 생성</button>
	<button type="button" id="callAjax">ajax 전송</button>
	<button type="button" id="callAjax_r">ajax 전송2</button>
</body>
</html>