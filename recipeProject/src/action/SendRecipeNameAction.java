package action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.AnswerFrame;

public class SendRecipeNameAction implements Action2 {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();

		String userInputText = URLDecoder.decode(request.getParameter("userInputText"), "UTF-8");//이케 해줘야 안깨지고 한글 받을 수 있음.

		userInputText=userInputText.replace(" ","");
		userInputText=userInputText.replaceAll(",","");
		String[] IngreNames=userInputText.split(",");
		String answer = "";
		System.out.println("userInputText : " + userInputText + "\n");
		String addBotButton = 
		  "<div class='question1' style='margin: 10px;'>"+
		  "<button onclick='submitFunction(2)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"+
		  "style='text-align:center;margin:2px;width:100px;' type='submit' name='yesOrNo'>응</button>"+
		  "<button onclick='submitFunction(4)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"+
		  "style='text-align:center;margin:2px;width:100px;' type='submit' name='yesOrNo'>아니</button>"+ "</div>";
		AnswerFrame IngreY_N = new AnswerFrame();
		
		String user_id=(String)session.getAttribute("user_id");
		if(user_id=="null"||user_id==null) {
			user_id="비회원";
		}

		SimpleDateFormat sdf=new SimpleDateFormat("a hh:mm");
		Calendar cal=Calendar.getInstance();
		String currentTime=sdf.format(cal.getTime());

		IngreY_N.setUserName(user_id);
		IngreY_N.setCurrentTime(currentTime);
		IngreY_N.setSelectHSentence(userInputText);
		IngreY_N.setSelectBSentence("가지고 있는 재료도 입력할래?");
		IngreY_N.setAddBotButton(addBotButton);
		answer = IngreY_N.getCompleteSentence();
		
		session.setAttribute("recipeName", userInputText);
		return answer;
	}

}
