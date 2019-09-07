package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.ChatbotDAO;

public class ChatRecipeAddFavoritesService  {

	public boolean addBookmark(int num, String id)throws Exception  {
		
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		boolean registSuccess=false;
		
		int ckBm = chatbotDAO.insertBookmark(num, id);
		
		if(ckBm > 0) {
			
			registSuccess = true;
			commit(con);
			
		}else {
			rollback(con);
		}	
		close(con);
		
		return registSuccess;
	}

	public boolean deleteBookmark(int num, String id)throws Exception  {
		
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		boolean registSuccess=false;
		int ckBm = chatbotDAO.deleteBookmark(num,id);
		
		if(ckBm > 0) {
			
			registSuccess = true;
			commit(con);
			
		}else {
			rollback(con);
		}	
		close(con);
		
		return registSuccess;
	}

}
