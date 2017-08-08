

$(function(){
	
	var dialogJoin = $("#dialog-user").dialog({
		autoOpen: false,
		height: 200,
		width: 400,
		modal: true,
		buttons: {
			"회원가입": function(){
				//ajax 통신
				var name = $("#dialog-name").val();
				var email = $("#dialog-email").val();
				var passwd = $("#dialog-password").val();

				$.ajax({
					url : contextPath + "/user/api/join",
					type : "post",
					dataType : "json",
					data : "name="+name+"&email="+email+"&passwd=" +passwd,
					success : function(response) {
						dialogJoin.dialog("close");
					},
					error : function(jqXHR, status, e) {
						alert(status + " : " + e);
					}
				});
			},
			"취소": function() {
				dialogJoin.dialog("close");
			}
		},
		close: function() {
		}

	})
	
	$(document).on("click","#dialog-join-form",function(event){

		event.preventDefault();
		dialogJoin.dialog("open");
	})
})