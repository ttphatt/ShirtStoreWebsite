package com.shoestore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shoestore.entity.Shoe;
import com.shoestore.entity.Type;

public class ShoeDAOTest   {
	private static ShoeDAO shoeDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shoeDAO = new ShoeDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		shoeDAO.close();
	}

	@Test
	public void testCreateShoe() throws ParseException, IOException {
		Shoe newShoe = new Shoe();
		
		Type type = new Type("Slip-on");
		type.setTypeId(11);
		
		newShoe.setType(type);
		newShoe.setShoeName("Slip-on checkerboard");
		newShoe.setBrand("Vans");
		newShoe.setDescription("Buy this if you want to play chess");
		newShoe.setShoePrice(69.99f);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date releasedDate = df.parse("24/04/2024");
		newShoe.setReleasedDate(releasedDate);
		
		String imagePath = "C:\\Users\\Admin\\Downloads\\LTW\\Images for the Final Project\\VansSlipOn-Checkerboard.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newShoe.setShoeImage(imageBytes);
		
		Shoe createdShoe = shoeDAO.create(newShoe);
		assertTrue(createdShoe.getShoeId() > 0);
	}
	
	@Test
	public void testUpdateShoe() throws ParseException, IOException {
			Shoe existShoe = new Shoe();
			existShoe.setShoeId(4);
			
			Type type = new Type("Slip-on");
			type.setTypeId(11);
			
			existShoe.setType(type);
			existShoe.setShoeName("Slip-on checkerboard");
			existShoe.setBrand("Vans");
			existShoe.setDescription("Buy this if you want to play chess");
			existShoe.setShoePrice(59.99f);
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date releasedDate = df.parse("24/04/2024");
			existShoe.setReleasedDate(releasedDate);
			
			String imagePath = "C:\\Users\\Admin\\Downloads\\LTW\\Images for the Final Project\\vans-slip-on-checkerboard-black.jpg";
			byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
			existShoe.setShoeImage(imageBytes);
			
			Shoe updatedShoe = shoeDAO.update(existShoe);
			assertEquals(updatedShoe.getShoeName(), "Slip-on checkerboard");
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteShoeFail() {
		Integer shoeId = 100;
		shoeDAO.delete(shoeId);
		
		assertTrue(true);
	}
	
	@Test
	public void testDeleteShoeSuccess() {
		Integer shoeId = 1;
		shoeDAO.delete(shoeId);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetShoeFail() {
		Integer shoeId = 99;
		Shoe shoe = shoeDAO.get(shoeId);	
		assertNull(shoe);
	}
	
	@Test
	public void testGetShoeSuccess() {
		Integer shoeId = 2;
		Shoe shoe = shoeDAO.get(shoeId);
		assertNotNull(shoe);
	}
	
	@Test
	public void testListAll() {
		List<Shoe> listShoes = shoeDAO.listAll();
		
		for(Shoe temp: listShoes) {
			System.out.println(temp.getShoeName() + " - " + temp.getShoePrice());
		}
		
		assertFalse(listShoes.isEmpty());
	}
	
	@Test
	public void testFindByShoeNameNonExist() {
		String shoeName = "Classic old-skool";
		Shoe shoe = shoeDAO.findByName(shoeName);
		
		assertNull(shoe);
	}
	
	@Test
	public void testFindByShoeNameExist() {
		String shoeName = "Classic shoe V.1";
		Shoe shoe =  shoeDAO.findByName(shoeName);
		
		/* System.out.println("Shoe's id: " + shoe.getShoeId()); */
		assertNotNull(shoe);
	}
	
	@Test
	public void testCountAll() {
		long totalShoes = shoeDAO.count();
		
		assertEquals(3, totalShoes);
	}
	
	@Test
	public void testFindByType() {
		int typeId = 1;
		
		List<Shoe> listShoes = shoeDAO.listByType(typeId);
		
		assertTrue(listShoes.size() > 0);
	}
	
	@Test
	public void testFindNewShoes() {
		List<Shoe> listNewShoes = shoeDAO.listNewShoes();
		
		for(Shoe shoe: listNewShoes) {
			System.out.println(shoe.getShoeName());
			System.out.println(shoe.getReleasedDate());
		}
		
		assertEquals(listNewShoes.size(), 4);
	}
	
	@Test
	public void testSearchShoes() {
		String keyWord = "Classic";
		List<Shoe> listShoes = shoeDAO.search(keyWord);
		
		System.out.println("There are " + listShoes.size() + " results for " + keyWord);
		
		assertTrue(listShoes.size() > 0);
	}
	
	@Test
	public void testSearchShoesByBrand() {
		String keyWord = "Vans";
		List<Shoe> listShoes = shoeDAO.search(keyWord);
		
		assertEquals(7, listShoes.size());
	}
	
	@Test
	public void testSearchShoesByDescription() {
		String keyWord = "Durable";
		List<Shoe> listShoes = shoeDAO.search(keyWord);
		
		assertEquals(7, listShoes.size());
	}
	
	@Test
	public void testCountByType() {
		int typeId = 1;
		long shoeNumb = shoeDAO.countByType(typeId);
		
		assertTrue(shoeNumb == 7);
	}
	
	@Test
	public void testListBestSellingShoes() {
		List<Shoe> listBestSellingShoes = shoeDAO.listBestSellingShoes();
		
		for(Shoe shoe : listBestSellingShoes) {
			System.out.println(shoe.getShoeId() + " " + shoe.getShoeName());
		}
		
		assertEquals(4, listBestSellingShoes.size());
	}
	
	@Test
	public void testListMostFavoredShoes() {
		List<Shoe> listMostFavoredShoes = shoeDAO.listMostFavoredShoes();
		
		for(Shoe shoe : listMostFavoredShoes) {
			System.out.println(shoe.getShoeId() + " " + shoe.getShoeName());
		}
		
		assertEquals(4, listMostFavoredShoes.size());
	}
}
