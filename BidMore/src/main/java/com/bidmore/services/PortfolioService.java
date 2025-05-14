package com.bidmore.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bidmore.config.DbConfig;
import com.bidmore.model.UserModel;
import com.bidmore.util.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;

public class PortfolioService {
	private Connection dbConn;
	boolean isConnectionError = false;

	public PortfolioService() {
		try {
			this.dbConn = DbConfig.getDbConnection();

		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	public UserModel getUserInfo(String currentUserName) {

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

		String query = "SELECT first_name, last_name, user_name, birthdate, email, phone,image_path,user_id FROM users where user_name=?";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, currentUserName);
			ResultSet result = stmt.executeQuery();
			UserModel user = null;

			if (result.next()) {
				// Extracting user details
				int userId=result.getInt("user_id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String userName = result.getString("user_name");
				LocalDate birthDate = result.getDate("birthdate").toLocalDate();
				String email = result.getString("email");
				String phone = result.getString("phone");
				String imagePath = result.getString("image_path");
				
				


				// Creating UserModel Instance
				user = new UserModel(userId,firstName, lastName, userName, birthDate, email, phone, imagePath);
				System.out.println("Image URL: " + (user != null ? user.getImageUrl() : "null"));
				
			}

			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	public Boolean updateUser(UserModel user,int userId) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return null;
	    }

	    String updateQuery = "UPDATE users SET first_name = ?, last_name = ?, user_name = ?, "
	            + "birthdate = ?, email = ?, phone = ?, image_path = ? "
	            + "WHERE user_id = ?";

	    try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
	        // Setting user details
	        updateStmt.setString(1, user.getFirstName());
	        updateStmt.setString(2, user.getLastName());
	        updateStmt.setString(3, user.getUserName());
	        updateStmt.setDate(4, Date.valueOf(user.getBirthDate()));
	        updateStmt.setString(5, user.getEmail());
	        updateStmt.setString(6, user.getPhone());
	        updateStmt.setString(7, user.getImageUrl());
	        updateStmt.setInt(8, userId);

	        return updateStmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error updating user details: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	/**
	 * Checks if a username already exists for any user other than the specified user
	 * @param username The username to check
	 * @param userId The ID of the current user (to exclude from the check)
	 * @return true if the username exists for another user, false otherwise
	 */
	public boolean isUsernameExistsForOtherUser(String username, int userId) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return false;
	    }
	    
	    String query = "SELECT COUNT(*) FROM users WHERE user_name = ? AND user_id != ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, username);
	        stmt.setInt(2, userId);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error checking username existence: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}

	/**
	 * Checks if an email already exists for any user other than the specified user
	 * @param email The email to check
	 * @param userId The ID of the current user (to exclude from the check)
	 * @return true if the email exists for another user, false otherwise
	 */
	public boolean isEmailExistsForOtherUser(String email, int userId) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return false;
	    }
	    
	    String query = "SELECT COUNT(*) FROM users WHERE email = ? AND user_id != ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, email);
	        stmt.setInt(2, userId);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error checking email existence: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}

	/**
	 * Checks if a phone number already exists for any user other than the specified user
	 * @param phone The phone number to check
	 * @param userId The ID of the current user (to exclude from the check)
	 * @return true if the phone number exists for another user, false otherwise
	 */
	public boolean isPhoneExistsForOtherUser(String phone, int userId) {
	    if (dbConn == null) {
	        System.err.println("Database connection is not available.");
	        return false;
	    }
	    
	    String query = "SELECT COUNT(*) FROM users WHERE phone = ? AND user_id != ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, phone);
	        stmt.setInt(2, userId);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error checking phone existence: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	

}
