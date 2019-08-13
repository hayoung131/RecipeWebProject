package vo;

public class SignupInfo {
	private String name;
	private String id;
	private String pwd;
	private String phone;
	private int findQuestion;//질문의 value값이 들어오게됨
	private String findAnswer;
	private String exceptIngredients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getFindQuestion() {
		return findQuestion;
	}
	public void setFindQuestion(int findQuestion) {
		this.findQuestion = findQuestion;
	}
	public String getFindAnswer() {
		return findAnswer;
	}
	public void setFindAnswer(String findAnswer) {
		this.findAnswer = findAnswer;
	}
	public String getExceptIngredients() {
		return exceptIngredients;
	}
	public void setExceptIngredients(String exceptIngredients) {
		this.exceptIngredients = exceptIngredients;
	}
	
}
