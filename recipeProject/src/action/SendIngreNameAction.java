package action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ChatSelectServingsService;
import vo.AnswerFrame;

public class SendIngreNameAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();

		String userInputText = URLDecoder.decode(request.getParameter("userInputText"), "UTF-8");//이케 해줘야 안깨지고 한글 받을 수 있음.
		userInputText=userInputText.replaceAll(" ","");
		String[] IngreNames=userInputText.split(",");
		String answer = "";
		for (String n :IngreNames) {
			System.out.println(" 배열:"+n);
		}
		
		String addBSentence = "몇 인분 요리할꺼야?"+
			"<div class='btn-toolbar' role='toolbar' aria-label='Toolbar with button groups' style='margin:10px;'>"+
		    "<div class='btn-group mr-2' role='group' aria-label='First group' id='servingsDiv'>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,1)' name='servingsBtn'>1</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,2)' name='servingsBtn'>2</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,3)' name='servingsBtn'>3</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,4)' name='servingsBtn'>4</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,5)' name='servingsBtn'>5</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,6)' name='servingsBtn'>6</button>"+
		    "</div></div>";
		session.setAttribute("IngreNames", IngreNames);//재료 배열을 넘김
		
		String user_id=(String)session.getAttribute("user_id");
		if(user_id=="null"||user_id==null) {
			user_id="비회원";
		}

		SimpleDateFormat sdf=new SimpleDateFormat("a hh:mm");
		Calendar cal=Calendar.getInstance();
		String currentTime=sdf.format(cal.getTime());
		
		AnswerFrame exceptForm = new AnswerFrame();
		exceptForm.setUserName(user_id);
		exceptForm.setCurrentTime(currentTime);
		exceptForm.setSelectHSentence(userInputText);
		exceptForm.setSelectBSentence(addBSentence);
		answer = exceptForm.getCompleteSentence();
		return answer;
	}

}
