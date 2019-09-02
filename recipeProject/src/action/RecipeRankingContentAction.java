package action;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecipeDAO;
import svc.RecipeRankingContentService;
import svc.RecipeRankingListService;
//import svc.RecipeSearchInfoService;
import vo.ActionForward;
import vo.Ingredient;
import vo.Recipe;
//import vo.RecipeChat;

public class RecipeRankingContentAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
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
		HttpSession session=request.getSession(); //아이디 값을 불러옴.
		String id = (String)session.getAttribute("user_id");
	    List<Ingredient> ingredientList = null;
	    String [] cooking_steps = null;
	    
	    System.out.println("넘겨받은 아이디 : "+id);
	    System.out.println("즐겨찾기 추가여부 : "+bookmarkAlert);
	    
	    RecipeRankingContentService recipeRankingContentService
	    = new RecipeRankingContentService();
	   /////////////////////////////////////////여기부터하세여~~!!!///////////////////
	    Recipe information = recipeRankingContentService.getArticle(num,id);
	    ingredientList = recipeRankingContentService.getIngredientList(num);
	    System.out.println("잘 받았니"+information.getHit_standard());
	    
	    cooking_steps = new String[recipeRankingContentService.getCooking_steps(num).length]; //조리단계 개수 가져오기
	    cooking_steps = recipeRankingContentService.getCooking_steps(num); //조리단계 데이터 가져오기
	    System.out.println("잘 받았니"+information.getHit_standard());
	    
	    request.setAttribute("information", information);
	    request.setAttribute("ingredientList", ingredientList);
	    request.setAttribute("cooking_steps", cooking_steps);
/*		   request.setAttribute("check_num", check_num);*/
	   System.out.println("잘 받았니"+information.getHit_standard());
	    
	    ActionForward forward = new ActionForward();
	    forward.setUrl("/recipeInfo.jsp");
		
	    return forward;
	}

}
