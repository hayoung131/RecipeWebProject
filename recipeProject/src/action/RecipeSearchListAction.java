package action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecipeSearchListService;
import vo.ActionForward;
import vo.LastAnswerTag;
import vo.LastAnswerTag2;
import vo.SearchInformation;
import vo.SearchResult;


public class RecipeSearchListAction implements Action2 {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		//*****여기서부터******
		/*String title = "삼겹살 덮밥";
		String [] haveIngredients = new String[] {"삼겹살","양파","양배추","계란"};
		int serving = 2;
		String id = "dpwls0947";*/
		
		//서비스 파일에서 해당 회원이 싫어하는 재료를 dao를 통해서 미리 가져온다음 최종 sql문에 같이 보내면 됨.
		
		//세션에 레시피명이 있는지 재료명이 있는지 확인을 하나하나 해서 조건에 맞는 sql문으로 보내는거 하면 됨.
		
	/*	session.setAttribute("id", id);
		session.setAttribute("title", title);
		session.setAttribute("haveIngredients", haveIngredients);
		session.setAttribute("serving", serving);*/
		//*****여기까지는 이미 앞에서 데이터를 세션에 저장해뒀다는 가정하임.*****************************
		
		String importance = request.getParameter("importance");
		String accuracy = request.getParameter("accuracy");
		String order = "accuracy";
		String checkNumm = "0";
		String isOnlyIngre = "0";
		int negative_number;
		
		List<SearchResult> searchResultList = null;
		LastAnswerTag lastAnswerTag = new LastAnswerTag(); //여기에 보낼 태그를 붙이는거임.
		LastAnswerTag2 lastAnswerTag2 = new LastAnswerTag2();
		String forward = "";
		
		RecipeSearchListService recipeSearchListService  //서비스클래스 이용하려고 선언
		= new RecipeSearchListService();

		String id = (String)session.getAttribute("user_id");
		System.out.println("이것은 아이디 로그인했나? 비회원왜 안뜸"+id);
		
		String [] hateIngredients = recipeSearchListService.selectHateIngre(id);  // 사용자가 설정해둔 싫어하는 재료 목록
		SearchInformation searchInformation = new SearchInformation();
		searchInformation.setHateIngredients(hateIngredients);

		
		String [] ingre = (String[])session.getAttribute("IngreNames");
		System.out.println("+++++레시피명들 나왔니 : " + (String)session.getAttribute("recipeName"));
		/*System.out.println("+++++재료명들 나왔니 : " + ingre[0]);*/
		
		
		if((String)session.getAttribute("recipeName")==null && (String[])session.getAttribute("IngreNames") !=null ) {
			System.out.println("===재료명만 있음");
			
			searchInformation.setHaveIngredients((String[])session.getAttribute("IngreNames")); //가지고있는 재료
			String servingsNum = (String)session.getAttribute("servingsNum");
			searchInformation.setServing(Integer.parseInt(servingsNum));//몇인분으로 요리할지
			isOnlyIngre = "1";
			checkNumm="0";	
			
		}
		if( (String[])session.getAttribute("IngreNames")==null && (String)session.getAttribute("recipeName")!=null) {
			System.out.println("레시피명만 있음");
			
			searchInformation.setTitle((String)session.getAttribute("recipeName"));//레시피명
			String servingsNum = (String)session.getAttribute("servingsNum");
			searchInformation.setServing(Integer.parseInt(servingsNum));//몇인분으로 요리할지
			
			checkNumm="1";
		}
		if((String[])session.getAttribute("IngreNames") !=null && (String)session.getAttribute("recipeName")!=null) {
			System.out.println("둘 다 있음");
			
			searchInformation.setTitle((String)session.getAttribute("recipeName")); //레시피명
			searchInformation.setHaveIngredients((String[])session.getAttribute("IngreNames")); //가지고있는 재료
			String servingsNum = (String)session.getAttribute("servingsNum");
			searchInformation.setServing(Integer.parseInt(servingsNum));//몇인분으로 요리할지
			
			isOnlyIngre="0";
			checkNumm="0";
		}
				
