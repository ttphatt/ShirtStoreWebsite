package com.shoestore.controller.admin;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.dao.CustomerDAO;
import com.shoestore.dao.OrderDAO;
import com.shoestore.dao.RateDAO;
import com.shoestore.dao.ShoeDAO;
import com.shoestore.dao.TypeDAO;
import com.shoestore.dao.UserDAO;
import com.shoestore.entity.Rate;
import com.shoestore.entity.ShoeOrder;

/**
 * Servlet implementation class AdminHomePageServlet
 */
@WebServlet("/admin/")
public class AdminHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AdminHomePageServlet() {
        super();
        
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String homePage = "index.jsp";
		
		//Lấy ra danh sách các đơn đặt hàng để hiển thị lên trang index của admin
		OrderDAO orderDAO = new OrderDAO();
		List<ShoeOrder> listMostRecentSales = orderDAO.listMostRecentSales();
		
		//Lấy ra danh sách các đánh giá để để hiển thị trên trand index của admin
		RateDAO rateDAO = new RateDAO();
		List<Rate> listMostRecentRates = rateDAO.listMostRecentRates();
		
		List<String> listRatingStars = rateDAO.listRatingStars();
		List<Integer> countRatingStars = rateDAO.countRatingStars();
		
		//Đếm số lượng user có trong database
		UserDAO userDAO = new UserDAO();
		long totalUsers = userDAO.count();
		
		//Đếm số lượng đôi giày có trong database
		ShoeDAO shoeDAO = new ShoeDAO();
		long totalShoes = shoeDAO.count();
		List<String>soldShoeName = shoeDAO.listSoldShoeName();
		List<Integer>eachShoeRevenue = shoeDAO.listEachShoeRevenue();
		
		List<Integer>shoesByTypes = shoeDAO.countShoesByTypes();
		
		//Đếm số lượng khách hàng có trong database
		CustomerDAO customerDAO = new CustomerDAO();
		long totalCustomers = customerDAO.count();
		
		TypeDAO typeDAO = new TypeDAO();
		long totalTypes = typeDAO.count();
		
		//Lấy ra danh sách chứa tên các loại
		List<String>typeNames = typeDAO.listTypeName();
		//Đếm số lượng đánh giá
		long totalRates = rateDAO.count();
		
		//Đếm số lượng đơn hàng
		long totalOrders = orderDAO.count();
				
		//Đẩy dữ liệu qua view
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		request.setAttribute("listMostRecentRates", listMostRecentRates);
		
		request.setAttribute("listRatingStars", listRatingStars);
		request.setAttribute("countRatingStars", countRatingStars);
		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalShoes", totalShoes);
		
		request.setAttribute("shoesByTypes", shoesByTypes);
		request.setAttribute("soldShoeName", soldShoeName);
		request.setAttribute("eachShoeRevenue", eachShoeRevenue);
		
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalTypes", totalTypes);
		request.setAttribute("typeNames", typeNames);
		request.setAttribute("totalRates", totalRates);
		request.setAttribute("totalOrders", totalOrders);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
		dispatcher.forward(request, response);
	}
}
