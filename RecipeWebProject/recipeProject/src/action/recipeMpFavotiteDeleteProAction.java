package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeMpFavotiteDeleteProService;
import vo.ActionForward;

public class recipeMpFavotiteDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		String [] num_arr = request.getParameterValues("num");
		
		int [] num= new int[num_arr.length];
		
		boolean removeSuccess = false;
		
		for (int i = 0; i < num_arr.length; i++) {
			System.out.println(num_arr[i]);
			num[i] = Integer.parseInt(num_arr[i]);
			
		}
		
		
		String pageNum = request.getParameter("pageNum");
		
		RecipeMpFavotiteDeleteProService recipeMpFavotiteDeleteProService
		= new RecipeMpFavotiteDeleteProService();
		
		for (int i =0; i<num.length;i++) {
		removeSuccess = recipeMpFavotiteDeleteProService.removeArticle(num[i]);
		}
		ActionForward forward = null;
		if(removeSuccess){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setUrl("recipeMpFavoriteList.bo");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
