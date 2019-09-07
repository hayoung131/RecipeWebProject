package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.List;
import dao.ChatbotDAO;
import vo.SearchInformation;
import vo.SearchResult;

public class RecipeSearchListService {

	public String[] selectHateIngre(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		String[] hateIngre = recipeDAO.selectHateIngre(id);
		close(con);
		
		return hateIngre;
	}

	public List<SearchResult> selectSearchResult(SearchInformation searchInformation ,String order) throws Exception {
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchResult(searchInformation,order);
		
		return resultList;
	}

	public List<SearchResult> selectSearchResultIm(SearchInformation searchInformation, String importance)throws Exception {
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchResultIm(searchInformation,importance);
		
		return resultList;
	}
//��Ḹ �Է�, ��Ȯ�� ����
	public List<SearchResult> selectSearchOnlyIngre(SearchInformation searchInformation, String accuracy)throws Exception {
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyIngre(searchInformation,accuracy);
		
		return resultList;
	}
//��Ḹ �Է�, ��õ�� ����
	public List<SearchResult> selectSearchOnlyIngreIm(SearchInformation searchInformation, String importance) throws Exception {
		
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyIngreIm(searchInformation,importance);
		
		return resultList;
	}

	public List<SearchResult> selectSearchOnlyTitle(SearchInformation searchInformation) throws Exception{
		
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyTitle(searchInformation);
		
		return resultList ;
	}

	public List<SearchResult> selectSearchResultImNo(SearchInformation searchInformation, String importance)throws Exception {
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchResultImNo(searchInformation,importance);
		
		return resultList;
	}

	public List<SearchResult> selectSearchResultNo(SearchInformation searchInformation, String order)throws Exception {

		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchResultNo(searchInformation,order);
		
		return resultList;
	}

	public List<SearchResult> selectSearchOnlyIngreNo(SearchInformation searchInformation, String accuracy) throws Exception{
		

		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyIngreNo(searchInformation,accuracy);
		
		return resultList;
		
	}

	public List<SearchResult> selectSearchOnlyIngreImNo(SearchInformation searchInformation, String importance)throws Exception {
		
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyIngreImNo(searchInformation,importance);
		
		return resultList;
	}

	public List<SearchResult> selectSearchOnlyTitleNo(SearchInformation searchInformation)throws Exception {
		
		Connection con = getConnection();
		ChatbotDAO recipeDAO = ChatbotDAO.getInstance();
		recipeDAO.setConnection(con);
		
		List<SearchResult> resultList = null;
		
		resultList = recipeDAO.selectSearchOnlyTitleNo(searchInformation);
		
		return resultList ;
	}

}
