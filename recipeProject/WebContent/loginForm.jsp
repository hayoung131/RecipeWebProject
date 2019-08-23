<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


    <script>
    $('.message a').click(function(){
       $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
    </script>
    <style>
      .login-page {/*로그인 창 작은거 */
      /* width: 360px; */
      padding: 10% 0 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
      margin: auto;
    }
    .form {
      position: relative;
      z-index: 1;
      background: #FFFFFF;
      max-width: 360px;
      margin: 0 auto 100px;
      padding: 45px;/*박스 내부의 요소들과 간격*/

      box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }
    .form input {
      font-family: "Roboto", sans-serif;
      outline: 0;
      background: #f2f2f2;
      width: 100%;
      border: 0;
      margin: 0 0 15px;
      padding: 15px;
      box-sizing: border-box; /*이건 모하는놈이지*/
      font-size: 14px;
    }
    #login {
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

    #login:hover,#login:active,#login:focus {
      background: #43A047;
    }
    .form .message {
      margin: 15px 0 0;/*위 좌우 아래*/
      color: #b3b3b3;
      font-size: 12px;


    }
    .form .message a {
      color: #4CAF50;
      text-decoration: none;
      padding:10%;
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
    }
    </style>

</head>
<body>
 <div class="login-page">

    <div class="form">
      <h2>레시피를 부탁해</h2>
      <form method="post" class="login-form" action="recipeLoginForm.bo">
        <input type="text" placeholder="Id" name="id"/>
        <input type="password" placeholder="password" name="password"/>
        <input type="submit" value="login" id="login">
        <p class="message"><a href='signupForm.jsp'>회원가입</a><a href="findIdForm.jsp" style="padding:0;">아이디</a>/<a href="findPwdForm.jsp" style="padding:0;">비밀번호 찾기</a><a href="recipeRankingList.bo">비회원 접속</a></p>
      </form>
    </div>
  </div>
</body>
</html>
