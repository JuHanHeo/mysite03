<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="dialog-user" title="회원가입" style="display: none">


	<form>
      <label for="name">이름</label>
      <input type="text" name="name" id="dialog-name" value="" class="text ui-widget-content ui-corner-all"><br>
      <label for="email">이메일</label>
      <input type="text" name="email" id="dialog-email" value="" class="text ui-widget-content ui-corner-all"><br>
      <label for="password">패스워드</label>
      <input type="password" name="password" id="dialog-password" value="" class="text ui-widget-content ui-corner-all"><br>
 
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	</form>

</div>