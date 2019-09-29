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

<style>
.container {
	width: 70%; /* 전체 창의 70%를 차지 */
	height: 70%; /* 전체 높이의 70%를 차지*/
	margin: 40px auto;
	background: red;
}

html, body {
	width: 100%;
	height: 100%;
}

.outer {
	display: table;
	width: 100%;
	height: 100%;
}

.inner {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

.centered {
/* 	position: relative;
	display: inline-block; */
	width: 90%;
	padding: 1em;
	background: orange;
	color: white;
}

body {
	background: #FFFFFF; /* fallback for old browsers */
	font-family: "Roboto", sans-serif;
	/* -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale; */
}

.centered h2 {
	text-align: left;
	padding: 3px;
	border-bottom: 2px solid;
	font-size: 24px;
	font-family: "Roboto", sans-serif;
	margin: 19.92px 0px;
	font-weight: bold;
}


}
#mod_question {
	margin: 0 0 15px 0;
	width: 70%;
}

textarea {
	padding: 10px;
	margin: 0 0 15px;
}

.centered input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	float: left;
	background: #f2f2f2;
	width: 70%;
	border: 0;
	/*margin: 0 180px 10px 0;위 좌우 아래*/
	padding: 10px;
	font-size: 14px;
}

#modBtn {
	background: #353535;
	width: 28%;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	float: right;
	margin: 0 10px 0 0;
}
*
/
</style>
</head>
<body>
<%@include file="/topMenu.jsp"%>
	<div class="container">
		<div class="outer">
			<div class="inner">
			<!-- 	<div class="centered"> -->
					<div class="form-group row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">현재 비밀번호</label>
						<div class="col-sm-10">
							<input type="email" class="form-control"
								placeholder="col-form-label">
						</div>
					</div>
				<!-- </div> -->
			</div>
		</div>
	</div>
</body>
</html>