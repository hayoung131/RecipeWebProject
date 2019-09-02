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
		boolean check=recipeDAO.confirmPwd(modInfo);
		if(check==true) {
			System.out.println("비밀번호가 일치합니다-service.confirmPwd");
			return modMemInfo(modInfo);//수정 성공여부
		}else {
			System.out.println("비밀번호가 일치하지않습니다.-service.confirmPwd");
			return false;	//비밀번호 불일치
		}
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
