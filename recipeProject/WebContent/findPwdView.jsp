<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/jquery.selectlist.css">


<script>
$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
</script>
<style>
  .finPwd-page {/*로그인 창 작은거 */
  width: 550px;
  padding: 10% 0 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
  margin: auto;
}
.form {
  /* position: relative; */
  /* z-index: 1; */
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 40px;/*박스 내부의 요소들과 간격*/
  height:300px;/*이거 바꿔야 박스크기 바뀜*/
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  float:left;
  background: #f2f2f2;
  width: 55%;
  border: 0;
  margin: 0 0 15px;/*위 좌우 아래*/
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

.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}

body {
  background: #76b852; /* fallback for old browsers */
  font-family: "Roboto", sans-serif;
  /* -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
}
.form h2{
  text-align:center;
  
}
label{
  display:block;
  text-align:left;
  width:130px;
  height:47.6px;
  float:left;
  font-family:tahoma;
  padding-top: 10px;
}

</style>
</head>
<body>

  <div class="finPwd-page">

    <div class="form">
      <h2>비밀번호 찾기</h2>
      <form class="findPwd-form">

          <label for="newPwd">새 비밀번호</label>
          <input type="password" placeholder="새 비밀번호를 입력해주세요" id="newPwd"/>

          <label for="newPwd-re">새 비밀번호 확인</label>
          <input type="password" placeholder="비밀번호를 다시 입력해주세요" id="newPwd-re"/>

        <button>새 비밀번호 설정하기</button>

      </form>
    </div>
  </div>
</body>
</html>