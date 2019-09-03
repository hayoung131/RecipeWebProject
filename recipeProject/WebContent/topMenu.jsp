<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<%!static String id="";%>
<%
id=(String)session.getAttribute("user_id");
System.out.print("\n session id -top Menu에서 ..: "+id+"\n");
%>
<script>
	var id='<%=id%>';
	function loginCheck(){
		if(id==null || id.equals("")){//비회원 접속상태
			alert(id);
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		else
			return true;
	}
</script>
</head>
<body>

<div class="jumbotron text-center mb-0" style="padding-bottom:20px; padding-top: 20px;" >
        <h1>레시피를 부탁해</h1>
        <p>냠냠봇에게 레시피를 물어 보세요!</p>
    </div>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" >
  <a class="navbar-brand" href="recipeRankingList.bo"><h4>레시피를 부탁해</h4></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExample03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="recipeRankingList.bo" style="font-size: 13pt"> 레시피 랭킹<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" style="font-size: 13pt">챗봇</a>
      </li>
      <li class="nav-item dropdown" style="font-size: 13pt">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a>
        <div class="dropdown-menu" aria-labelledby="dropdown03">
          <a class="dropdown-item" href="confirmPwd.jsp" onclick="return loginCheck();">회원정보</a>
          <a class="dropdown-item" href="recipeMpFavoriteList.bo" onclick="return loginCheck();">즐겨찾기</a>
          <a class="dropdown-item" href="#" onclick="return loginCheck();">제외할 재료 설정</a>
        </div>
      </li>
      <li class="nav-item" style="padding-top: 5px;">
        <a class="nav-link" href="#" style="font-size: 10pt">로그아웃</a>
      </li>

    </ul>
<!--     <form class="form-inline my-2 my-md-0">
      <input class="form-control" type="text" placeholder="Search">
    </form> -->
  </div>
</nav>



<%-- <table border="0" style="text-align:center; font-size: 17px; 
display: inline-table;">
	<tr>
		<!-- <td width="20%">
		<a href="/myproject3/main/main.jsp">레시피랭킹</a>
		</td> -->
		<td style="width:200px; height: 30px; background-color: #F15F5F" >
		<a href="/myproject3/board/boardList.jsp" style="text-decoration: none; color: black;">레시피랭킹</a>
		</td>
		<td style="width:200px; height: 30px; background-color: #E7E7E7 ;">챗봇</td>
		<td style="width:200px; height: 30px; background-color: #F15F5F;">
		<a href="/myproject3/board/myPage2.jsp" style="text-decoration: none; color: black;">마이페이지</td>
		<!-- <td width="20%"></td> -->
<!-- 		<td width="20%">
		
		<a href="/myproject3/member/memberWrite.jsp" style="text-decoration: none;">회원가입()</a>
		
		</td> -->
<!-- 		<td width="20%" style="font-size: 10px;">
		
		<a href="/myproject3/member/memberWrite.jsp">로그아웃</a>
		
		</td> -->
		
		<!-- <td width="20%"> -->

<%
if(session.getAttribute("session_id")==null){//null 이라는 것은 로그인 안했다는 뜻
%>	
	<a href="/myproject3/member/loginWrite.jsp">로그인</a></td>
<%
}else {
%>
	<a href="/myproject3/member/logout.jsp">로그아웃</a></td>
<%
}
%>
 여기서 로그아웃되면 로그아웃 처리하는거 넣고 하면 될 듯		
		
		
	</tr>
</table>
<a href="/myproject3/member/logout.jsp" style="font-size: 10px; text-decoration: none; 
	color:white; background-color: #4C4C4C;margin-bottom: 10px; padding: 3px; ">로그아웃</a> --%>
</body>
</html>
