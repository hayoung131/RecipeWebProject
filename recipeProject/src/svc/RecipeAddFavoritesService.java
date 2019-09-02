package svc;

import static db.JDBCUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.RecipeDAO;
import vo.Ingredient;
import vo.Recipe;

public class RecipeAddFavoritesService {

//	public Recipe SelectRecipeInfo(int num) throws Exception{
//		
//		Connection con = getConnection();
//		RecipeDAO recipeDAO = RecipeDAO.getInstance();
//		recipeDAO.setConnection(con);
//		
//		Recipe information = recipeDAO.selectRecipeInfo(num);
//		close(con);
//		
//		return information;
//	}
	
//public boolean UpdateOn(int num, String img, String status) throws Exception{
//		
//		boolean registSuccess = false;
//		Connection con = getConnection();
//		RecipeDAO recipeDAO = RecipeDAO.getInstance();
//		recipeDAO.setConnection(con);
//		
//		int check = recipeDAO.UpdateFavoritesOn(num,img,status);
//		
//		if(check > 0) {
//			
//			registSuccess = true;
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}	
//		close(con);
//		return registSuccess;
//	}
	
	public List<Ingredient> getIngredientList(int num) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<Ingredient> ingredientList = recipeDAO.selectRecipeIngredient(num);
		
		close(con);
		return ingredientList;
	}

	public boolean deleteBookmark(int num, String id)throws Exception {
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		boolean registSuccess=false;
		int ckBm = recipeDAO.deleteBookmark(num,id);
		
		if(ckBm > 0) {
			
			registSuccess = true;
			commit(con);
			
		}else {
			rollback(con);
		}	
		close(con);
		
		return registSuccess;
	}

	public boolean addBookmark(int num, String id)throws Exception  {
		
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		boolean registSuccess=false;
		
		int ckBm = recipeDAO.insertBookmark(num, id);
		
		if(ckBm > 0) {
			
			registSuccess = true;
			commit(con);
			
		}else {
			rollback(con);
		}	
		close(con);
		
		return registSuccess;
	}

}
