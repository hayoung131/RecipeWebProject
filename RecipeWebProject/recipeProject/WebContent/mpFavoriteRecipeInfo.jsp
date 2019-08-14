<%@page import="vo.Recipe"%>
<%@page import="vo.Ingredient"%>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>레시피 상세페이지</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

</head>
<%@ include file="/topMenu.jsp" %>
<%

	
	String check_num = (String)request.getAttribute("check_num");
	
	List<Ingredient> ingredientList = (List<Ingredient>)request.getAttribute("ingredientList");
	String message = (String)request.getAttribute("message");
	 
	if(check_num.equals("1")){
		 %> 
		 <script>alert('<%=message%>');</script>
		<%
		 }
	 
	try{
		
		Recipe information = (Recipe)request.getAttribute("information");
		String pageNum = (String)request.getAttribute("pageNum");
		
		int num = information.getNum();
		String title = information.getTitle();
		String level =  information.getLevel();
		String hit_count =  information.getHit_count();
		String time = information.getTime();
		String cooking_step = information.getCooking_step();
		String img = information.getImg();
		String status = information.getStatus();

		%>

<body>

<center>


<!-- 전체 내용을 감싸는 div -->
<div style="margin-top:60px; margin-bottom:10px; padding-top:10px; padding-bottom:5px; width:1140px;">
<!-- 제목부분 -->
<div>
<table class="table table-sm"  style ="margin-left: auto; margin-right: auto; text-align: center;">

   <colgroup>
		<col width="80%">
		<col width="10%">
		<col width="4%">
	</colgroup>

<thead class="thead-dark" >
	<tr>
		<th text-align="center" style="padding-bottom: 7px;font-size: 17pt;" >
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=title%></th>
		<th style="text-align:right; font-size: 13pt; padding-bottom: 12px;">조회수&nbsp;<%=hit_count %></th>
		<th style="padding-bottom: 10px;">
															
			<img src=<%=img %> width="25px" height="25px" >
			
		</th>
	</tr>
</thead>
</table>
</div>

<!-- 레시피 난이도 시간 부분 -->
<div style="text-align: left; margin-top: 40px;">
	<table border = "0" style=" width:640px; text-align: center; ">
		<tr>
			<td style="width: 180px;">
			<button type="button" class="btn btn-sm btn-dark btn-primary" style="width: 180px;" disabled>난이도</button>
			</td>
			
			<td><%=level%></td>
			
			<td style="width: 180px;">
			<button type="button" class="btn btn-sm btn-dark btn-primary" style="width: 180px;" disabled>시간</button>
			</td>
			
			<td><%=time%></td>
		</tr>
	</table>
</div>
<!-- 재료부분 -->
<div style="text-align: left; margin-top: 30px;">
<div>
	<button type="button" class="btn btn-sm btn-dark btn-primary" style="width: 180px;" disabled>재료</button>
</div>

<%
	if(ingredientList.size()<6){
%>

<div style="text-align: center; margin: 20px; width: 350px; margin-left: 220px;">
	<ul class="list-group list-group-sm list-group-flush">
<%
	for(int i = 0; i< ingredientList.size(); i++){
		Ingredient ingredient = (Ingredient)ingredientList.get(i);
	
%>
	  <li class="list-group-item d-flex justify-content-between align-items-center" style="font-size: 11pt">
	    <%=ingredient.getSearching_ingredient() %>
	    <span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	  </li>
	  <%} %>
	</ul>
</div>
<%
	} else{ 
%>
<div class="container" style="margin-top: 20px;">
	<div class="row justify-content-md-center">
	<div class="col col-md-auto">
	<ul class="list-group list-group-sm list-group-flush">
<%
	for(int i = 0; i<6; i++){
		Ingredient ingredient = (Ingredient)ingredientList.get(i);
	
%>
	  <li class="list-group-item d-flex justify-content-between align-items-center" style="font-size: 11pt">
	    <%=ingredient.getSearching_ingredient() %>
	    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	    <span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	  </li>
	  <%} %>
	</ul>
	</div>
	<div class="col col-md-auto">
	<ul class="list-group list-group-sm list-group-flush">
<%
	for(int i = 6; i<ingredientList.size(); i++){
		Ingredient ingredient = (Ingredient)ingredientList.get(i);
	
%>	
	<li class="list-group-item d-flex justify-content-between align-items-center" style="font-size: 11pt">
	    <%=ingredient.getSearching_ingredient() %>
	    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	    <span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	  </li>
	  <%} %>
	</ul>
	</div>
	
</div>

<%
	}
%>

</div>

<!-- 조리순서 -->
<div>
<div style="text-align: left; margin-top: 30px; margin-bottom: 30px;">
	<button type="button" class="btn btn-sm btn-dark btn-primary" style="width: 180px;" disabled>조리순서</button>
</div>
<div style="margin: 20px; width: 840px; margin-left: 180px;">

	<table class="table table-striped">

	  <tbody>
	    <tr>
	      <td>1. 먼저 새송이 버섯 3동은 밑동을 제거하고 일정한 두께로 잘라 줍니다. 대파와 청양고추는 쫑쫑 썰어줍니다.</td>
	    </tr>
	    <tr>
	      <td>2. 간장 ST, 올리고당2T, 물2T, 다진마늘1T, 참기름1T를 섞어 주신뒤 썰어놓은 청양고추, 대파를 섞어줍니다.</td>
	    </tr>
	    <tr>
	      <td>1. 먼저 새송이 버섯 3동은 밑동을 제거하고 일정한 두께로 잘라 줍니다. 대파와 청양고추는 쫑쫑 썰어줍니다.</td>
	    </tr>
	    <tr>
	      <td>2. 간장 ST, 올리고당2T, 물2T, 다진마늘1T, 참기름1T를 섞어 주신뒤 썰어놓은 청양고추, 대파를 섞어줍니다.</td>
	    </tr>
	  </tbody>
	</table>

</div>
</div>
</div>
<%
 }catch(Exception e){} 
 %>
</center>
</body>
</html>
