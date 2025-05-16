package com.bidmore.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bidmore.config.DbConfig;
import com.bidmore.model.UserModel;

public class DashboardService {
	private Connection dbConn;
	boolean isConnectionError = false;

	public DashboardService() {
		try {
			this.dbConn = DbConfig.getDbConnection();

		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	
	public int getUserCount() {
		// Check for database connection
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return -1; // returning -1 to indicate error
		}

		String query = "SELECT COUNT(*) AS user_count FROM users WHERE role_id = 2";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int count = result.getInt("user_count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // If query fails or no result, return 0
	}
	
	public int getItemCount() {
		// Check for database connection
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return -1; // returning -1 to indicate error
		}

		String query = "SELECT COUNT(*) AS item_count FROM items";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int count = result.getInt("item_count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // If query fails or no result, return 0
	}
	
	public int getAuctionCount() {
		// Check for database connection
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return -1; // returning -1 to indicate error
		}

		String query = "SELECT COUNT(*) AS auction_count FROM auctions";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int count = result.getInt("auction_count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // If query fails or no result, return 0
	}
	
	public int getBidCount() {
		// Check for database connection
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return -1; // returning -1 to indicate error
		}

		String query = "SELECT COUNT(*) AS bid_count FROM bids";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int count = result.getInt("bid_count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // If query fails or no result, return 0
	}
	
	public String getLatestUser() {
	    if (isConnectionError) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String latestUser = null;
	    String query = "SELECT user_name FROM users ORDER BY registration_date DESC LIMIT 1";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            latestUser = rs.getString("user_name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return latestUser;
	}


	public String getLatestAuction() {
	    if (isConnectionError) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String latestAuction = null;
	    String query = "SELECT i.name"
	    		+ " FROM auctions a"
	    		+ " JOIN items i ON a.item_id = i.item_id"
	    		+ " ORDER BY a.date_created DESC"
	    		+ " LIMIT 1;";
	    		

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            latestAuction = rs.getString("name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return latestAuction;
	}
	
	public Double getLatestBidAmount() {
	    if (isConnectionError) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    Double latestBidAmount = null;
	    String query = "SELECT bid_amount FROM bids ORDER BY bid_time DESC LIMIT 1";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            latestBidAmount = rs.getDouble("bid_amount");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return latestBidAmount;
	}
	public List<UserModel> getAllUserInfo() {

		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return null;
		}

		/*
		 * Getting the user's username from Session username is used to fetched user
		 * detail as each username is unique
		 */

		// SQL query to fetch the user's details

		String query = "SELECT first_name, last_name, user_name, birthdate, email, phone, user_id, status , registration_date FROM users where role_id=2";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();
			UserModel user = null;
			List<UserModel> userList = new ArrayList<UserModel>();

			while (result.next())  {
				// Extracting user details
				int userId=result.getInt("user_id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String userName = result.getString("user_name");
				LocalDate birthDate = result.getDate("birthdate").toLocalDate();
				String email = result.getString("email");
				String phone = result.getString("phone");
				String status = result.getString("status");
				LocalDateTime registrationDate = result.getTimestamp("registration_date").toLocalDateTime();

				
				
				


				// Creating UserModel Instance
				user = new UserModel(userId,firstName, lastName, userName, birthDate, email, phone,registrationDate,status);
				userList.add(user);
				
			}

			return userList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

}
