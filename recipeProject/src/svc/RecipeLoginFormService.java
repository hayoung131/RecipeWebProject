package svc;

import java.sql.Connection;

import dao.RecipeDAO;


import static db.JDBCUtil.*;

public class RecipeLoginFormService {

	public boolean login(/* ���� �����ͺ�*/) {
		// ��2���� Ʈ����� ������ ����Ŭ�������� ó���������.
		boolean loginSuccess = false;
		
		//1.db����
		Connection con = getConnection();
		
		//. ���� sql�ѱ� RecipeDAO �����ϱ�
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
/*		if(insertCount > 0){
			//insert �����Ѱ�
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
