package com.shoestore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.shoestore.entity.Customer;
import com.shoestore.entity.OrderDetail;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.ShoeOrder;

public class PaymentServices {
	private static final String CLIENT_ID = "AZv2cYwNaqzx0au1TooXwNd_A9GpR1_0fzYovkVCIbMxdyMP7yA0z2iTsKgu5zeh3oVfClGnU4eJteDt";
	private static final String CLIENT_SECRET = "EJSZH7IUlO1TJdwLToG1MLl2OC8zn3jTqViYZQegL9HVSANsfFt_Z0DeZ8KhIUcLSszXaNk6iMFUT46o";
	private String mode = "sandbox";

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public PaymentServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	public void authorizePayment(ShoeOrder order) throws ServletException, IOException {
		//Get Payer's information
		Payer payer = getPayerInformation(order);
		
		//Redirect URLs
		RedirectUrls redirectUrls = getRedirectUrls();
		
		List<Transaction> transactions = getTransactionInformation(order);
		
		//Request payment
		Payment requestPayment = new Payment();
		requestPayment.setPayer(payer)
					  .setRedirectUrls(redirectUrls)
					  .setIntent("authorize")
					  .setTransactions(transactions);
					  
		System.out.println("=====REQUEST PAYMENT=====");
		System.out.println(requestPayment);
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		try {
			Payment authorizedPayment = requestPayment.create(apiContext);
			System.out.println("=====AUTHORIZED PAYMENT=====");
			System.out.println(authorizedPayment);
			
			String approvalURL = getApprovalURL(authorizedPayment);
			
			response.sendRedirect(approvalURL);
			
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Error in authorizing payment.");
		}
		
		//Get approval link
		
		//Redirect to Paypal's payment page
	}
	
	private String getApprovalURL(Payment authorizedPayment) {
		String approvalURL = null;
		
		List<Links> links = authorizedPayment.getLinks();
		
		for(Links link : links) {
			if(link.getRel().equalsIgnoreCase("approval_url")) {
				approvalURL = link.getHref();
				break;
			}
		}
		return approvalURL;
	}

	private Payer getPayerInformation(ShoeOrder order) {
		Payer payer = new Payer();
		payer.setPaymentMethod("Paypal");
				
		PayerInfo payerInfo = new PayerInfo();
		Customer customer = order.getCustomer();
		payerInfo.setFirstName(customer.getFirstname());
		payerInfo.setLastName(customer.getLastname());
		payerInfo.setEmail(customer.getEmail());
		payer.setPayerInfo(payerInfo);
	
		return payer;
	}
	
	private RedirectUrls getRedirectUrls() {
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI().toString();
		String baseURL = requestURL.replace(requestURI, "").concat(request.getContextPath());
		
		RedirectUrls redirectUrls = new RedirectUrls();
		String cancelUrl = baseURL.concat("/view_cart");
		String returnUrl = baseURL.concat("/review_payment");
		
		System.out.println("Return URL:" + returnUrl);
		System.out.println("Cancel URL:" + cancelUrl);
		
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(returnUrl);
		
		return redirectUrls;
	}
	
	private Amount getAmountDetails(ShoeOrder order) {
		Details details = new Details();
		details.setShipping(String.format(Locale.US, "%.2f", order.getShippingFee()));
		details.setTax(String.format(Locale.US, "%.2f", order.getTax()));
		details.setSubtotal(String.format(Locale.US, "%.2f", order.getSubtotal()));
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setDetails(details);
		amount.setTotal(String.format(Locale.US, "%.2f", order.getOrderSum()));
		
		return amount;
	}
	
	private	ShippingAddress getRecipientInformation(ShoeOrder order) {
		ShippingAddress shippingAddress = new ShippingAddress();
		
		String recipientName = order.getFirstname() + " " + order.getLastname();
		shippingAddress.setRecipientName(recipientName)
					   .setPhone(order.getPhone())
					   .setLine1(order.getAddressLine1())
					   .setLine2(order.getAddressLine2())
					   .setCity(order.getCity())
					   .setState(order.getState())
					   .setCountryCode(order.getCountry())
					   .setPostalCode(order.getZipcode());
		
		return shippingAddress;
	}
	
	private List<Transaction> getTransactionInformation(ShoeOrder order) {
		Transaction transaction = new Transaction();
		transaction.setDescription("Shoes ordered on Classic shoe V.1");
		
		Amount amount = getAmountDetails(order);
		transaction.setAmount(amount);
		
		ItemList itemList = new ItemList();
		ShippingAddress shippingAddress = getRecipientInformation(order);
		itemList.setShippingAddress(shippingAddress);
		
		
		List<Item>  paypalItems = new ArrayList<Item>();
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			Shoe shoe = orderDetail.getShoe();
			Integer quantity = orderDetail.getQuantity();
			
			Item paypalItem = new Item();
			paypalItem.setCurrency("USD")
					  .setName(shoe.getShoeName())
					  .setQuantity(String.valueOf(quantity))
					  .setPrice(String.format(Locale.US, "%.2f", shoe.getShoePrice()));
			
			paypalItems.add(paypalItem);
		}
		
		itemList.setItems(paypalItems);
		transaction.setItemList(itemList);
		
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		listTransaction.add(transaction);
		
		return listTransaction;
	}

	public void reviewPayment() throws ServletException, IOException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		if(paymentId == null || payerId == null) {
			throw new ServletException("Error in displaying payment");
		}
		
		//Connect to Paypal server
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		try {
			Payment payment = Payment.get(apiContext, paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
			request.setAttribute("recipient", shippingAddress);
			
			String reviewPage = "frontend/review_payment.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
			request.getRequestDispatcher(reviewPage).forward(request, response);
			
		} catch (PayPalRESTException | IOException e) {
			e.printStackTrace();
			throw new ServletException("Error in getting payment details from Paypal");
		}
	}

	public Payment executePayment() throws PayPalRESTException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		Payment payment = new Payment().setId(paymentId);
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);
		
		return payment.execute(apiContext, paymentExecution);
	}
}