		// 비회원이 검색한거라면 
		if(id.equals("비회원")) {
			
			System.out.println("@@비회원 입니다.");
			
			//재료명만 or 레시피명+재료명으로 검색할 때
			if(checkNumm.equals("0")) {
				
				//추천순으로 눌렀을때
				if(importance != null) {
					System.out.println("비회원 + 추천순으로 누릅니다");
					
					if(isOnlyIngre.equals("0")) {
						System.out.println("재료명 레시피명 모두 존재합니다");
						searchResultList = recipeSearchListService.selectSearchResultImNo(searchInformation, importance);
						
					}else if(isOnlyIngre.equals("1")) {
						System.out.println("재료명만 존재합니다");
						searchResultList = recipeSearchListService.selectSearchOnlyIngreImNo(searchInformation,importance);
						//여기에 서비스를 통해   searchResultList를 반환하는 코드
					}
					
					//forward에 문자열 붙이는 부분
					
					forward +=  lastAnswerTag.getStringTag2();
					
					for(int i = 0; i < searchResultList.size(); i++ ) {
						SearchResult searchResult = (SearchResult)searchResultList.get(i); 
						negative_number = (int)searchResult.getAccuracy();
						if(negative_number < 0) {
							negative_number = 0;
						}
						forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
						
						if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
							forward += searchResult.getTitle().substring(0,20)+"...";
						}else {
							forward +=searchResult.getTitle();
						}
						forward += lastAnswerTag.getStringTag5()+
								negative_number+"개"+lastAnswerTag.getStringTag6();
						
					}forward += lastAnswerTag.getStringTag7();
					
					return forward;
					
				}else if (accuracy != null) {
					//정확도 순으로 눌렀을때
					
					System.out.println("아큐러시 값이 있다?! 비회원 + 정확도"+accuracy);
					
					if(isOnlyIngre.equals("0")) {
						//재료 + 레시피명
						System.out.println("재료+레시피명");
						searchResultList = recipeSearchListService.selectSearchResultNo(searchInformation,accuracy);}
					else if(isOnlyIngre.equals("1")) {
						//여기에 재료만 했을때ㅐㅐㅐ
						System.out.println("재료");
						searchResultList = recipeSearchListService.selectSearchOnlyIngreNo(searchInformation,accuracy);
						}
					
					forward +=  lastAnswerTag.getStringTag2();
					
					for(int i = 0; i < searchResultList.size(); i++ ) {
						SearchResult searchResult = (SearchResult)searchResultList.get(i); 
						negative_number = (int)searchResult.getAccuracy();
						if(negative_number < 0) {
							negative_number = 0;
						}
						forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
						
						if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
							forward += searchResult.getTitle().substring(0,20)+"...";
						}else {
							forward +=searchResult.getTitle();
						}
						forward += lastAnswerTag.getStringTag5()+
								negative_number+"개"+lastAnswerTag.getStringTag6();
						
					}forward += lastAnswerTag.getStringTag7();
					
					return forward;
						
				}
				//비회원 처음화면
				System.out.println("????!"+order);
				if(isOnlyIngre.equals("0")) {
					searchResultList = recipeSearchListService.selectSearchResultNo(searchInformation, order);}
				else if(isOnlyIngre.equals("1")) {
					searchResultList = recipeSearchListService.selectSearchOnlyIngreNo(searchInformation,accuracy);}
					//여기에 재료만 했을때ㅐㅐㅐ
				
				forward += lastAnswerTag.getStringTag1()+ lastAnswerTag.getStringTag2();
				
