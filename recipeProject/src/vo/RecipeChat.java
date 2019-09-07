package vo;

public class RecipeChat {
	
	private int num;
	private String cooking_title;
	private String cooking_tips;
	private String cooking_time;
	private String cooking_level;
	private int hit_standard;
	private boolean isBookmark;
	
	public boolean isBookmark() {
		return isBookmark;
	}
	public void setBookmark(boolean isBookmark) {
		this.isBookmark = isBookmark;
	}
	public int getHit_standard() {
		return hit_standard;
	}
	public void setHit_standard(int hit_standard) {
		this.hit_standard = hit_standard;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCooking_title() {
		return cooking_title;
	}
	public void setCooking_title(String cooking_title) {
		this.cooking_title = cooking_title;
	}
	public String getCooking_tips() {
		return cooking_tips;
	}
	public void setCooking_tips(String cooking_tips) {
		this.cooking_tips = cooking_tips;
	}
	public String getCooking_time() {
		return cooking_time;
	}
	public void setCooking_time(String cooking_time) {
		this.cooking_time = cooking_time;
	}
	public String getCooking_level() {
		return cooking_level;
	}
	public void setCooking_level(String cooking_level) {
		this.cooking_level = cooking_level;
	}

}
