import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito.*;


public class DriverTest {
	@Test
	public void badValueGetNextTest(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(6,locales);
		String loc1 = test.getLocation();
		test.chooseNext(6,locales);
		assertEquals(test.getLocation(),loc1);
	}
	@Test
	public void getRandomTest(){
		Driver test = new Driver();
		Random generator = mock(Random.class);
		when(generator.nextInt(4)).thenReturn(4);
		assertEquals(test.getRandomNumbers(generator,4),4);
		//Random returns 4 - 4 is the best number
	}
	@Test
	public void testDefault(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(0,locales);
		test.setLocation("Bad Location");
		test.chooseNext(0,locales);
		test.chooseNext(1, locales);
		assertEquals(test.getLocation(),"Bad Value");
	}
	@Test
	public void testFunFirstLoc(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		Driver test2 = new Driver();
		test2.chooseFirst(0, locales);
		test.chooseNext(0, locales);
		test2.chooseNext(1, locales);
		/*
		 * Why 2 asserts here? It's against the recommendations.
		 * This test is for a requirement FUN FIRST LOC - if this test passes then the req should pass
		 * These 2 asserts cover both cases. When this test fails, it will give a line number
		 * Which could help the developer work out what happened.
		 */
		assertTrue(test.getLocation().equals("University"));
		assertTrue(test2.getLocation().equals("Mall"));
	}
	@Test
	public void testChooseNextOutsideToInside(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(1,locales);
		String loc = test.getLocation();
		assertEquals(locales[1],loc);
	}
	@Test
	public void testChooseNextInsideToInside(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(0,locales);
		String loc1 = test.getLocation();
		test.chooseNext(0,locales);
		String loc2 = test.getLocation();
		assertNotEquals(loc1,loc2);
	}
	@Test
	public void testChooseFirstZero(){
		Driver test = new Driver();
		String locales[] = {"Outside City"};
		test.chooseFirst(0, locales);
		String loc = test.getLocation();
		assertEquals("Outside City",loc);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void testChooseFirstOverflow(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(5, locales);
	}
	@Test
	public void getLocalesLengthTest(){
		Driver test = new Driver();
		assertEquals(test.getLocales().length,5);
	}
	@Test
	public void getLocalesContentTest(){
		Driver test = new Driver();
		String[] ret = {"Outside City","Mall" , "Bookstore" , "Coffee Shop","University"};
		String[] test2 = test.getLocales();
		for(int i = 0; i < test2.length; i++){
			//I couldn't get assertTrue to work for the whole array
			//This is ~4 asserts, which is against recommendations but it's because
			// I want to check the whole string array (content + Order)
			assertTrue(test2[i].equals(ret[i]));
		}
	}

}
