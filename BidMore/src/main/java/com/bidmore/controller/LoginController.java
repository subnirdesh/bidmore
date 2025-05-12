package com.bidmore.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bidmore.model.UserModel;
import com.bidmore.services.LoginService;
import com.bidmore.util.CookieUtil;
import com.bidmore.util.RedirectUtil;
import com.bidmore.util.SessionUtil;
import com.bidmore.util.ValidationUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RedirectUtil redirectUtil;
	private LoginService loginService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// Initialize dependencies in init()
		this.redirectUtil = new RedirectUtil();
		this.loginService = new LoginService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = req.getParameter("username");
		String password = req.getParameter("password");

		// Validate for empty fields
		if (ValidationUtil.isNullOrEmpty(userName) || ValidationUtil.isNullOrEmpty(password)) {
			// Handling empty field error
			String errorMessage = "Username and password cannot be empty";
			redirectUtil.redirect("error", errorMessage, "WEB-INF/pages/login.jsp", req, resp);
			return; // Important to return here to stop further processing
		}

		UserModel userModel = new UserModel(userName, password);
		Boolean loginStatus = loginService.loginUser(userModel);

		if (loginStatus != null && loginStatus) {
			if (userName.equals("admin")) {
				CookieUtil.addCookie(resp, "username", userName, 5 * 30);
				SessionUtil.setAttribute(req, "role", "admin");
				SessionUtil.setAttribute(req, "username", userName);
				resp.sendRedirect(req.getContextPath() + "/dashboard"); // Redirecting to /dashboard
			} else {
				CookieUtil.addCookie(resp, "username", userName, 5 * 30);
				SessionUtil.setAttribute(req, "role", "user");
				SessionUtil.setAttribute(req, "username", userName);
				resp.sendRedirect(req.getContextPath() + "/home"); // Redirecting to Home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
	}

	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	public void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		redirectUtil.redirect("error", errorMessage, "WEB-INF/pages/login.jsp", req, resp);

	}

}
