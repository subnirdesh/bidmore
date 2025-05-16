package com.bidmore.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.bidmore.model.AuctionModel;
import com.bidmore.model.ItemModel;
import com.bidmore.model.UserModel;
import com.bidmore.services.PortfolioService;
import com.bidmore.services.SellService;
import com.bidmore.util.ImageUtil;
import com.bidmore.util.SessionUtil;
import com.bidmore.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class SellController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/sell")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class SellController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PortfolioService portfolioService;
	private SellService sellService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// Initializing dependencies in init()
		this.portfolioService = new PortfolioService();
		this.sellService = new SellService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("WEB-INF/pages/sell.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String validationMessage = validateSellForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			boolean imageUploaded;
			try {
				imageUploaded = ImageUtil.uploadImage(req.getPart("image_path"), req.getParameter("name"), "items");

			} catch (IOException | ServletException e) {
				handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
				e.printStackTrace(); // Log the error
				return;
			}
			if (!imageUploaded) {
				handleError(req, resp, "Could not upload the image. Please try again later!");
				return;
			}

			// Using SessionUtil to get the username
			String userName = (String) SessionUtil.getAttribute(req, "username");
			UserModel userModel = portfolioService.getUserInfo(userName);
			ItemModel itemModel = extractItemModel(req, userModel);
			AuctionModel auctionModel = extractAuctionModel(req, userModel, itemModel);

			Boolean isListed = false;
			try {
			    boolean itemListed = sellService.listItem(itemModel);
			    if (itemListed) {
			        isListed = sellService.listAuction(auctionModel);
			    }
			    
			    if (!isListed) {
			        // Handling the case where either item or auction listing failed
			        System.out.println("Failed to list item or auction");
			    }
			} catch (Exception e) {
			    // Handle any exceptions
			    e.printStackTrace();
			    isListed = false;
			}

			if (isListed) {

				handleSuccess(req, resp, "Your item is successfully listed for auction!", "/buy");

			} else {
				handleError(req, resp, "Could not list your item for auction. Please try again later!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			return;
		}

	}

	private String validateSellForm(HttpServletRequest request) {
		String name = request.getParameter("name");
		String condition = request.getParameter("condition");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String startingPrice = request.getParameter("start_price");
		String reservePrice = request.getParameter("reserve_price");
		String duration = request.getParameter("duration");

		// Checking for null or empty fields first using isNullorEmpty Method form

		if (ValidationUtil.isNullOrEmpty(name))
			return "Item Name is required.";
		if (ValidationUtil.isNullOrEmpty(condition))
			return "Please select a condition";
		if (ValidationUtil.isNullOrEmpty(category))
			return "Please select a category";
		if (ValidationUtil.isNullOrEmpty(description))
			return "Please enter description of your item";
		if (ValidationUtil.isNullOrEmpty(startingPrice))
			return "Please enter a starting price.";
		if (ValidationUtil.isNullOrEmpty(reservePrice))
			return "Please enter a reserve price.";
		if (ValidationUtil.isNullOrEmpty(duration))
			return "Please select a duration";

		if (name.length() > 50)
			return "Item name is too long.";
		if (description.length() > 300)
			return "Description is too long.";

		try {
			Part image = request.getPart("image_path");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return "Error handling image file. Please ensure the file is valid.";
		}

		if (!ValidationUtil.isValidFloat(startingPrice))
			return "Please enter a valid starting price";
		if (!ValidationUtil.isValidFloat(reservePrice))
			return "Please enter a valid reserve price.";

		float sp = Float.parseFloat(startingPrice);
		float rp = Float.parseFloat(reservePrice);
		
		if(sp>5000)
			return "Bidmore only supports auction for item below $5000.";

		// Logical validation to ensure reserve price is greater than or equal to
		// starting price.
		if (sp > rp)
			return "Reserve price must be greater than or equal to starting price.";

		return null;

	}

	private ItemModel extractItemModel(HttpServletRequest request, UserModel user)
			throws IOException, ServletException {
		String itemName = request.getParameter("name");
		String condition = request.getParameter("condition");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		// Setting default image path
		String imageUrl = "";

		Part image = request.getPart("image_path");

		if (image != null && image.getSize() > 0) {
			String originalImageName = ImageUtil.getImageNameFromPart(image);
			String item = request.getParameter("name"); // Getting item again
			String savedImageName = item + "_" + originalImageName; 
																	
			// Settings the full path, not just the filename
			// Setting the imageUrl to use the saved filename
			
			imageUrl = "/itemimages/items/" + savedImageName;
		}

		// Creating and returning the ItemModel with extracted parameters using the
		// constructor
		return new ItemModel(itemName, description, condition, category, imageUrl, user);
	}

	private AuctionModel extractAuctionModel(HttpServletRequest request, UserModel user, ItemModel item)
			throws IOException, ServletException {
		String startingPriceStr = request.getParameter("start_price");
		String reservePriceStr = request.getParameter("reserve_price");
		String duration = request.getParameter("duration");
		LocalDateTime endTime = calculateEndTime(duration);

		float startingPrice = Float.parseFloat(startingPriceStr);
		float reservePrice = Float.parseFloat(reservePriceStr);
		


		return new AuctionModel(endTime, startingPrice, reservePrice, user, item);

	}

	/**
	 * 
	 * @param durationStr
	 * @return
	 */
	private LocalDateTime calculateEndTime(String durationStr) {
		// Parsing the number of days from the dropdown value
		int days;
		try {
			// Parsing the value directly since dropdown returns numeric values
			days = Integer.parseInt(durationStr);
		} catch (NumberFormatException e) {
			// Defaulting to 3 days if there's an issue parsing
			days = 3;
		}

		// Calculating end date as current time + days
		LocalDateTime now = LocalDateTime.now();
		return now.plusDays(days);
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.getSession().setAttribute("successMessage", message);
		String contextPath = req.getContextPath();
	    resp.sendRedirect(contextPath + redirectPage);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("condition", req.getParameter("condition"));
		req.setAttribute("category", req.getParameter("category"));
		req.setAttribute("description", req.getParameter("description"));
		req.setAttribute("startingPrice", req.getParameter("start_price"));
		req.setAttribute("reservePrice", req.getParameter("reserve_price"));
		req.getRequestDispatcher("/WEB-INF/pages/sell.jsp").forward(req, resp);

	}
	
	
}
