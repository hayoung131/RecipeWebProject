package svc;

import dao.RecipeDAO;
import vo.LoginInfo;
import vo.MemberInfo;
import static db.JDBCUtil.*;

import java.sql.Connection;

public class RecipeConfirmPwdService {

	public MemberInfo confirm(LoginInfo loginInfo) throws Exception{
		MemberInfo memberInfo=null;
		
		//1. db연결
		Connection con=getConnection();
		
		//실제 sql넘길 RecipeDAO 생성하기
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		memberInfo=recipeDAO.confirmAndGetMemInfo(loginInfo);
		
		close(con);
		return memberInfo;
	}

}
