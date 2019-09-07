package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeCheckIdDuplicationService;
import vo.ActionForward;

public class RecipeCheckIdDuplicationAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//해당 아이디가 db에 존재하는지 여부를 체크하고 return 되는 t/f를 그대로 포워드에 담아 넘겨주기 
		String dupCheck_id=request.getParameter("dupCheck_id");
		RecipeCheckIdDuplicationService recipeCheckIdDuplicationService=new RecipeCheckIdDuplicationService();
		
		String Duplicate=recipeCheckIdDuplicationService.checkDup(dupCheck_id);
		return Duplicate;
	}

}
