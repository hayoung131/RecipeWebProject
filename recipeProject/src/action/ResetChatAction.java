package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetChatAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answer="<div class=\"direct-chat-msg\">\r\n" + 
				"							<div class=\"direct-chat-info clearfix\">\r\n" + 
				"								<span class=\"direct-chat-name pull-left\">냠냠봇</span> <span\r\n" + 
				"									class=\"direct-chat-timestamp pull-right\"></span>\r\n" + 
				"							</div>\r\n" + 
				"							<!-- /.direct-chat-info -->\r\n" + 
				"							<img class=\"direct-chat-img\"\r\n" + 
				"								src=\"https://bootdey.com/img/Content/user_1.jpg\"\r\n" + 
				"								alt=\"Message User Image\">\r\n" + 
				"							<!-- /.direct-chat-img -->\r\n" + 
				"							<div class=\"direct-chat-text\" style=\" margin-right: 500px; min-width: 600px;\">\r\n" + 
				"								안녕 나는 냠냠봇이야! 궁금한 레시피가 있으면 나에게 물어봐줘\r\n" + 
				"							<div class=\"question1\" style=\"margin: 10px;\">\r\n" + 
				"								<button id=aaa onclick=\"submitFunction('1')\"\r\n" + 
				"									class=\"btn btn-secondary btn-sm\" role=\"button\"\r\n" + 
				"									aria-pressed=\"true\" style=\"text-align:center;width:100px\" type=\"submit\"\r\n" + 
				"									name='recipeBtn'>레시피명</button>\r\n" + 
				"								<button onclick=\"submitFunction('2')\"\r\n" + 
				"									class=\"btn btn-secondary btn-sm \" role=\"button\"\r\n" + 
				"									aria-pressed=\"true\" style=\"text-align:center;width:100px\" type=\"submit\"\r\n" + 
				"									name='recipeBtn'>재료명</button>\r\n" + 
				"							</div>\r\n" + 
				"							</div>\r\n" + 
				"						</div>";
		return answer;
		
	}

}
