package com.bidmore.model;



public class ItemModel {
	private int itemId;
	private String itemName;
	private String description;
	private String condition;
	private String category;
	private String imagePath;
	private UserModel user;
	
	
	
	public ItemModel(String itemName, String description, String condition, String category, String imagePath, UserModel user) {
		this.itemName = itemName;
		this.description = description;
		this.condition = condition;
		this.category = category;
		this.imagePath = imagePath;
		this.user = user;
	
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagepath) {
		this.imagePath = imagepath;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
