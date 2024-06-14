package com.shoestore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoestore.dao.CustomerDAO;
import com.shoestore.dao.OrderDAO;
import com.shoestore.dao.RateDAO;
import com.shoestore.entity.Customer;
import com.shoestore.entity.ShoeOrder;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		
		this.customerDAO = new CustomerDAO();
	}
	
	
	public void listCustomer() throws ServletException, IOException {
		listCustomer(null);
	}
	
	public void listCustomer(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
	
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomer", listCustomer);
		
		String path = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		
		Customer existCustomer = customerDAO.findByEmail(email);
		
		if(existCustomer != null) {
			String message = "The email: " + email + " already existed";
			listCustomer(message);
		}
		else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
		
			String message = "A new customer has been created successfully";
			listCustomer(message);
		}
		
	}

	private void updateCustomerFieldsFromForm(Customer customer) {
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phone");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		
		if(email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		
		if(password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		customer.setPhoneNumber(phoneNumber);
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setCity(city);
		customer.setState(state);
		customer.setZip(zip);
		customer.setCountry(country);
	}
	
	public void editCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		
		Customer customer = customerDAO.get(customerId);
		
		request.setAttribute("customer", customer);
		CommonUtility.generateCountriesList(request);
		
		String path = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}


	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		
		Customer customerByEmail = customerDAO.findByEmail(email);
		String message = null;
		
		if(customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			message = "Could not update the customer ID: " + customerId + " since there is another user with the email address: " + email;
			
		}
		else {

			
			
			Customer customerById = customerDAO.get(customerId);
			updateCustomerFieldsFromForm(customerById);
			
			customerDAO.update(customerById);
			
			message = "The customer has been updated successfully";
		}
		
		listCustomer(message);
	}


	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		
		RateDAO rateDAO = new RateDAO();
		long numberOfRate = rateDAO.countByCustomer(customerId);
		System.out.println("Number of rates: " + numberOfRate);
		
		
		OrderDAO orderDAO = new OrderDAO();
		long numberOfOrder = orderDAO.countByCustomer(customerId);
		System.out.println("Number of orders: " + numberOfOrder);
		
		String message = "";
		
		if(numberOfRate > 0) {
			message = "Unable to delete customer with id: " + customerId + ". Because this customer has already rated some shoes";
		}
		else if(numberOfOrder > 0) {
			message =  "Unable to delete customer with id: " + customerId + ". Because this customer has ordered some shoes";
		}
		else {
			customerDAO.delete(customerId);
			message = "The customer with id: " + customerId + " has been deleted successfully";
		}
		listCustomer(message);
		
	}
	
	public void registerAsCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		String message = "";
		Customer existCustomer = customerDAO.findByEmail(email);
		
		
		if(existCustomer != null) {
			message = "Could not sign up. The email: " + email + " is already registered by another customer";
		}
		else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			Customer createdCustomer = customerDAO.create(newCustomer);
		
			message = "You have signed up successfully.\n" + "<a href='login'>Click here</a> to login";
		}
	
		
		String path = "frontend/message.jsp";
		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}


	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
	}


	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = "";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if(customer != null) {
			HttpSession session = request.getSession();
			request.getSession().setAttribute("loggedCustomer", customer);
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if(objRedirectURL != null) {
				String redirectURL = (String)objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}
			else {
				showCustomerProfile();
			}
		}
		else {
			message = "Login failed. Please check your email and password again";
			request.setAttribute("message", message);
			showLogin();
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePath = "frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePath);
		requestDispatcher.forward(request, response);
	}


	public void showCustomerProfileFormEdit() throws ServletException, IOException {
		CommonUtility.generateCountriesList(request);
		String profilePath = "frontend/edit_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePath);
		requestDispatcher.forward(request, response);
	}


	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		
		showCustomerProfile();
	}

	public void newCustomer() throws ServletException, IOException {
		CommonUtility.generateCountriesList(request);
		
		String customerForm = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(customerForm);
		requestDispatcher.forward(request, response);
	}

	public void showCustomerRegistrationForm() throws ServletException, IOException {
		CommonUtility.generateCountriesList(request);
		
		String path = "frontend/customer_registration.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}