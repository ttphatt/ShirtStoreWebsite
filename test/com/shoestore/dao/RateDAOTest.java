package com.shoestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shoestore.entity.Customer;
import com.shoestore.entity.Rate;
import com.shoestore.entity.Shoe;

public class RateDAOTest {
	private static RateDAO rateDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rateDAO = new RateDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		rateDAO.close();
	}

	@Test
	public void testCreateRate() {
		Rate rate = new Rate();
		
		Shoe shoe = new Shoe();
		shoe.setShoeId(13);
		
		Customer customer = new Customer();
		customer.setCustomerId(9);
		
		rate.setShoe(shoe);
		rate.setCustomer(customer);
		
		rate.setHeadline("A stunning pair of shoes");
		rate.setRatingStars(3);
		rate.setRatingDetail("They are so comfortable when I use them for walking");
		
		Rate createdRate = rateDAO.create(rate);
		
		assertTrue(createdRate.getRateId() > 0);
	}

	@Test
	public void testGet() {
		Integer rateId = 1;
		Rate rate = rateDAO.get(rateId);
		
		assertNotNull(rate);
	}

	@Test
	public void testUpdate() {
		Integer rateId = 1;
		Rate rate = rateDAO.get(rateId);
		
		rate.setHeadline("A durable pair of shoes");
		
		Rate updatedRate = rateDAO.update(rate);
		
		assertEquals(rate.getHeadline(), updatedRate.getHeadline());
	}
	
	@Test
	public void testDeleteObject() {
		int rateId = 2;
		rateDAO.delete(rateId);
		
		Rate rate = rateDAO.get(rateId);
		
		assertNull(rate);
	}

	@Test
	public void testListAll() {
		List<Rate> listRates = rateDAO.listAll();
		
		for(Rate rate : listRates) {
			System.out.println(rate.getRateId() + " " + rate.getHeadline() + " " + rate.getShoe().getShoeName() + " " + rate.getCustomer().getFirstname() + " " + rate.getCustomer().getLastname());
		}
		
		assertTrue(listRates.size() > 0);
	}

	@Test
	public void testCount() {
		long numberOfRates = rateDAO.count();
		
//		System.out.println("Total number of rates: " + numberOfRates);
		assertTrue(numberOfRates > 0);
	}

	@Test
	public void testFindByCustomerAndShoeNotFound() {
		Integer customerId = 99;
		Integer shoeId = 99;
		
		Rate res = rateDAO.findByCustomerAndShoe(customerId, shoeId);
		
		assertNull(res);
	}
	
	@Test
	public void testFindByCustomerAndShoeFound() {
		Integer customerId = 7;
		Integer shoeId = 3;
		
		Rate res = rateDAO.findByCustomerAndShoe(customerId, shoeId);
		
		assertNotNull(res);
	}
	
	@Test
	public void testListMostRecentRates() {
		List<Rate> recentRates = rateDAO.listMostRecentRates();
		
		assertEquals(3, recentRates.size());
	}
}
