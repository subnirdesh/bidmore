package com.bidmore.services;

import java.sql.Connection;
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
		
		
		public Boolean updateUser() {
			
		}
	}
	
	

}
