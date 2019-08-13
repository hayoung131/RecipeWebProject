package svc;

import java.sql.Connection;
import dao.RecipeDAO;

import static db.JDBCUtil.*;
import vo.SignupInfo;

public class RecipeSignupFormService {

	public boolean signUp(SignupInfo signupInfo) throws Exception{
		boolean successSignup=false;
		int result=0;
		Connection con=getConnection();
		
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		
		recipeDAO.setConnection(con);
		
		result=recipeDAO.saveMemberInfo(signupInfo);
		if(result !=0) {
			con.commit();
			successSignup=true;
		}
		else {
			con.rollback();
		}
		close(con);
		return successSignup;
	}

}
