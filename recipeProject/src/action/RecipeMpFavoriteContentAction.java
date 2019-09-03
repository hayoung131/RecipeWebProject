package action;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecipeDAO;
import svc.RecipeMpFavoriteContentService;
import svc.RecipeRankingContentService;
import svc.RecipeRankingListService;
import vo.ActionForward;
import vo.Ingredient;
import vo.Recipe;

public class RecipeMpFavoriteContentAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	 int num = Integer.parseInt(request.getParameter("num"));
	 HttpSession session=request.getSession();
//	 session.setAttribute("user_id","dpwls0947");//이부분 나중에 삭제하기
	   String id=(String)session.getAttribute("user_id");
	   String pageNum = request.getParameter("pageNum");
//	   String check_num = "0";
	   List<Ingredient> ingredientList = null;
	   String [] cooking_steps = null;

	   System.out.println("넘겨받은 아이디 : "+id);
	   
	   RecipeMpFavoriteContentService recipeMpFavoriteContentService
	   = new RecipeMpFavoriteContentService();
	   
	   //랭킹리스트 상세페이지랑 같은걸 가져오기때매 있는거 그대로 씀.
	   Recipe information = recipeMpFavoriteContentService.getArticle(num,id);
	   ingredientList = recipeMpFavoriteContentService.getIngredientList(num);
	   
	   cooking_steps = new String[recipeMpFavoriteContentService.getCooking_steps(num).length]; //조리단계 개수 가져오기
	   cooking_steps = recipeMpFavoriteContentService.getCooking_steps(num); //조리단계 데이터 가져오기
	   
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("information", information);
	   request.setAttribute("ingredientList", ingredientList);
	   request.setAttribute("cooking_steps", cooking_steps);
//	   request.setAttribute("check_num", check_num);
	   
	   ActionForward forward = new ActionForward();
	   forward.setUrl("/mpFavoriteRecipeInfo.jsp");
	return forward;
}

}
