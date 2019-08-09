package svc;

import java.sql.Connection;

import dao.RecipeDAO;


import static db.JDBCUtil.*;

public class RecipeLoginFormService {

	public boolean login(/* 받은 데이터빈*/) {
		// 모델2에서 트랜잭션 단위는 서비스클래스에서 처리해줘야함.
		boolean loginSuccess = false;
		
		//1.db연결
		Connection con = getConnection();
		
		//. 실제 sql넘길 RecipeDAO 생성하기
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
/*		if(insertCount > 0){
			//insert 성공한것
			loginSuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
*/		close(con);
		return loginSuccess;
	}

}
