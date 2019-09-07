package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ChatRecipeAddFavoritesService;
import svc.RecipeSearchInfoService;
import vo.ActionForward;

public class ChatRecipeAddFavoritesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session=request.getSession(); //아이디 값을 불러옴.
		String id = (String)session.getAttribute("user_id");
		
		System.out.println(id+"님의 즐겨찾기에 추가");
		
		String check = request.getParameter("isBookmark");
		String isBookmark = "false";
		
		ChatRecipeAddFavoritesService chatRecipeAddFavoritesService
	    = new ChatRecipeAddFavoritesService();
		
		boolean changeBookmark=false;
		
		ActionForward forward = new ActionForward();
		
		if (check.equals("true")) { //트루이면 즐겨찾기 해제함
			isBookmark = "false";
			changeBookmark = chatRecipeAddFavoritesService.deleteBookmark(num,id);
		}else if (check.equals("false")) {
			isBookmark = "true"; //펄스면 즐겨찾기함. 트루로 바꾸면서 ㅇㅇ
			changeBookmark = chatRecipeAddFavoritesService.addBookmark(num,id);
		}

		
			request.setAttribute("num", num);
			request.setAttribute("bookmarkAlert", isBookmark);
			
			response.setContentType("text/html; charset=UTF-8");
			
		    forward.setUrl("/searchRecipeContent.do");
		
		return forward;
	}

}
