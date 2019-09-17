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

<script>
	$(document).ready(
			function() {
				document.getElementById('idBtn').onclick = function() {
					var phone = document.getElementById("findId-phone").value;
					var name = document.getElementById("findId-name").value;
					if (name == '') {
						alert("이름을 입력해주세요.")
						return false;
					} else if (phone == '' || phone.length != 11
							|| isNaN(phone) != false) {//공백이거나 11자리가 아니거나 숫자가아니면
						alert("휴대폰번호 숫자 11자리를 입력해주세요.");
						return false;
					} else {
						document.getElementById('frm').submit();
						return true;
					}
				};

			});
</script>
<style>
.login-page { /*로그인 창 작은거 */
	width: 440px;
	padding: 10% 0 0; /*네모 박스가 상단으로부터 얼마나 떨어지는지*/
	margin: auto;
}

.form {
	/* position: relative; */
	/* z-index: 1; */
	background: #FFFFFF;
	max-width: 440px;
	margin: 0 auto 100px;
	padding: 45px; /*박스 내부의 요소들과 간격*/
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 264px;
	border: 0;
	margin: 0 0 15px; /*위 좌우 아래*/
	padding: 15px;
	/* box-sizing: border-box; /*이건 모하는놈이지*/ */
	font-size: 14px;
	/* float:right; */
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	text-align: center;
	transition: all 0.5 ease;
	cursor: pointer;
}

.form button:hover, .form button:active, .form button:focus {
	background: #43A047;
}

.form .message {
	margin: 15px 0 0; /*위 좌우 아래*/
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
	padding: 4%;
	width: 100%;
}

#idBtn {
	width: 100%;
	border: 0;
	padding: 15px;
	font-size: 14px;
	text-align: center;
	background: #4CAF50;
}

body {
	background: #76b852; /* fallback for old browsers */
	font-family: "Roboto", sans-serif;
	/* -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale; */
}

.form h2 {
	text-align: center;
	font-size: 24px;
	font-family: "Roboto", sans-serif;
	margin: 19.92px 0px;
	font-weight: bold;
}
</style>

</head>
<body>
	<div class="login-page">

		<div class="form">
			<h2>아이디 찾기</h2>
			<form method="post" class="findId-form" action="recipeFindIdForm.bo">

				<label for="findId-name" id="name">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
				<input type="text" placeholder="이름을 입력해주세요" id="findId-name"
					name="name" />

				<div>
					<label for="findId-phone">휴대폰번호</label> <input type="text"
						placeholder="-를 제외한 폰번호를 입력하세요." id="findId-phone"
						name="phoneNumber" />
				</div>
				<input type="submit" id="idBtn" class="btn btn-success"
					value="아이디 찾기"></input>

				<p class="message">
					<a href="loginForm.jsp">로그인</a> <a href="signupForm.jsp">회원가입</a> <a
						href="findPwdForm.jsp">비밀번호 찾기</a> <a href="recipeRankingList.bo">비회원
						접속</a>
				</p>
			</form>
		</div>
	</div>
</body>
</html>