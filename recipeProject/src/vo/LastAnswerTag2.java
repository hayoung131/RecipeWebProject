package vo;

public class LastAnswerTag2 {
	
	private String stringTag1 = "<div class='direct-chat-msg' id = 'chatbotSay'>"+
				 "<div class='direct-chat-info clearfix'>"+
				 "<span class='direct-chat-name pull-left'>냠냠봇</span>"+
					    "<span class='direct-chat-timestamp pull-right'></span>"+
					  "</div>"+
					  
					  "<img class='direct-chat-img' src='https://bootdey.com/img/Content/user_1.jpg' alt='Message User Image'>"+
					  "<div class='direct-chat-text' style=' height:440px; margin-right: 500px; width: 550px;'>"+
					  	 "<div style='height:420px; border-radius: 5px; text-align: center; padding-top: 20px; margin-top: 10px;'>  <p style='margin-bottom:20px;'>검색한 레시피들이야! </p>"+ 	 
					  	
					  	  "<div style='height:300px; overflow: auto; display:inline-block; width:530px;' id = 'recipeTable'> ";
	
	private String stringTag2 =  "<table class='table table-sm table-hover' style=' margin-left: auto; margin-right: auto; text-align:center;'>"+
								  	"<colgroup>"+
										"<col width='80%'>"+
										"</colgroup>"+
										"<tr class = 'text-dark' ><th>제목</th></tr>";
					  	
	
							//여기부터 for문돌림**
	private String stringTag3 = " <tr>"+
	  	 						"<td> "+
	  	 						/*"<button type='button' class='btn btn-outline-info' style = 'width: 380px;'>"+*/
	  	 						"<a style = 'width: 400px; 'href='searchRecipeContent.do?num=";
							//이사이에 레시피 아이디 넣어야함!!
	private String stringTag4 = "'target = '_blank' class='btn btn-outline-info'>";
									//이사이에 제목 들어가면 됨ㅁㅁㅁㅁㅁㅁㅁ
	private String stringTag5 =	"</a>";
									/*"</button>"+*/
							  	 	
								//이사이에 일치도 들어가는거임
	private String stringTag6 = "</td>"+
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
