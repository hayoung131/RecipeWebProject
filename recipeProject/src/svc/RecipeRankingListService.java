package svc;
import static db.JDBCUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.RecipeDAO;
import vo.Recipe;
public class RecipeRankingListService {

	/*public int getArticleCount() throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO boardDAO = RecipeDAO.getInstance();
		boardDAO.setConnection(con);
		
		int articleCount = boardDAO.selectArticleCount();
		close(con);
		return articleCount;
	}*/

	public List<Recipe> getArticles(int startRow, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RecipeDAO boardDAO = RecipeDAO.getInstance();
		boardDAO.setConnection(con);
		
		List<Recipe> articleList = boardDAO.selectArticleList(startRow,pageSize);
		close(con);
		return articleList;
	}
	
	

}








