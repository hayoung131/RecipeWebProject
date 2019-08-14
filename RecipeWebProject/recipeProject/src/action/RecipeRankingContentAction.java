package action;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RecipeDAO;
import svc.RecipeRankingContentService;
import svc.RecipeRankingListService;
import vo.ActionForward;
import vo.Ingredient;
import vo.Recipe;

public class RecipeRankingContentAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	 int num = Integer.parseInt(request.getParameter("num"));
	   String pageNum = request.getParameter("pageNum");
	   String check_num = "0";
	   List<Ingredient> ingredientList = null;

	   RecipeRankingContentService recipeRankingContentService
	   = new RecipeRankingContentService();
	   
	   Recipe information = recipeRankingContentService.getArticle(num);
	   ingredientList = recipeRankingContentService.getIngredientList(num);
	   
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("information", information);
	   request.setAttribute("ingredientList", ingredientList);
	   request.setAttribute("check_num", check_num);
	   
	   ActionForward forward = new ActionForward();
	   forward.setUrl("/recipeInfo.jsp");
	return forward;
}

}
