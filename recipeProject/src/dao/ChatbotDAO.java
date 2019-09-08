 package dao;
 
 import java.sql.*;
 import javax.sql.*;

import vo.Ingredient;
import vo.RecipeChat;
import vo.SearchInformation;
import vo.SearchResult;

/*import vo.SearchInformation;
import vo.SearchResult;*/

import static db.JDBCUtil.*;
import javax.naming.*;
 import java.util.*;
 
 public class ChatbotDAO {

	 
    private Connection con;
 
 	private static ChatbotDAO instance = new ChatbotDAO();
    
 	private ChatbotDAO() {
 		 }

    public static ChatbotDAO getInstance() {
        return instance;
       
    }
    

    
    public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}



public ArrayList<String> bringExceptIngre(String id) throws Exception{
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	ArrayList<String> exceptIngre=new ArrayList<String>();

	
	try {
		pstmt=con.prepareStatement("select ingredient from dislike_ingredient where user_id=?");
		pstmt.setString(1, id);
		System.out.println(pstmt);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			int i=0;
			exceptIngre.add(rs.getString("ingredient"));
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	return exceptIngre;
}

public int InsertExcludeIngre(String user_id, String[] ingreNames) {
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	int a = 0;
	try {
		
		for (int i =0; i<ingreNames.length;i++) {
			pstmt=con.prepareStatement("insert into dislike_ingredient (user_id, ingredient) values (?,?) ");
			pstmt.setString(1, user_id);
			pstmt.setString(2, ingreNames[i]);
			System.out.println(pstmt);
			a = pstmt.executeUpdate();
		}
		

	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
	
		close(pstmt);
	}
	
	return a;
}

public int DeleteExcludeIngre(String user_id, String[] ingre_arr) {
	
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	int a = 0;
	try {
		
		for (int i =0; i<ingre_arr.length;i++) {
			pstmt=con.prepareStatement("delete from dislike_ingredient where user_id = ? and ingredient = ? ");
			pstmt.setString(1, user_id);
			pstmt.setString(2, ingre_arr[i]);
			System.out.println(pstmt);
			a = pstmt.executeUpdate();
		}
		

	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
	
		close(pstmt);
	}
	
	return a;
	

}

public String[] selectHateIngre(String id) 
		throws Exception {
	
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String [] hateIngre = null;
        
        try {
        	pstmt = con.prepareStatement
        			("select count(ingredient) as num from dislike_ingredient where user_id = ? ");
        	pstmt.setString(1, id);
        	rs = pstmt.executeQuery();
        	int num = 0;
        	if (rs.next()) {
        		num = rs.getInt("num");
        	}
        
        	pstmt = con.prepareStatement
        			("select ingredient from dislike_ingredient where user_id = ? ");
        	pstmt.setString(1, id);
        	rs = pstmt.executeQuery();
        	
        	if (rs.next()) {
        		
        		int i = 0;
        		
        		String [] hateIngre1 = new String[num];

        		do {
        			System.out.println("싫어하는 재료드륻르드드 : "+rs.getString("ingredient"));
        			
        			hateIngre1[i] = rs.getString("ingredient");
        			i++;
        			
        		}while(rs.next());
        		
        		hateIngre = hateIngre1;
        	}
        	
        }catch (Exception e) {
        	e.printStackTrace();
		}finally {
			close(rs);
            close(pstmt);
		}
	
        
	return hateIngre;
}

public List<SearchResult> selectSearchResult(SearchInformation searchInformation,String order) throws Exception {
	
	PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
    
    
    try {
    	
    	String [] haveIngredients = searchInformation.getHaveIngredients();
    	String [] hateIngredients = searchInformation.getHateIngredients();
    	
    	String haveIngredient = " s.ingredient_num - (" ;
    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
    	String hateIngredient = "";
    	
    	String Title = "s.searching_title like '%"+searchInformation.getTitle()+"%' and ";
    	
    	if(hateIngredients != null && hateIngredients.length == 0) {
	    	for(int i=0; i< hateIngredients.length; i++) {
	    		
	    		if(i != 0) {
	    			hateIngredient += "and ";
	    		}
	    		hateIngredient += "s.searching_ingredients not like '%"+hateIngredients[i]+"%' ";
	    		
	    	}
    	}
    	
    	int i = 0;
    	do{
    		
    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
    		
    		i++;
    		if(i >= haveIngredients.length) {
    			break;
    		}
    		haveIngredient += "+";
    		
    	}while(i<haveIngredients.length);
    	
    	haveIngredient += ")";
    	IngredientSelect_sub += Title+hateIngredient +" ORDER BY necessaryNum ASC limit 0,15";
    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
    	
    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
    	
    	pstmt = con.prepareStatement(IngredientSelect);
    	
    	System.out.println("**********sql : "+ IngredientSelect);
    	
    	rs = pstmt.executeQuery();

    	if (rs.next()) {

               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
            	   
            	   SearchResult searchResult = new SearchResult();
            	   
            	   searchResult.setNum(rs.getInt("recipe_id"));
            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
            	   searchResult.setTitle(rs.getString("cooking_title"));
            	   
            	   searchResultList.add(searchResult);
            	   
               }while(rs.next());
			}
    	
    	
    }catch (Exception e) {
    	e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
    
    for(int i = 0; i < searchResultList.size(); i++) {
		SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
		
		System.out.println("제목     :  "+searchResult1.getTitle()+i);
	}
	
	return searchResultList;
}


public List<SearchResult> selectSearchResultIm(SearchInformation searchInformation,String order) throws Exception {
	
	PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
    
    
    try {
    	
    	String [] haveIngredients = searchInformation.getHaveIngredients();
    	String [] hateIngredients = searchInformation.getHateIngredients();
    	
    	String haveIngredient = " s.ingredient_num - (" ;
    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
    	String hateIngredient = "";
    	String Title = "s.searching_title like '%"+searchInformation.getTitle()+"%' and ";
    	
    	if(hateIngredients != null && hateIngredients.length == 0) {
	    	for(int i=0; i< hateIngredients.length; i++) {
	    		
	    		if(i != 0) {
	    			hateIngredient += "and ";
	    		}
	    		hateIngredient += "s.searching_ingredients not like '%"+hateIngredients[i]+"%' ";
	    		
	    	}
    	}
    	
    	int i = 0;
    	do{
    		
    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
    		
    		i++;
    		if(i >= haveIngredients.length) {
    			break;
    		}
    		haveIngredient += "+";
    		
    	}while(i<haveIngredients.length);
    	
    	haveIngredient += ")";
    	IngredientSelect_sub += Title+ hateIngredient +" ORDER BY importance DESC limit 0,15";
    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
    	
    	System.out.println("중요도순으로 나영여령"+IngredientSelect);
    	
    	pstmt = con.prepareStatement(IngredientSelect);
    	
    	rs = pstmt.executeQuery();

    	if (rs.next()) {

               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
            	   
            	   SearchResult searchResult = new SearchResult();
            	   
            	   searchResult.setNum(rs.getInt("recipe_id"));
            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
            	   searchResult.setTitle(rs.getString("cooking_title"));
            	   
            	   searchResultList.add(searchResult);
            	   
               }while(rs.next());
			}
    	
    	
    }catch (Exception e) {
    	e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
    
    for(int i = 0; i < searchResultList.size(); i++) {
		SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
		
		System.out.println("제목     :  "+searchResult1.getTitle()+i);
	}
	
	return searchResultList;
}

public RecipeChat selectRecipeInfo(int num, String id) throws Exception{

		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    RecipeChat information = null;
	    try {
	    	information = new RecipeChat();
	    	
	    	//해당 아이디에 레시피가 즐겨찾기로 등록되어있나 확인하는거임.
	    	pstmt = con.prepareStatement("select recipe_id from bookmark where recipe_id =? and user_id = ?");
	    	pstmt.setInt(1, num);
	    	pstmt.setString(2, id);
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next()) {
	    		information.setBookmark(true); //즐겨찾기 설정이 되어있다면
	    	}else{
	    		information.setBookmark(false); //설정이 안되어있다면
	    	}
	    	
	    	
	    	pstmt = con.prepareStatement("select * from mainrecipe where recipe_id = ?");
	    	pstmt.setInt(1, num);
	    	rs = pstmt.executeQuery();
	    	
	    	//제목 난이도 시간 팁 등 정보 저장
	        if (rs.next()) {
	        	
	        	information.setCooking_title(rs.getString("cooking_title"));
	        	information.setCooking_level(rs.getString("cooking_level"));
	        	information.setCooking_time(rs.getString("cooking_time"));
	        	information.setCooking_tips(rs.getString("cooking_tips"));
	        	information.setNum(rs.getInt("recipe_id"));
			}
	        //조회수는 따른 테이블이라 따로 가져와서 저장
	        pstmt = con.prepareStatement("select hit_standard from comments where recipe_id = ?");
	    	pstmt.setInt(1, num);
	    	rs = pstmt.executeQuery();
	    	
	    	if (rs.next()) {
	    		information.setHit_standard(rs.getInt("hit_standard"));
	    	}

	    } catch(Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        close(rs);
	        close(pstmt);
	    }
	    return information;
}

