package com.shoestore.controller.frontend.cart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCartServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arrShoeIds = request.getParameterValues("shoeId");
		String[] arrQuantities = new String[arrShoeIds.length];
		
		String tempQuantities;
		
		for(int i = 1; i <= arrQuantities.length; i++) {
			tempQuantities = request.getParameter("quantity" + i);
			arrQuantities[i - 1] = tempQuantities;
		}
		
		int[] shoeIds = Arrays.stream(arrShoeIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrQuantities).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(shoeIds, quantities);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
