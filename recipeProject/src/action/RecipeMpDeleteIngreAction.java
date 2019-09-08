package action;

import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipeMpDeleteIngreService;
import vo.ActionForward;

public class RecipeMpDeleteIngreAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(); //아이디 값을 불러옴.
		String user_id = (String)session.getAttribute("user_id");
		String [] checkedHateIngre = request.getParameterValues("hateIngre");
		String btnName = request.getParameter("btnName");
		System.out.println("무슨버튼이 눌렸니 : "+btnName);
		
		
		
		
		boolean check_success = false;
		
		RecipeMpDeleteIngreService recipeMpDeleteIngreService = new RecipeMpDeleteIngreService();
		ActionForward forward = null;
		//삭제버튼 눌렀을때
		if(btnName.equals("1") && checkedHateIngre!=null && checkedHateIngre.length !=0) {

			check_success =  recipeMpDeleteIngreService.deleteIngre(user_id,checkedHateIngre);
			
			if(check_success){
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setUrl("hateIngreList.bo");
			}
			else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			return forward;
		}else if(btnName.equals("2") ) {
			String addIngre = URLDecoder.decode(request.getParameter("addIngre"), "UTF-8");//이케 해줘야 안깨지고 한글 받을 수 있음.
			if(addIngre!=null){
				addIngre=addIngre.replaceAll(" ","");
				String[] IngreNames = addIngre.split(",");
				
				for (String n :IngreNames) {
					System.out.println(" 배열:"+n);
				}
				check_success = recipeMpDeleteIngreService.insertIngre(user_id,IngreNames);
				
				if(check_success){
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setUrl("hateIngreList.bo");
				}
				else{
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('추가실패')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
			return forward;
			
		}
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setUrl("hateIngreList.bo");
		
		return forward;
	}

}
