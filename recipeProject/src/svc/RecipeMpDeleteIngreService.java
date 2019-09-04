package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.RecipeDAO;

public class RecipeMpDeleteIngreService {

	public boolean deleteIngre(String user_id ,String[] checkedHateIngre)throws Exception {
		// TODO Auto-generated method stub
		boolean removeSuccess = false;
		
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		
		boolean deleteCount = recipeDAO.deleteIngre(user_id,checkedHateIngre);
		
		if(deleteCount){
			commit(con);
			close(con);
			removeSuccess = true;
			return deleteCount;
		}
		else{
			rollback(con);
		}
		
		return removeSuccess;
	}

	public boolean insertIngre(String user_id, String[] addIngre)throws Exception {
		Connection con = getConnection();
		RecipeDAO recipeDAO = RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		boolean addSuccess = false;
		
		addSuccess = recipeDAO.insertIngre(user_id,addIngre);
		if(addSuccess){
			commit(con);
			close(con);
		}
		else{
			rollback(con);
		}
		
		// TODO Auto-generated method stub
		return addSuccess;
	}

}
