package com.shoestore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoestore.dao.RateDAO;
import com.shoestore.dao.ShoeDAO;
import com.shoestore.entity.Customer;
import com.shoestore.entity.Rate;
import com.shoestore.entity.Shoe;

public class RateServices {
	private RateDAO rateDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public RateServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.rateDAO = new RateDAO();
	}
	
	public void listAllRate(String message) throws ServletException, IOException {
		List<Rate> listRate = rateDAO.listAll();
		
		request.setAttribute("listRate", listRate);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String path = "rate_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void listAllRate() throws ServletException, IOException {
		listAllRate(null);
	}
	
	public void editRate() throws ServletException, IOException {
		Integer rateId = Integer.parseInt(request.getParameter("id"));
		
		Rate rate = rateDAO.get(rateId);
		
		request.setAttribute("rate", rate);
		
		String path = "rate_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void updateRate() throws ServletException, IOException {
		Integer rateId = Integer.parseInt(request.getParameter("rateId"));
		String headline = request.getParameter("headline");
		String ratingDetail = request.getParameter("ratingDetail");
		
		Rate rate = rateDAO.get(rateId);
		rate.setHeadline(headline);
		rate.setRatingDetail(ratingDetail);
		
		rateDAO.update(rate);
		
		String message = "The rate has been updated successfully";
		listAllRate(message);
	}

	public void deleteRate() throws ServletException, IOException {
		Integer rateId = Integer.parseInt(request.getParameter("id"));
		
		rateDAO.delete(rateId);
		
		String message = "The rate has been deleted successfully";
		
		listAllRate(message);
	}

	public void showRateForm() throws ServletException, IOException {
		Integer shoeId = Integer.parseInt(request.getParameter("shoeId"));
		ShoeDAO shoeDAO = new ShoeDAO();
		Shoe shoe = shoeDAO.get(shoeId);
		
		HttpSession session = request.getSession();
		session.setAttribute("shoe", shoe);
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		Rate existRate = rateDAO.findByCustomerAndShoe(customer.getCustomerId(), shoeId);
		
		String path = "frontend/rate_form.jsp";
		
		if(existRate != null) {
			request.setAttribute("rate", existRate);
			path = "frontend/rate_info.jsp";
		}
			
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void submitRate() throws ServletException, IOException {
		Integer shoeId = Integer.parseInt(request.getParameter("shoeId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String ratingDetail = request.getParameter("ratingDetail");
		
		Rate rate = new Rate();
		rate.setHeadline(headline);
		rate.setRatingDetail(ratingDetail);
		rate.setRatingStars(rating);
		
		Shoe shoe = new Shoe();
		shoe.setShoeId(shoeId);
		rate.setShoe(shoe);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		rate.setCustomer(customer);
		
		rateDAO.create(rate);
		
		String path = "frontend/rate_done.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	
}
