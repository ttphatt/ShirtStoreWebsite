package com.shoestore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.dao.ShoeDAO;
import com.shoestore.dao.TypeDAO;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.Type;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomePageServlet() {
        super();       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoeDAO shoeDAO = new ShoeDAO();
		
		List<Shoe>listNewShoes = shoeDAO.listNewShoes();
		List<Shoe>listBestSellingShoes = shoeDAO.listBestSellingShoes();
		List<Shoe> listMostFavoredShoes = shoeDAO.listMostFavoredShoes();
		
		request.setAttribute("listNewShoes", listNewShoes);
		request.setAttribute("listBestSellingShoes", listBestSellingShoes);
		request.setAttribute("listMostFavoredShoes", listMostFavoredShoes);
		
		String homePage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
		dispatcher.forward(request, response);
	}



}
