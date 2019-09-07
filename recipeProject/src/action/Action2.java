package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action2 {
	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
