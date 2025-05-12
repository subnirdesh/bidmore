package com.bidmore.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.bidmore.config.DbConfig;
import com.bidmore.model.AuctionModel;
import com.bidmore.model.ItemModel;
import com.bidmore.model.UserModel;

public class SellService {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes database connection
	 */

	public SellService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	public Boolean listItem(ItemModel item) {

		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return false;
		}

		String insertQuery = "INSERT INTO items (seller_id, name, description, `condition`, image_path, category)"

				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS)) {

			// Inserting user details
			insertStmt.setInt(1, item.getUser().getUserId());
			insertStmt.setString(2, item.getItemName());
			insertStmt.setString(3, item.getDescription());
			insertStmt.setString(4, item.getCondition());
			insertStmt.setString(5, (item.getImagePath()));
			insertStmt.setString(6, (item.getCategory()));

			int rowsInserted = insertStmt.executeUpdate();

			if (rowsInserted > 0) {
				// Retrieve generated item ID
				try (var generatedKeys = insertStmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int itemId = generatedKeys.getInt(1);
						item.setItemId(itemId);
						return true;
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error during listing item " + e.getMessage());
			e.printStackTrace();

		}
		return false;
	}

	public Boolean listAuction(AuctionModel auction) {

		/*
		 * Checking for database connection; in case of connection error returning null
		 */
		if (isConnectionError) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO auctions (item_id,seller_id,end_time,start_price,reserve_price)"

				+ "VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Inserting user details
			insertStmt.setInt(1, auction.getItem().getItemId());
			insertStmt.setInt(2, auction.getUser().getUserId());
			insertStmt.setTimestamp(3, Timestamp.valueOf(auction.getEndTime()));
			insertStmt.setFloat(4, auction.getStartPrice());
			insertStmt.setFloat(5, auction.getReservePrice());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during listing item " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
