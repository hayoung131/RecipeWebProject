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
  max-width: 440px;
  margin: 0 auto 100px;
  padding: 40px;/*박스 내부의 요소들과 간격*/
  height:350px;/*이거 바꿔야 박스크기 바뀜*/
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  float:left;
  background: #f2f2f2;
  width: 63%;
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
  font-size: 24px;
  font-family: "Roboto", sans-serif;
  margin:19.92px 0px;
  font-weight:bold;
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

    #pwdBtn {
  width: 100%;
  border: 0;
  padding: 15px;
  font-size: 14px;
  text-align: center;
  background: #4CAF50;
}
</style>

</head>
<%
String matchedId=(String)request.getAttribute("matchedId");
%>
<body>
<script>
function checkPw(){
	var pw1 = document.getElementById("newPwd");
	var pw2 = document.getElementById("newPwd-re");
	
	if(pw1.value == pw2.value){
		return true;
	}else{
		alert("패스워드가 동일하지 않습니다.");
		return false;
	}
}
</script>
  <div class="finPwd-page">

    <div class="form">
      <h2>비밀번호 찾기</h2>
      <form onSubmit="return checkPw()" method="post" class="findPwd-view" name="findPwd-view" action="recipeFindPwdView.bo">
		  <input type="hidden" name="matchedId" value="<%=matchedId%>">
          <label for="newPwd">새 비밀번호</label>
          <input type="password" placeholder="새 비밀번호를 입력하세요" id="newPwd" name="newPwd"/>

          <label for="newPwd-re">새 비밀번호 확인</label>
          <input type="password" placeholder="비밀번호를 다시 입력하세요" id="newPwd-re" name="newPwd-re"/>

        <input type="submit" id="pwdBtn" class="btn btn-success" value="새 비밀번호 설정하기"></input>

      </form>
    </div>
  </div>
</body>
</html>