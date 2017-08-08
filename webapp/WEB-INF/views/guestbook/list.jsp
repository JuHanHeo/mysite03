<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.servletContext.contextPath }/guestbook/list" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="passwd"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
				<c:forEach items="${list }" var="vo" varStatus="status">
					<li>
					<c:set var="count" value="${fn:length(list) }"/>
						<table>
							<tr>
								<td>[${status.index  } : ${status.count }]</td>
								<td>${vo.name }</td>
								<td>${vo.date }</td>
								<td><a href="${pageContext.servletContext.contextPath }/guestbook/deleteform?no=${vo.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.message, newLine, "<br>") }
								</td>
							</tr>
						</table>
						
						<br>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>