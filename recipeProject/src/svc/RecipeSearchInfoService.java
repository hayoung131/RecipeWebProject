package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.ChatbotDAO;
import vo.Ingredient;
import vo.RecipeChat;

public class RecipeSearchInfoService {

	public RecipeChat getArticle(int num,String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		
		RecipeChat information = chatbotDAO.selectRecipeInfo(num,id);
		close(con);
		return information;
	}
	
	public List<Ingredient> getIngredientList(int num) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		
		List<Ingredient> ingredientList = chatbotDAO.selectRecipeIngredient(num);
		
		close(con);
		return ingredientList;
	}

	public String[] getCooking_steps(int num) throws Exception{
		
		Connection con = getConnection();
		ChatbotDAO chatbotDAO = ChatbotDAO.getInstance();
		chatbotDAO.setConnection(con);
		System.out.println("잘 받았니??????????");
		String[] cooking_step = chatbotDAO.selectCooking_step(num);
		System.out.println("잘 받았니??????????" + cooking_step[0]);
		
		close(con);
		return cooking_step;
		
	}

}
