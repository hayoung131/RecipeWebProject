<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.selectlist.css">


<script>
$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
</script>
<style>
  .signUp-page {/*로그인 창 작은거 */
  width: 550px;
  padding: 3% 0 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
  margin: auto;
}
.form {
  /* position: relative; */
  /* z-index: 1; */
  background: #FFFFFF;
  max-width: 550px;
  margin: 0 auto 100px;
  padding: 40px;/*박스 내부의 요소들과 간격*/
  height:720px;/*이거 바꿔야 박스크기 바뀜*/
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  float:left;
  background: #f2f2f2;
  width: 68%;
  border: 0;
  margin: 0 0 13px;/*위 좌우 아래*/
  padding: 13px;
  /* box-sizing: border-box; /*이건 모하는놈이지*/ */
  font-size: 14px;
  /* float:right; */
}

.form button {
  width: 100%;
  border: 0;
  padding: 15px;
  font-size: 14px;
  text-align: center;
}

.form .message {
  margin: 15px 0 0;/*위 좌우 아래*/
  color: #b3b3b3;
  font-size: 12px;


}
.form .message a {
  color: #4CAF50;
  text-decoration: none;

  width:100%;
}
body {
  background: #76b852; /* fallback for old browsers */
  font-family: "Roboto", sans-serif;
  /* -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
}
.form h2{
  text-align:center;
  font-size: 24px;
  font-family: "Roboto", sans-serif;
  margin:19.92px 0px;
  font-weight:bold;
}
label{
  display:block;

  text-align:left;
  width:134px;
  height:47.6px;
  float:left;
  font-family:tahoma;
  padding-top: 10px;
}
#signUp-question{
  margin:0 0 15px 0;
  width:68%;
}
textarea{
	padding:15px;
	margin:0 0 15px;
}

#confirm-duplication{
  width:90px;
  margin:0 0 0 10px;
  text-align: left;
}
#signupBtn {
  width: 100%;
  border: 0;
  padding: 15px;
  font-size: 14px;
  text-align: center;
  background: #4CAF50;
}
</style>
</head>
<body>
<script>
function checkForm(){
	var name = document.getElementById("signUp-name");
	var id = document.getElementById("signUp-id");
	
	var dup = document.getElementById("confirm-duplication");//중복확인
	
	var pwd1= document.getElementById("signUp-pwd");
	var pwd2 = document.getElementById("signUp-pwd-re");
	var answer = document.getElementById("signUp-answer");
	var phone = document.getElementById("signUp-phone");

	if(name.value=='' ||id.value=='' ||pwd1.value=='' ||pwd2.value=='' ||answer.value=='' ||phone.value==''){
		alert("마지막내용을 제외한 모든 내용을 입력해주세요");
		return false;
	}
	else if(phone.value=='' || phone.value.length!=11 || isNaN(phone.value)!=false){//공백이거나 11자리가 아니거나 숫자가아니면
		alert(phone.value.length);
		alert("휴대폰번호 숫자 11자리를 입력해주세요.");     
		return false;
	}
	else if(pwd1.value != pwd2.value){
		alert("패스워드가 동일하지 않습니다.");
		return false;
	}else{
		return true;
	}

	
}
</script>

<div class="signUp-page">

    <div class="form">
      <h2>회원가입</h2>
      <form class="signUp-form" method="post" action="recipeSignupForm.bo" onSubmit="return checkForm()">

          			<label for="signUp-name">이름</label>
          <input type="text" placeholder="이름을 입력해주세요" id="signUp-name" name="signUp-name"/>

					<label for="signUp-id">아이디</label>
          <input type="text" placeholder="아이디를 입력해주세요" id="signUp-id" style="width:220px;" name="signUp-id"/>
          <input type='hidden' name='idCheckOn' value='0'/> 
		  <button type="button" class="btn btn-success" id="confirm-duplication">중복확인</button>
					<label for="signUp-pwd">비밀번호</label>
          <input type="text" placeholder="비밀번호를 입력해주세요" id="signUp-pwd" name="signUp-pwd"/>
          			<label for="newPwd-re">새 비밀번호 확인</label>
          <input type="password" placeholder="비밀번호를 다시 입력하세요" id="signUp-pwd-re" name="signUp-pwd-re"/>
          

					<label for="signUp-phone">휴대폰번호</label>
		  <input type="text" placeholder="-를 제외한 휴대폰번호를 입력해주세요." id="signUp-phone" name="signUp-phone"/>

          			<label for="signUp-question">질문</label>
          <select id="signUp-question" class="form-control" name="signUp-question">
            <option value="0">기억에 남는 장소는?</option>
            <option value="1">자신의 인생 좌우명은?</option>
            <option value="2">인상 깊게 읽은 책은?</option>
          </select>
          <input type="text" placeholder="질문에대한 답을 입력해주세요" id="signUp-answer" name="signUp-answer"/>

					<label for="exceptIngredients">제외할 재료 설정</label>
		  <textarea name="exceptIngredients"  id="exceptIngredients" rows="2" cols="38" placeholder="레시피를 추천할 때 해당 재료를 제외한 레시피를 검색하여 보여줍니다. ex)새우,게,복숭아" ></textarea>


        <input type="submit" class="btn btn-success" id="signupBtn" value="회원가입"></input>
        <p class="message">
          <a href="loginForm.jsp">로그인페이지로 돌아가기</a>
        </p>
      </form>
    </div>
  </div>
</body>
</html>