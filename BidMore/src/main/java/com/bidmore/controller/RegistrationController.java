
package com.bidmore.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.bidmore.model.UserModel;
import com.bidmore.services.RegisterService;
import com.bidmore.util.ImageUtil;
import com.bidmore.util.PasswordUtil;
import com.bidmore.util.RedirectUtil;
import com.bidmore.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// Initialize dependencies in init()
		this.registerService = new RegisterService();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;

			}

			boolean imageUploaded;
			try {
				imageUploaded = ImageUtil.uploadImage(req.getPart("image"), req.getParameter("userName"), "users");
						
			} catch (IOException | ServletException e) {
				handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
				e.printStackTrace(); // Log the error
				return;
			}

			if (!imageUploaded) {
				handleError(req, resp, "Could not upload the image. Please try again later!");
				return;
			}

			UserModel userModel = extractUserModel(req);
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
				return;
			} else if (isAdded) {

				handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");

			} else {
				handleError(req, resp, "Could not register your account. Please try again later!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			return;

		}

	}

	/**
	 * 
	 * @param request
	 * @return
	 */

	private String validateRegistrationForm(HttpServletRequest request) {
		// Getting form parameters
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String birthdayStr = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		String email = request.getParameter("email");

		// Checking for null or empty fields first using isNullorEmpty Method form
		// ValidationUtil
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(userName))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(birthdayStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(phone))
			return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";

		// Converting date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(birthdayStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validating if username begins with letter and only contains alphabets and
		// numeric values
		if (!ValidationUtil.isAlphaNumericStartingWithLetters(userName))
			return "Username must start with a letter and contain only letters and numbers begining with letters.";

		// Validating email
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";

		// Validating phone number
		if (!ValidationUtil.isValidPhoneNumber(phone))
			return "Phone number must be 10 digits and start with 98.";
		// Validating password
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";

		// Validating if password and retyped password match
		if (!ValidationUtil.doPasswordMatch(password, retypePassword))
			return "Passwords do not match.";

		// Checking if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

		// Checking into database if the username already exists
		if (registerService.isUsernameDuplicate(userName)) {
			return "Username already exists.";
		}
		// Checking into database if the phone already exists
		if (registerService.isPhoneDuplicate(phone)) {
			return "The phone is already registered in our system.";
		}
		// Checking into database if the email already exists
		if (registerService.isEmailDuplicate(email)) {
			return "The email is already registered in our system.";
		}

		try {
			Part image = request.getPart("image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return "Error handling image file. Please ensure the file is valid.";
		}

		return null; // returning null if all validation pass

	}

	private UserModel extractUserModel(HttpServletRequest req) throws IOException, ServletException {
		// Getting form parameters
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String userName = req.getParameter("userName");
		LocalDate birthdate = LocalDate.parse(req.getParameter("birthday"));
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		// Assuming password validation is already done in validateRegistrationForm
		password = PasswordUtil.encrypt(userName, password);

		// Set default image path
		String imageUrl = "/resources/images/users/defaultdp.png";

		Part image = req.getPart("image");

		if (image != null && image.getSize() > 0) {
			String originalImageName = ImageUtil.getImageNameFromPart(image);
			String username = req.getParameter("userName");
			String savedImageName = username + "_" + originalImageName; 
			
			// Settings the full path, not just the filename
			// Setting the imageUrl to use the saved filename
			imageUrl = "/userimages/users/" + savedImageName;
		}

		return new UserModel(userName, password, email, phone, birthdate, firstName, lastName, imageUrl);

	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("userName", req.getParameter("userName"));
		req.setAttribute("birthday", req.getParameter("birthday"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("phone", req.getParameter("phone"));
		req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
	}

}
