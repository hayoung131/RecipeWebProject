<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.selectlist.css">

<%
	String id = (String) session.getAttribute("user_id");
	System.out.print("\n session id -confirmPwd에서 ..: "+id+"\n");
%>
<style>
.form-inline {
	/* position: absolute; */
	left: 50%;
	top: 50%;
	margin-left: 430px;
	/* margin-top: 10px; */
	margin-top: 150px;
	width: 680px;
}

h3 {
	text-align: left;
	padding: 3px;
	border-bottom: 2px solid;
	font-size: 16px;
	font-family: "Roboto", sans-serif;
	padding: 0 0 20px 0;
	font-weight: bold;
	display: block;
	margin: 0 0 150px 0;
}

#confirm {
	margin-left: 30px;
	background: #353535;
	border: 0;
}
</style>

</head>
<body>
	<%@ include file="/topMenu.jsp"%>
	<div>
		<form class="form-inline" action="recipeConfirmPwd.bo" method="post">
			<h3>개인정보 보호를 위해 비밀번호를 재입력해주세요</h3>
			<div class="form-group mb-2">
				<label for="staticEmail2" class="sr-only">Email</label> <input
					type="text" readonly class="form-control-plaintext"
					id="staticEmail2" name="confirmId" value="<%=id%>"
					style="margin-left: 25px;">
			</div>
			<div class="form-group mx-sm-3 mb-2">
				<label for="inputPassword2" class="sr-only">Password</label> <input
					type="password" class="form-control" id="inputPassword2"
					name="confirmPwd" placeholder="Password">
			</div>
			<button type="submit" class="btn btn-primary mb-2" id="confirm">Confirm
				identity</button>
		</form>
	</div>
</body>
</html>