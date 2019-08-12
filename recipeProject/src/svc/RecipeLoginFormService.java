package svc;

import java.sql.Connection;

import dao.RecipeDAO;
import vo.LoginInfo;

import static db.JDBCUtil.*;

public class RecipeLoginFormService {

	public boolean login(LoginInfo loginInfo) throws Exception{
		// 모델2에서 트랜잭션 단위는 서비스클래스에서 처리해줘야함.
		boolean loginPossible = false;
		
		//1.db연결
		Connection con = getConnection();
		
		//. 실제 sql넘길 RecipeDAO 생성하기
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		loginPossible=recipeDAO.compareLoginInfo(loginInfo);
/*		if(insertCount > 0){
			//insert 성공한것
			loginSuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
*/		close(con);
		return loginPossible;
	}

}
