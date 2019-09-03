package svc;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.RecipeDAO;
import vo.ModInfo;

public class RecipeModMemInfoService {

	public boolean confirmPwd(ModInfo modInfo) throws Exception{//비밀번호 체크
		//1. db연결
		Connection con=getConnection();
		
		//실제 sql넘길 RecipeDAO 생성하기
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		boolean check=recipeDAO.confirmPwd(modInfo);//비밀번호가 일치한단부분
		return check;	//비밀번호 불일치/일치여부 반환

	}

	public boolean modMemInfo(ModInfo modInfo) throws Exception {
		//1. db연결
		Connection con=getConnection();
		
		//실제 sql넘길 RecipeDAO 생성하기
		RecipeDAO recipeDAO=RecipeDAO.getInstance();
		recipeDAO.setConnection(con);
		boolean successMod=recipeDAO.modMemInfo(modInfo);
		
		if(successMod) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return successMod;
	}
}
