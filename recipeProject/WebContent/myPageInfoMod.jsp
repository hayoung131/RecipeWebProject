<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.selectlist.css">

	<style>
	  .myPage-menu {/*로그인 창 작은거 */
	  width: 800px;
		height:900px;
	  padding: 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
		margin-left: -450px;
 		margin-top: -250px;
		position: absolute;
		left: 50%;
		top: 50%;
	}
	.form {
	  background: #EAEAEA;
	  max-width: 900px;/*이부분 분경하면 수정정보 창 크기 조절됨*/
	  padding: 20px 20px 20px 30px;/*박스 내부의 요소들과 간격*/
	  height:540px;/*이거 바꿔야 박스크기 바뀜*/
	  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
	}
	.form input {
	  font-family: "Roboto", sans-serif;
	  outline: 0;
	  float:left;
	  background: #f2f2f2;
	  width: 333.5px;
	  border: 0;
	  margin: 0 180px 10px 0;/*위 좌우 아래*/
	  padding: 10px;
	  font-size: 14px;

	}

	#modBtn {
	  background: #353535;
	  width: 28%;
	  padding:15px;
	  color: #FFFFFF;
	  font-size: 14px;
	  float:right;
	  margin:0 10px 0 0;
	}


	body {
	  background: #FFFFFF; /* fallback for old browsers */
	  font-family: "Roboto", sans-serif;
	  /* -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale; */
	}
	.form h2{
	  text-align:left;
	  padding:3px;
	  border-bottom:2px solid;
	  font-size: 24px;
	  font-family: "Roboto", sans-serif;
	  margin:19.92px 0px;
	  font-weight:bold;
	}
	
	label{
	  display:block;

	  text-align:left;
	  width:200px;
	  height:37.6px;
	  float:left;
	  font-family:tahoma;
	  padding-top: 10px;
	}

	#mod-question{
	  margin:0 0 15px 0;
	  width:333.5px;
	}
	
	textarea{
		padding:10px;
		margin:0 0 15px;
	}


	.info_div{
	  border-bottom:1px solid;
	}
	</style>

</head>
<body>
<%@ include file="/topMenu.jsp" %>
<div class="myPage-menu">

		<div class="form">
			<h2>회원정보 수정</h2>
			<form class="mod-form">

					<label for="mod-name">이름</label>
					<input type="text" placeholder="이름" id="mod-name" readOnly/>

					<label for="mod-id">아이디</label>
					<input type="text" placeholder="아이디" id="mod-id" readOnly/>

					<label for="mod-pwd">비밀번호</label>
					<input type="text" placeholder="*********" id="mod-pwd" />

					<label for="mod-phone">휴대폰번호</label>
					<input type="text" placeholder="01012341234" id="mod-phone"/ >

					<label for="mod-question">질문</label>
					<select id="mod-question" class="form-control" name="mod-question">
			            <option value="0">기억에 남는 장소는?</option>
			            <option value="1">자신의 인생 좌우명은?</option>
			            <option value="2">인상 깊게 읽은 책은?</option>
			        </select>


					<label></label>
					<input type="text" placeholder="집이최고얌" id="mod-answer" />

					<label for="exceptIngredients">제외할 재료 설정</label>
					<textarea name="exceptIngredients"  id="exceptIngredients" rows="1" cols="41" placeholder="새우,게,복숭아"></textarea>


				<input type="submit" class="btn btn-dark" id="modBtn" value="수정하기"/>
			</form>
		</div>
	</div>
</body>
</html>