package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipehateIngreListService;
import vo.ActionForward;

public class RecipehateIngreListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		RecipehateIngreListService recipehateIngreListService =
				new RecipehateIngreListService();
		
		String [] hateIngreList = recipehateIngreListService.selecthateIngre(user_id);
		
		System.out.println("리스트 길이 :" +hateIngreList.length );
		request.setAttribute("hateIngreList", hateIngreList);
		ActionForward forward =  new ActionForward();
		forward.setUrl("/mpHateIngreList.jsp");
		 
		return forward;
	}

}
