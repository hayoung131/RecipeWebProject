package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeFindPwdViewService;
import vo.ActionForward;
import vo.NewPwd;

public class RecipeFindPwdViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NewPwd newPwd=new NewPwd();
		newPwd.setMatchedId(request.getParameter("matchedId"));
		newPwd.setNewPwd(request.getParameter("newPwd"));
		
		RecipeFindPwdViewService recipeFindPwdViewService=new RecipeFindPwdViewService();
		
		int saveSuccess=recipeFindPwdViewService.savePwd(newPwd);
		ActionForward forward=null;
		
		if(saveSuccess>0) {
			forward=new ActionForward();
			forward.setUrl("/loginForm.jsp");
			/* forward.setRedirect(true); */
		}else {
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('변경 실패')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		
		
		return forward;
	}

}
