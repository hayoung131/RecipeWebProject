package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipeModMemInfoService;
import vo.ActionForward;
import vo.ModInfo;

public class RecipeModMemInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		ActionForward forward=null;
		String current_pwd=request.getParameter("current_pwd");
		String new_pwd=request.getParameter("new_pwd");

		ModInfo modInfo=new ModInfo();
		modInfo.setId((String)session.getAttribute("user_id"));
		modInfo.setCurrent_pwd(current_pwd);
		modInfo.setNew_pwd(new_pwd);
		modInfo.setMod_answer(request.getParameter("mod_answer"));
		modInfo.setMod_question(Integer.parseInt(request.getParameter("mod_question")));
		modInfo.setMod_phone(request.getParameter("mod_phone"));
		
		RecipeModMemInfoService recipeModMemInfoService=new RecipeModMemInfoService();
		
		boolean successMod=false;
		if(new_pwd=="" && current_pwd=="") {
			System.out.println("비밀번호 변경하지않음");
			successMod= recipeModMemInfoService.modMemInfo(modInfo);
		}
		else{
			System.out.println("비밀번호 체크 후 변경함");
			boolean checkPwd=recipeModMemInfoService.confirmPwd(modInfo);
			if(checkPwd==true) {
				successMod=recipeModMemInfoService.modMemInfo(modInfo);
			}else {
				System.out.println("비밀번호가 일치합니다-action.confirmPwd");
				//경고창 띄우고, 글쓰기 창 다시 띄워주기
				response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.')");
				out.println("history.back()");//이전 url 로 돌아가기
				out.println("</script>");
				successMod=false;
			}
		}
		
		if(successMod) {
			forward=new ActionForward();
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("</script>");
			forward.setUrl("/confirmPwd.jsp");
		}
		else {
			//경고창 띄우고, 글쓰기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!!')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		return forward;
	}

}
