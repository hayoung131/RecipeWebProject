package vo;

import svc.RecipeSearchListService;

public class SearchInformation {

	private String title;
	private String [] haveIngredients ;
	private int serving;
	private String id;
	private String [] hateIngredients;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getHaveIngredients() {
		return haveIngredients;
	}
	public void setHaveIngredients(String[] haveIngredients) {
		this.haveIngredients = haveIngredients;
	}
	public int getServing() {
		return serving;
	}
	public void setServing(int serving) {
		this.serving = serving;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getHateIngredients() {
		return hateIngredients;
	}
	public void setHateIngredients(String[] hateIngredients) {
		this.hateIngredients = hateIngredients;
	}
}
