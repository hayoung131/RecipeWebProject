<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta charset="utf-8">
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
  .findPwd-page {/*로그인 창 작은거 */
  width: 550px;
  padding: 10% 0 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
  margin: auto;
}
.form {
  /* position: relative; */
  /* z-index: 1; */
  background: #FFFFFF;
  max-width:440px;
  margin: 0 auto 100px;
  padding: 40px;/*박스 내부의 요소들과 간격*/
  height:480px;/*이거 바꿔야 박스크기 바뀜*/
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  float:left;
  background: #f2f2f2;
  width: 282px;
  border: 0;
  margin: 0 0 15px;/*위 좌우 아래*/
  padding: 15px;
  /* box-sizing: border-box; /*이건 모하는놈이지*/ */
  font-size: 14px;
  /* float:right; */
}

#pwdBtn {
  width: 100%;
  border: 0;
  padding: 15px;
  font-size: 14px;
  text-align: center;
  background: #4CAF50;
}


.form .message {
  margin: 15px 0 0;/*위 좌우 아래*/
  color: #b3b3b3;
  font-size: 12px;


}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
  padding:4%;
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
  width:70px;
  height:47.6px;
  float:left;
  font-family:tahoma;
  padding-top: 10px;
}
#findPwd-question{
  margin:0 0 15px 0;
  width:282px;
}
</style>
</head>
<body>

  <div class="findPwd-page">

    <div class="form">
      <h2>비밀번호 찾기</h2>
      <form method="post" class="findPwd-form" action="recipeFindPwdForm.bo">

          <label for="findPwd-id">아이디</label>
          <input type="text" placeholder="아이디를 입력해주세요" id="findPwd-id" name="findPwd-id"/>



          <label for="findPwd-name">이름</label>
          <input type="text" placeholder="이름을 입력해주세요" id="findPwd-name" name="findPwd-name"/>

          <label for="findPwd-name">질문</label>
          <select id="findPwd-question" class="form-control" name="findPwd-question">
            <option value="0">기억에 남는 장소는?</option>
            <option value="1">자신의 인생 좌우명은?</option>
            <option value="2">인상 깊게 읽은 책은?</option>
          </select>
          
          <input type="text" placeholder="질문에대한 답을 입력해주세요" id="findPwd-ans" name="findPwd-ans"/>

        <input type="submit" id="pwdBtn" class="btn btn-success" value="비밀번호 찾기"></input>
        <p class="message">
          <a href="loginForm.jsp">로그인</a>
          <a href="signupForm.jsp">회원가입</a>
          <a href="findIdForm.jsp">아이디 찾기</a>
          <a href="recipeRankingList.bo">비회원 접속</a>
        </p>
      </form>
    </div>
  </div>

</body>
</html>