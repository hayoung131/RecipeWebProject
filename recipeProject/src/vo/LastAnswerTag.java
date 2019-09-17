package vo;

public class LastAnswerTag {
	
	private String stringTag1 = "<div class='direct-chat-msg' id = 'chatbotSay'>"+
				 "<div class='direct-chat-info clearfix'>"+
				 "<span class='direct-chat-name pull-left'>냠냠봇</span>"+
					    "<span class='direct-chat-timestamp pull-right'></span>"+
					  "</div>"+
					  
					  "<img class='direct-chat-img' src='https://bootdey.com/img/Content/user_1.jpg' alt='Message User Image'>"+
					  "<div class='direct-chat-text' style=' height:440px; margin-right: 500px; max-width: 550px; width : 550px;'>"+
					  	 "<div style='height:420px; border-radius: 5px; text-align: center; padding-top: 20px; margin-top: 10px;'>"+ 	 
					  	 
						"<div class='btn-group btn-group-toggle' data-toggle='buttons' style='float: center; margin-bottom:10px;'>"+
					  	  "<label class='btn btn-info active' id = 'accuracy' value = 'accuracy' onclick = 'accuracyList();' style = 'width:150px;'>"+
					  	    "<input type='radio' name='options' id='option1' autocomplete='off' checked > 재료일치순"+
					  	  "</label>"+
					  	  "<label class='btn btn-info' style = 'width:150px;' id = 'importance' value = 'importance'  onclick = 'importanceList();' >"+
					  	    "<input type='radio' name='options' id='option3' autocomplete='off'> 추천순"+
					  	  "</label>"+
					  	"</div>"+
					  	 
					  	 
					  	  "<div style='height:320px; overflow: auto; display:inline-block; width:530px;' id = 'recipeTable'>";
	
	private String stringTag2 =  "<table class='table table-sm table-hover' style=' margin-left: auto; margin-right: auto; text-align:center;'>"+
								  	"<colgroup>"+
										"<col width='80%'>"+
										"<col width='20%'>"+
									"</colgroup>"+
									"<tr class = 'text-dark' ><th>제목</th><th>필요재료수</th></tr>";
					  	
	
							//여기부터 for문돌림**
	private String stringTag3 = " <tr>"+
	  	 						"<td> "+
	  	 						/*"<button type='button' class='btn btn-outline-info' style = 'width: 380px;'>"+*/
	  	 						"<a style = 'width: 380px; 'href='searchRecipeContent.do?num=";
							//이사이에 레시피 아이디 넣어야함!!
	private String stringTag4 = "'target = '_blank' class='btn btn-outline-info'>";
									//이사이에 제목 들어가면 됨ㅁㅁㅁㅁㅁㅁㅁ
	private String stringTag5 =	"</a>"+
									/*"</button>"+*/
							  	 	"</td>"+
							  	 	"<td style='padding-top: 15px;'><b style='color: #5D5D5D;'>";
								//이사이에 일치도 들어가는거임
	private String stringTag6 = "</b></td>"+
							  	 "</tr>";
							//여기까지 for문**
	
	
	private String stringTag7 =	 "</table>";
	private String stringTag8 = "</div>"+
							  	 "</div>"+
							
								  "</div>"+
								  "<div class='options1' style='margin: 10px; margin-left: 50px;'>"+
									"<input class='btn btn-secondary btn-sm ' role='button' aria-pressed='true' "+
									"style='text-align: right;' type='submit' value='처음으로' onclick='reset()'>"+
								"</div>"+
								"</div>";


	public String getStringTag8() {
		return stringTag8;
	}


	public String getStringTag6() {
		return stringTag6;
	}


	public String getStringTag7() {
		return stringTag7;
	}


	public String getStringTag1() {
		return stringTag1;
	}


	public String getStringTag2() {
		return stringTag2;
	}


	public String getStringTag3() {
		return stringTag3;
	}


	public String getStringTag4() {
		return stringTag4;
	}


	public String getStringTag5() {
		return stringTag5;
	}
		
}
