<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/chatbot_ex1/css/main.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<style>
.servingsDiv>button {
	color: red;
}

.btn btn-secondary {
	margin: 3px;
	padding: 5px;
}
</style>
<script type="text/javascript">
window.onload=function(){
	<%session.removeAttribute("servingsNum");%>
	<%session.removeAttribute("recipeName");%>
	<%session.removeAttribute("IngreNames");%>
	
}
document.onkeydown=f5Event;
function f5Event(){
	if(event.keyCode == 116 ||event.ctrlKey == true &&(event.keyCode==82)){//F5, Ctrl+F5,Ctrl+R
		alert("새로고침시 입력했던 값들이 초기화됩니다.");
		<%session.removeAttribute("servingsNum");%>
		<%session.removeAttribute("recipeName");%>
		<%session.removeAttribute("IngreNames");%>
		
		
	}

}
	function reset(){
		document.location.replace("chat.jsp");
		<%-- <%session.removeAttribute("servingsNum");%>
		<%session.removeAttribute("recipeName");%>
		<%session.removeAttribute("IngreNames");%>
		
		//채팅내용 전부 없애기 
		checkQStatud='0';
		$.ajax({
			type:"POST",
			url: "resetChat.do",
			data:{},
			success: function(data){
				$('#userSay1').val('');
				$(".direct-chat-messages").empty();
				$(".direct-chat-messages").append(data);
				$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
				
			}
		});  --%>
		
	}

	checkQStatus='0';
	function autoClosingAlert(seletor,delay){
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function(){alert.hide()},delay);
	}
	//#question1 > button
	function submitFunction(questionNum,buttonNum='0'){
		var question = String(questionNum);
		if(checkQStatus=='0'){
			if(question=='1' || question=='2'){//레시피명 입력하란거 or 재료명 입력하란 UI띄우기
			 	$.ajax({
					type:"POST",
					url: "recipeOrIngreQ.do",
					data:{
						questionNum: encodeURIComponent(question)
					},
					success: function(data){
						$('#userSay1').val('');
						$('#chat_bbody').append(data);
						$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
						checkQStatus=question;
					}
				}); 
				if(question=='1'||question=='2'){
					 var btnSize=document.getElementsByName("recipeBtn").length; 
						for(var i=0;i<btnSize;i++){
							var btn= document.getElementsByName("recipeBtn")[i];
							btn.disabled='disabled';
						}
				}
				if(question=='2'){
					var btnSize=document.getElementsByName("yesOrNo").length; 
					for(var i=0;i<btnSize;i++){
						var btn= document.getElementsByName("yesOrNo")[i];
						btn.disabled='disabled';
					}
				}
				
			}
			else if(question=='3'){//몇인분인지 클릭된 버튼 Session에공유+ 제외재료 수정 UI띄우기
				servingsNum=(String(buttonNum));
				var user_id= "<%=(String)session.getAttribute("user_id")%>"; 
				//user_id에는 "null"이 들어있음  java-html-javascript부분이 실행되기에 java부분이 변경되는 시점엔 user_id값이 null이니깐
				if(user_id != "null" && user_id != "비회원"){
					//여기서 오류범했음.ㅠㅠ...흑흑.. user_id에는 "null"이 들어있는데 
					//user_id != "null" || user_id != "비회원"이라했으니 .. "null" != "비회원"은 true니까 이 안으로 들어왔던것
					$.ajax({
						type:"POST",
						url: "selectServings.do",
						data:{
							servingsNum: encodeURIComponent(servingsNum)
						},
						success: function(data){
							$('#userSay1').val('');
							$('#chat_bbody').append(data);
							$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
							checkQStatus='4';
						}
					});	
				}else{//비회원 인경우에는 이쪽으루 들어옴.
					$.ajax({
						type:"POST",
						url: "sendRecipeInfoForNonMem.do",
						data:{
							servingsNum: encodeURIComponent(servingsNum)
						},
						success: function(data){
							
							$('#userSay1').val('');
							$('#chat_bbody').append(data);
							$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
							checkQStatus='0';
							recommendRecipe();
						}
					}); 
				}
				
				 
				var btnSize=document.getElementsByName("servingsBtn").length;
				for(var i=0;i<btnSize;i++){
					var btn= document.getElementsByName("servingsBtn")[i];
					btn.disabled='disabled';
				}
				 
			}
			else if(question=='4'){//몇인분인지 선택하란 UI만 띄우기
				var selectButton="아니";
				$.ajax({
					type:"POST",
					url: "showServings.do",
					data:{
						selectButton: encodeURIComponent(selectButton)
					},
					success: function(data){
						$('#userSay1').val('');
						$('#chat_bbody').append(data);
						$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
						
					}
				});
				var btnSize=document.getElementsByName("yesOrNo").length; 
				for(var i=0;i<btnSize;i++){
					var btn= document.getElementsByName("yesOrNo")[i];
					btn.disabled='disabled';
				}
			}
		}
		 
		 
		else if(checkQStatus=='1'){//레시피명 입력받은 것 session에 공유
			var userInputText=$('#userSay1').val();
			
			$.ajax({
				type:"POST",
				url: "sendRecipeName.do",
				dataType:"text",
				data:{
					userInputText: encodeURIComponent(userInputText)//여기에 사용자가 입력한 값이 들어있음
				},
				success: function(data){
					var str = decodeURIComponent(data.toString());//이케 해줘야 안깨짐 ㅠ.ㅠ
					$('#userSay1').val('');
					$('#chat_bbody').append(str);
					$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
					checkQStatus='0';
				}
			});
		}
		else if(checkQStatus=='2'){//재료명 입력받은 것 session에 공유
			var userInputText=$('#userSay1').val();
				$.ajax({
					type:"POST",
					url: "sendIngreName.do",
					data:{
						userInputText: encodeURIComponent(userInputText)
					},
					success: function(data){
						
						$('#userSay1').val('');
						$('#chat_bbody').append(data);
						$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
						checkQStatus='0';
					}
				}); 
		}
		else if(checkQStatus=='4'){//제외재료 설정
			var userInputText=$('#excludeIngre').val();
		
			if(question=='0'){
				//추가 클릭시
				$.ajax({
					type:"POST",
					url: "sendExcludeIngreName.do",
					data:{
						userInputText: encodeURIComponent(userInputText)
					},
					success: function(data){
						
							$('#direct_chat_reset').html(data)
							$('#userSay1').val('');
							$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
							/* checkQStatus='0' //원래 이건데 추가를 여러번 할 수도 있으니께 */
						checkQStatus='4';
					} //여기까지 success
				}); 
				
			}else if(question=='1'){
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
					url: "deleteExcludeIngreName.do",
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
				
			}else if(question=='2'){
				//넘어가기
/*하영*/				checkQStatus='0'
				var btnSize=document.getElementsByName("exceptBtn").length;
				for(var i=0;i<btnSize;i++){
					var btn= document.getElementsByName("exceptBtn")[i];
					btn.disabled='disabled';
				}
				
				recommendRecipe();//이 함수 만들면 됨
			}
		}

	}
	
	
	function recommendRecipe(){
		 $.ajax({
			type:"POST",
			url: "./recipeSearchList.do",
			
			success: function(data){
				
				$('#chat_bbody').append(data);
				$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
			}
			
		});   
		
		
		/* $('#userSay11').val(''); */
		
	}
	
	
	
	function importanceList(){
		 var importance = "importance"
		 $.ajax({
				type:"POST",
				url: "./recipeSearchList.do",
				data:{
					importance: encodeURIComponent(importance)
				},
				success: function(data){

					$('#recipeTable').html(data);

				}
				
			}); 
		 
	 }
	 
	 function accuracyList(){
		 var accuracy = "accuracy"
		 $.ajax({
				type:"POST",
				url: "./recipeSearchList.do",
				data:{
					accuracy: encodeURIComponent(accuracy)
				},
				success: function(data){

					$('#recipeTable').html(data);

				}
				
			}); 
		 
	 }
	
	
