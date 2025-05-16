package com.bidmore.controller.admin;

import java.io.IOException;

import com.bidmore.services.DashboardService;
import com.bidmore.services.PortfolioService;
import com.bidmore.services.RegisterService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashBoardController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/dashboard")
public class DashboardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardController() {
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
		
		req.setAttribute("totalUser", dashboardService.getUserCount());
		req.setAttribute("totalItem", dashboardService.getItemCount());
		req.setAttribute("totalAuction", dashboardService.getAuctionCount());
		req.setAttribute("totalBid", dashboardService.getBidCount());
		req.setAttribute("latestUser", dashboardService.getLatestUser());
		req.setAttribute("latestAuction", dashboardService.getLatestAuction());
		req.setAttribute("latestBid", dashboardService.getLatestBidAmount());
		
		
		
		
		req.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
