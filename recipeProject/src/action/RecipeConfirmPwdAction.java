package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipeConfirmPwdService;
import svc.RecipeLoginFormService;
import vo.ActionForward;
import vo.LoginInfo;
import vo.MemberInfo;

public class RecipeConfirmPwdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		//1. 정보를 request에서 받아와 data bean 객체에 넣기
		LoginInfo loginInfo=new LoginInfo();
		loginInfo.setId((String)session.getAttribute("user_id"));//session에 공유된 아이디 가져오기
		loginInfo.setPwd(request.getParameter("confirmPwd"));//사용자가 입력한 비번 가져오기 
		
		
		RecipeConfirmPwdService recipeConfirmPwdService=new RecipeConfirmPwdService();
		MemberInfo memberInfo=recipeConfirmPwdService.confirm(loginInfo);//비번 일치 =T , 불일치 =F
		ActionForward forward = null;//메소드에서 최종적으로 반환할 포워드값을 저장할 놈
		if(memberInfo.isConfirm() != false){
			//성공시 레시피홈페이지 메인화면 보여주기
			forward = new ActionForward();
			forward.setUrl("/myPageInfoMod.jsp");
			request.setAttribute("memberInfo", memberInfo);
			
		}
		else{
			//경고창 띄우고, 글쓰기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		return forward;
	}

}
