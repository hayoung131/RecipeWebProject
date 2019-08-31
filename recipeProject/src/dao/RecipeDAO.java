package dao;

import static db.JDBCUtil.close;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vo.Board;
import vo.FindIdInfo;
import vo.FindPwdInfo;
import vo.LoginInfo;
import vo.MemberInfo;
import vo.ModInfo;
import vo.NewPwd;
import vo.Ingredient;
import vo.LoginInfo;
import vo.Recipe;
import vo.SignupInfo;


public class RecipeDAO {
	/*
	전역 변수를 사용하지 않고 객체를 하나만 생성 하도록 하며, 생성된 객체를 어디에서든지 참조할 수 있도록 하는 패턴
	애플리케이션이 시작될 때 어떤 클래스가 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인패턴.
	생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나고 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다. 
	(자바에선 생성자를 private로 선언해서 생성 불가하게 하고 getInstance()로 받아쓰기도 함)
	=> 싱글톤 패턴은 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴이다.
	*/
	
	private Connection con;
	/*외부클래스에서 BoardDAO 변수에 직접접근하지못하게 private으로 지정해준것
	*				클래스명	변수명	 = new 메소드()
	* 밖에서는 BoardDAO() 생성자로 객체 생성 못하게 막아뒀으니, 요 안에서 객체 생성하고 그걸 getInstance() 메소드를
	* 통해서만 객체를 가져갈 수 있도록해둔것.
	*/
	private RecipeDAO() {
 		//외부 클래스에서 생성자를 이용해 객체를 생성할 수 없도록 생성자의 접근제한을 private으로 설정하고
 	}
	private static RecipeDAO instance = new RecipeDAO();
	public static RecipeDAO getInstance() {
        return instance;
        /*싱글톤패턴 : 특정클래스 기능사용시 매번 클래스객체 매번 다 생성하는게아니라 
         * 처음만 생성하고 힙에있는걸 공유해서 다음엔 생성안하고도 가능하게....
         * getInstance()메소드를 통해서만..
         *외부에서 하나만 생성하도록 함... */
    }
	
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}
	
	public int selectArticleCount()
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
		    }
	
	
	public int selectArticleCountFavorite()
		    throws Exception {
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        int x=0;

		        try {
		            
		            pstmt = con.prepareStatement
		            		("select count(*) from favorite where status = 'on'");
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
		    }
	
	
	public List<Recipe> selectArticleList(int start, int end)
		    throws Exception {
		        Statement stmt = null;
		        ResultSet rs = null;
		        List<Recipe> articleList=null;
		        PreparedStatement pstmt = null;
		        try {
		            
		            pstmt = con.prepareStatement(
		"select list2.* from(select  list1.*  " +
		"from(select *  from recipes order by num desc)list1) " +
		"list2 limit ?,?");
		            pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);
		            rs = pstmt.executeQuery();
		   
		                if (rs.next()) {
		                int i=0;
		                articleList = new ArrayList<Recipe>(end);
		                do{
		                  Recipe article= new Recipe();
		                  article.setNum(rs.getInt("num"));
		                  article.setTitle(rs.getString("title"));
		                  article.setHit_count(rs.getString("hit_count"));
		                  article.setLevel(rs.getString("level"));
		                  article.setTime(rs.getString("time"));
		                  article.setCooking_step(rs.getString("cooking_step"));
		      
		                  articleList.add(article);
		                  i++;
		       }while(rs.next()&& i<end);
		   }
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		           close(rs);
		           close(pstmt);
		        }
		  return articleList;
		    }
	
	
	public List<Recipe> selectArticleListFavorite(int start, int end)
		    throws Exception {
		        Statement stmt = null;
		        ResultSet rs = null;
		        List<Recipe> articleList=null;
		        PreparedStatement pstmt = null;
		        try {
		            
		            pstmt = con.prepareStatement(
		"select list2.* from(select  list1.*  " +
		"from(select *  from recipes order by num desc)list1) " +
		"list2 inner join favorite as f where list2.num=f.num and f.status='on' limit ?,? ");
		            pstmt.setInt(1, start-1);
					pstmt.setInt(2, end);
		            rs = pstmt.executeQuery();
		   
		                if (rs.next()) {
		                int i=0;
		                articleList = new ArrayList<Recipe>(end);
		                do{
		                  Recipe article= new Recipe();
		                  article.setNum(rs.getInt("num"));
		                  article.setTitle(rs.getString("title"));
		                  article.setHit_count(rs.getString("hit_count"));
		                  article.setLevel(rs.getString("level"));
		                  article.setTime(rs.getString("time"));
		                  article.setCooking_step(rs.getString("cooking_step"));
		      
		                  articleList.add(article);
		                  i++;
		       }while(rs.next()&& i<end);
		   }
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		           close(rs);
		           close(pstmt);
		        }
		  return articleList;
		    }
	
	
	public Recipe selectRecipeInfo(int num)
    	    throws Exception {
    	        PreparedStatement pstmt = null;
    	        ResultSet rs = null;
    	        Recipe information = null;
    	        try {
    	        	pstmt = con.prepareStatement("select * from favorite as f inner join recipes as r"
    	        			+ " on f.num = r.num where r.num = ?");
    	        	pstmt.setInt(1, num);
    	        	rs = pstmt.executeQuery();

    	            if (rs.next()) {
    	                information = new Recipe();
    	                information.setNum(rs.getInt("num"));
    	                information.setTitle(rs.getString("title"));
    	                information.setHit_count(rs.getString("hit_count"));
    	                information.setLevel(rs.getString("level"));
    	                information.setTime(rs.getString("time"));
    	                information.setCooking_step(rs.getString("cooking_step"));
    	                information.setImg(rs.getString("img"));
    	                information.setStatus(rs.getString("status"));
    				}
    	        } catch(Exception ex) {
    	            ex.printStackTrace();
    	        } finally {
    	            close(rs);
    	            close(pstmt);
    	        }
    			return information;
    	    }
	
	public List<Ingredient> selectRecipeIngredient(int num)
    	    throws Exception {
    	        PreparedStatement pstmt = null;
    	        ResultSet rs = null;
    	        List<Ingredient> informationList = null;
    	        try {
    	        	int x = 0;
    	        	
    	        	pstmt = con.prepareStatement
    	            		("select count(*) from ingredient");
    	            rs = pstmt.executeQuery();

    	            if (rs.next()) {
    	               x= rs.getInt(1);
    				}

    	            pstmt = con.prepareStatement(
    	            	"select * from ingredient where num = ?");
    	            pstmt.setInt(1, num);
    	            rs = pstmt.executeQuery();

    	            if (rs.next()) {
    	            	int i=0;
    	            	informationList = new ArrayList<Ingredient>(x);
    	            	
    	            	do {
    	            		Ingredient information = new Ingredient();
    	            		
    	            		information.setNum(rs.getInt("num"));
        	                information.setIngredient_id(rs.getInt("ingredient_id"));
        	                information.setMeasu(rs.getString("measu"));
        	                information.setAmount(rs.getString("amount"));
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

	public String searchId(FindIdInfo findIdInfo) 
		throws Exception{
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String id = null;
			
			try {
				pstmt=con.prepareStatement(
						"select user_id "+
						" from user "+
						" where user_name=? and user_phone=? "		
						);
				pstmt.setString(1, findIdInfo.getName());
				pstmt.setString(2, findIdInfo.getPhoneNumber());
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					id=rs.getString("user_id");
				}
				else {
					id="0";
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			/* System.out.printf(id, rs.next()); */
			}finally {
				close(rs);
				close(pstmt);
			}
			return id;
		}

	public boolean compareLoginInfo(LoginInfo loginInfo) throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		boolean loginPossible=false;
		try {
			pstmt=con.prepareStatement(
					"select user_id "+
					" from user "+
					" where user_id=? and user_pw=? "
					);
			pstmt.setString(1, loginInfo.getId());
			pstmt.setString(2, loginInfo.getPwd());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//로그인정보와 db상의 정보가 일치한단의미
				loginPossible=true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return loginPossible;
	}

	public String searchPwd(FindPwdInfo findPwdInfo) throws Exception {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String id=null;
		
		try {
			pstmt=con.prepareStatement(
					"select user_id "+
					" from user "+
					" where user_id=? and user_name=? and find_question=? and find_answer=? "
					);
			pstmt.setString(1, findPwdInfo.getId());
			pstmt.setString(2, findPwdInfo.getName());
			pstmt.setInt(3, findPwdInfo.getFindQuestion());
			pstmt.setString(4,findPwdInfo.getFindAnswer());
			/* System.out.print("\n***********sql:  "+pstmt+"\n"); */
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				id=rs.getString("user_id");
			}
			else {
				id="0";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}

	public int savePwd(NewPwd newPwd) throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int changeSuccess=0;
		try {
			pstmt=con.prepareStatement("update user set user_pw=? where user_id=?");
			pstmt.setString(1, newPwd.getNewPwd());
			pstmt.setString(2, newPwd.getMatchedId());
			System.out.print("\n***********sql:  "+pstmt+"\n");
			int a=pstmt.executeUpdate();
			System.out.print("\n***********sql   :  "+a+"\n");
			if(a>0) {
				changeSuccess=a;
			}else {
				changeSuccess=0;
			}
			
			/* pstmt.close(); */
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			/*
			 * // close(rs);
			 */			close(pstmt);
		}
		return changeSuccess;
	}

	public int saveMemberInfo(SignupInfo signupInfo) throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int successSignup=0;
		try {
			pstmt=con.prepareStatement(
				"insert into "+
				"user(user_id,user_name,user_pw,user_phone,find_question,find_answer) "+
				"values(?,?,?,?,?,?);"
				);
			pstmt.setString(1,signupInfo.getId());
			pstmt.setString(2,signupInfo.getName());
			pstmt.setString(3,signupInfo.getPwd());
			pstmt.setString(4,signupInfo.getPhone());
			pstmt.setInt(5,signupInfo.getFindQuestion());
			pstmt.setString(6,signupInfo.getFindAnswer());
			
			
			System.out.print("\n***********sql:  "+pstmt+"\n");
			successSignup=pstmt.executeUpdate();
			
			String[] exceptList=signupInfo.getExceptIngredients();
			
			for(int i=0;i<exceptList.length;i++) {
				pstmt=con.prepareStatement("insert into dislike_ingredient(user_id,ingredient) values(?,?);");
				pstmt.setString(1, signupInfo.getId());
				pstmt.setString(2,exceptList[i]);
				pstmt.executeUpdate();
			}
			if(successSignup>0) {
				System.out.print("\n***********sql   :  "+successSignup+"\n");
			}
			else {
				successSignup=0;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return successSignup;
	}
	public int UpdateFavoritesOn(int num, String img, String status) 
			throws Exception {
		
			PreparedStatement pstmt = null;
	        Recipe information = null;
	        int b =0;
        
		try {

            pstmt = con.prepareStatement(
            	"update favorite set img = ?,status = ? where num = ?");
            pstmt.setString(1, img);
            pstmt.setString(2, status);
            pstmt.setInt(3, num);
            int a = pstmt.executeUpdate();
            
            if(a>0) {
            	System.out.println(pstmt+"수정되었습니다.");
            	b=a;
            }else {
            	System.out.println("수정실패.");
            	b=0;
            }
            		
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           close(pstmt);
           
        }
		
		return b;
	}

	
	public int deleteFavorite(int num) throws Exception {
			PreparedStatement pstmt = null;
	        ResultSet rs= null;

	        int x=-1;
	        try {
	        	
				pstmt = con.prepareStatement("update favorite set img = 'images/star1.png', status = 'off' where num = ?");
                pstmt.setInt(1, num);
                x = pstmt.executeUpdate();
					
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	           close(pstmt);
	        }
			return x;
	}

	public MemberInfo confirmAndGetMemInfo(LoginInfo loginInfo) throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberInfo memberInfo=new MemberInfo();
		
		try {
			pstmt=con.prepareStatement("select user_id from user where user_id=? and user_pw=?");
			pstmt.setString(1, loginInfo.getId());
			pstmt.setString(2, loginInfo.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//해당 id와 pw가 일치하는 아이디가 있다는 의미
				pstmt=con.prepareStatement("select user_name, user_phone,find_question,find_answer from user where user_id=?");
				pstmt.setString(1, loginInfo.getId());
				System.out.println("query:  "+pstmt);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("이름,폰,qNum,답변 : " + rs.getString("user_name")+"             "+rs.getString("user_phone")+"             "+rs.getInt("find_question")+"             "+rs.getString("find_answer"));
					memberInfo.setConfirm(true);
					memberInfo.setName(rs.getString("user_name"));
					memberInfo.setPhoneNum(rs.getString("user_phone"));
					memberInfo.setQuestion(rs.getInt("find_question"));
					memberInfo.setAnswer(rs.getString("find_answer"));
					
				}
			}else {
				memberInfo.setConfirm(false);
				System.out.println("###정보가져오기 실패###");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return memberInfo;
	}

	public boolean confirmPwd(ModInfo modInfo) throws Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean confirmSuccess=false;
		
		try {
			pstmt=con.prepareStatement("select user_id from user where user_id=? and user_pw=?");
			pstmt.setString(1, modInfo.getId());
			pstmt.setString(2, modInfo.getCurrent_pwd());
			System.out.print("\n***********sql:  "+pstmt+"\n");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//아이디가 존재한단의미
				confirmSuccess=true;
			}else {
				confirmSuccess=false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return confirmSuccess;
	}

	public boolean modMemInfo(ModInfo modInfo) throws Exception{
		PreparedStatement pstmt=null;
		boolean modSuccess=false;
		ResultSet rs=null;
		MemberInfo memberInfo=new MemberInfo();
		String query="update user set user_phone=?, find_question=?, find_answer=?";
		try {
			//pstmt=con.prepareStatement();
			if(modInfo.getCurrent_pwd() !="") {//비밀번호도 변경하겠다는 의미
				query +=", user_pw=? ";
			}
			query+="where user_id=?";
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, modInfo.getMod_phone());
			pstmt.setInt(2, modInfo.getMod_question());
			pstmt.setString(3, modInfo.getMod_answer());
			if(modInfo.getCurrent_pwd() !="") {
				pstmt.setString(4, modInfo.getNew_pwd());
				pstmt.setString(5, modInfo.getId());
			}else{
				pstmt.setString(4, modInfo.getId());
			}
			System.out.print("\n***********sql:  "+pstmt+"\n");
			int result=pstmt.executeUpdate();
			
			if(result>0) {//업뎃 성공했단의미
				System.out.println("업뎃 성공-DAO");
				modSuccess=true;
			}else {
				System.out.println("업뎃 실패-DAO");
				modSuccess=false;
			}
			if(modSuccess){
				pstmt=con.prepareStatement("select user_name, user_phone,find_question,find_answer from user where user_id=?");
				pstmt.setString(1, modInfo.getId());
				System.out.println("query:  "+pstmt);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("이름,폰,qNum,답변 : " + rs.getString("user_name")+"             "+rs.getString("user_phone")+"             "+rs.getInt("find_question")+"             "+rs.getString("find_answer"));
					memberInfo.setConfirm(true);
					memberInfo.setName(rs.getString("user_name"));
					memberInfo.setPhoneNum(rs.getString("user_phone"));
					memberInfo.setQuestion(rs.getInt("find_question"));
					memberInfo.setAnswer(rs.getString("find_answer"));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return modSuccess;
	}
		
	}