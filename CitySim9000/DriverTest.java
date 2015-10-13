import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito.*;


public class DriverTest {
	/*
	 * This tests that it fails if you use a bad random for i.
	 */
	@Test(expected=Exception.class)
	public void badRandomGetNextTest() throws Exception{
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseNext(2,locales);
	}
	/*
	 * This chooese a bad value
	 * What if someone passes in a bad int?
	 * It needs to not do something unexpected
	 */
	@Test
	public void badValueGetNextTest() throws Exception{
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(6,locales);
		String loc1 = test.getLocation();
		test.chooseNext(6,locales);
		assertEquals(test.getLocation(),loc1);
	}
	/*
	 * This simply mocks get random - which checks that it returns a good bound
	 * If modified, then it could be used to send in bad ints
	 * Without realizing that results are somewhat silly.
	 */
	@Test
	public void getRandomTest(){
		Driver test = new Driver();
		Random generator = mock(Random.class);
		when(generator.nextInt(4)).thenReturn(4);
		assertEquals(test.getRandomNumbers(generator,4),4);
		//Random returns 4 - 4 is the best number
	}
	/*
	 * This tests the bad value case
	 * If this changes then it would be a serious issue
	 * A good location showing up from a bad value would mean the programming wasn't working
	 */
	@Test
	public void testDefault() throws Exception{
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(0,locales);
		test.setLocation("Bad Location");
		test.chooseNext(0,locales);
		test.chooseNext(1, locales);
		assertEquals(test.getLocation(),"Bad Value");
	}
	/*
	 * This simply tests a simple function that is for the first location.
	 * There's no real edge case here so it just chooses 2.
	 * This function was more complicated but then was refactored to a one liner,
	 * which is part of the reason why this test is so long.
	 */
	@Test
	public void testFunFirstLoc() throws Exception{
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
	/*
	 * Outside to inside is a special case where it might behave strangely.
	 * Since it's a requirement, it needs to be tested.
	 */
	@Test
	public void testChooseNextOutsideToInside() throws Exception{
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(1,locales);
		String loc = test.getLocation();
		assertEquals(locales[1],loc);
	}
	/*
	 * Inside to inside is another big case.
	 * It's the meat and potatoes case which determines much functionality.
	 * If I could here, I would have it iterate through all locations and do
	 * an assert for each case, isnteading just testing with 0.
	 */
	@Test
	public void testChooseNextInsideToInside() throws Exception{
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(0, locales);
		test.chooseNext(0,locales);
		String loc1 = test.getLocation();
		test.chooseNext(0,locales);
		String loc2 = test.getLocation();
		assertNotEquals(loc1,loc2);
	}
	/*
	 * This tests that if you choose first, does it pick the first value in the array?
	 * This was another TDD test that's less useful now that all the code is here.
	 * It's redundant with almost everything else however there's almost no reason to delete it.
	 */
	@Test
	public void testChooseFirstZero(){
		Driver test = new Driver();
		String locales[] = {"Outside City"};
		test.chooseFirst(0, locales);
		String loc = test.getLocation();
		assertEquals("Outside City",loc);
	}
	/*
	 * This checks for index out of bounds if you chose an array outside of the get Locales function
	 * Would have to be rewritten if you change the amount of locations in the city.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testChooseFirstOverflow(){
		Driver test = new Driver();
		String locales[] = test.getLocales();
		test.chooseFirst(5, locales);
	}
	/*
	 * Similar to the previous test, this checks length in antoehr way.
	 */
	@Test
	public void getLocalesLengthTest(){
		Driver test = new Driver();
		assertEquals(test.getLocales().length,5);
	}
	/*
	 * This uses a for loop to check each value of the locales and it's placement
	 * order is very important to the driver code so this test is very important.
	 * If someone changes outside city to another location in the array then the
	 * Whole thing should break.
	 */
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