public List<Ingredient> selectRecipeIngredient(int num) throws Exception {
	 PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Ingredient> informationList = null;
    try {
    	int x = 0;
    	
    	pstmt = con.prepareStatement
        		("select count(*) from recipe_ingredient where recipe_id = ? and amount != '' ");
    	 pstmt.setInt(1, num);
        rs = pstmt.executeQuery();

        if (rs.next()) {
           x= rs.getInt(1);
			}

        pstmt = con.prepareStatement(
        	"select * from recipe_ingredient where recipe_id = ? and amount != '' ");
        pstmt.setInt(1, num);
        rs = pstmt.executeQuery();
        
        informationList = new ArrayList<Ingredient>(x);
        if (rs.next()) {
        	int i=0;
        	
        	
        	do {
        		Ingredient information = new Ingredient();
        		
        		information.setNum(rs.getInt("recipe_id"));
               information.setIngredient_id(rs.getInt("ingredient_id"));
               information.setMeasu(rs.getString("measu"));
               
               information.setAmount(rs.getString("amount"));
               if(rs.getString("amount")=="")
               	System.out.println("재료들의 양이 없니??:");
               System.out.println("재료들의 양:"+information.getAmount()+"무야-------------");
               information.setSearching_ingredient(rs.getString("searching_ingredient"));
    	
               informationList.add(information);
               i++;
        	}while(rs.next()&& i<x);
            
			}
        //으휴~~~!~!~!~!~!~~~~~디비 병신!!!!!!!
        
        pstmt = con.prepareStatement
         		("select count(*) from recipe_ingredient where recipe_id = ? and amount = '' ");
     	 pstmt.setInt(1, num);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            x= rs.getInt(1);
			}
        
        pstmt = con.prepareStatement(
             	"select * from recipe_ingredient where recipe_id = ? and amount = '' ");
             pstmt.setInt(1, num);
             rs = pstmt.executeQuery();

             if (rs.next()) {
             	int i=0;
           
             	
             	do {
             		Ingredient information = new Ingredient();
             		
             		information.setNum(rs.getInt("recipe_id"));
                    information.setIngredient_id(rs.getInt("ingredient_id"));
                    information.setMeasu(rs.getString("measu"));
                    information.setAmount(" ");
                    information.setSearching_ingredient(rs.getString("searching_ingredient"));
         	
                    informationList.add(information);
                    i++;
             	}while(rs.next()&& i<x);
                 
    			}
        
        
        
        
    } catch(Exception ex) {
        ex.printStackTrace();
    } finally {
        close(rs);
        close(pstmt);
    }
    

		return informationList;
}

