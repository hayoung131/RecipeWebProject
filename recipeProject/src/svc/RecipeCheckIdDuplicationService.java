package svc;

import static db.JDBCUtil.*;

import java.sql.Connection;

import dao.RecipeDAO;

public class RecipeCheckIdDuplicationService {

	public String checkDup(String dupCheck_id) {
		
		Connection con=getConnection();
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		String duplicate=recipeDAO.checkDup(dupCheck_id);
		
		close(con);
		return duplicate;
	}

}
