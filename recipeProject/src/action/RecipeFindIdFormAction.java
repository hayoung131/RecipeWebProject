package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeFindIdFormService;
import vo.ActionForward;
import vo.FindIdInfo;

public class RecipeFindIdFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		FindIdInfo findIdInfo = new FindIdInfo();
		findIdInfo.setName(request.getParameter("name"));
		findIdInfo.setPhoneNumber(request.getParameter("phoneNumber"));
		
		RecipeFindIdFormService recipeFindIdFormService=new RecipeFindIdFormService();
		
		String matchedId=recipeFindIdFormService.findId(findIdInfo);
		ActionForward forward=null;
		
		if(matchedId != "0") {
			//해당 아이디가 존재한단 의미
			forward=new ActionForward();
			forward.setUrl("/findIdView.jsp");
			request.setAttribute("matchedId", matchedId);
		}
		else {
			//경고창 띄우고, 아이디찾기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록된 아이디가 없습니다')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		
		return forward;
	}

}
