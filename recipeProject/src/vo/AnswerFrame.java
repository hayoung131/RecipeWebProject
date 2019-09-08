package vo;

public class AnswerFrame {
	private String humanSentence1=
			"<div id='question_text' class='direct-chat-msg right'>"+
			"<div class='direct-chat-info clearfix' style='text-align: right; margin-right: 10px;'>"+
			"<span class='direct-chat-name' style='margin-right:5px'>";
	private String currentTime="";
	private String humanSentence2=
			"</span>"+
			"<span class='direct-chat-timestamp pull-left'>";
	private String userName;
	private String humanSentence3=
			"</span>"+
			"</div>"+
			"<img class='direct-chat-img' src='https://bootdey.com/img/Content/user_2.jpg' alt='Message User Image'>"+
			"<div class='direct-chat-text' style = 'float:right; margin-right: 10px; max-width:360px; text-align : right;'>";
	private String selectHSentence;		
	private String humanSentence4="</div>"+"</div>";
	
	private String botSentence1=
			"<div class='direct-chat-msg'>"+
		    "<div class='direct-chat-info clearfix'>"+
		      "<span class='direct-chat-name pull-left'>냠냠봇</span>"+
		      "<span class='direct-chat-timestamp pull-right'></span>"+
		    "</div>"+
		    "<img class='direct-chat-img' src='https://bootdey.com/img/Content/user_1.jpg' alt='Message User Image'>"+
		    "<div class='direct-chat-text' style=' margin-right: 500px; max-width: 550px;  width : 550px;'>";
	private String selectBSentence;
	private String addBotButton="";
    private String botSentence2= "</div></div>";
    
 	private String completeSentence;
	public String getCompleteSentence() {
		completeSentence=
				humanSentence1+currentTime+humanSentence2+userName+humanSentence3+selectHSentence+humanSentence4
				+botSentence1+selectBSentence+addBotButton+botSentence2;
		System.out.println("dfasdf"+completeSentence);
		return completeSentence;
	}


	
	public String getSelectHSentence() {
		return selectHSentence;
	}


	public void setSelectHSentence(String selectHSentence) {
		this.selectHSentence = selectHSentence;
	}


	public String getSelectBSentence() {
		return selectBSentence;
	}


	public void setSelectBSentence(String selectBSentence) {
		this.selectBSentence = selectBSentence;
	}


	public String getAddBotButton() {
		return addBotButton;
	}


	public void setAddBotButton(String addBotButton) {
		this.addBotButton += addBotButton;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getCurrentTime() {
		return currentTime;
	}



	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	
}
