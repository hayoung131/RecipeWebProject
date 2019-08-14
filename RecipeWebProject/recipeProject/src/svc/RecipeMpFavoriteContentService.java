package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.RecipeDAO;
import vo.Ingredient;
import vo.Recipe;

public class RecipeMpFavoriteContentService {

	public Recipe getArticle(int num) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		Recipe information = recipeDAO.selectRecipeInfo(num);
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


}
