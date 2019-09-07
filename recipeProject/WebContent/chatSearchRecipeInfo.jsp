<%@page import="vo.RecipeChat"%>
<%@page import="vo.Ingredient"%>
<%@ page import = "java.util.List"%>
<%@page import ="java.text.*"%> 
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
<%
	DecimalFormat format = new DecimalFormat("0.0");%>


<% 	String check_num = (String)request.getAttribute("check_num");
	String check_user_id = (String)request.getAttribute("user_id");

	List<Ingredient> ingredientList = (List<Ingredient>)request.getAttribute("ingredientList");
	String message = (String)request.getAttribute("message");
	int serving = (int)request.getAttribute("serving");
	String check = null;
	check = (String)request.getAttribute("check");
	if(check!=null){
	if(check.equals("1")){
%>
		<script>alert('즐겨찾기에 추가되었습니다.');</script>
		<%}
	if(check.equals("2")){
			%><script>alert('즐겨찾기가 해제되었습니다.');</script><% }
	}	 
		try{
		
		RecipeChat information = (RecipeChat)request.getAttribute("information");
		String[] cooking_steps = (String[])request.getAttribute("cooking_steps");
		
		
		int num = information.getNum();
		String title = information.getCooking_title();
		String level =  information.getCooking_level();
		int hit_count =  information.getHit_standard();
		String time = information.getCooking_time();
		String tip = information.getCooking_tips();
		boolean isBookmark = information.isBookmark();
		String img = null;
	
	if(isBookmark){
		img = "images/star2.png";

		}else{
			img = "images/star1.png";
		}
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
		<% if (check_user_id == "비회원" || check_user_id== null ){ %>
																	
			<img src='images/star1.png' width="25px" height="25px">
			
			<%}else { %>
				<a href="chatRecipeAddFavorites.do?num=<%=information.getNum()%>&isBookmark=<%=information.isBookmark()%>&user_id=<%=information.isBookmark()%>">														
				<img src=<%=img %> width="25px" height="25px">
				</a>
			<%} %>
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
	     <%
	    if(ingredient.getAmount()==" "){
	    	%> 
	    	<span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	     <%	} else {
	    		double amountD = Double.valueOf(ingredient.getAmount()).doubleValue();
	    		double amountDD = amountD*serving;
	    		%>
	    <span class="badge" style="font-size: 11pt"><%=format.format(amountDD)%><%=ingredient.getMeasu()%></span>
	    <%} %> 
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
	    <%
	    	if(ingredient.getAmount()==" "){
	    		
	    		 %><span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	    <%	} else {
	    		double amountD = Double.valueOf(ingredient.getAmount()).doubleValue();
	    		double amountDD = amountD*serving;
	    		%>
	    <span class="badge" style="font-size: 11pt"><%=format.format(amountDD)%><%=ingredient.getMeasu()%></span>
	    <%} %>
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
	    <%
	    	if(ingredient.getAmount()==" "){
	    		
	    		 %><span class="badge" style="font-size: 11pt"><%=ingredient.getAmount()%><%=ingredient.getMeasu()%></span>
	    <%	} else {
	    		double amountD = Double.valueOf(ingredient.getAmount()).doubleValue();
	    		double amountDD = amountD*serving;
	    		%>
	    <span class="badge" style="font-size: 11pt"><%=format.format(amountDD)%><%=ingredient.getMeasu()%></span>
	    <%} %>
	  </li>
	  <%} %>
	</ul>
	</div>
	
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
<%
		for(int i = 0; i< cooking_steps.length;i++){
%>
	    <tr>
	      <td><%=cooking_steps[i]%></td>
	    </tr>
<%
		}
%>
	  </tbody>
	</table>
	
	<div style="border-radius: 10px; margin-top:30px; background-color:#4C4C4C; padding-left : 10px; text-align: center;">
	<dl class="row" style="margin-top: 15px; margin-bottom: 15px;">
	  <dt class="col-sm-1" style="color: white;">* tip</dt>
  		<dd class="col-sm-10" style="color: white;"><%=tip %></dd>
</dl>
</div>
</div>
</div>

</div>
<%
 }catch(Exception e){} 
 %>
</center>
</body>
</html>
