package com.shoestore.controller.frontend.shoe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.service.ShoeServices;


@WebServlet("/search")
public class SearchShoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchShoesServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoeServices shoeService = new ShoeServices(request, response);
		
		shoeService.search();
	}

}
