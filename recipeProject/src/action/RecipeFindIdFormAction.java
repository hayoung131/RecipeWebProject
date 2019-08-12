package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeFindIdFormService;
import vo.ActionForward;
import vo.FindIdInfo;

public class RecipeFindIdFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1. 아이디 찾기위한 정보를 담는 vo를 만들고 jsp form으로 부터 온 값을 저장
		FindIdInfo findIdInfo = new FindIdInfo();
		findIdInfo.setName(request.getParameter("name"));
		findIdInfo.setPhoneNumber(request.getParameter("phoneNumber"));
		
		//2. 아이디 찾기 트랜젝션을 처리할 서비스 클래스 생성
		RecipeFindIdFormService recipeFindIdFormService=new RecipeFindIdFormService();
		
		//3. 서비스 클래스에 vo전달/ 반환값: 아이디가 든 string 객체 
		String matchedId=recipeFindIdFormService.findId(findIdInfo);
		ActionForward forward=null;
		
		//4. 상황에 맞게 forward나 토스트 띄워주기
		if(matchedId != "0") {
			//해당 아이디가 존재한단 의미
			forward=new ActionForward();
			forward.setUrl("/findIdView.jsp");
			request.setAttribute("matchedId", matchedId);
		}
		else {
			//경고창 띄우고, 아이디찾기 창 다시 띄워주기
			response.setContentType("text/html;charset=UTF-8");//마인드타입은 text/html로
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록된 아이디가 없습니다')");
			out.println("history.back()");//이전 url 로 돌아가기
			out.println("</script>");
		}
		
		return forward;
	}

}
