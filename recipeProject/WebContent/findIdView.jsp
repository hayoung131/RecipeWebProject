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
    <script>
    $('.message a').click(function(){
       $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
    </script>
    <style>
      .findId-page {/*로그인 창 작은거 */
       width: 440px; 
      padding: 10% 0 0;/*네모 박스가 상단으로부터 얼마나 떨어지는지*/
      margin: auto;
    }
    .form {
      /* position: relative; */
      /* z-index: 1; */
      background: #FFFFFF;
      max-width: 440px;
      margin: 0 auto 100px;
      padding: 45px;/*박스 내부의 요소들과 간격*/
      box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }
    .form input {
      font-family: "Roboto", sans-serif;
      outline: 0;
      background: #f2f2f2;
      width: 75%;
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
    .form .message {
      margin: 15px 0 0;/*위 좌우 아래*/
      color: #b3b3b3;
      font-size: 12px;


    }
    .form .message a {
      color: #4CAF50;
      text-decoration: none;
      padding:7%;
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

    </style>
</head>
<% 
	String matchedId=(String)request.getAttribute("matchedId"); 
	String idHint=matchedId.substring(0, matchedId.length()-2) + "**"; //마지막 두글짜를 **로 대체
%>
<body>
  <div class="findId-page">

    <div class="form">
      <h2>아이디 찾기</h2>

        <div style="margin:25px">
          회원님의 아이디는 <%=idHint %>입니다
        </div>
        <button class="btn btn-success" onclick="location.href='loginForm.jsp'">로그인하러 가기</button>
        <p class="message">

          <a href="signupForm.jsp">회원가입</a>
          <a href="findPwdForm.jsp">비밀번호 찾기</a>
          <a href="rankingList.jsp">비회원 접속</a>
        </p>
  
    </div>
  </div>
</body>
</html>