package vo;

public class ModInfo {
	private String current_pwd;
	private String new_pwd;
	private String mod_phone;
	private String id;
	private int mod_question;
	private String mod_answer;
	public String getCurrent_pwd() {
		return current_pwd;
	}
	public void setCurrent_pwd(String current_pwd) {
		this.current_pwd = current_pwd;
	}
	public String getNew_pwd() {
		return new_pwd;
	}
	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}
	public String getMod_phone() {
		return mod_phone;
	}
	public void setMod_phone(String mod_phone) {
		this.mod_phone = mod_phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMod_question() {
		return mod_question;
	}
	public void setMod_question(int mod_question) {
		this.mod_question = mod_question;
	}
	public String getMod_answer() {
		return mod_answer;
	}
	public void setMod_answer(String mod_answer) {
		this.mod_answer = mod_answer;
	}
}
