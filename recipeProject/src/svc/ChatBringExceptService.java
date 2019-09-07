package svc;


import static db.JDBCUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.ChatbotDAO;


public class ChatBringExceptService {

	public ArrayList<String> bringExceptIngre(String id) throws Exception{
		
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		
		ArrayList<String> exceptIngre=chatbotDAO.bringExceptIngre(id);
		close(con);
		return exceptIngre;
	}

}
