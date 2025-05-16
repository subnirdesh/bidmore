package com.bidmore.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bidmore.config.DbConfig;
import com.bidmore.model.UserModel;

public class RegisterService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new user in the database.
	 * 
	 * @param user
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addUser(UserModel user) {

		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO users (first_name, last_name, user_name, birthdate, email, phone, password,image_path) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Inserting user details
			insertStmt.setString(1, user.getFirstName());
			insertStmt.setString(2, user.getLastName());
			insertStmt.setString(3, user.getUserName());
			insertStmt.setDate(4, Date.valueOf(user.getBirthDate()));
			insertStmt.setString(5, (user.getEmail()));
			insertStmt.setString(6, (user.getPhone()));
			insertStmt.setString(7, (user.getPassword()));
			insertStmt.setString(8, (user.getImageUrl()));

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean isUsernameDuplicate(String username) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false; // Cannot check, assume it doesn't exist
		}

		String checkQuery = "SELECT COUNT(*) FROM users WHERE user_name = ?";
		try (PreparedStatement checkStmt = dbConn.prepareStatement(checkQuery)) {
			checkStmt.setString(1, username);
			ResultSet rs = checkStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Return true if count > 0
			}
		} catch (SQLException e) {
			System.err.println("Error checking username existence: " + e.getMessage());
			e.printStackTrace();
		}
		return false; // Default to false if we can't check
	}


	public boolean isPhoneDuplicate(String phone) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false; // Cannot check, assume it doesn't exist
		}

		String checkQuery = "SELECT COUNT(*) FROM users WHERE phone = ?";
		try (PreparedStatement checkStmt = dbConn.prepareStatement(checkQuery)) {
			checkStmt.setString(1, phone);
			ResultSet rs = checkStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Return true if count > 0
			}
		} catch (SQLException e) {
			System.err.println("Error checking email existence: " + e.getMessage());
			e.printStackTrace();
		}
		return false; // Default to false if we can't check
	}


	public boolean isEmailDuplicate(String email) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false; // Cannot check, assume it doesn't exist
		}

		String checkQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
		try (PreparedStatement checkStmt = dbConn.prepareStatement(checkQuery)) {
			checkStmt.setString(1, email);
			ResultSet rs = checkStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Return true if count > 0
			}
		} catch (SQLException e) {
			System.err.println("Error checking email existence: " + e.getMessage());
			e.printStackTrace();
		}
		return false; // Default to false if we can't check
	}

}
