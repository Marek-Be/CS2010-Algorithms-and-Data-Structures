// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  Marek Betka
 *  @version 12/10/2017 00:49
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              5.681
     *  2000              44.897
     *  4000              358.194
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *
     *  b = -27.286
     *  a = 2.989
     *
     *  What running time do you predict using your results for input size 5000?
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              695.532                               698.74                            0.46%
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: n^3
     *
     *  Explanation: For every for loop we need to go through n different values, this
     *  can be approximated mathematically as
     *  n*n*n
     *  Therefore
     *  n^3
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
    	for (int i = 0; i<a1.length;i++)
    	{
    		for (int j = 0; j<a2.length;j++)
        	{
    			for (int k = 0; k<a3.length;k++)
    	    	{
    	    		if (a2[j]-a3[k] + 2*(a3[k]-a1[i]) + 3*(a1[i]-a2[j]) == 0)
    	    		{
    	    			count++;
    	    		}
    	    	}
        	}
    	}
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.147
     *  2000              0.622
     *  4000              2.607
     *  5000              4.233
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the sppedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              5.681/0.147	=	38.65
     *  2000              44.897/0.622	=	72.18
     *  4000              358.194/2.607	=	137.4
     *  5000              698.74/4.233	=	165.07 
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: 
     *
     *  Explanation: Since we are only using 2 for loops, this can be approximated as
     *  n*n
     *  or n^2
     *  
     *  One array is sorted at the beginning once, which using insertion sort takes approximately
     *  n^2 (See function for explanation)
     *
     *	And the binarySearch function is run once every for loop ever for loop, i.e mathematically:
     *	n*n*lg(n)
     *	or n^2 * lg(n)
     *
     *	To get the total order of growth we must add these together, which results in:
     *
     *	2 * (n^2) + (n^2) lg(n)
     *	
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
    	
    	int count = 0;
    	int numberToFind = 0;
    	sort(a3);
    	for (int i = 0; i<a1.length;i++)
    	{
    		for (int j = 0; j<a2.length;j++)
        	{
    			numberToFind =  2*a2[j] - a1[i];
    			
    			if (binarySearch(a3, numberToFind))
    				count++;
        	}
    	}
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: n^2
     *
     *  Explanation: Each of the for loops has to go through n variables, which can
     *  be expressed mathematically as
     *  n*n
     *	Therefore
     *	n^2
     */
    static void sort(int[] a)
    {
        int temp;
        
        for (int i = 1; i < a.length; i++) 
        {
            for(int j = i; j > 0; j--)
            {
                if(a[j] < a[j-1]){
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: lg(n)
     *
     *  Explanation: The algorithm functions by removing half of all irrelevant values
     *	until it has found the value. This can be approximated mathematically as
     *	1 = N / 2^x
     *	We can move 2^x to the other side and get
     *	2^x = N
     *	Finally we can use log2
     *	log2(2^x) = log2(N)
     *	x = log2(N)
     *	Therefore,
     *	x = lg(N)
     */
    static boolean binarySearch(int[] a, int x)
    {
    	int upperLim = a.length - 1;
    	int lowerLim = 0;
    	while (lowerLim <= upperLim)
    	{
        	int mid = (upperLim + lowerLim)/2;
    		if (x < a[mid])
    		{
    			upperLim = mid-1;
    		}
    		else if (x > a[mid])
    		{
    			lowerLim = mid+1;
    		}
    		else return true;
    	}
      return false;
    }

}
