package vo;
//뷰 페이지로 포워딩할 때 필요한 정보를 저장하는 클래스
public class ActionForward {
	private String url;//alt+shift+s
	private boolean redirect;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
