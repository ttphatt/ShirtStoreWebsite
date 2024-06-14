package com.shoestore.controller.frontend.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoestore.dao.ShoeDAO;
import com.shoestore.entity.Shoe;


@WebServlet("/remove_from_cart")
public class RemoveShoeFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveShoeFromCartServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer shoeId = Integer.parseInt(request.getParameter("shoeId"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = (ShoppingCart)cartObject;
		
		
		shoppingCart.removeItem(new Shoe(shoeId));
	
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
