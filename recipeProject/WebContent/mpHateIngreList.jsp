<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="/topMenu.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<% String [] hateIngreList = (String [])request.getAttribute("hateIngreList"); %>
<script type="text/javascript">

function submitFunction(questionNum){
	var question = String(questionNum);
	if(question=='1'){
		//삭제클릭시
		var idxVal = "";
		var chList = [];
		var checkNum = 0;
			$(".checkIngre").each(function() {
				
				if($(this).is(":checked")){
					idxVal = $(this).attr("idx");
					chList.push(idxVal);
					checkNum++
				}
			});

		$.ajax({
			type:"POST",
			url: "mpDeleteExcludeIngre.bo",
			data:{"idx":chList,
				"checkNum":checkNum},
			success: function(data){
				
					$('#direct_chat_reset').html(data)
					$('#userSay1').val('');
					$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
				
					/* checkQStatus='0' //원래 이건데 추가를 여러번 할 수도 있으니께 */
				checkQStatus='4';
			} //여기까지 success
		}); 
		
	}
}

</script>
<body>
<center>
<div style="text-align:center; margin: 20px; margin-top: 50px; ">
	<p class="font-weight-bold text-secondary" style="font-size: 19pt; text-align: center;">제외 재료 리스트</p>
	<form method="post" name="writeform" action="mpDeleteExcludeIngre.bo">
	<center>
	
	<div style="text-align:center;  overflow: auto; width: 820px; height: 250px; ">
	
	<table class="table" style =" width: 800px;">
		<% for(int i = 0; i<hateIngreList.length ; i++){ %>
		<tr>
			<td>
			  <button class="btn btn-outline-info" style="font-size: 14pt; width:240px;" > <%=hateIngreList[i] %>
			  	<span class="badge" style="font-size: 11pt; float: right;">
			    <div class="btn-group-toggle " data-toggle="buttons" >
			  		<label class="btn btn-sm btn-outline-warning " style="height: 23px;">
			    	<input class="checkIngre" type="checkbox" name="hateIngre" value="<%=hateIngreList[i] %>">&nbsp;
			    	</label>
		    	</div>
			    </span>
			  </button>
			</td>
			<% i++; %>
			<% if(i<hateIngreList.length){ %>
			<td>
			  <button class="btn btn-outline-info" style="font-size: 14pt; width:240px;" ><%=hateIngreList[i] %>
			  	<span class="badge" style="font-size: 11pt; float: right;">
			    <div class="btn-group-toggle " data-toggle="buttons" >
			  		<label class="btn btn-sm btn-outline-warning " style="height: 23px;">
			    	<input class="checkIngre"  type="checkbox" name="hateIngre" value="<%=hateIngreList[i] %>">&nbsp;
			    	</label>
		    	</div>
			    </span>
			  </button>
			</td>
			<% i++; %>
			<% if(i<hateIngreList.length){ %>
			<td>
			  <button class="btn btn-outline-info" style="font-size: 14pt; width:240px;" ><%=hateIngreList[i] %>
			  	<span class="badge" style="font-size: 11pt; float: right;">
			    <div class="btn-group-toggle " data-toggle="buttons" >
			  		<label class="btn btn-sm btn-outline-warning " style="height: 23px;">
			    	<input class="checkIngre"  type="checkbox" name="hateIngre" value="<%=hateIngreList[i] %>">&nbsp;
			    	</label>
		    	</div>
			    </span>
			  </button>
			</td>
			<%} %>
			<%} %>
		</tr>
		<%} %>
		
		
	</table>
	</div>
	</center>
	<div style="margin-top: 40px;">
	<input id = 'excludeIngre' name='addIngre' type='text' placeholder='제외할 재료를 이곳에 입력해주세요' style='margin:8px; width:260px;padding-bottom:3px;'>
				<button class='btn btn-secondary btn-sm' role='button' aria-pressed='true'
				style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>추가</button>
				<button class='btn btn-secondary btn-sm' role='button' aria-pressed='true'
				style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>삭제</button>
	</div>
	</form>
</div>
</center>
</body>
</html>