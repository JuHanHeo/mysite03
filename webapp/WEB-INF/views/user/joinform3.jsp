<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
window.addEventListener("load", function(){
	
	document.getElementById("join-form").onsubmit = function(){
		//return 값이 false 이면 submit으로 안넘어간다 false를 이용해서 validation check 함
		//validation
		
		var inputName = $("#name").val()
		if(inputName===""){
			alert("이름은 필수 항목입니다.");
			inputName.focus();
			return false;
		}
		
		var inputEamil = document.getElementById("email");
		if(inputEamil.value==""){
			alert("이메일은 필수 항목입니다.");
			inputEamil.focus();
			return false;
		}
		
		var imageCheck = document.getElementById("check-image")
		if(imageCheck.style.display==="none"){
			alert("이메일 중복 체크를 해주세요");
			inputEamil.focus();
			return false;
		}
		
		var inputPasswd = document.getElementById("passwd");
		if(inputPasswd.value===""){
			alert("비밀번호를 입력 해주세요");
			inputPasswd.focus();
			return false;
		}
		var agreeCheck = document.getElementById("agree-prov");
		if(agreeCheck.checked===false){
			alert("약관에 동의 해주세요");
			agreeCheck.focus()
			return false;
		}
		return true;
	};
	
	$("#check-botton").click(function(){
		var email = document.getElementById( "email" ).value;
		$.ajax({
			url : "/mysite03/user/api/checkemail?email=" + email,
			type : "get",
			dataType : "json",
			data : "",
			
			//  contentType: "application/json",
			success : function(response) {
				console.log(response)
				if (response.data == true) {
					alert("이미 존재");
					//이메일을 다시입력 해야 하므로 이메일 입력창에 focus를 준다
					document.getElementById("email").focus();
					
				} else {
					alert("이메일 사용가능");
					var imageCheck = document.getElementById( "check-image" );
					var buttonCheck = document.getElementById( "check-botton" );
					
					imageCheck.style.display = "";
					buttonCheck.style.display = "none";
					
				}
			},
			error : function(jqXHR, status, error) {
				console.error(status + " : " + error);
			}

		});
	})
	
	var btnCheck = document.getElementById("check-botton");
	btnCheck.addEventListener("click", function(){
		

		
	})
});
	$(function() {
		$("#check-botton").click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}

			//ajax 통신


		});
	});
</script>

</head>
<body>


	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${userVo.name }">

					<!--<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="text-align: left; color: red">
								<strong><spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>-->


					<label class="block-label" for="email">이메일</label> 
					<form:input path="email" id="email" name="email" type="text" value=""/>
					<img id="check-image" alt="check-image" src="${pageContext.servletContext.contextPath }/assets/images/email-check.png" style="display: none">
					<input id="check-botton" type="button" value="id 중복체크" style="display: ">
					<p style="color:red; text-align: left">
					<!--<form:errors path="email" />-->
					</p>
					

					<!-- <spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('email') }">
							<p style="text-align: left; color: red">
								<strong><spring:message
										code="${errors.getFieldError( 'email' ).codes[0] }"
										text="${errors.getFieldError( 'email' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors> -->
					 <label
						class="block-label">패스워드</label> <input name="passwd" id="passwd"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>