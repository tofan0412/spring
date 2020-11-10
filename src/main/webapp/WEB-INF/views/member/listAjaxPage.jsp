<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function(){
		// 문서 로딩이 완료되면, 1페이지에 해당하는 정보를 json으로 받는다.
		// JS 객체. 3개의 변수와 1개의 함수로 이뤄진다. (하단의 Ajax 객체)
		//memberListAjax(1);
		memberListAjaxHTML(1);

		$('#memberList').on('click','tr', function(){
			var userid = $(this).data("userid");
			document.location = "/member/MemberInfoAjax?userid="+userid;
		});
	});
	
	function memberListAjax(p){
		$.ajax({
			url : "/member/listAjax",			// url
			data : {page : p , pageSize : 5},
			//data : "page=1&pageSize=5",
			//data : JSON.stringify({page : 1, pageSize : 5}),	
			// 이 경우 controller에서 @RequestBody 어노테이션을 붙여야 한다. (JSON <--> JAVA OBJECT)(마샬링)
			method : "GET", 					// 별도로 지정 안하면 get
			success : function(res){
				var i = 0;
				// memList tbody 영역에 들어갈 html 문자열을 생성
				
				var html ="";
				for (var i = 0 ; i < res.memList.length ; i++){
					var member = res.memList[i];
					// 클라이언트의 변수에 server에서 처리한 값을 할당하는 건 가능하다.
					// var i = ${member.size()};
					html += "<tr data-userid='"+member.userid+"'>";
					html += 	"<td>"+member.userid+"</td>";
					html += 	"<td>"+member.usernm+"</td>";
					html += 	"<td>"+member.alias+"</td>";
					html += 	"<td>"+member.fmt_reg_dt+"</td>";
					// 필드로 선언하지는 않았지만, getter만으로 fmt_reg_dt라는 필드가 원래 있는 것럼 사용 가능
					// Client Side에서 Server Side에서 실행되는 코드를 돌려서 에러가 발생한다.
					//html 	+= "<td><fmt:formatDate value=\""+member.reg_dt+"\" pattern=\"yyyy-MM-dd\" /></td>";
					html += "</tr>";
				}
				$("#memberList").html(html);

				var html_btn = "";
				for(var j = 1 ; j <= res.pages ; j++ ){
					if ( j == res.pageVo.page){
						html_btn += "<li class=\"active\"><span>"+j+"</span></li>";
					}
					else{
						html_btn += 
						"<li><a href=\"javascript:memberListAjax("+ j +");\">" + j + "</a></li>";
					}
				}
				$(".pagination").html(html_btn);
				
				// 동적으로 생성된 컨텐츠에 대해 function 적용하기
				// 1. 이미 기존에 존재하는 대상에 대해 이벤트 적용하기
				// $('#memberList').on('click',"tr",function(){
				// 2. 동적으로 처리하는 함수 내에 이벤트 기입하기 
			}
		});
	}

	function memberListAjaxHTML(p){
		$.ajax({
			url : "/member/listAjaxHTML",
			data : {page : p , pageSize : 5},
			//data : "page=1&pageSize=5",
			//data : JSON.stringify({page : 1, pageSize : 5}),	
			// 이 경우 controller에서 @RequestBody 어노테이션을 붙여야 한다. (JSON <--> JAVA OBJECT)(마샬링)
			method : "GET",
			success : function(res){
				var html = res.split("$$$");	// 구분자
				$("#memberList").html(html[0]);
				$(".pagination").html(html[1]);
			}
		});
	}
</script>

<div class="row">
	Tile <strong>tiles : memberListContent.jsp</strong>
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				<tbody id="memberList">

				</tbody>
			</table>
		</div>

		<a href="${cp }/member/memberRegist"
			class="btn btn-default pull-right">사용자 등록</a>
		<div class="text-center">
			<ul class="pagination">
				<!-- 필요한 페이지의 갯수에 맞춰 자동으로 생성.. -->
			</ul>
		</div>
	</div>
</div>