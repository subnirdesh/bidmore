package com.bidmore.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.bidmore.model.AuctionModel;
import com.bidmore.services.BuyService;
import com.bidmore.services.PortfolioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BuyService buyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// Initializing dependencies in init()
		this.buyService = new BuyService();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Getting all listing with their item and auction details
		List<AuctionModel> auctions = buyService.getListings();
		for (AuctionModel auction : auctions) {
		    Duration duration = Duration.between(LocalDateTime.now(), auction.getEndTime());
		    int hoursLeft = (int) duration.toHours();
		    auction.setHoursLeft(hoursLeft); 
		}
		
		
		
		req.setAttribute("auctions", auctions);
		
		req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String searchTerm = req.getParameter("query");
		

	}
	
	
	

}
