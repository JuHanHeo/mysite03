<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="navigation">
			<ul>
				<li><a href="<%=request.getContextPath()%>/main">안대혁</a></li>
				<li><a href="<%=request.getContextPath()%>/guestbook/list">방명록</a></li>
				<li><a href="<%=request.getContextPath()%>/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="<%=request.getContextPath()%>/board/list?p=1">게시판</a></li>
				<li><a href="<%=request.getContextPath()%>/gallery">갤러리</a></li>
			</ul>
		</div>