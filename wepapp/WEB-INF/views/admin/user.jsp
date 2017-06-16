<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite03/assets/css/admin/main.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-form">

					<h2>사용자 관리 페이지</h2>

					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>이메일</th>
							<th>성별</th>
							<th>역할</th>
							<th>&nbsp;</th>
						</tr>
						<c:forEach items="${list }" var="list" varStatus="status">
							<tr>
								<td>${list.no }</td>
								<td>${list.name }</td>
								<td>${list.email }</td>
								<td>${list.gender }</td>
								<td>${list.role }</td>
								<td><a href="${pageContext.servletContext.contextPath }/board/delete?no=${list.no }" class="del">삭제</a></td>
							</tr>
						</c:forEach>
					</table>


				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp" />
		</div>
	</div>
</body>
</html>