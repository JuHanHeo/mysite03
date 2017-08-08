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
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet"
	type="text/css">
	<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/list?p=1"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list }" var="list" varStatus="status">
						<tr>
							<td>${list.no }</td>
							<td class="left" style="padding-left:${list.depth*10}px"><c:if
									test="${list.depth != 0 }">
									<img
										src="${pageContext.request.contextPath }/assets/images/reply.png">
								</c:if> <a href="${pageContext.servletContext.contextPath }/board/view?no=${list.no }&p=${param.p}">${list.title }</a></td>
							<td>${list.userName }</td>
							<td>${list.hit }</td>
							<td>${list.regDate }</td>
							<c:if test="${authUser.no == list.userNo }">
								<td><a href="${pageContext.servletContext.contextPath }/board/delete?no=${list.no }"
									class="del">삭제</a></td>
							</c:if>

						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<c:choose>

						<c:when test="${count <= 1 }">
							<ul>
								<li><a href="">◀</a></li>
								<li class="selected">1</li>
								<li><a href="">▶</a></li>
							</ul>
						</c:when>

						<c:otherwise>
							<ul>
								<li><a href="">${param.p}◀</a></li>
								<c:forEach begin="1" end="${count }" var="i" step="1">
									<c:if test="${param.p == i}">
										<li class="selected">${i }</li>
									</c:if>
									<c:if test="${param.p != i }">
										<c:if test="${!empty kwd}">
											<li><a href="${pageContext.servletContext.contextPath }/board/list?p=${i }&kwd=${kwd}">${i }</a></li>
										</c:if>
										<c:if test="${empty kwd}">
											<li><a href="${pageContext.servletContext.contextPath }/board/list?p=${i }">${i }</a></li>
										</c:if>
									</c:if>
								</c:forEach>
								<li><a href="">▶</a></li>

							</ul>
						</c:otherwise>

					</c:choose>

				</div>
				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser }">
						</c:when>

						<c:otherwise>
							<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>