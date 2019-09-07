package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.Action2;
import action.ChatRecipeAddFavoritesAction;
import action.ChatShowServingsAction;
import action.DeleteExcludeIngreNameAction;
import action.RecipeOrIngreQAction;
import action.RecipeSearchInfoAction;
import action.RecipeSearchListAction;
import action.ResetChatAction;
import action.SelectServingsAction;
import action.SendExcludeIngreNameAction;
import action.SendIngreNameAction;
import action.SendRecipeInfoForNonMemAction;
import action.SendRecipeNameAction;
import vo.ActionForward;

@WebServlet("*.do")
public class chatbotFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);// get으로 request가 오든
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
		Action action = null;// 얘를 action2로 변경해야하고
		Action2 action2 = null;// 기존 action2로 되어있던걸 action으로 변경시켜야됨.
		ActionForward forward = null;// Action호출되면 ActionForward가 반환이 되니 이걸 저장할곳.
		String answer = "";
		if (command.equals("/recipeOrIngreQ.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			action2 = new RecipeOrIngreQAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명 action
													// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/sendRecipeName.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			request.setCharacterEncoding("UTF-8");
			action2 = new SendRecipeNameAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명 action
													// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/sendIngreName.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			request.setCharacterEncoding("UTF-8");
			action2 = new SendIngreNameAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명 action
												// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/sendRecipeInfoForNonMem.do")) {
			request.setCharacterEncoding("UTF-8");
			action2 = new SendRecipeInfoForNonMemAction();

			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/selectServings.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			request.setCharacterEncoding("UTF-8");
			action2 = new SelectServingsAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명 action
													// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/sendExcludeIngreName.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			request.setCharacterEncoding("UTF-8");
			action2 = new SendExcludeIngreNameAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명
														// action
														// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deleteExcludeIngreName.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			request.setCharacterEncoding("UTF-8");
			action2 = new DeleteExcludeIngreNameAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create
															// class-패키지명
															// action
			// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/recipeSearchList.do")) {// 만약 로그인 요청이 넘어왔다면 ?
			action2 = new RecipeSearchListAction();// 요 모델에서 처리하겟다! 즉 이 action 클래스에서 처리하겠다.ctrl+1 create class-패키지명
													// action
			// 주고 생성하기
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/searchRecipeContent.do")) {
			action = new RecipeSearchInfoAction();

			try {
				answer = "";
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/chatRecipeAddFavorites.do")) {
			action = new ChatRecipeAddFavoritesAction();

			try {
				answer = "";
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/showServings.do")) {
			action2 = new ChatShowServingsAction();
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/resetChat.do")) {
			action2 = new ResetChatAction();
			try {
				answer = action2.execute(request, response);
				System.out.print("answer : " + answer + "\n"
						+ "-------------------------------------------------------------------------------------------------\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 4. 뷰페이지로 포워딩. 화면에 띄워주는부분
		if (answer != "") {// null이 아니다? 요청처리가 제대로 됐다는 의미
			response.getWriter().write(answer);
		} else if (forward != null) {// null이 아니다? 요청처리가 제대로 됐다는 의미

			if (forward.isRedirect()) {// 리다이렉트 방식
				response.sendRedirect(forward.getUrl());
			} else {// 디스패치방식 기본이 이방식, request, response가 공유됨.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}
//	public String getJSON(String questionNum) {
//		
//		String question = "레시피명으로 검색";
//		String z =null;
//		
//		if(questionNum.equals("1")) {
//			z = "{ \"question\" : \""+question+"\"}";
//			System.out.println("questionNum = "+questionNum);
//		}
//		return z;
//	
//	}

}