/* 	function addchat(str){
		$('#userSay1').val('');
		$('#chat_bbody').append(
				'<div id="question_text" class="direct-chat-msg right">'+
				'<div class="direct-chat-info clearfix" style="text-align: right; margin-right: 10px;">'+
				'<span class="direct-chat-name">Sarah Bullock</span>'+
				'<span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>'+
				'</div>'+
				'<img class="direct-chat-img" src="https://bootdey.com/img/Content/user_2.jpg" alt="Message User Image">'+
				'<div class="direct-chat-text">'+
				str+
				'</div>'+
				'</div>'+
				'<button id = "questionNum" onclick="submitFunction()" class="btn btn-secondary btn-sm" role="button" aria-pressed="true" style="text-align: right;" type="submit" value="1">레시피명</button>');
		$('#chat_bbody').scrollTop($('#chat_bbody')[0].scrollHeight);
	} */

</script>



<%@ include file="/topMenu2.jsp" %>
</head>

<body>
	<div class="bootstrap snippets">
		<div>
			<!-- DIRECT CHAT PRIMARY -->
			<div class="box box-primary direct-chat direct-chat-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Direct Chat</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Conversations are loaded here -->
					<div id="chat_bbody" class="direct-chat-messages"
						style="height: 500px;">
						<!-- Message. Default to the left -->
						<!-- 챗봇 -->
						<div class="direct-chat-msg">
							<div class="direct-chat-info clearfix">
								<span class="direct-chat-name pull-left">냠냠봇</span> <span
									class="direct-chat-timestamp pull-right"></span>
							</div>
							<!-- /.direct-chat-info -->
							<img class="direct-chat-img"
								src="https://bootdey.com/img/Content/user_1.jpg"
								alt="Message User Image">
							<!-- /.direct-chat-img -->
							<div class="direct-chat-text" style=" margin-right: 500px; max-width: 550px; width : 550px;">								안녕 나는 냠냠봇이야! 궁금한 레시피가 있으면 나에게 물어봐줘
							<div class="question1" style="margin: 10px;">
								<button id=aaa onclick="submitFunction('1')"
									class="btn btn-secondary btn-sm" role="button"
									aria-pressed="true" style="text-align:center;width:100px" type="submit"
									name='recipeBtn'>레시피명</button>
								<button onclick="submitFunction('2')"
									class="btn btn-secondary btn-sm " role="button"
									aria-pressed="true" style="text-align:center;width:100px" type="submit"
									name='recipeBtn'>재료명</button>
							</div>
							</div>
							

						</div>
						<!-- 여기까지 챗봇 -->

						<!-- /.direct-chat-msg -->

						<!-- Message to the right -->
						<!-- 여기까지 -->
					</div>
					<!--/.direct-chat-messages-->

				</div>

				<div class="box-footer">
					<div class="input-group">
						<textarea type="text" name="message"
							placeholder="Type Message ..." id="userSay1" class="form-control"
							onkeypress="if(event.keyCode == 13){ submitFunction()}"></textarea>
						<button id="execute" type="submit"
							class="btn btn-secondary btn-flat" onclick="submitFunction()">Send</button>
					</div>

				</div>
				<!-- /.box-footer-->
			</div>
			<!--/.direct-chat -->
		</div>
	</div>

</body>
</html>