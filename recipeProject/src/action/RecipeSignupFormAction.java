package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeSignupFormService;
import vo.ActionForward;
import vo.SignupInfo;

public class RecipeSignupFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean successSignup=false;
		ActionForward forward=null;
		SignupInfo signupInfo = new SignupInfo();
		signupInfo.setExceptIngredients(request.getParameter("exceptIngredients"));
		signupInfo.setFindAnswer(request.getParameter("signUp-answer"));
		signupInfo.setFindQuestion(Integer.parseInt(request.getParameter("signUp-question")));
		signupInfo.setId(request.getParameter("signUp-id"));
		signupInfo.setName(request.getParameter("signUp-name"));
		signupInfo.setPhone(request.getParameter("signUp-phone"));
		signupInfo.setPwd(request.getParameter("signUp-pwd"));
		
		RecipeSignupFormService recipeSignupFormService=new RecipeSignupFormService();
		successSignup = recipeSignupFormService.signUp(signupInfo);
		
		if(successSignup) {
			//해당 아이디가 존재한단 의미
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("setTimeout(function() {alert('회원가입되었습니다! 환영합니다>__<');}, 3000)");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
			//이게 안먹히네...짜증
			forward=new ActionForward();
			forward.setUrl("/loginForm.jsp");
			
		}
		else {
			//경고창 띄우고, 아이디찾기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("setTimeout(function() {alert('회원가입에 실패했습니다.');}, 3000)");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		return forward;
	}

}
