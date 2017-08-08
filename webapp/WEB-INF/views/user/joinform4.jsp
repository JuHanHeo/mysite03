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
var joinAction = {
		
		init: function(){
			console.log(this);
			$(document).ready(function(){
				console.log(this);
				$("#join-form").submit(function(){
					var $inputName = $("#name");
					if($inputName.val()===""){
						alert("이름은 필수 항목입니다.");
						$inputName.focus();
						return false;
					}
					
					var $inputEmail = $("#email")
					if($inputEmail.val()===""){
						alert("이메일은 필수 항목입니다.");
						$inputEmail.focus();
						return false;
					}
					
					var $checkBtn = $("#check-botton");
					if($checkBtn.is(":visible")==true){
						alert("이메일 중복 체크를 해주세요");
						$checkBtn.focus();
						return false;
					}
					
					var $inputPasswd = $("#passwd");
					if($inputPasswd.val()===""){
						alert("비밀번호를 입력 해주세요");
						$inputPasswd.focus();
						return false;
					}
					
					var $agreeProv = $("#agree-prov");
					if($agreeProv.is(":checked")==false){
						alert("약관에 동의 해주세요");
						$agreeProv.focus();
						return false;
					}
					alert("true")
					return true;
				})
				
				$("#email").change(function(){
					$("#check-image").hide();
					$("#check-botton").show();
				})
				
				$("#check-botton").click(function(){
					var email = $("#email").val();
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
								$("#email").focus();
								
							} else {
								alert("이메일 사용가능");
								$("#check-image").show();
								$("#check-botton").hide();
								
							}
						},
						error : function(jqXHR, status, error) {
							console.error(status + " : " + error);
						}

					});
				})
			})
			
		},
		
		onSubmit: function(){
			
		}
}

$(function(){

	$(document).ready(joinAction);
	
	
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

					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="text-align: left; color: red">
								<strong><spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>


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