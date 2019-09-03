package svc;
import static db.JDBCUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.RecipeDAO;
import vo.Recipe;
public class RecipeMpFavoriteListService {

	public int getArticleCount(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO boardDAO = RecipeDAO.getInstance();
		boardDAO.setConnection(con);
		
		int articleCount = boardDAO.selectArticleCountFavorite(id);
		close(con);
		return articleCount;
	}

	public List<Recipe> getArticles(int startRow, int pageSize,String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO boardDAO = RecipeDAO.getInstance();
		boardDAO.setConnection(con);
		
		List<Recipe> articleList = boardDAO.selectArticleListFavorite(startRow,pageSize,id);
		close(con);
		return articleList;
	}
	
	

}








