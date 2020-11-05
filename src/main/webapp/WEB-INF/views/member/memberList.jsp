<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<%@ include file="../layout/commonlib.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#memberList tr').on('click', function(){
		var userid = $(this).data("userid");
		document.location = "/member/showMemberInfo?userid=" + userid;
	});
});
</script>
</head>

<body>
	<%@ include file="../layout/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="../layout/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
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
								<c:forEach items="${memList}" var="member">
									<tr data-userid="${member.userid }">
										<td>${member.userid }</td>
										<td>${member.usernm }</td>
										<td>${member.alias }</td>
										<td>${member.reg_dt }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>

						<a href="${cp }/member/memberRegist" class="btn btn-default pull-right">사용자 등록</a>
						<div class="text-center">
							<ul class="pagination">
							<!-- 필요한 페이지의 갯수에 맞춰 자동으로 생성.. -->
								<c:forEach var="i" begin="1" end="${pages}">
									<c:choose>
										<c:when test="${i == page}">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/member/getMemberList_P?page=${i}&pageSize=${pageSize}">${i}</a></li>	
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
