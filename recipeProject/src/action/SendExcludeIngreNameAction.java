package action;

import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ChatBringExceptService;
import svc.SendExcludeIngreNameService;
import vo.AnswerFrame;

public class SendExcludeIngreNameAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		String userInputText = URLDecoder.decode(request.getParameter("userInputText"), "UTF-8");//이케 해줘야 안깨지고 한글 받을 수 있음.
		userInputText=userInputText.replaceAll(" ","");
		//입력받은게 아무것도 없으면 아무일도 안일어나게 함.
		if(userInputText.equals("")) {
			String addBotButton = 
					  "<div class='question1' id = 'exclude_div' style='margin: 10px; margin-left: 50px;'>"
					+"<input id = 'excludeIngre' type='text' placeholder='제외할 재료를 추가' style='margin:8px; width:200px;padding-bottom:3px;'>"
					+ "<button onclick='submitFunction(0)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
					+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>추가</button>"
					+ "<button onclick='submitFunction(1)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
					+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>삭제</button>"
					+ "<button onclick='submitFunction(2)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
					+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>완료</button>";

			
			ChatBringExceptService chatBringExceptService=new ChatBringExceptService();
			ArrayList<String> exceptIngre= chatBringExceptService.bringExceptIngre(user_id);
			int arrayLength=exceptIngre.size();
			int divCount=arrayLength;
			String code="";
			int index=0;
			for(int i=0;i<Math.round(divCount/2.0);i++) {//출력해야하는 행 수만큼 돌것
				System.out.println((i+1)+"번째 for문");
				code+="<div class='row'>\n";
				for(int u=0;u<2;u++) {//한행에 두번씩 돌되
					if(arrayLength!=0) {
						code+="<div class='col'>\n" + 
								" <div class='btn-group-toggle' data-toggle='buttons' style='padding:3px;'>\n" + 
								" <label class='btn btn-sm btn-outline-danger ' style='height: 19px;margin:3px;'>\n" + 
								" <input type='checkbox' class ='checkIngre' idx='"+exceptIngre.get(index)+"' name='num' value='"+exceptIngre.get(index)+"'>\n" + 
								" </label>"; //채크박스 코드1
						code += exceptIngre.get(index++);//배열에서 하나의 값 빼내기
								code+="</div>\n" + 
								"</div>"+"\n";//채크박스  코드2
						arrayLength--;//arrayList에서 값 빼내기
					}
				}
				code+="</div>"+"\n\n";
			}
			System.out.println(code);

			
			String answer = code+addBotButton;
			System.out.println(answer);
			
			
			return answer;
		}
		
		
		String[] IngreNames = userInputText.split(",");
		
		for (String n :IngreNames) {
			System.out.println(" 배열:"+n);
		}
		
		SendExcludeIngreNameService sendExcludeIngreNameService = new SendExcludeIngreNameService();
		String checkSuccess = sendExcludeIngreNameService.InsertExcludeIngre(user_id,IngreNames);
		
		if(checkSuccess!=null) {
			System.out.println("제외할 재료 목록 저장 성공");
			
		}
		
		String addBotButton = 
		"<div class='question1' id = 'exclude_div' style='margin: 10px; margin-left: 50px;'>"+
					 "<input id = 'excludeIngre' type='text' placeholder='제외할 재료를 추가' style='margin:8px; width:200px;padding-bottom:3px;'>"+
				  "<button onclick='submitFunction(0)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"+
				  "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>추가</button>"+
				  "<button onclick='submitFunction(1)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"+
				  "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>삭제</button>"+
				  "<button onclick='submitFunction(2)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"+
				  "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>완료</button>";
		
		ChatBringExceptService chatBringExceptService=new ChatBringExceptService();
		ArrayList<String> exceptIngre= chatBringExceptService.bringExceptIngre(user_id);
		int arrayLength=exceptIngre.size();
		int divCount=arrayLength;
		String code="";
		int index=0;
		for(int i=0;i<Math.round(divCount/2.0);i++) {//출력해야하는 행 수만큼 돌것
			System.out.println((i+1)+"번째 for문");
			code+="<div class='row'>\n";
			for(int u=0;u<2;u++) {//한행에 두번씩 돌되
				if(arrayLength!=0) {
					code+="<div class='col'>\n" + 
							" <div class='btn-group-toggle' data-toggle='buttons' style='padding:3px;'>\n" + 
							" <label class='btn btn-sm btn-outline-danger ' style='height: 19px;margin:3px;'>\n" + 
							" <input type='checkbox' class ='checkIngre' idx='"+exceptIngre.get(index)+"' name='num' value='"+exceptIngre.get(index)+"'>\n" + 
							" </label>"; //채크박스 코드1
					code += exceptIngre.get(index++);//배열에서 하나의 값 빼내기
							code+="</div>\n" + 
							"</div>"+"\n";//채크박스  코드2
					arrayLength--;//arrayList에서 값 빼내기
				}
			}
			code+="</div>"+"\n\n";
		}
		System.out.println(code);

		
		String answer = code+addBotButton;
		System.out.println(answer);
		
		
		return answer;
	}

}
