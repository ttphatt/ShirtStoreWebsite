package com.shoestore.controller.admin.order;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoestore.dao.ShoeDAO;
import com.shoestore.entity.OrderDetail;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.ShoeOrder;


@WebServlet("/admin/add_shoe_to_order")
public class AddShoeToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddShoeToOrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int shoeId = Integer.parseInt(request.getParameter("shoeId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		ShoeDAO shoeDAO = new ShoeDAO();
		Shoe shoe = shoeDAO.get(shoeId);
	
		HttpSession session = request.getSession();
		ShoeOrder order = (ShoeOrder) session.getAttribute("order");
		
		float subTotal = quantity * shoe.getShoePrice();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setShoe(shoe);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubTotal(subTotal);
		
		float newTotal = order.getOrderSum() + subTotal;
		order.setOrderSum(newTotal);
		
		boolean isShoeInOrder = false;
		Set<OrderDetail>orderDetails = order.getOrderDetails();
		
		for(OrderDetail od : orderDetails) {
			if(od.getShoe().equals(shoe)) {
				isShoeInOrder = true;
				od.setQuantity(od.getQuantity() + quantity);
				od.setSubTotal(od.getSubTotal() + subTotal);
				break;
			}
		}
		
		if(!isShoeInOrder) {
			order.getOrderDetails().add(orderDetail);
		}
		
		session.setAttribute("newShoePendingToAddToOrder", true);
		request.setAttribute("shoe", shoe);
		
		String path = "add_shoe_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
