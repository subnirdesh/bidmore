
package com.bidmore.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bidmore.config.DbConfig;
import com.bidmore.model.UserModel;
import com.bidmore.util.PasswordUtil;

public class LoginService {
	private Connection dbConn;
	private boolean isConnectionError=false;

	/**
	 * Constructor initializes database connection
	 */

	public LoginService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError=true;
			
		}

	}
	/**
	 * 
	 * @param userModel the UserModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; 
	 * 		   null if a connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
		if(isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}
		
		
		
	  
		
		String query ="SELECT user_name,password FROM users WHERE user_name=?";
		try(PreparedStatement stmt =dbConn.prepareStatement(query)){
			stmt.setString(1, userModel.getUserName());
			ResultSet result=stmt.executeQuery();
			
			if(result.next()) {
				return validatePassword(result,userModel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
		return false;
				
	}
	
	
	/**
	 * 
	 * @param result  the ResultSet containing the username and password from the database
	 * @param userModel  userModel the UserModel object containing user credentials
	 * @return true if the password match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result , UserModel userModel) throws SQLException{
		String dbUsername=result.getString("user_name");
		String dbPassword=result.getString("password");
		String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
		System.out.println("DB Username: " + dbUsername);
		System.out.println("Input Username: " + userModel.getUserName());
		System.out.println("DB Password (decrypted): " + decryptedPassword);
		System.out.println("Input Password: " + userModel.getPassword());

	    return dbUsername.equals(userModel.getUserName()) &&
	           decryptedPassword != null &&
	           decryptedPassword.equals(userModel.getPassword());
		
		
		
	}

} 
