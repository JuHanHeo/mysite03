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
	$(function() {
		$("#check-botton").click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}

			//ajax 통신

			$.ajax({
				url : "/mysite03/user/api/checkemail?email=" + email,
				type : "get",
				dataType : "json",
				data : "",
				//  contentType: "application/json",
				success : function(response) {
					if (response.data == true) {
						alert("이미 존재");
					} else {
						alter("없음");
					}
				},
				error : function(jqXHR, status, error) {
					console.error(status + " : " + error);
				}

			});

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
					<p style="color:red; text-align: left">
					<form:errors path="email" />
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
					<input id="check-botton" type="button" value="id 중복체크"> <label
						class="block-label">패스워드</label> <input name="passwd"
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