package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.RecipeLoginFormAction;
import vo.ActionForward;


@WebServlet("*.bo")//������ url�� *.bo �� ������ ��û�� �����ϴ� �������� �����ϴ� �κ���. 

public class RecipeFrontController extends HttpServlet{
	static final long serialVersionUID=1L;
	
	public RecipeFrontController() {
		super();
		String s="Hello";
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		//1. ��û �ľ�(��ŷ���� ��û����, ���������� ��û���� ���)
		String requestURI=request.getRequestURI();
		//��û URL : http://localhost:8088/project/boardWriteForm.bo
		//requestURI : ���ø����̼��̸����� ���������� ����� /project/boardWriteForm.bo
		String contextPath=request.getContextPath();
		//contextPath : /project �̰� �Ѿ��
		
		String command=requestURI.substring(contextPath.length());
		/*�ε������� ������ �ε������� ����ض�  /project �� �����ϱ� 8���� index����
		 * index�� 0���� ī��Ʈ�� ���� /boardWriteForm.bo  */
		
		//2.��û�� �ش��ϴ� �����Ͻ� ���� ȣ��
		Action action =null;//������ �̿��Ͽ� �������̽� ����� ctrl+1������ create interface Ŭ�� ���� �����Ͻ��� ������� �������̽���
		ActionForward forward=null;//Actionȣ��Ǹ� ActionForward�� ��ȯ�� �Ǵ� �̰� �����Ұ�.
		
		//3. ��û�� ó��
		if(command.equals("/recipeLoginFrom.bo")) {//���� �α��� ��û�� �Ѿ�Դٸ� ?
			action=new RecipeLoginFormAction();//�� �𵨿��� ó���ϰٴ�! �� �� action Ŭ�������� ó���ϰڴ�.ctrl+1 create class-��Ű���� action �ְ� �����ϱ�
			try {
				forward=action.execute(request,response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//else if
		
		//4. ���������� ������. ȭ�鿡 ����ִºκ�
		if(forward != null) {//null�� �ƴϴ�? ��ûó���� ����� �ƴٴ� �ǹ�
			if(forward.isRedirect()) {//�����̷�Ʈ���
				response.sendRedirect(forward.getUrl());
			}
			else {//����ġ��� �⺻�� �̹��, request, response�� ������.
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);//get���� request�� ����
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request,response);//post�� request�� ���� doProcess()�Լ����� �ذ��ض�
	}

}
