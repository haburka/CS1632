import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class ArrayTest {

	private int[][] testArrays;
	@Before
	public void setUp(  ) {
        this.testArrays = new int[100][];
        Random gen = new Random();
        for(int i = 0; i < 100; i++){
        	this.testArrays[i] = new int[gen.nextInt(1000)];
        	for(int j = 0; j < this.testArrays[i].length; j++){
        		this.testArrays[i][j] = gen.nextInt(1000);
        	}
        }
    }

	/**
	 * Output array same size of what is passed in.
	 */
	@Test
	public void sameSizeTest() {
		for(int i = 0; i < this.testArrays.length; i++){
			int[] clone = this.testArrays[i].clone();
			Arrays.sort(clone);
			Arrays.sort(this.testArrays[i]);
			assertEquals(this.testArrays[i].length,clone.length);
		}
	}
	/**
	 * Values always increasing or staying the same
	 */
	@Test
	public void testIncreasing() {
		for(int i = 0; i < this.testArrays.length; i++){
			Arrays.sort(this.testArrays[i]);
			for(int j = 1; j < this.testArrays[i].length; j++){
				assertTrue(this.testArrays[i][j] >= this.testArrays[i][j-1] );
			}
		}
	}
	/**
	 * Tests if every element in the input array is in the output array.
	 */
	@Test
	public void testDuplicity() {
		for(int i = 0; i < this.testArrays.length; i++){
			int[] clone = this.testArrays[i].clone();
			Arrays.sort(this.testArrays[i]);
			for(int j = 0; j < clone.length; j++){
				int a = Arrays.binarySearch(this.testArrays[i], clone[j]);
				if(a >= 0 )
					assertTrue(true);
				else
					fail();
			}
		}
	}
	/**
	 * Running sort again does not change it.
	 */
	@Test
	public void testIdempotent() {
		for(int i = 0; i < this.testArrays.length; i++){
			Arrays.sort(this.testArrays[i]);
			int[] clone = this.testArrays[i].clone();
			Arrays.sort(clone);
			for(int j = 0; j < clone.length; j++){
				assertEquals(this.testArrays[i][j],clone[j]);
			}
		}
	}
}
