/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		//list{65,21,42}
		//emptyList{}
		//shortList{A,B}
		//longerList{0,1,2...9}
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		//assertEquals("Remove: check reference is correct", list1.head, list1.getNodeAtIndex(0).prev);
		
		// TODO: Add more tests here
		String A = shortList.remove(1);
		assertEquals("Remove: check B is correct ", "B", A);
		assertEquals("Remove: check element 0 is correct", "A", shortList.get(0));
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			longerList.remove(11);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			shortList.add(null);
			fail("Check Null pointer");
		}
		catch (NullPointerException e){
			
		}
		list1.add(999);
		assertEquals("Add End: check element added? ", (Integer)999, list1.get(3));
		assertEquals("Add End: check size updated? ", 4, list1.size());
		//assertEquals("Add End: check reference ", list1.tail, list1.getNodeAtIndex(3).next);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		
		// TODO: implement this test
		list1.remove(0);
		assertEquals("Check size: ", 2, list1.size());
		list1.add(1);
		assertEquals("Check size: ", 3, list1.size());
		list1.add(0,23);
		assertEquals("Check size: ", 4, list1.size());
		list1.set(0, 24);
		assertEquals("Check size: ", 4, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			list1.add(2, null);
			fail("Check Null pointer");
		}
		catch (NullPointerException e){
			
		}
		try {
			list1.add(4, 999);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.add(-2, 999);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		list1.add(1, 111);
		assertEquals("Check testAddAtIndex: ", (Integer)111, list1.get(1));
		assertEquals("Check testAddAtIndex: ", (Integer)21, list1.get(2));
		//assertEquals("Check testAddAtIndex: ", list1.getNodeAtIndex(1), list1.getNodeAtIndex(2).prev);
		//assertEquals("Check testAddAtIndex: ", list1.getNodeAtIndex(0), list1.getNodeAtIndex(1).prev);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			list1.set(2, null);
			fail("Check Null pointer");
		}
		catch (NullPointerException e){
			
		}
		try {
			list1.set(4, 999);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		list1.set(2, 222);
		assertEquals("Set: check", (Integer)222, list1.get(2));
	}
	@Test
	public void testToString() {
		System.out.println(list1.toString());
	}
	
	@Test
	public void testPlus() {
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		lst.add(0, 1);
		lst.remove(0);
		lst.add(0, 1);
	}
	// TODO: Optionally add more test methods.
	
}
