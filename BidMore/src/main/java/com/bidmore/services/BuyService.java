package com.bidmore.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bidmore.config.DbConfig;
import com.bidmore.model.AuctionModel;
import com.bidmore.model.BidModel;
import com.bidmore.model.ItemModel;
import com.bidmore.model.UserModel;

public class BuyService {
	private Connection dbConn;
	boolean isConnectionError = false;

	public BuyService() {
		try {
			this.dbConn = DbConfig.getDbConnection();

		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	public List<AuctionModel> getListings() {

		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return null;
		}

		List<AuctionModel> auctionList = new ArrayList<>();

		// Query to fetch item info using join queries
		String query = "SELECT a.auction_id, a.start_price, a.reserve_price,a.end_time, "
				+ "i.item_id, i.name, i.description, i.condition, i.category, i.image_path, "
				+ "u.user_id, u.user_name, u.first_name, u.last_name " + "FROM auctions a"
				+ " JOIN items i ON a.item_id = i.item_id" + " JOIN users u ON i.seller_id = u.user_id ;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				// Creating user model for the seller
				UserModel userModel = new UserModel();
				userModel.setUserId(result.getInt("user_id"));
				userModel.setUserName(result.getString("user_name"));
				userModel.setFirstName(result.getString("first_name"));
				userModel.setLastName(result.getString("last_name"));

				// Creating item model with user data
				ItemModel item = new ItemModel(result.getString("name"), result.getString("description"),
						result.getString("condition"), result.getString("category"), result.getString("image_path"),
						userModel);
				item.setItemId(result.getInt("item_id"));

				// Creating auction model and link to item
				AuctionModel auction = new AuctionModel();
				auction.setAuctionId(result.getInt("auction_id"));
				auction.setStartPrice(result.getFloat("start_price"));
				auction.setEndTime(result.getTimestamp("end_time").toLocalDateTime());

				// Setting the item in the auction model
				auction.setItem(item);
				// Setting the user in the auction model
				auction.setUser(userModel);

				// Adding to list
				auctionList.add(auction);
			}

			return auctionList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public AuctionModel getAuctionById(int auctionId) {
	    /*
	     * Checking for database connection; in case of connection error returning null
	     */
	    if (isConnectionError) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    // Query to fetch specific auction info using join queries and auction_id parameter
	    String query = "SELECT a.auction_id, a.start_price, a.reserve_price, a.end_time, "
	            + "i.item_id, i.name, i.description, i.condition, i.category, i.image_path, "
	            + "u.user_id, u.user_name, u.first_name, u.last_name " 
	            + "FROM auctions a"
	            + " JOIN items i ON a.item_id = i.item_id" 
	            + " JOIN users u ON i.seller_id = u.user_id "
	            + "WHERE a.auction_id = ?";
	            
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        // Set the auction_id parameter
	        stmt.setInt(1, auctionId);
	        
	        ResultSet result = stmt.executeQuery();
	        if (result.next()) {
	            // Creating user model for the seller
	            UserModel userModel = new UserModel();
	            userModel.setUserId(result.getInt("user_id"));
	            userModel.setUserName(result.getString("user_name"));
	            userModel.setFirstName(result.getString("first_name"));
	            userModel.setLastName(result.getString("last_name"));

	            // Creating item model with user data
	            ItemModel item = new ItemModel(result.getString("name"), result.getString("description"),
	                    result.getString("condition"), result.getString("category"), result.getString("image_path"),
	                    userModel);
	            item.setItemId(result.getInt("item_id"));

	            // Creating auction model and link to item
	            AuctionModel auction = new AuctionModel();
	            auction.setAuctionId(result.getInt("auction_id"));
	            auction.setStartPrice(result.getFloat("start_price"));
	            auction.setEndTime(result.getTimestamp("end_time").toLocalDateTime());

	            // Setting the item in the auction model
	            auction.setItem(item);
	            // Setting the user in the auction model
	            auction.setUser(userModel);

	            return auction;
	        }
	        
	        // Return null if no auction found with the specified ID
	        return null;
	        
	    } catch (SQLException e) {
	        // Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public Boolean placeBid(BidModel bid) {
		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return null;
		}
		
		// Insert the bid
        String insertQuery = "INSERT INTO bids ( bidder_id, auction_id,bid_amount) " +
                     "VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Inserting user details
			insertStmt.setInt(1, bid.getUser().getUserId());
			insertStmt.setInt(2, bid.getAuction().getAuctionId());
			insertStmt.setFloat(3, bid.getBidAmount());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
        	
	}
	
	
	public Boolean hasUserBidOnAuction( UserModel user,  AuctionModel auction) {
		boolean hasBid=false;
		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return null;
		}
		String query  = "SELECT COUNT(*) FROM bids WHERE bidder_id = ? AND auction_id = ?";
       
        
		try(PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, auction.getAuctionId());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                hasBid = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return hasBid;
    }
    

}