public String[] selectCooking_step(int num) throws Exception {
	
	PreparedStatement pstmt = null;
    ResultSet rs = null;
    String[] informationList = null;
    try {
    	int x = 0;
    	
    	pstmt = con.prepareStatement
        		("select count(*) from recipe_steps where recipe_id = ?");
    	 pstmt.setInt(1, num);
        rs = pstmt.executeQuery();

        if (rs.next()) {
           x= rs.getInt(1);
			}

        pstmt = con.prepareStatement(
        	"select * from recipe_steps where recipe_id = ?");
        pstmt.setInt(1, num);
        rs = pstmt.executeQuery();

        if (rs.next()) {
        	int i=0;
        	informationList = new String[x];
        	
        	do {
        		informationList[i] = rs.getString("cooking_steps");
	                i++;
        	}while(rs.next()&& i<x);
            
			}
    } catch(Exception ex) {
        ex.printStackTrace();
    } finally {
        close(rs);
        close(pstmt);
    }


	return informationList;
}


	public int insertBookmark(int num,String id) throws Exception {
	
		PreparedStatement pstmt = null;
		
		String[] informationList = null;
		int a=0;
		try {
			
			pstmt=con.prepareStatement("insert into bookmark (recipe_id,user_id) values (?,?) ");
			pstmt.setInt(1, num);
		 	pstmt.setString(2, id);
			a = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		
		    close(pstmt);
		}
		return a;
	}
		
		public int deleteBookmark(int num, String id) throws Exception {
		
		PreparedStatement pstmt = null;
		
		String[] informationList = null;
		int a=0;
		
		try {
			
			pstmt = con.prepareStatement("delete from bookmark where user_id = ? and recipe_id=?");
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			a = pstmt.executeUpdate();
		 	
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			close(pstmt);
		}
		
		return a;
	}

		public List<SearchResult> selectSearchOnlyIngre(SearchInformation searchInformation, String importance) throws Exception{
			//재료명으로만 레시피가져오는 sql문
			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();
		    	String [] hateIngredients = searchInformation.getHateIngredients();
		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
		    	String hateIngredient = "";
		    	
		    	if(hateIngredients != null && hateIngredients.length == 0) {
			    	for(int i=0; i< hateIngredients.length; i++) {
			    		
			    		if(i != 0) {
			    			hateIngredient += "and ";
			    		}
			    		hateIngredient += "s.searching_ingredients not like '%"+hateIngredients[i]+"%' ";
			    		
			    	}
		    	}
		    	
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub += hateIngredient +" ORDER BY necessaryNum ASC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
		    	
		    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
			
		}

		public List<SearchResult> selectSearchOnlyIngreIm(SearchInformation searchInformation, String importance)throws Exception {
			//재료만 중요도순 정렬

			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();
		    	String [] hateIngredients = searchInformation.getHateIngredients();
		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
		    	String hateIngredient = "";
		    	
		    	if(hateIngredients != null && hateIngredients.length == 0) {
			    	for(int i=0; i< hateIngredients.length; i++) {
			    		
			    		if(i != 0) {
			    			hateIngredient += "and ";
			    		}
			    		hateIngredient += "s.searching_ingredients not like '%"+hateIngredients[i]+"%' ";
			    		
			    	}
		    	}
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub += hateIngredient +" ORDER BY importance DESC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
		    	
		    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
			
		}

		public List<SearchResult> selectSearchOnlyTitle(SearchInformation searchInformation) throws Exception {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		   
		    
		    try {
		    	
		    	String [] hateIngredients = searchInformation.getHateIngredients();
		    	String hateIngredient = "";
		    	if(hateIngredients != null && hateIngredients.length == 0) {
			    	for(int i=0; i< hateIngredients.length; i++) {
			    		
			    		if(i != 0) {
			    			hateIngredient += "and ";
			    		}
			    	
			    		hateIngredient += "s.searching_ingredients not like '%"+hateIngredients[i]+"%' ";
			    		
			    	}
		    	}
		    	pstmt = con.prepareStatement("select s.recipe_id, m.cooking_title from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where "
		    								+ " s.searching_title like '%"+searchInformation.getTitle()+"%'" + hateIngredient +" limit 0,15");
		    	
		    	System.out.println("sql : "+pstmt);
		    	rs = pstmt.executeQuery();
		    	System.out.println("sql :"+pstmt);
		    	if(rs.next()) {
		    		do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
		    		
		    	}
		    	
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }finally {
		    	close(rs);
				close(pstmt);
		    }
		    
		    return searchResultList;
		}

		public List<SearchResult> selectSearchResultImNo(SearchInformation searchInformation, String importance) throws Exception {
			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
		    	String Title = "s.searching_title like '%"+searchInformation.getTitle()+"%' and ";
		  
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub += Title + "  ORDER BY importance DESC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
		    	
		    	System.out.println("중요도순으로 나영여령"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
		}

		public List<SearchResult> selectSearchResultNo(SearchInformation searchInformation, String order)throws Exception  {
			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();
		    	
		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
	
		    	String Title = "s.searching_title like '%"+searchInformation.getTitle()+"%' ";
		    	
		    	
		    
		    	
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub += Title+ " ORDER BY necessaryNum ASC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum" + IngredientSelect_sub;
		    	
		    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
		}

		public List<SearchResult> selectSearchOnlyIngreNo(SearchInformation searchInformation, String accuracy)throws Exception {
			//재료명으로만 레시피가져오는 sql문
			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();
		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id ";
		    
		    	

		    	
		    	
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub +=  " ORDER BY necessaryNum ASC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum " + IngredientSelect_sub;
		    	
		    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
		}

		public List<SearchResult> selectSearchOnlyIngreImNo(SearchInformation searchInformation, String importance)throws Exception {
			//재료만 중요도순 정렬

			
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    
		    try {
		    	
		    	String [] haveIngredients = searchInformation.getHaveIngredients();
		
		    	
		    	String haveIngredient = " s.ingredient_num - (" ;
		    	String IngredientSelect = "select s.recipe_id, m.cooking_title, ";
		    	String IngredientSelect_sub = " from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where ";
		    

		    	
		    	int i = 0;
		    	do{
		    		
		    		haveIngredient += "(s.searching_ingredients like '%" + haveIngredients[i] + "%')"; //for문 돌면서 사용자가 입력한 재료들을 검색조건에 넣음
		    		
		    		i++;
		    		if(i >= haveIngredients.length) {
		    			break;
		    		}
		    		haveIngredient += "+";
		    		
		    	}while(i<haveIngredients.length);
		    	
		    	haveIngredient += ")";
		    	IngredientSelect_sub +=  " ORDER BY importance DESC limit 0,15";
		    	IngredientSelect += haveIngredient + " as necessaryNum " + IngredientSelect_sub;
		    	
		    	System.out.println("호호호호고고혹ㅎㄱ"+IngredientSelect);
		    	
		    	pstmt = con.prepareStatement(IngredientSelect);
		    	
		    	rs = pstmt.executeQuery();

		    	if (rs.next()) {

		               do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setAccuracy(rs.getInt("necessaryNum"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
					}
		    	
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
		    
		    for(int i = 0; i < searchResultList.size(); i++) {
				SearchResult searchResult1 = (SearchResult)searchResultList.get(i); 
				
				System.out.println("제목     :  "+searchResult1.getTitle()+i);
			}
			
			return searchResultList;
		}

		public List<SearchResult> selectSearchOnlyTitleNo(SearchInformation searchInformation)throws Exception {
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		    
		    try {
		    	
		    	pstmt = con.prepareStatement("select s.recipe_id, m.cooking_title from searching s inner join mainrecipe m on s.recipe_id = m.recipe_id where "
		    								+ " s.searching_title like '%"+searchInformation.getTitle()+"%' limit 0,15");
		    	rs = pstmt.executeQuery();
		    	System.out.println("sql :"+pstmt);
		    	if(rs.next()) {
		    		do { //이쯤에서 에러난다면 원래 recipedao와 비교해보자
		            	   
		            	   SearchResult searchResult = new SearchResult();
		            	   
		            	   searchResult.setNum(rs.getInt("recipe_id"));
		            	   searchResult.setTitle(rs.getString("cooking_title"));
		            	   
		            	   searchResultList.add(searchResult);
		            	   
		               }while(rs.next());
		    		
		    	}
		    	
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }finally {
		    	close(rs);
				close(pstmt);
		    }
		    
		    return searchResultList;
		}
    
/*    public int selectArticleCount()
    	    throws Exception {
    	        PreparedStatement pstmt = null;
    	        ResultSet rs = null;

    	        int x=0;

    	        try {
    	            
    	            pstmt = con.prepareStatement
    	            		("select count(*) from recipes");
    	            rs = pstmt.executeQuery();

    	            if (rs.next()) {
    	               x= rs.getInt(1);
    				}
    	        } catch(Exception ex) {
    	            ex.printStackTrace();
    	        } finally {
    	            close(rs);
    	            close(pstmt);
    	        }
    			return x;
    	    }*/

	
 }
