import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Random;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    /**
     * Check function works for negative numbers
     */

    
    @Test
    public void testNegArray()
    {
    	int[] a3 = {-15};			int[] a2 = {-10};			int[] a1 = {-5};
    	
    	int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    
    }
    
    /**
     * Check for zero working
     */
    
    @Test
    public void testZeroArray()
    {
    	int[] a3 = {0};			int[] a2 = {0};			int[] a1 = {0};
    	
    	int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    @Test
    public void testSort()
    {
    	int[] array = {1,6,2,4,8,10};			int[] sortedArray = {1,2,4,6,8,10};
    	
    	Collinear.sort(array);
    }
    
    @Test
    public void testBinarySearch()
    {
    	int[] array = {2,7,10,20};
    	int x = 5;
    	Collinear.binarySearch(array, x);
    }
    
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     */
     public static void main(String[] args)
     {
    	 //TESTS FOR THE TIMING OF THE FUNCTIONS.
    	 
    	 /*
    	 Random rn = new Random();
    	 int numberOfNumbers = 5000;
    	 int[] a1 = new int[numberOfNumbers];
    	 int[] a2 = new int[numberOfNumbers];
    	 int[] a3 = new int[numberOfNumbers];
    	 
    	 for (int i = 0; i < numberOfNumbers; i++)
    	 {
    		 a1[i] = rn.nextInt();
    		 a2[i] = rn.nextInt();
    		 a3[i] = rn.nextInt();
    	 }
    	 
    	 Stopwatch sw = new Stopwatch();
	     Collinear.countCollinear(a1, a2, a3);					//This was changed between countCollinear, and countCollinearFast during the tests.
	     System.out.println(sw.elapsedTime());
    	 
    	 
    	 
    	 
    	 */
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 //TESTS FOR SORTING AND BINARY SEARCH FUNCTIONS ONLY
    	 
    	 /*
    	 int[] a = {1,6,2,12,721,72};
    	 for (int i = 0; i < a.length; i++) {
             if (i > 0) {
                System.out.print(", ");
             }
             System.out.print(a[i]);
             System.out.println("");
          }
    	 Collinear.sort(a);
    	 for (int i = 0; i < a.length; i++) {
             if (i > 0) {
                System.out.print(", ");
             }
             System.out.print(a[i]);
             System.out.println("");
          }
    	 System.out.println(Collinear.binarySearch(a, 3));
    	 System.out.println(Collinear.binarySearch(a, 2));
    	 */
     }

}

