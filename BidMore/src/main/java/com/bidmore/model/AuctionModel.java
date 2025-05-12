package com.bidmore.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AuctionModel {
	private int auctionId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private float startPrice;
	private float reservePrice;
	private UserModel user;
	private ItemModel item;
	private int hoursLeft;
	
	public AuctionModel(LocalDateTime endTime,float startPrice,float reservePrice, UserModel user, ItemModel item ) {
		this.endTime=endTime;
		this.startPrice=startPrice;
		this.reservePrice=reservePrice;
		this.user=user;
		this.item= item;
					
		
	}

	public AuctionModel() {
		// TODO Auto-generated constructor stub
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public float getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(float startPrice) {
		this.startPrice = startPrice;
	}

	public float getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(float reservePrice) {
		this.reservePrice = reservePrice;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ItemModel getItem() {
		return item;
	}

	public void setItem(ItemModel item) {
		this.item = item;
	}

	public int getHoursLeft() {
		return hoursLeft;
	}

	public void setHoursLeft(int hoursLeft) {
		this.hoursLeft = hoursLeft;
	}

}
