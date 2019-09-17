package action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ChatSelectServingsService;
import svc.ChatBringExceptService;
import vo.AnswerFrame;

public class SelectServingsAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String answer = "";
		String element = "";
		String addBotButton = 
				  "<div class='question1' id = 'exclude_div' style='margin: 10px; margin-left: 50px;'>"
				+"<input id = 'excludeIngre' type='text' placeholder='제외할 재료를 추가' style='margin:8px; width:200px;padding-bottom:3px;'>"
				+ "<button onclick='submitFunction(0)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
				+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>추가</button>"
				+ "<button onclick='submitFunction(1)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
				+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>삭제</button>"
				+ "<button onclick='submitFunction(2)' class='btn btn-secondary btn-sm' role='button' aria-pressed='true'"
				+ "style='text-align: right;margin:2px;padding:4px 10px 4px 10px;' type='submit' name='exceptBtn'>완료</button>";
		String servingsNum = request.getParameter("servingsNum");// 몇인분 입력했는지 get

		System.out.println("servingsNum : " + servingsNum);

		session.setAttribute("servingsNum", servingsNum);// session에 공유

		String user_id = (String) session.getAttribute("user_id");

		if (user_id == "null" || user_id == null) {
			user_id = "비회원";
		}

		ChatBringExceptService chatBringExceptService = new ChatBringExceptService();
		ArrayList<String> exceptIngre = chatBringExceptService.bringExceptIngre(user_id);
		// 제외재료 체크박스 코드 생성부분

		int arrayLength = exceptIngre.size();
		int divCount = arrayLength;
		String code = "";
		int index = 0;
		for (int i = 0; i < Math.round(divCount / 2.0); i++) {// 출력해야하는 행 수만큼 돌것
			System.out.println((i + 1) + "번째 for문");
			code += "<div class='row'>\n";
			for (int u = 0; u < 2; u++) {// 한행에 두번씩 돌되
				if (arrayLength != 0) {
					code += "<div class='col'>\n"
							+ " <div class='btn-group-toggle' data-toggle='buttons' style='padding:3px;'>\n"
							+ " <label class='btn btn-sm btn-outline-danger ' style='height: 19px;margin:3px;'>\n"
							+ " <input type='checkbox' class ='checkIngre' name='num' idx='" + exceptIngre.get(index)
							+ "' value='" + exceptIngre.get(index) + "'>\n" + " </label>";// 채크박스 코드1
					code += exceptIngre.get(index++);// 배열에서 하나의 값 빼내기
					code += "</div>\n" + "</div>" + "\n";// 채크박스 코드2
					arrayLength--;// arrayList에서 값 빼내기
				}
			}
			code += "</div>" + "\n\n";
		}
		System.out.println(code);
		// 여기까지
		System.out.println("왜 안돼ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ");
		SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
		Calendar cal = Calendar.getInstance();
		String currentTime = sdf.format(cal.getTime());

		AnswerFrame exceptForm = new AnswerFrame();
		exceptForm.setCurrentTime(currentTime);
		exceptForm.setUserName(user_id);
		exceptForm.setSelectHSentence(servingsNum + "인분");
		exceptForm.setSelectBSentence(
				"니가 제외 설정한 재료들이야. 추가하려면 하단에 입력해 줘" + "<div id = 'direct_chat_reset' style='margin-top:15px;'>" + code);
		// 디비에서 sql로 유저가 입력했던 제외설정 정보 가져와서 ui생성해서 추가하기

		exceptForm.setAddBotButton(addBotButton + "</div>");
		answer = exceptForm.getCompleteSentence();
		return answer;
	}
}
