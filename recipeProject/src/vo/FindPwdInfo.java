package vo;

public class FindPwdInfo {
	private String id;
	private String name;
	private int findQuestion;//질문의 value값이 들어오게됨
	private String findAnswer;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
