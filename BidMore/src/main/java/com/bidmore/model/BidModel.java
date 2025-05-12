package com.bidmore.model;

import java.time.LocalDateTime;

public class BidModel {
	private int bidId;
    private AuctionModel auction;
    private UserModel user; 
    private Float  bidAmount;
    private LocalDateTime bidTime;
	public int getBidId() {
		return bidId;
	}
	
	public BidModel() {
		
	}
	
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public AuctionModel getAuction() {
		return auction;
	}
	public void setAuction(AuctionModel auctionId) {
		this.auction = auctionId;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel userId) {
		this.user = userId;
	}
	public Float getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Float bidAmount) {
		this.bidAmount = bidAmount;
	}
	public LocalDateTime getBidTime() {
		return bidTime;
	}
	public void setBidTime(LocalDateTime bidTime) {
		this.bidTime = bidTime;
	}
	

}

