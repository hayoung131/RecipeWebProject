package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.RecipeAddFavoritesAction;
import action.RecipeConfirmPwdAction;
import action.RecipeRankingListAction;
import action.RecipeSignupFormAction;
import action.recipeMpFavotiteDeleteProAction;
import action.RecipeFindIdFormAction;
import action.RecipeFindPwdFormAction;
import action.RecipeFindPwdViewAction;
import action.RecipeLoginFormAction;
import action.RecipeLogoutAction;
import action.RecipeModMemInfoAction;
import action.RecipeMpFavoriteContentAction;
import action.RecipeMpFavoriteListAction;
import action.RecipeRankingContentAction;
import vo.ActionForward;

@WebServlet("*.bo") // 마지막 url이 *.bo 로 끝나는 요청을 매핑하는 서블릿으로 지정하는 부분임.

public class RecipeFrontController extends HttpServlet {
	static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getGlobal();

	public RecipeFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 요청 파악(랭킹보기 요청인지, 마이페이지 요청인지 등등)
		String requestURI = request.getRequestURI();

		// 요청 URL : http://localhost:8088/project/boardWriteForm.bo
		// requestURI : 애플리케이션이름부터 마지막까지 저장됨 /project/boardWriteForm.bo
		String contextPath = request.getContextPath();
		// contextPath : /project 이게 넘어옴

		String command = requestURI.substring(contextPath.length());
		/*
		 * 인덱스부터 마지막 인덱스까지 출력해라 /project 를 줬으니까 8번쨰 index부터 index는 0부터 카운트됨 따라서
		 * /boardWriteForm.bo
		 */
		System.out.print("url 주소 :    " + command + "\n");
		// 2.요청에 해당하는 비지니스 로직 호출
		Action action = null;// 다형성 이용하여 인터페이스 만들기 ctrl+1누르면 create interface 클릭 이제 비지니스모델 만들거임 인터페이스로
		ActionForward forward = null;// Action호출되면 ActionForward가 반환이 되니 이걸 저장할곳.

		// 3. 요청별 처리
		if (command.equals("/recipeLoginFrom.bo")) {// 만약 로그인 요청이 넘어왔다면 ?
			action = new RecipeLoginFormAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명 action
													// 주고 생성하기
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/recipeRankingList.bo")) {// 만약 글쓰기 요청이 넘어왔다면 ?
			action = new RecipeRankingListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/recipeFindIdForm.bo")) {
			action = new RecipeFindIdFormAction();
			try {

				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/recipeLoginForm.bo")) { 
			 action=new RecipeLoginFormAction(); 
			 try { 
				 forward=action.execute(request,response);
			 }catch(Exception e) { 
				 e.printStackTrace(); 
				 } 
		}else if(command.equals("/recipeFindPwdForm.bo")) { 
			 action=new RecipeFindPwdFormAction(); 
			 try { 
				/*
				 * String b=request.getParameter("findPwd-name");
				 * System.out.print("\n          값값    "+b+"\n");
				 */
				 forward=action.execute(request,response);
			 }catch(Exception e) { 
				 e.printStackTrace(); 
				 } 
		}else if(command.equals("/recipeFindPwdView.bo")) { 
			 action=new RecipeFindPwdViewAction(); 
			 try { 
				 String b=request.getParameter("matchedId");
				 System.out.print("\n          matchedId값값    "+b+"\n");
				 forward=action.execute(request,response);
			 }catch(Exception e) { 
				 e.printStackTrace(); 
				 } 
		}else if(command.equals("/recipeRankingContent.bo")){
			action = new RecipeRankingContentAction();
			try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}


		}else if(command.equals("/recipeSignupForm.bo")){
			action = new RecipeSignupFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else if(command.equals("/recipeMpFavoriteContent.bo")){
			action = new RecipeMpFavoriteContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/recipeMpFavotiteDeletePro.bo")){
			action = new recipeMpFavotiteDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/recipeAddFavorites.bo")){
					action = new RecipeAddFavoritesAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		 else if(command.equals("/recipeMpFavoriteList.bo")){
			action = new RecipeMpFavoriteListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else if(command.equals("/recipeMpFavotiteDeletePro.bo")){
			action = new recipeMpFavotiteDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/recipeConfirmPwd.bo")){
			action = new RecipeConfirmPwdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/recipeModMemInfo.bo")){
			action = new RecipeModMemInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/recipeLogout.bo")){
			action = new RecipeLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 4. 뷰페이지로 포워딩. 화면에 띄워주는부분
		if (forward != null) {// null이 아니다? 요청처리가 제대로 됐다는 의미
			if (forward.isRedirect()) {// 리다이렉트방식
				response.sendRedirect(forward.getUrl());
			} else {// 디스패치방식 기본이 이방식, request, response가 공유됨.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);// get으로 request가 오든
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);// post로 request가 오든 doProcess()함수에서 해결해라
	}

}
