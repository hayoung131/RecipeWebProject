package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.AnswerFrame;

public class RecipeOrIngreQAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		
		String questionNum = request.getParameter("questionNum");//request영역에 오네.. ajax로 보내도.. 
		//1이 넘어오면 레시피명으로 검색하겠단거, 2가 넘어오면 재료명으로 검색하겠단거
		System.out.println("questionNum : "+questionNum+ "\n");
		String answer="";
		
		//bean 객체에 담기 
//		session.setAttribute("user_id", "비회원");
//		session.setAttribute("user_id","hayoung131");//이부분 나중에 삭제하기
		String user_id=(String)session.getAttribute("user_id");
		if(user_id=="null"||user_id==null) {
			user_id="비회원";
			session.setAttribute("user_id", "비회원");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("a hh:mm");
		Calendar cal=Calendar.getInstance();
		String currentTime="    "+sdf.format(cal.getTime());
		AnswerFrame firstAnswer= new AnswerFrame();
		
			//공통되는 부분 vo에 셋팅
		firstAnswer.setUserName(user_id);
		firstAnswer.setCurrentTime(currentTime);
		if(questionNum.equals("1")) {
			firstAnswer.setSelectHSentence("레시피명");
			firstAnswer.setSelectBSentence("어떤 요리가 궁금해? 레시피 명을 입력해 줘!");
			System.out.println("HSentence : "+firstAnswer.getSelectHSentence());
			System.out.println("BSentence : "+firstAnswer.getSelectBSentence());
			System.out.println(answer);
			answer=firstAnswer.getCompleteSentence();
		}else if(questionNum.contentEquals("2")){
			firstAnswer.setSelectHSentence("재료명");
			firstAnswer.setSelectBSentence("가지고 있는 재료를 입력하면 만들 수 있는 요리를 추천해줄게 ex)새우,칠리소스");
			answer=firstAnswer.getCompleteSentence();
		}
	
		return answer;
	}

}
