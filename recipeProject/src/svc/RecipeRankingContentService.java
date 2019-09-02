package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

//import dao.ChatbotDAO;
import dao.RecipeDAO;
import vo.Ingredient;
import vo.Recipe;

public class RecipeRankingContentService {

	public Recipe getArticle(int num, String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		Recipe information = recipeDAO.selectRecipeInfo(num,id);
		close(con);
		return information;
	}
	
	public List<Ingredient> getIngredientList(int num) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<Ingredient> ingredientList = recipeDAO.selectRecipeIngredient(num);
		
		close(con);
		return ingredientList;
	}

public String[] getCooking_steps(int num) throws Exception{
		
		Connection con = getConnection();
		RecipeDAO recipeDAO= RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		System.out.println("조리과정 잘 받았니????");
		String[] cooking_step = recipeDAO.selectCooking_step(num);
		System.out.println("잘 받았니??????????" + cooking_step[0]);
		
		close(con);
		return cooking_step;
		
	}


}
