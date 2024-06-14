package Temp;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.shoestore.entity.Rate;
import com.shoestore.entity.Shoe;

public class ShoeRatingTest {

	@Test
	public void testRatingStars1() {
		Shoe shoe = new Shoe();
		
		Set<Rate>listRates = new HashSet<Rate>();
		Rate rate1 = new Rate();
		rate1.setRatingStars(5);
		listRates.add(rate1);
		
		shoe.setRates(listRates);
		
		float averageRatingStars = shoe.getAverageRatingStars();
		
		assertEquals(5.0, averageRatingStars, 0.0);
	}

	@Test
	public void testRatingStars2() {
		Shoe shoe = new Shoe();
		float averageRatingStars = shoe.getAverageRatingStars();
		
		assertEquals(0.0, averageRatingStars, 0.0);
	}
	
	@Test
	public void testRatingStars3() {
		Shoe shoe = new Shoe();
		
		Set<Rate>listRates = new HashSet<Rate>();
		Rate rate1 = new Rate();
		rate1.setRatingStars(5);
		listRates.add(rate1);
		
		Rate rate2 = new Rate();
		rate2.setRatingStars(4);
		listRates.add(rate2);
		
		Rate rate3 = new Rate();
		rate3.setRatingStars(3);
		listRates.add(rate3);
		
		shoe.setRates(listRates);
		
		float averageRatingStars = shoe.getAverageRatingStars();
		
		assertEquals(4.0, averageRatingStars, 0.0);
	}
	
	@Test
	public void testRatingString1() {
		float averageRating = 0.0f;
		Shoe shoe = new Shoe();
		
		String actual = shoe.getRatingString(averageRating);
		String expected = "off,off,off,off,off";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString2() {
		float averageRating = 5.0f;
		Shoe shoe = new Shoe();
		
		String actual = shoe.getRatingString(averageRating);
		String expected = "on,on,on,on,on";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString3() {
		float averageRating = 3.0f;
		Shoe shoe = new Shoe();
		
		String actual = shoe.getRatingString(averageRating);
		String expected = "on,on,on,off,off";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString4() {
		float averageRating = 4.5f;
		Shoe shoe = new Shoe();
		
		String actual = shoe.getRatingString(averageRating);
		String expected = "on,on,on,on,half";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString5() {
		float averageRating = 2.4f;
		Shoe shoe = new Shoe();
		
		String actual = shoe.getRatingString(averageRating);
		String expected = "on,on,half,off,off";
		
		assertEquals(expected, actual);
	}
}
