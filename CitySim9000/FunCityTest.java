import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;


public class FunCityTest {
	/*
	 * Test the add driver method - it needs to return true and this shouldn't be changed
	 */
	@Test
	public void testAddDriver() {
		FunCity test = new FunCity();
		Driver driver = mock(Driver.class);
		assertTrue(test.addDriver(driver));
	}
	/*
	 * Make sure that there are exactly 4 drivers in the city
	 * A lot of code is based off that assumption and it's not changeable
	 * 2 asserts here because it tests if it's 4 and not 4.
	 */
	@Test
	public void testIterateSize(){
		FunCity test = new FunCity();
		Driver driver = mock(Driver.class);
		assertEquals("need more drivers.",test.iterate(1));
		test.addDriver(driver);
		test.addDriver(driver);
		test.addDriver(driver);
		test.addDriver(driver);
		when(driver.getLocation()).thenReturn("Outside City");
		assertNotEquals("need more drivers.",test.iterate(1));
	}
	/*
	 * This tests what happens if you put in 4 drivers and then the last one goes to the city the second time.
	 * This is an important edge case. If anything else happens, this test will fail.
	 * If for example, that they leave early or that the simulation doesn't end, then it will fail.
	 */
	@Test
	public void testFunIterateLastDriver(){
		FunCity test = new FunCity();
		String resultString = new String("Step 0:\n"
				+ "Driver 0 starts in the Mall.\n"
				+ "Step 1:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 starts in the University.\n"
				+ "Step 2:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 goes to the University.\n"
				+ "Driver 2 starts in the Coffee Shop.\n"
				+ "Step 3:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 goes to the University.\n"
				+ "Driver 2 goes to the Coffee Shop.\n"
				+ "Driver 3 starts in the Bookstore.\n"
				+ "Step 4:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 goes to the University.\n"
				+ "Driver 2 goes to the Coffee Shop.\n"
				+ "Driver 3 goes to the Outside City.\n"
				+ "Driver 3 has left.\n"
				+ "Simulation end.\n");
		Driver driver1 = mock(Driver.class);
		when(driver1.getLocation()).thenReturn("Mall");
		Driver driver2 = mock(Driver.class);
		when(driver2.getLocation()).thenReturn("University");
		Driver driver3 = mock(Driver.class);
		when(driver3.getLocation()).thenReturn("Coffee Shop");
		Driver driver4 = mock(Driver.class);
		when(driver4.getLocation()).thenReturn("Bookstore").thenReturn("Outside City");
		test.addDriver(driver1);
		test.addDriver(driver2);
		test.addDriver(driver3);
		test.addDriver(driver4);
		assertEquals(resultString, test.iterate(1));
	}
	/*
	 * This tests an early ending. 
	 * This is important so that it ends early instead of going on.
	 * Potentially this catches the case where it keeps going based off of the drivers.
	 */
	@Test
	public void testFunEnd(){
		FunCity test = new FunCity();
		String resultString = new String("Step 0:\n"
				+ "Driver 0 starts in the Mall.\n"
				+ "Step 1:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 starts in the University.\n"
				+ "Step 2:\n"
				+ "Driver 0 goes to the Mall.\n"
				+ "Driver 1 goes to the Outside City.\n"
				+ "Driver 1 has left.\n"
				+ "Simulation end.\n");
		Driver driver1 = mock(Driver.class);
		when(driver1.getLocation()).thenReturn("Mall");
		Driver driver2 = mock(Driver.class);
		//double returns!! What a rush!
		when(driver2.getLocation()).thenReturn("University").thenReturn("Outside City");
		Driver driver3 = mock(Driver.class);
		Driver driver4 = mock(Driver.class);
		when(driver3.getLocation()).thenReturn("Coffee Shop");
		assertEquals("need more drivers.",test.iterate(1));
		test.addDriver(driver1);
		test.addDriver(driver2);
		test.addDriver(driver3);
		test.addDriver(driver4);
		assertEquals(resultString, test.iterate(1));
	}
}
