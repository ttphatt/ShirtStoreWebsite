package com.shoestore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.shoestore.controller.frontend.cart.ShoppingCart;
import com.shoestore.dao.OrderDAO;
import com.shoestore.entity.Customer;
import com.shoestore.entity.OrderDetail;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.ShoeOrder;

public class OrderServices {
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}
	
	public void listAllOrders() throws ServletException, IOException {
		listAllOrders(null);
	}
	
	public void listAllOrders(String message) throws ServletException, IOException {
		List<ShoeOrder> listOrder = orderDAO.listAll();
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listOrder", listOrder);
		
		String path = "order_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		
		ShoeOrder order = orderDAO.get(orderId);
		
		request.setAttribute("order", order);
		
		String path = "order_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void showCheckOutForm() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		
		//Tax = Subtotal * 10%
		float tax = shoppingCart.getTotalAmount() * 0.1f;
		
		//Shipping fee = totalQuantity * 1$
		float shippingFee = shoppingCart.getTotalQuantity() * 1.0f;
		
		float totalPrice = shoppingCart.getTotalAmount() + tax + shippingFee;
		
		session.setAttribute("tax", tax);
		session.setAttribute("shippingFee", shippingFee);
		session.setAttribute("totalPrice", totalPrice);
		
		
		CommonUtility.generateCountriesList(request);
		
		
		String path = "frontend/checkout.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

	public void placeOrder() throws ServletException, IOException {
		String payment = request.getParameter("payment");
		ShoeOrder order = readOrderInfo();
		
		if(payment.equals("Paypal")) {
			PaymentServices paymentService = new PaymentServices(request, response);
			request.getSession().setAttribute("order4Paypal", order);
			paymentService.authorizePayment(order);
			sendEmailToCustomer();
		}
		else {
			placeOrderCOD(order);
			sendEmailToCustomer();
		}
	}
	
	private void sendEmailToCustomer(){
		//lấy email hiện tại ra
        HttpSession session = request.getSession();
        Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
        String email = loggedCustomer.getEmail();
        String name = loggedCustomer.getFirstname();
        String title = "Order Confirmation from PHK SHOE STORE";
        String body = formEmail(name);
        //gửi mail
        MailServices.SendMail(email,title,body);
	}
	
	public String formEmail(String name) {
		String form = "PHK SHOE STORE\r\n"
				+ "\r\n"
				+ "Order Confirmation and Thank You\r\n"
				+ "\r\n"
				+ "Dear " + name + ",\r\n"
				+ "\r\n"
				+ "Thank you for placing an order with PHK SHOE STORE. We are excited to process your order and ensure you receive your items as soon as possible.\r\n"
				+ "\r\n"
				+ "Order Details:\r\n"
				+ "\r\n"
				+ "Order Number: [Order Number]\r\n"
				+ "Order Date: [Order Date]\r\n"
				+ "Estimated Delivery Date: [Estimated Delivery Date]\r\n"
				+ "Items Ordered:\r\n"
				+ "[Item 1]\r\n"
				+ "[Item 2]\r\n"
				+ "[Item 3]\r\n"
				+ "We appreciate your trust in us and strive to deliver the highest quality products and services. Your satisfaction is our top priority.\r\n"
				+ "\r\n"
				+ "Next Steps:\r\n"
				+ "\r\n"
				+ "You will receive a notification once your order is shipped, including tracking information.\r\n"
				+ "If you have any special instructions or need to make changes to your order, please contact us promptly.\r\n"
				+ "Customer Support:\r\n"
				+ "If you have any questions or require further assistance, our customer service team is here to help. You can reach us at [Customer Service Email] or call us at [Customer Service Phone Number].\r\n"
				+ "\r\n"
				+ "Thank you once again for your purchase. We look forward to serving you and hope you enjoy your new items!\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "[Your Name]\r\n"
				+ "[Your Title]\r\n"
				+ "[Your Company Name]\r\n"
				+ "[Company Address]\r\n"
				+ "[Company Email]\r\n"
				+ "[Company Phone Number]\r\n"
				+ "\r\n"
				+ "";
		return form;
	}
	
	public Integer placeOrderPaypal(Payment payment) {
		ShoeOrder order =  (ShoeOrder) request.getSession().getAttribute("order4Paypal");
		ItemList itemList = payment.getTransactions().get(0).getItemList();
		ShippingAddress shippingAddress = itemList.getShippingAddress();
		String shippingPhonenumber = itemList.getShippingPhoneNumber();
		
		String recipientName = shippingAddress.getRecipientName();
		String[] names = recipientName.split(" ");
		
		order.setFirstname(names[0]);
		order.setLastname(names[1]);
		order.setAddressLine1(shippingAddress.getLine1());
		order.setAddressLine2(shippingAddress.getLine2());
		order.setCity(shippingAddress.getCity());
		order.setState(shippingAddress.getState());
		order.setCountry(shippingAddress.getCountryCode());
		order.setPhone(shippingPhonenumber);
		
		return saveOrder(order);
	}
	
	private Integer saveOrder(ShoeOrder order) {
		ShoeOrder savedOrder = orderDAO.create(order);
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		
		shoppingCart.clearCart(); 
		
		return savedOrder.getOrderId();
	}
	
	private ShoeOrder readOrderInfo() {
		String payment = request.getParameter("payment");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phoneNumber = request.getParameter("phoneNumber");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		
		ShoeOrder order = new ShoeOrder();
		
		order.setFirstname(firstname);
		order.setLastname(lastname);
		order.setPhone(phoneNumber);
		order.setAddressLine1(addressLine1);
		order.setAddressLine2(addressLine2);
		order.setCity(city);
		order.setState(state);
		order.setZipcode(zip);
		order.setCountry(country);
		order.setPayment(payment);
		
		//Lấy thông tin khách hàng từ session khi khách hàng đăng nhập tài khoản
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Shoe, Integer>items = shoppingCart.getItems();
		
		Iterator<Shoe> iterator = items.keySet().iterator();
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		
		while(iterator.hasNext()) {
			Shoe shoe = iterator.next();
			Integer quantity = items.get(shoe);
			float subTotal = quantity * shoe.getShoePrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setShoe(shoe);
			orderDetail.setShoeOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subTotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		
		float tax = (float) session.getAttribute("tax");
		float shippingFee = (float) session.getAttribute("shippingFee");
		float total = (float) session.getAttribute("totalPrice");
		
		order.setSubtotal(shoppingCart.getTotalAmount());
		order.setTax(tax);
		order.setShippingFee(shippingFee);
		order.setOrderSum(total);
		
		return order;
	}
	
	private void placeOrderCOD(ShoeOrder order) throws ServletException, IOException {
		saveOrder(order);
		String message = "Thank you for choosing our products. Your order has been received. Your shoes will arrive within a few days.";
		request.setAttribute("message", message);
		
		String path = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		List<ShoeOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());
	
		request.setAttribute("listOrders", listOrders);
	
		String path = "frontend/order_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		ShoeOrder order = orderDAO.get(orderId, customer.getCustomerId());
		
		request.setAttribute("order", order);
		
		String path = "frontend/order_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		
		HttpSession session = request.getSession();
		Object isPendingShoe = session.getAttribute("newShoePendingToAddToOrder");
		
		if(isPendingShoe == null) {
			ShoeOrder order = orderDAO.get(orderId);
			session.setAttribute("order", order);
		}
		else {
			session.removeAttribute("newShoePendingToAddToOrder");
		}
		
		CommonUtility.generateCountriesList(request);
		
		String path = "order_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoeOrder order = (ShoeOrder) session.getAttribute("order");
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		
		float shippingFee = Float.parseFloat(request.getParameter("shippingFee"));
		float tax = Float.parseFloat(request.getParameter("tax"));
		
		
		String payment = request.getParameter("payment");
		String orderStatus = request.getParameter("orderStatus");
		
		
		order.setFirstname(firstname);
		order.setLastname(lastname);
		order.setPhone(phone);
		order.setAddressLine1(addressLine1);
		order.setAddressLine2(addressLine2);
		order.setCity(city);
		order.setState(state);
		order.setCountry(country);
		order.setZipcode(zipcode);
		order.setPayment(payment);
		order.setStatus(orderStatus);
	
		order.setShippingFee(shippingFee);
		order.setTax(tax);
		
		String[] arrShoeId = request.getParameterValues("shoeId");
		String[] arrShoePrice = request.getParameterValues("shoePrice");
		String[] arrQuantity = new String[arrShoeId.length];
		
		for(int i = 1; i <= arrQuantity.length; i++) {
			arrQuantity[i - 1] = request.getParameter("quantity" + i);
		}
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();
		
		float orderSum = 0.0f;
		
		for(int i = 0; i < arrShoeId.length; i++) {
			int shoeId = Integer.parseInt(arrShoeId[i]);
			int quantity = Integer.parseInt(arrQuantity[i]);
			float shoePrice = Float.parseFloat(arrShoePrice[i]);
			float subTotal = quantity * shoePrice;
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setShoe(new Shoe(shoeId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subTotal);
			orderDetail.setShoeOrder(order);
			
			orderDetails.add(orderDetail);
			
			orderSum += subTotal;
		}
		
		order.setSubtotal(orderSum);
		orderSum += shippingFee;
		orderSum += tax;
		
		order.setOrderSum(orderSum);
		
		orderDAO.update(order);
		
		String message = "The order with id: " + order.getOrderId() + " has been updated successfully";
		listAllOrders(message);
	}

	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		orderDAO.delete(orderId);
		
		String message = "The order with id: " + orderId + " has been deleted successfully";
		listAllOrders(message);
		
	}
	
}