				for(int i = 0; i < searchResultList.size(); i++ ) {
					SearchResult searchResult = (SearchResult)searchResultList.get(i); 
					negative_number = (int)searchResult.getAccuracy();
					if(negative_number < 0) {
						negative_number = 0;
					}
					forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
					
					if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
						forward += searchResult.getTitle().substring(0,20)+"...";
					}else {
						forward +=searchResult.getTitle();
						System.out.println("제목들 : " + searchResult.getTitle());
					}
					forward += lastAnswerTag.getStringTag5()+
							negative_number+"개"+lastAnswerTag.getStringTag6();
					
				}
				
				forward += lastAnswerTag.getStringTag7() + lastAnswerTag.getStringTag8();
				System.out.println("forward : " + forward);
				return forward;
				
			}else if(checkNumm.equals("1")) {
				//비회원 제목만
				System.out.println("비회원 제목만 ");
				
				searchResultList = recipeSearchListService.selectSearchOnlyTitleNo(searchInformation);
				////여기에 레시피명만 입력하면 나오도록 하는거임.
				
				forward += lastAnswerTag2.getStringTag1()+lastAnswerTag2.getStringTag2();
				
				for(int i = 0; i < searchResultList.size(); i++ ) {
					SearchResult searchResult = (SearchResult)searchResultList.get(i);
					forward += lastAnswerTag2.getStringTag3()+ searchResult.getNum()+lastAnswerTag2.getStringTag4();
					
					if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
						forward += searchResult.getTitle().substring(0,20)+"...";
					}else {
						forward +=searchResult.getTitle();
					}
					forward += lastAnswerTag2.getStringTag5()+lastAnswerTag2.getStringTag6();
					
				}
				
					forward += lastAnswerTag2.getStringTag7() + lastAnswerTag2.getStringTag8();
				
			}
			
			
		}else { //회원일때 
			//재료명만으로 검색했거나 레시피명+재료명으로 검색할때만 하는 거임....!
			if(checkNumm.equals("0")) {
			
				//추천순으로 눌렀을때
				if(importance !=null) {
					System.out.println("임폴턴스 값이 있다?!"+importance);
					if(isOnlyIngre.equals("0")) {
						System.out.println("\n 재료 레시피명 다있고 중요도순으로 나열함");
						searchResultList = recipeSearchListService.selectSearchResultIm(searchInformation,importance);}
					else if(isOnlyIngre.equals("1")) {
						searchResultList = recipeSearchListService.selectSearchOnlyIngreIm(searchInformation,importance);}
						//여기에 재료만 했을때ㅐㅐㅐ
						
					forward +=  lastAnswerTag.getStringTag2();
					
					for(int i = 0; i < searchResultList.size(); i++ ) {
						SearchResult searchResult = (SearchResult)searchResultList.get(i); 
						negative_number = (int)searchResult.getAccuracy();
						if(negative_number < 0) {
							negative_number = 0;
						}
						forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
						
						if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
							forward += searchResult.getTitle().substring(0,20)+"...";
						}else {
							forward +=searchResult.getTitle();
						}
						forward += lastAnswerTag.getStringTag5()+
								negative_number+"개"+lastAnswerTag.getStringTag6();
						
					}forward += lastAnswerTag.getStringTag7();
					
					return forward;
					//정확도순으로 눌렀을때
				}else if (accuracy != null) {
					
					System.out.println("아큐러시 값이 있다?!"+accuracy);
					
					if(isOnlyIngre.equals("0")) {
						searchResultList = recipeSearchListService.selectSearchResult(searchInformation,accuracy);}
					else if(isOnlyIngre.equals("1")) {
						searchResultList = recipeSearchListService.selectSearchOnlyIngre(searchInformation,accuracy);}
						//여기에 재료만 했을때ㅐㅐㅐ}
					
					forward +=  lastAnswerTag.getStringTag2();
					
					for(int i = 0; i < searchResultList.size(); i++ ) {
						SearchResult searchResult = (SearchResult)searchResultList.get(i); 
						negative_number = (int)searchResult.getAccuracy();
						if(negative_number < 0) {
							negative_number = 0;
						}
						forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
						
						if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
							forward += searchResult.getTitle().substring(0,20)+"...";
						}else {
							forward +=searchResult.getTitle();
						}
						forward += lastAnswerTag.getStringTag5()+
								negative_number+"개"+lastAnswerTag.getStringTag6();
						
					}forward += lastAnswerTag.getStringTag7();
					
					return forward;
					
				}
				//제일 처음 화면 ㅇㅇ
					System.out.println("????!"+order);
					if(isOnlyIngre.equals("0")) {
						searchResultList = recipeSearchListService.selectSearchResult(searchInformation,accuracy);}
					else if(isOnlyIngre.equals("1")) {
						searchResultList = recipeSearchListService.selectSearchOnlyIngre(searchInformation,accuracy);}
						//여기에 재료만 했을때ㅐㅐㅐ
					
					forward += lastAnswerTag.getStringTag1()+ lastAnswerTag.getStringTag2();
					
					for(int i = 0; i < searchResultList.size(); i++ ) {
						SearchResult searchResult = (SearchResult)searchResultList.get(i); 
						negative_number = (int)searchResult.getAccuracy();
						if(negative_number < 0) {
							negative_number = 0;
						}
						forward += lastAnswerTag.getStringTag3() + searchResult.getNum()+lastAnswerTag.getStringTag4();
						
						if(searchResult.getTitle().length() > 20) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
							forward += searchResult.getTitle().substring(0,20)+"...";
						}else {
							forward +=searchResult.getTitle();
							System.out.println("제목들 : " + searchResult.getTitle());
						}
						forward += lastAnswerTag.getStringTag5()+
								negative_number+"개"+lastAnswerTag.getStringTag6();
						
					}
					
					forward += lastAnswerTag.getStringTag7() + lastAnswerTag.getStringTag8();
					System.out.println("forward : " + forward);
					return forward;
				
			}else if(checkNumm.equals("1")) {
				
				searchResultList = recipeSearchListService.selectSearchOnlyTitle(searchInformation);
				////여기에 레시피명만 입력하면 나오도록 하는거임.
				
				forward += lastAnswerTag2.getStringTag1()+lastAnswerTag2.getStringTag2();
				
				for(int i = 0; i < searchResultList.size(); i++ ) {
					SearchResult searchResult = (SearchResult)searchResultList.get(i);
					forward += lastAnswerTag2.getStringTag3()+ searchResult.getNum()+lastAnswerTag2.getStringTag4();
					
					if(searchResult.getTitle().length() > 25) { // 제목길이가 9개보다 길면 9까지만 가져오고 뒤에 ...붙임
						forward += searchResult.getTitle().substring(0,25)+"...";
					}else {
						forward +=searchResult.getTitle();
					}
					forward += lastAnswerTag2.getStringTag5()+lastAnswerTag2.getStringTag6();
					
				}
				
					forward += lastAnswerTag2.getStringTag7() + lastAnswerTag2.getStringTag8();
			}
		}
		
		return forward;
	}

}
