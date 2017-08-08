<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var messageBox = function(title, message, callback) {
	console.log(title);
	$("#dialog-message").attr("title", title);
	$("#dialog-message p").text(message);
	$("#dialog-message").dialog({
		modal : true,
		buttons : {
			확인 : function() {
				$(this).dialog("close");
			},

		},
		close : callback
	});
}
	var joinAction = {

		init : function() {
			$("#join-form").submit(this.onJoinBotton)
			$("#email").change(this.onEmailChange)
			$("#check-botton").click(this.onCheckBotton)

		},

		onCheckBotton : function() {
			
			var email = $("#email").val();
			if (email === "") {
				alert("이메일을 입력해주세요");
			} else {
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
			}
		},

		onEmailChange : function() {
			$("#check-image").hide();
			$("#check-botton").show();
		},

		onJoinBotton : function(event) {
			var $inputName = $("#name");
			var $inputEmail = $("#email");
			var $checkBtn = $("#check-botton");
			var $inputPasswd = $("#passwd");
			var $agreeProv = $("#agree-prov");
			console.log(this);
			if ($inputName.val() === "") {
				messageBox("이름필수","이름필수",$inpuptName.focus());
				return false;
			}

			if ($inputEmail.val() === "") {
				messageBox("이메일필수","이메일필수",$inputEmail.focus());
				return false;
			}

			if ($checkBtn.is(":visible") == true) {
				messageBox("중복체크필수","중복체크필수",$checkBtn.focus());
				return false;
			}

			if ($inputPasswd.val() === "") {
				messageBox("패스워드필수","패스워드필수",$inputPasswd.focus());
				return false;
			}

			if ($agreeProv.is(":checked") == false) {
				messageBox("약관체크필수","약관체크필수",$agreeProv.focus())	;
				
				return false;
			}

			return true;
		}
	}

	$(function() {

		$(document).ready(joinAction.init());
		
	});
</script>

</head>
<body>


	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm"
					method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">

					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="text-align: left; color: red">
								<strong><spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" /> </strong>
							</p>
						</c:if>
					</spring:hasBindErrors>


					<label class="block-label" for="email">이메일</label>
					<form:input path="email" id="email" name="email" type="text"
						value="" />
					<img id="check-image" alt="check-image"
						src="${pageContext.servletContext.contextPath }/assets/images/email-check.png"
						style="display: none">
					<input id="check-botton" type="button" value="id 중복체크"
						style="display:">
					<p style="color: red; text-align: left">
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
					<label class="block-label">패스워드</label>
					<input name="passwd" id="passwd" type="password" value="">

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

					<div id="dialog-message" title="" style="display: none">
						<p></p>
					</div>
				</form:form>

			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>