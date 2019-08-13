package svc;

import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import dao.RecipeDAO;
import vo.FindPwdInfo;

public class RecipeFindPwdFormService {

	public String findPwd(FindPwdInfo findPwdInfo) throws Exception {
//		boolean compareSuccess=false;
//		
		Connection con=getConnection();
		
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		
		recipeDAO.setConnection(con);
		
		String id=recipeDAO.searchPwd(findPwdInfo);
		
		return id;
	}

}
