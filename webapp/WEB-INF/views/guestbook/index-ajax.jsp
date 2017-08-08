<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/ajax.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script	src="${pageContext.servletContext.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset{
	float: none;
	text-align:center
}
.ui-dialog .ui-dialog-buttonpane button {
	margin-left:10px;
	margin-right:auto;
}
#dialog-message p {
	padding:20px 0;
	font-weight:bold;
	font-size:1.0em;
}

#dialog-deleteForm p{
	padding: 10px 0;
	font-weight: bold;
	font-size: 1.0em;
}

#dialog-deleteForm p.error{
	color:#f00;
}

#dialog-deleteForm input[type="password"]{
	padding: 5px;
	border: 1px solid #888;
	outline: none;
	width: 180px;
}

</style>
<script type="text/javascript">
//jquery plugin
(function($){
	$.fn.hello = function(){
		console.log($(this).attr("id") + "-----> hello!!!")
	}
	
})(jQuery);


	var isEnd = false;
	var listItemTemplate = new EJS ({url:"${pageContext.servletContext.contextPath }/assets/js/ejs-template/guestbook-list-item.ejs"});
	var listTemplate = new EJS ({url:"${pageContext.servletContext.contextPath }/assets/js/ejs-template/guestbook-list.ejs"});
	var startNo = 0;
	var messageBox = function(title, message, callback) {
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

	var render = function(vo, mode) {

		//상용 app에서는 template library 를 사용한다. ex) ejs.leaf
		var html = listItemTemplate.render( vo );
			
				//"<li data-no='"+vo.no+"'>" + "<strong>" + vo.name
				//+ "</strong>" + "<p>" + vo.message.replace(/\n/gi, "<br>")
				//+ "</p>" + "<a href='' data-no='"+vo.no+"'>삭제</a>" + "</li> ";

		if(mode==true){
			$("#list-guestbook").prepend(html);
		}else{
			$("#list-guestbook").append(html);
		}
	}

	var fetchList = function() {
		startNo = $("#list-guestbook li").last().data("no") || 0;
		if (isEnd == true) {
			return;
		}
		$.ajax({
			url : "http://www.ongoing.com:8080${pageContext.request.contextPath}/guestbook/api/list?sno=" + startNo,
			type : "get",
			dataType : "json",
			data : "",
			contentType: 'application/json',
			success : function(response) {
				if (response.result === "fail") {
					console.error("response.message");
				}
				//detect end
				if (response.data.length < 5) {
					isEnd = true;
					$("#btn-next").prop("disabled", true);
				}

				//rendering - html 태그 만드는것
				//$.each(response.data, function(index, vo) {
					//render(vo,false);
				//});
				var html = listTemplate.render( response );
				$( "#list-guestbook" ).append( html );
				$( "#list-guestbook" ).hello();
			},
			error : function(jqXHR, status, e) {
				console.error(status + " : " + e);
			}
		});
	}

	$(function() {
		
		
		
		
	    var dialogDelete = $( "#dialog-deleteForm" ).dialog({
	        autoOpen: false,
	        height: 200,
	        width: 300,
	        modal: true,
	        buttons: {
	          "삭제": function(){
	        	  var password = $("#delete-password").val();
	        	  var no = $("#delete-no").val();
	        	  
	        	  //ajax 통신
	        	  
	        	  $.ajax({
	  				url : "${pageContext.request.contextPath}/guestbook/api/delete",
	  				type : "post",
	  				dataType : "json",
	  				data : "no=" + no + "&passwd=" + password,
	  				//contentType: 'application/json',
	  				success : function(response) {
	  					if(response.result ==="fail"){
	  						console.error(response.message);
	  						return;
	  					}
	  					
	  					//삭제 실패!!
	  					if(response.data===-1){
	  						$("#dialog-deleteForm .validateTips").hide();
	  						$("#dialog-deleteForm .validateTips.error").show();
	  						$("#delete-password").val("");
	  						return ;
	  					}
	  					
	  					//삭제 성공
	  					console.log("삭제 성공 번호 - "+response.data)
	  					$("#list-guestbook li[data-no='"+response.data+"']").remove();
	  					dialogDelete.dialog( "close" );
	  				},
	  				error : function(jqXHR, status, e) {
	  					console.error(status + " : " + e);
	  				}
	  			});
	          },
	          "Cancel": function() {
	        	  $("#dialog-deleteForm .validateTips").show();
					$("#dialog-deleteForm .validateTips.error").hide();
	        	  dialogDelete.dialog( "close" );
	          }
	        },
	        close: function() {
	        }
	      });
	   
	  //live event
		$(document).on("click","#list-guestbook li a",function(event){
			
			event.preventDefault();
			
			var no = $(this).data("no");
			$("#delete-no").attr("value", no);
			dialogDelete.dialog("open");
		})
		
		
		$("#add-form").submit(function(event) {
			//submit event  기본 동작을 막음
			//posting 막음
			event.preventDefault();

			//validate form data
			var name = $("#input-name").val();
			if (name === "") {
				
				messageBox("이름필수", "이름필수",function(){
					$("#input-name").focus();
				});
				return;
			}

			var password = $("#input-password").val();
			if (password === "") {
				messageBox("비밀번호필수", "비밀번호필수",function(){
					$("#input-password").focus();
				});
				return;
			}
			
			var message = $("#ta-message").val();
			if(message === ""){
				messageBox("메세지필수", "메세지필수",function(){
					$("#ta-message").focus();
				});
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/guestbook/api/add",
				type : "post",
				dataType : "json",
				data : "name=" + name + "&" +
						"passwd=" + password + "&" +
						"message=" + message,
				//contentType: 'application/json',
				success : function(response) {
					if(response.result==="fail"){
						console.error(response.message);
						return
					}
					render(response.data, true);
					
					//reset form 폼에 있는거 지우기
					
					$("#add-form")[0].reset();//jquery에는 reset이 없고 html element에 있는 reset
				},
				error : function(jqXHR, status, e) {
					console.error(status + " : " + e);
				}
			});

		});

		$(window).scroll(
				function() {

					var $window = $(this);
					var scrollTop = $window.scrollTop();
					var windowHeight = $window.height();
					var documentHeight = $(document).height();

					console.log(documentHeight + " - " + scrollTop + " - "
							+ windowHeight)
					if (scrollTop + windowHeight + 10 > documentHeight) {
						fetchList();
					}
				})
		$("#btn-next").click(function() {
			fetchList();
		})
		//최초 실행 부분
		fetchList();
	})
</script>
<%
	pageContext.setAttribute("newLine", "\n");
%>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<span> <img class="logo" alt="aa"
					src="/mysite03/assets/images/guestbook.png">
					<h1>방명록</h1>
				</span>

				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름">
					<input type="password" id="input-password" name="passwd"
						placeholder="비밀번호">
					<textarea id="ta-message" name="message" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

				</ul>
				<div style="margin: 10px 0; text-align: center">
					<button style="padding: 10px 20px" id="btn-next">다음</button>
				</div>
				<div id="dialog-message" title="방명록 글남기기" style="display: none">
					<p></p>
				</div>

				<div id="dialog-deleteForm" title="메시지 삭제" style="display:none">
					<p class="validateTips">작성시 입력했던 비밀번호를 입력하세요</p>
					<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>

					<form>
							<input type="password" name="password" id="delete-password" value="" class="text ui-widget-content ui-corner-all">
							<input type="hidden" name="no" id="delete-no" value=""/>
							<!-- Allow form submission with keyboard without duplicating the dialog button -->
							<input type="submit" tabindex="-1"
								style="position: absolute; top: -1000px">
					</form>
				</div>


			</div>

		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>