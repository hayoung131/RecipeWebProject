package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeAddFavoritesService;
import svc.RecipeRankingContentService;
import vo.ActionForward;
import vo.Ingredient;
import vo.Recipe;

public class RecipeAddFavoritesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		String img = null;
		String status= null;
		String message = null;
		String change_status= null;
		List<Ingredient> ingredientList = null;
		Recipe information = new Recipe();
		String check_num = "1";
		boolean registSuccess = false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		status = request.getParameter("status");
		String pageNum = request.getParameter("pageNum");
	   

		RecipeAddFavoritesService recipeAddFavoritesService 
		= new RecipeAddFavoritesService();
	   
		ingredientList = recipeAddFavoritesService.getIngredientList(num);
	   	
		if(status.equals("off")) {
			
			img = "'images/star2.png'";
			change_status = "on";
			message = "즐겨찾기에 추가되었습니다.";
			
			registSuccess = recipeAddFavoritesService.UpdateOn(num,img,change_status);
			
			
		}else if(status.equals("on")) {
			
			img = "'images/star1.png'";
			change_status = "off";
			message = "즐겨찾기가 해제되었습니다.";
			
			registSuccess = recipeAddFavoritesService.UpdateOn(num,img,change_status);
		}
		
		ActionForward forward = null;
		
		if(registSuccess) {
		information=recipeAddFavoritesService.SelectRecipeInfo(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("information", information);
		request.setAttribute("ingredientList", ingredientList);
		request.setAttribute("message", message);
		request.setAttribute("check_num", check_num);
		
		response.setContentType("text/html; charset=UTF-8");

		forward = new ActionForward();
		
		forward.setUrl("/recipeInfo.jsp");
		
		}
		
		return forward;
	}

}
