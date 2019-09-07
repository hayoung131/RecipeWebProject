package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.ChatbotDAO;

public class SendExcludeIngreNameService {

	public String InsertExcludeIngre(String user_id, String[] ingreNames)throws Exception {
		
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		
		int checkSuccess = chatbotDAO.InsertExcludeIngre(user_id,ingreNames);
		String registSuccess = null;
		
		if(checkSuccess > 0) {
			
			registSuccess = "true";
			commit(con);
			
		}else {
			rollback(con);
		}	
		close(con);
		
		
		return registSuccess;
	}

}
