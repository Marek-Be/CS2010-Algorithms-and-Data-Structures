import static org.junit.Assert.assertEquals;

import java.awt.geom.CubicCurve2D;
import java.nio.channels.NetworkChannel;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T>
 *            This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *
 *            When creating a new DoublyLinkedList, T should be instantiated
 *            with an actual class name that extends the class Comparable. Such
 *            classes include String and Integer.
 *
 *            For example to create a new DoublyLinkedList class containing
 *            String data: DoublyLinkedList<String> myStringList = new
 *            DoublyLinkedList<String>();
 *
 *            The class offers a toString() method which returns a
 *            comma-separated sting of all elements in the data structure.
 * 
 *            This is a bare minimum class you would need to completely
 *            implement. You can add additional methods to support your code.
 *            Each method will need to be tested by your jUnit tests -- for
 *            simplicity in jUnit testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData
		 *            : data of type T, to be stored in the node
		 * @param prevNode
		 *            : the previous Node in the Doubly Linked List
		 * @param nextNode
		 *            : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: The basic operations only take Theta(1) runtime.
	 *         The code only runs through once, without a loop.
	 */
	public boolean isEmpty() {
		return head == null && tail == null;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos
	 *            : The integer location at which the new data should be
	 *            inserted in the list. We assume that the first position in the
	 *            list is 0 (zero). If pos is less than 0 then add to the head
	 *            of the list. If pos is greater or equal to the size of the
	 *            list then add the element at the end of the list.
	 * @param data
	 *            : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: The basic operations are all Theta(1), there worst
	 *         case is it cycles through the entire list from start to finish.
	 *         Therefore it will have a total running time of Theta(n)
	 */
	public void insertBefore(int pos, T data) {
		if (isEmpty())
		{
			DLLNode node = new DLLNode(data, null, null);
			head = node;
			tail = head;
		}
		else
		{
			DLLNode node = new DLLNode(data, null, null);
			if (pos <=0)
			{
				head.prev = node;
				node.next = head;
				head = node;
			}
			else
			{
				DLLNode temp = head;
				int tempPos = pos;
				while (temp.next != null && pos > 0)
				{
					pos-=1;
					temp = temp.next;
				}
				if(get(tempPos) == null)
				{
					tail.next = node;
					node.prev = tail;
					tail = node;
				}
				else
				{
					temp.prev.next = node;
					node.next = temp;
					temp.prev = node;
				}
			}
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos
	 *            : the position
	 * @return the data at pos, if pos is within the bounds of the list, and
	 *         null otherwise.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: The basic operations are all Theta(1), there worst
	 *         case is it cycles through the entire list from start to finish.
	 *         Therefore it will have a total running time of Theta(n).
	 */
	public T get(int pos) {
		DLLNode newNode = new DLLNode(null, tail, head);
		pos += 1;
		if (pos <= 0) {
			return null;
		}
		while (pos != 0) {
			if (newNode.next == null)
				return null;
			newNode = newNode.next;
			pos -= 1;
		}
		return newNode.data;
	}

	/**
	 * Deletes the element of the list at position pos. First element in the
	 * list has position 0. If pos points outside the elements of the list then
	 * no modification happens to the list.
	 * 
	 * @param pos
	 *            : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been
	 *         modified.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: The basic operations are all Theta(1), there worst
	 *         case is it cycles through the entire list from start to finish.
	 *         Therefore it will have a total running time of Theta(n).
	 */
	public boolean deleteAt(int pos) {
		DLLNode currentNode = head;
		DLLNode previousNode = null;
		DLLNode node = new DLLNode(null, tail, head);
		if (isEmpty()) {
			return false;
		}
		if (pos < 0) {
			return false;
		}

		while (pos != 0) {
			previousNode = currentNode;
			currentNode = currentNode.next;

			pos -= 1;
		}

		if (previousNode == null) {
			head = node.next;
			head.prev = null;
			return true;
		} else if (currentNode.next == null) {
			tail = currentNode.prev;
			tail.next = null;
			return true;
		} else {
			previousNode = currentNode.prev;
			previousNode.next = currentNode.next;
			previousNode = currentNode.next;
			previousNode.prev = currentNode.prev;
			return true;
		}
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the
	 * method is called Then it should contain "D", "C", "B", "A" after it
	 * returns.
	 *
	 * Worst-case asymptotic runtime cost: Theta(n)
	 *
	 * Justification: The basic operations are all Theta(1), there worst case is
	 * it cycles through the entire list from start to finish. Therefore it will
	 * have a total running time of Theta(n).
	 */
	public void reverse() {

		DLLNode temp = null;
		DLLNode currentNode = head;

		while (currentNode != null) {
			temp = currentNode.prev;
			currentNode.prev = currentNode.next;
			currentNode.next = temp;
			currentNode = currentNode.prev;
		}

		if (temp != null) {
			head = temp.prev;
		}
	}

	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT. If
	 * only the push and pop methods are called the data structure should behave
	 * like a stack. How exactly this will be represented in the Doubly Linked
	 * List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to push on the stack
	 *
	 * 
	 *            Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *            Justification: The basic operations only take Theta(1)
	 *            runtime. The code only runs through once, without a loop.
	 */
	public void push(T item) {
		DLLNode newNode = new DLLNode(item, tail, head);

		if (isEmpty()) {
			newNode.next = null;
			newNode.prev = null;
			head = newNode;
			tail = newNode;
		} else {
			head.prev = newNode;
			newNode.next = head;
			newNode.prev = null;
			head = newNode;
		}
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT. If
	 * only the push and pop methods are called the data structure should behave
	 * like a stack. How exactly this will be represented in the Doubly Linked
	 * List is up to the programmer.
	 * 
	 * @return the last item inserted with a push; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: The basic operations only take Theta(1) runtime.
	 *         The code only runs through once, without a loop.
	 */
	public T pop() {
		DLLNode temp = tail;
		if (isEmpty()) {
			return null;
		}
		if (!isEmpty()) {

			if (head.next == null) {
				head = null;
				tail = null;
			} else {
				tail = tail.prev;
				tail.next = null;
			}
		}
		return temp.data;
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure
	 * should behave like a FIFO queue. How exactly this will be represented in
	 * the Doubly Linked List is up to the programmer.
	 * 
	 * @param item
	 *            : the item to be enqueued to the stack
	 *
	 *            Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *            Justification: The basic operations only take Theta(1)
	 *            runtime. The code only runs through once, without a loop.
	 */
	public void enqueue(T item) {
		DLLNode newNode = new DLLNode(item, tail, head);

		if (isEmpty()) {
			newNode.next = null;
			newNode.prev = null;
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			newNode.next = null;
			tail = newNode;
		}
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure
	 * should behave like a FIFO queue. How exactly this will be represented in
	 * the Doubly Linked List is up to the programmer.
	 * 
	 * @return the earliest item inserted with a push; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic runtime cost: Theta(1)
	 *
	 *         Justification: The basic operations only take Theta(1) runtime.
	 *         The code only runs through once, without a loop.
	 */
	public T dequeue() {
		T item = null;
		if (isEmpty())
			return null;
		if (!isEmpty()) {
			DLLNode temp = head;

			item = temp.data;
			if (head.next == null) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				head.prev = null;
			}
		}
		return item;
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic runtime cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *         We assume all other method calls here (e.g., the iterator methods
	 *         above, and the toString method) will execute in Theta(1) time.
	 *         Thus, every one iteration of the for-loop will have cost
	 *         Theta(1). Suppose the doubly-linked list has 'n' elements. The
	 *         for-loop will always iterate over all n elements of the list, and
	 *         therefore the total cost of this method will be n*Theta(1) =
	 *         Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

	public static void main(String a[]) {
		
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(1, 2);
		
    	System.out.println(testDLL.toString());
	}

}
