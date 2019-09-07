package action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.AnswerFrame;

public class ChatShowServingsAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();

		String userAnswer= URLDecoder.decode(request.getParameter("selectButton"), "UTF-8");//이케 해줘야 안깨지고 한글 받을 수 있음.

		String answer = "";
		
		String addBSentence = "몇 인분 요리할꺼야?"+
			"<div class='btn-toolbar' role='toolbar' aria-label='Toolbar with button groups' style='margin:10px;'>"+
		    "<div class='btn-group mr-2' role='group' aria-label='First group' id='servingsDiv'>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,1)'>1</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,2)'>2</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,3)'>3</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,4)'>4</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,5)'>5</button>"+
		    "<button type='button' class='btn btn-secondary' onclick='submitFunction(3,6)'>6</button>"+
		    "</div></div>";
		String user_id=(String)session.getAttribute("user_id");
		if(user_id=="null"||user_id==null) {
			user_id="비회원";
		}

		SimpleDateFormat sdf=new SimpleDateFormat("a hh:mm");
		Calendar cal=Calendar.getInstance();
		String currentTime=sdf.format(cal.getTime());
		
		AnswerFrame exceptForm = new AnswerFrame();
		exceptForm.setCurrentTime(currentTime);
		exceptForm.setUserName(user_id);
		exceptForm.setSelectHSentence(userAnswer);
		exceptForm.setSelectBSentence(addBSentence);
		answer = exceptForm.getCompleteSentence();
		return answer;
	}

}
