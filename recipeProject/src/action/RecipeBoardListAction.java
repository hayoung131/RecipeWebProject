package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeBoardListService;
import vo.ActionForward;
import vo.Board;
import vo.PageInfo;

public class RecipeBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 int pageSize = 15;//한 페이지당 출력되는 글의 개수
		 String pageNum = request.getParameter("pageNum");//언제 request 영역에 공유된거임..;
		    if (pageNum == null) {//아.. 공유된게 없다면 null이 들어올거고,, 그런경우 1로 셋팅하는구나.
		        pageNum = "1";
		    }

		    int currentPage = Integer.parseInt(pageNum);
		    
		    int startRow = (currentPage - 1) * pageSize + 1; //2 ===>  (2 - 1) * 10 + 1
		    //즉 1페이지면 db상에서 1행부터 시작, 2페이지면 11행부터, 3페이지면 21행부터니깐
		    
		 
		    int count = 0;
		    //총 글의 개수를 저장할 변수
		    int number=0;
		    //해당 페이지에 첫번째로 출력되는 글의 번호

		    List<Board> articleList = null;
		    RecipeBoardListService boardListService
		    = new RecipeBoardListService();
		    
		    count = boardListService.getArticleCount();
		    if (count > 0) {
		        articleList = boardListService.getArticles(startRow, pageSize);
		    }

			number=count-(currentPage-1)*pageSize;
			//총 글의 개수 : 132
			//현재 페이지 : 1
			//글번호 : 132 -  (1 - 1) * 10
			int pageCount = 0;
			int startPage = 0;
			int endPage = 0;
			 if (count > 0) {
			        pageCount = count / pageSize + 
			        		( count % pageSize == 0 ? 0 : 1);
					 
			        startPage = ((currentPage-1)/10)*10+1;
			        //
			        
					int pageBlock=10;
			        endPage = startPage + pageBlock-1;
			        if (endPage > pageCount) endPage = pageCount;
			 }
			 //이제 속성으로 공유해야하니까 포워딩해야됨
			 request.setAttribute("articleList", articleList);
			 //페이징 정보를 저장하는 클래스를 설계하고 객체 만들고 그걸 공유하는게 훨씬 편하니 클래스 설계하자.
			 PageInfo pageInfo = new PageInfo();
			 pageInfo.setCount(count);
			 pageInfo.setCurrentPage(currentPage);
			 pageInfo.setEndPage(endPage);
			 pageInfo.setNumber(number);
			 pageInfo.setPageCount(pageCount);
			 pageInfo.setStartPage(startPage);
			 request.setAttribute("pageInfo", pageInfo);
			 
			 ActionForward forward =  new ActionForward();
			 forward.setUrl("/rankingList.jsp");
		return forward;
	}

}









