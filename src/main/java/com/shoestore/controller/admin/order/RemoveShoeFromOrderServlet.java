package com.shoestore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoestore.entity.OrderDetail;
import com.shoestore.entity.ShoeOrder;


@WebServlet("/admin/remove_shoe_from_order")
public class RemoveShoeFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveShoeFromOrderServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int shoeId = Integer.parseInt(request.getParameter("shoeId"));
		HttpSession session = request.getSession();
		ShoeOrder order = (ShoeOrder) session.getAttribute("order");
		
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		Iterator<OrderDetail> iterator = orderDetails.iterator();	
	
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			
			if(orderDetail.getShoe().getShoeId() == shoeId) {
				float newSum = order.getOrderSum() - orderDetail.getSubTotal();
				order.setOrderSum(newSum);
				iterator.remove();
			}
		}
		
		String path = "order_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
