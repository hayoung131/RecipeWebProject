<%@page import="vo.PageInfo"%>
<%@page import="vo.Recipe"%>
<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>


<%!SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm");%>

<%
	List<Recipe> articleList = (List<Recipe>)request.getAttribute("articleList");
     PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
     
     int number = pageInfo.getNumber();
     int count = pageInfo.getCount();
     int startPage = pageInfo.getStartPage();
     int endPage = pageInfo.getEndPage();
     int pageCount = pageInfo.getPageCount();
     int currentPage = pageInfo.getCurrentPage();
%>
<html>
<head>

<!-- 부트스트랩 사용하기위한 설정 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>


<title>메인 페이지</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<%@ include file="/topMenu.jsp" %>
<center style="margin-bottom: 30px;">

<%-- <b>글목록(전체 글:<%=count%>)</b> --%>
<%-- <table width="700">
<tr>
    <td align="right" bgcolor="<%=value_c%>">
    <a href="boardWriteForm.bo">글쓰기(없앨거임)</a>
    </td>
</table> --%>


<%
	if (count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    즐겨찾기에 추가한 레시피가 없습니다.
    </td>
</table>

<%
	} else {
%>
<div class="container">
<div class="row">
<div class="col">
<form method="post" name="writeform" 
	action="recipeMpFavotiteDeletePro.bo?pageNum=<%=currentPage%>" >
<table class="table table-striped " style ="margin-left: auto; margin-right: auto; 
margin-top:70px; text-align:center; width: 1140px; font-size: 13pt"> 
    
    <colgroup>
		<col width="10%">
		<col width="60%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
	</colgroup>
    
    <thead class="thead-dark">
    <tr> 
      <th align="center"   >순위</th> 
      <th align="center" >레시피 제목</th> 
      <th align="center"  >난이도</th>
      <th align="center"   >조회수</th>
      <th align="center" >삭제</th>  
      <!-- <th align="center"  width="100" >IP</th>    --> 
    </tr>
    </thead>
<%
	for (int i = 0 ; i < articleList.size() ; i++) {
          Recipe article = (Recipe)articleList.get(i);
%>
   <tr height="30">
    <td align="center"  > <%=number--%></td>
    <td   >
	<%-- <%
	      int wid=0; 
	      if(article.getRe_level()>0){
	        wid=5*(article.getRe_level());
	%>
	  <img src="images/level.gif" width="<%=wid%>" height="16">
	  <img src="images/re.gif">
	<%}else{%>
	  <img src="images/level.gif" width="<%=wid%>" height="16">
	<%}%> --%>
        <!-- 제목부분임 -->   
      <a href="recipeMpFavoriteContent.bo?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
           <%=article.getCooking_title()%></a> 
<%--           <% if(article.getReadcount()>=20){%>
         <img src="images/hot.gif" border="0"  height="16"><%}%>  --%>
         </td>
         
         <!-- 난이도 부분 -->
    <td align="center" >
       <%-- <a href="mailto:<%=article.getEmail()%>"> --%> <!-- 그 사람의(작성자의)이메일주소로 링크한거.  -->
       <%=article.getCooking_level()%><!-- </a> --></td>
    
    <td align="center"  ><%=article.getHit_standard()%>
    </td>
   <%--  <td align="center" width="100" ><%=article.getIp()%></td> --%>
   	<td align="center" >
   		<div class="btn-group-toggle" data-toggle="buttons" >
  		<label class="btn btn-sm btn-outline-danger " style="height: 25px;">
    	<input type="checkbox" name="num" value="<%=article.getNum()%>">&nbsp;&nbsp;
    	</label>
    	</div>
   	</td>
  </tr>
     <%}%>
    <tr>
    <td colspan="5" style="text-align: right; height: 45px;">
    
	<input class="btn btn-secondary btn-sm active" role="button" aria-pressed="true" 
	style="text-align: right;" type="submit" value="선택삭제" >
	
	</td>
    </tr>
</table>
</form>
</div>
<%}%>
<div class="w-100"></div>
<div class="col" style="text-align: center; font-size: 15pt ;">

<!-- 페이징 처리 -->

<%
    if (count > 0) {
        
        if (startPage > 10) {    %>
        <a href="recipeMpFavoriteList.bo?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="recipeMpFavoriteList.bo?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        if (endPage < pageCount) {  %>
        <a href="recipeMpFavoriteList.bo?pageNum=<%= startPage + 10 %>">[다음]</a>
<%
        }
    }
%>

</div>
</div>
</div>
</center>

</body>
</html>
