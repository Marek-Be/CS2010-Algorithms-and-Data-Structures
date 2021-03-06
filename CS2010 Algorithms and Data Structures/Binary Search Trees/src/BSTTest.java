import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Marek Betka
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  //TODO write more tests here.

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }
 	 /** <p>Test {@link BST#printKeysInOrder()}.</p> */
	 @Test
	 public void testNormalPrint()
	 {
		 BST<Integer, Integer> bst = new BST<Integer, Integer>();

	     bst.put(7, 7);   //        _7_
	     bst.put(8, 8);   //      /     \
	     bst.put(3, 3);   //    _3_      8
	     bst.put(1, 1);   //  /     \
	     bst.put(2, 2);   // 1       6
	     bst.put(6, 6);   //  \     /
	     bst.put(4, 4);   //   2   4
	     bst.put(5, 5);   //        \
	                      //         5 
	     
	     assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
	 }
	 
	 /** <p>Test {@link BST#median()}.</p> */
 	 @Test
 	 public void testMedian()
 	 {
 		BST<Integer, Integer> bst = new BST<Integer, Integer>();

        assertEquals("Null Median","null",bst.median() + "");
 		bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("Median","4",bst.median() + "");
        
        bst.put(9, 9);
        bst.put(11, 11);
 	 }

 	 /** <p>Test {@link BST#put()}.</p> */
 	 @Test
 	 public void testPut()
 	 {
  		BST<Integer, Integer> bst = new BST<Integer, Integer>();
  		
  		bst.put(1, null);
  		
 		 
 	 }

 	 /** <p>Test {@link BST#put()}.</p> */
 	 @Test
	 public void testContains()
	 {
 		BST<Integer, Integer> bst = new BST<Integer, Integer>();
 		
 		bst.put(1,1);
 		assertEquals("Contains 1", bst.contains(1), true);
 		assertEquals("Not Contains 2", bst.contains(2), false);
	 }
 	 
 	 /** <p>Test {@link BST#height()}.</p> */
 	 @Test
 	 public void testHeight()
 	 {
 		BST<Integer, Integer> bst = new BST<Integer, Integer>();

        assertEquals("Height of tree", -1, bst.height());
        bst.put(7, 7);   //        _7_
        assertEquals("Height of tree", 0, bst.height());
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        assertEquals("Height of tree", 4, bst.height());

        bst.put(11, 11);
        bst.put(10, 10);
        bst.put(12, 12);
        bst.put(13, 13);
        bst.put(9, 9);
        assertEquals("Height of tree", 4, bst.height());
 	 }
 	 
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(1, 1);
         bst.delete(1);
         
         assertEquals("Deleting only element from a tree", "()", bst.printKeysInOrder());
         
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1(()2()))5(()4()))7())", bst.printKeysInOrder());
     }
     
}