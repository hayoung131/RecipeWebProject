package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeLoginFormService;
import vo.ActionForward;
import vo.LoginInfo;

public class RecipeLoginFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1. 정보를 request에서 받아와 data bean 객체에 넣기
		LoginInfo loginInfo=new LoginInfo();
		loginInfo.setId(request.getParameter("id"));
		loginInfo.setPwd(request.getParameter("password"));
		
		//2. 등록작업 요청을위해 해당 트랜잭션의 작업을 하기위한 서비스를 생성하고 해당 서비스의 생성해둔메소드에 데이터빈객체를 넘겨주고 return 은 boolean형으로 받기  
		
		RecipeLoginFormService recipeLoginFormService =new RecipeLoginFormService();
		boolean loginSuccess=recipeLoginFormService.login(loginInfo);
		ActionForward forward = null;//메소드에서 최종적으로 반환할 포워드값을 저장할 놈
		if(loginSuccess){
			//성공시 레시피홈페이지 메인화면 보여주기
			forward = new ActionForward();
			forward.setUrl("recipeRankingList.bo");
			request.setAttribute("loginId", request.getParameter("id"));
			//요 정보 계속 가지고 있어야 ..되지않을까? 그래야 마이페이지도 븨줄수있으니깐.
		}
		else{
			//경고창 띄우고, 글쓰기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디,비밀번호가 틀렸습니다.')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		
		return forward;
	}

}
