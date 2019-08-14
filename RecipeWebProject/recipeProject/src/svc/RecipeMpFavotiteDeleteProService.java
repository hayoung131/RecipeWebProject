package svc;

import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.RecipeDAO;


public class RecipeMpFavotiteDeleteProService {
	
	public boolean removeArticle(int num) throws Exception {
		// TODO Auto-generated method stub
		boolean removeSuccess = false;
		
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		int deleteCount = recipeDAO.deleteFavorite(num);
		
		if(deleteCount > 0){
			commit(con);
			removeSuccess = true;
		}
		else{
			rollback(con);
		}
		return removeSuccess;
	}

}
