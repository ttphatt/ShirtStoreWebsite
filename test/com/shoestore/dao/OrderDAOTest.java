package com.shoestore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.shoestore.entity.Customer;
import com.shoestore.entity.OrderDetail;
import com.shoestore.entity.Shoe;
import com.shoestore.entity.ShoeOrder;

public class OrderDAOTest {
	private static OrderDAO orderDAO;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateShoeOrder() {
		ShoeOrder order = new ShoeOrder();
		Customer customer = new Customer();
		
		customer.setCustomerId(3);
		
		order.setCustomer(customer);
		order.setFirstname("Bart");
		order.setLastname("Williamson");
		order.setPhone("981234567");
		order.setAddressLine1("84 North Avenue");
		order.setAddressLine2("765 South-west street");
		order.setCity("New York");
		order.setState("New York");
		order.setCountry("US");
		order.setPayment("Paypal");
		order.setZipcode("123456");
		
		Set<OrderDetail>orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		
		Shoe shoe = new Shoe(17);
		orderDetail.setShoe(shoe);
		orderDetail.setQuantity(2);
		orderDetail.setSubTotal(111.98f);
		orderDetail.setShoeOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		order.setTax(11.198f);
		order.setShippingFee(1.0f);
		order.setOrderSum(124.178f);
		order.setSubtotal(111.98f);
		
		ShoeOrder savedOrder = orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0);
	}
	
	@Test
	public void testCreateShoeOrder2() {
		ShoeOrder order = new ShoeOrder();
		Customer customer = new Customer();
		
		customer.setCustomerId(2);
		
		order.setCustomer(customer);
		order.setFirstname("Bart Homer");
		order.setPhone("981234567");
		order.setAddressLine1("84 North Avenue, New Orleans, America");
		
		Set<OrderDetail>orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Shoe shoe = new Shoe(7);
		orderDetail1.setShoe(shoe);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubTotal(50.0f);
		orderDetail1.setShoeOrder(order);
		
		orderDetails.add(orderDetail1);
		
		
		Shoe shoe2 = new Shoe(9);
		OrderDetail orderDetail2 = new OrderDetail();
		
		orderDetail2.setShoe(shoe2);
		orderDetail2.setQuantity(3);
		orderDetail2.setSubTotal(42.00f);
		orderDetail2.setShoeOrder(order);
		
		orderDetails.add(orderDetail2);
		
		
		order.setOrderDetails(orderDetails);
		
		ShoeOrder savedOrder = orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);
	}
	
	@Test
	public void testGet() {
		Integer orderId = 19;
		ShoeOrder order = orderDAO.get(orderId);
	
		System.out.println(order.getFirstname());
		System.out.println(order.getLastname());
		System.out.println(order.getPhone());
		System.out.println(order.getAddressLine1());
		System.out.println(order.getAddressLine2());
		System.out.println(order.getCity());
		System.out.println(order.getState());
		System.out.println(order.getCountry());
		System.out.println(order.getZipcode());
		
		System.out.println(order.getStatus());
		System.out.println(order.getSubtotal());
		System.out.println(order.getShippingFee());
		System.out.println(order.getTax());
		System.out.println(order.getOrderSum());
		System.out.println(order.getPayment());
		
		assertEquals(1, order.getOrderDetails().size());
	}
	
	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 10;
		Integer customerId = 99;
		
		ShoeOrder order = orderDAO.get(orderId, customerId);
		assertNull(order);
	}
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 4;
		Integer customerId = 2;
		
		ShoeOrder order = orderDAO.get(orderId, customerId);
		assertNotNull(order);
	}
	
	@Test
	public void testDeleteObject() {
		Integer orderId = 3;
		orderDAO.delete(orderId);
		
		ShoeOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<ShoeOrder>listOrders = orderDAO.listAll();

		for (ShoeOrder shoeOrder : listOrders) {
			System.out.println(shoeOrder.getOrderId() + " " + shoeOrder.getCustomer().getFirstname() + " " + shoeOrder.getCustomer().getLastname() + " " + shoeOrder.getOrderSum() + " " + shoeOrder.getStatus());
			
			for(OrderDetail detail : shoeOrder.getOrderDetails()) {
				Shoe shoe = detail.getShoe();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubTotal();
				System.out.println(shoe.getShoeName() + " " + quantity + " " + subtotal);
			}
		
		}
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testUpdateShoeOrderToAddress() {
		Integer orderId = 2;
		ShoeOrder order = orderDAO.get(orderId);
		
		order.setAddressLine1("New Shipping Address");
		
		orderDAO.update(order);
		
		ShoeOrder updatedOrder = orderDAO.get(orderId);
		
		assertEquals(order.getAddressLine1(), updatedOrder.getAddressLine1());
	}
	
	@Test
	public void testUpdateShoeOrderDetail() {
		Integer orderId = 19;
		ShoeOrder order = orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getShoe().getShoeId() == 17) {
				 orderDetail.setQuantity(5);
				 orderDetail.setSubTotal(254.95f);
			}
		}
		
		orderDAO.update(order);
		
		iterator = order.getOrderDetails().iterator();
		
		int expectedQuantity = 5;
		float expectedSubTotal = 254.95f;
		
		int actualQuantity = 0;
		float actualSubTotal = 0;
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getShoe().getShoeId() == 17) {
				 actualQuantity = orderDetail.getQuantity();
				 actualSubTotal = orderDetail.getSubTotal();
			}
		}
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubTotal, actualSubTotal, 0.0f);
	}
	
	@Test
	public void testCount() {
		long totalOrders = orderDAO.count();
		
		assertEquals(4, totalOrders);
	}

	@Test
	public void testFindByCustomerWithoutOrders() {
		Integer customerId = 99;
		
		List<ShoeOrder>listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testFindByCustomerWithOrders() {
		Integer customerId = 2;
		
		List<ShoeOrder>listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testMostRecentSales() {
		List<ShoeOrder> listMostRecentOrders = orderDAO.listMostRecentSales();
		
		
		assertEquals(3, listMostRecentOrders.size());
	}
}
