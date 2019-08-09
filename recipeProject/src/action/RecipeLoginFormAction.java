package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeLoginFormService;
import vo.ActionForward;

public class RecipeLoginFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1. ������ request�� ������ü�κ��� �޾ƿð� �ִٸ� �޾ƿ� data bean ��ü�� �ֱ�
		//2. ����۾� ��û������ �ش� Ʈ������� �۾��� �ϱ����� ���񽺸� �����ϰ� �ش� ������ �����صи޼ҵ忡 �����ͺ�ü�� �Ѱ��ְ� return �� boolean������ �ޱ�  
		
		RecipeLoginFormService recipeLoginFormService
		=new RecipeLoginFormService();
		boolean loginSuccess=recipeLoginFormService.login(/*����ڰ� �Է��ߴ� �������� �� �����ͺ�*/);
		ActionForward forward = null;//�޼ҵ忡�� ���������� ��ȯ�� �����尪�� ������ ��
		if(loginSuccess){
			//������ ������Ȩ������ ����ȭ�� �����ֱ�
			forward = new ActionForward();
			forward.setRedirect(true);//������ ����� �����̷�Ʈ���ϰٴ�.
			forward.setUrl("����ȭ��.bo");
		}
		else{
			//���â ����, �۾��� â �ٽ� ����ֱ�
			response.setContentType("text/html;charset=UTF-8");//���ε�Ÿ���� text/html��
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���̵�,��й�ȣ�� Ʋ�Ƚ��ϴ�.')");
			out.println("history.back()");//���� url �� ���ư���
			out.println("</script>");
		}
		
		return forward;
	}

}
