import javax.crypto.CipherInputStream;
import javax.swing.RootPaneContainer;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @Marek Betka
 *
 * @version 14/12/15 15:08:22
 */
public class FacebookCircles {
	
	private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
    int numberOfCircles = 0;
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int n) {
      count = n;
      parent = new int[n];
      size = new int[n];
      for (int i = 0; i < n; i++) {
          parent[i] = i;
          size[i] = 1;
      }
  }

	
  public boolean connected(int p, int q) {
      return find(p) == find(q);
  }
  
  public int find(int p) {
	  while (p != parent[p]) {
          parent[p] = parent[parent[p]];    // path compression by halving
          p = parent[p];
      }
      return p;
  }
  
  
  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);
      if (rootP == rootQ) return;

      // make smaller root point to larger one
      if (size[rootP] < size[rootQ]) {
          parent[rootP] = rootQ;
          size[rootQ] += size[rootP];
      }
      else {
          parent[rootQ] = rootP;
          size[rootP] += size[rootQ];
      }
      count--;
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles()
  {
	  return count;
  }
  
  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
	  int max = size[0];

	  for (int i = 1; i < size.length; i++) {
	      if (size[i] > max) {
	        max = size[i];
	      }
	  }
	return max;
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
	  int a = 0;
	  for (int i = 0; i < parent.length; i++)
	  {
		  if (parent[i] == i)
			  a += size[i];
	  }
	  return a/count;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
	  int min = Integer.MAX_VALUE;

	  for (int i = 0; i < parent.length; i++) {
	      if (parent[i] == i && size[i] < min) 
	        min = size[i];
	  }
	return min;
  }


}
