<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
			<div class="col-sm-10">
				<img src="${cp }/member/profileImg?userid=${memberVo.userid}" /><br>
				<button id="profileDownBtn" type="button" class="btn btn-default">다운로드
					: ${memberVo.realFilename }</button>
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
			<div class="col-sm-10">
				<label id="label_userid" data-userid="${memberVo.userid }"
					class="control-label">${memberVo.userid }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.usernm }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">별명</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.alias }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.pass }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">주소1</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.addr1 }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.addr2 }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.zipcode }</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">등록일자</label>
			<div class="col-sm-10">
				<label class="control-label">${memberVo.reg_dt }</label>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="modBtn" type="button" class="btn btn-default">수정</button>
			</div>
		</div>
	</form>
	
	$$$
