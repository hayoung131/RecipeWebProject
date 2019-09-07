package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipeSearchInfoService;
import vo.ActionForward;
import vo.Ingredient;
import vo.RecipeChat;

public class RecipeSearchInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String s = (String)session.getAttribute("servingsNum");
		String user_id = (String)session.getAttribute("user_id");
		int serving = Integer.parseInt(s);
		int num = Integer.parseInt(request.getParameter("num"));
		String bookmarkAlert = null;
		bookmarkAlert = (String)request.getAttribute("bookmarkAlert");
		String check = null;
		
		if(bookmarkAlert != null) {
			//null이 아니라면 즐겨찾기 추가 or 삭제 버튼을 눌렀다는 뜻임!
			if(bookmarkAlert.equals("true") ) {
				//트루이ㅁ녀 즐찾했다는 뜻
				check = "1";
				request.setAttribute("check", check);
				num = (int)request.getAttribute("num");
				
			}else if(bookmarkAlert.equals("false")) {
				check = "2";
				request.setAttribute("check", check);
				num = (int)request.getAttribute("num");
			}
		}
		
	/*		   String check_num = "0";*/
		 //아이디 값을 불러옴.
		String id = (String)session.getAttribute("user_id");
	    List<Ingredient> ingredientList = null;
	    String [] cooking_steps = null;
	    
	    System.out.println("넘겨받은 아이디 : "+id);
	    System.out.println("즐찾여부 : "+bookmarkAlert);
	    System.out.println("몇인분? : "+serving);
	    
	    RecipeSearchInfoService recipeRankingContentService
	    = new RecipeSearchInfoService();
	   
	    RecipeChat information = recipeRankingContentService.getArticle(num,id);
	    ingredientList = recipeRankingContentService.getIngredientList(num);
	    System.out.println("잘 받았니"+information.getHit_standard());
	    
	    cooking_steps = new String[recipeRankingContentService.getCooking_steps(num).length];
	    cooking_steps = recipeRankingContentService.getCooking_steps(num);
	    System.out.println("잘 받았니"+information.getHit_standard());
	    
	    request.setAttribute("information", information);
	    request.setAttribute("ingredientList", ingredientList);
	    request.setAttribute("cooking_steps", cooking_steps);
	    request.setAttribute("serving", serving);
	    request.setAttribute("user_id", user_id);
	    
/*		   request.setAttribute("check_num", check_num);*/
	   System.out.println("잘 받았니"+information.getHit_standard());
	   System.out.println("즐찾 됐니????"+information.isBookmark());
	    
	    ActionForward forward = new ActionForward();
	    forward.setUrl("/chatSearchRecipeInfo.jsp");
		
	    return forward;
	}

}
