package svc;

import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import dao.RecipeDAO;

public class RecipehateIngreListService {

	public String[] selecthateIngre(String user_id) throws Exception {
		
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		String[] hateIngreList = recipeDAO.selectHateIngre(user_id);
		
		return hateIngreList;
	}

}
