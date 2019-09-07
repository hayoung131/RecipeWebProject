package action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRecipeInfoForNonMemAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String answer = "";
		String servingsNum=request.getParameter("servingsNum");
		session.setAttribute("servingsNum", servingsNum);//몇인분인지 정보넘김

		String user_id=(String)session.getAttribute("user_id");
		if(user_id=="null"||user_id==null) {
			user_id="비회원";
		}

		SimpleDateFormat sdf=new SimpleDateFormat("a hh:mm");
		Calendar cal=Calendar.getInstance();
		String currentTime=sdf.format(cal.getTime());
		answer="<div id='question_text' class='direct-chat-msg right'>"+
				"<div class='direct-chat-info clearfix' style='text-align: right; margin-right: 10px;'>"+
				"<span class='direct-chat-name' style='margin-right:5px'>"+
				currentTime+
				"</span>"+
				"<span class='direct-chat-timestamp pull-left'>"+
				user_id+
				"</span>"+
				"</div>"+
				"<img class='direct-chat-img' src='https://bootdey.com/img/Content/user_2.jpg' alt='Message User Image'>"+
				"<div class='direct-chat-text' style = 'float:right; margin-right: 10px; max-width:360px; text-align : right;'>"+
				servingsNum+"인분"+"</div>"+"</div>";
		return answer;		
	}

}
