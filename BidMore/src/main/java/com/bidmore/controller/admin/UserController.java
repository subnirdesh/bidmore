package com.bidmore.controller.admin;

import java.io.IOException;

import com.bidmore.services.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuctionController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/user")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DashboardService    dashboardService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		
	}
	
	@Override
	public void init() throws ServletException {
		// Initializing dependencies in init()
		this.dashboardService = new DashboardService();
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setAttribute("users", dashboardService.getAllUserInfo());
		
		
		req.getRequestDispatcher("WEB-INF/pages/admin/users.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
