package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class RecipeLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();
		ActionForward forward= new ActionForward();

		session.invalidate();
		forward.setUrl("/logout.jsp");
		forward.setRedirect(false);
		
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html lang=\"ko\">");
		content.append("	<head>");
		content.append("		<meta charset=\"UTF-8\">");
		content.append("		<script type=\"text/javascript\">");
		content.append("		</script>");
		content.append("	</head>");
		content.append("	<body>");
		content.append("		<script>");
		content.append("			alert(\"로그아웃 되었습니다.\");");
		content.append("		</script>");
		content.append("	</body>");
		content.append("</html>");
		
		
		return forward;
	}
}
