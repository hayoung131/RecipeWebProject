package svc;

import java.sql.Connection;

import dao.RecipeDAO;

import static db.JDBCUtil.*;

import vo.FindIdInfo;

public class RecipeFindIdFormService {
	public String findId(FindIdInfo findIdInfo) throws Exception {
		boolean findSuccess=false;
		Connection con=getConnection();
		
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		
		recipeDAO.setConnection(con);
		
		String id=recipeDAO.searchId(findIdInfo);
		
		return id;
	}
}
