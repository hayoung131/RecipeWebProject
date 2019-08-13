package svc;

import java.sql.Connection;
import dao.RecipeDAO;
import static db.JDBCUtil.*;
import vo.NewPwd;

public class RecipeFindPwdViewService {

	public int savePwd(NewPwd newPwd) throws Exception{
		int loginPossible =0;
		Connection con=getConnection();
		
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		loginPossible= recipeDAO.savePwd(newPwd);
		System.out.print("\n***********sql:  "+loginPossible+"\n");
		if(loginPossible > 0){
			//insert 성공한것
			commit(con);
		}
		else{
			rollback(con);
		}
		
		close(con);
		return loginPossible;
	}

}

