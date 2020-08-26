package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Remeber object cannot store null pointers");
		}
		LLNode<E> current = new LLNode<E>(element);
		
		current.next =tail;
		current.prev =tail.prev;
		tail.prev.next=current;
		tail.prev=current;
		size++;
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
	 if(index<0||index>size-1) {
			throw new IndexOutOfBoundsException("Index input is out of bounds!!! Check that !!!");
		}
	 else if(index == 0) {return head.next.data;}
		LLNode<E> current = head.next;
		for (int i=1; i <=index; i ++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Remeber object cannot store null pointers");
		}
		else if(size == 0&&index ==0) {
			add(element);
		}
		else if(index<0||index>size-1) { //check if size > 0 and if index is in the proper range
			throw new IndexOutOfBoundsException("Index input is out of bounds!!! Check that !!!");
		}
		else{
			LLNode<E> inserted = new LLNode<E>(element);
			LLNode<E> NodeAtIndex = getNodeAtIndex(index);
			
			//now that NodeAtIndex get the correct node
			inserted.prev = NodeAtIndex.prev;
			inserted.next = NodeAtIndex;
			NodeAtIndex.prev.next = inserted;
			NodeAtIndex.prev = inserted;
			size++;
		}
		
	}
	
	public LLNode<E>  getNodeAtIndex(int index) {
		LLNode<E> NodeAtIndex;
		NodeAtIndex = head;
		for (int i=0; i<=index; i++) {
			NodeAtIndex = NodeAtIndex.next;
		}
		return NodeAtIndex;
	}

	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index<0||index>size-1) { //check if size > 0 and if index is in the proper range
			throw new IndexOutOfBoundsException("Index input is out of bounds!!! Check that !!!");
		}
		else {
			LLNode<E> NodeAtIndex = getNodeAtIndex(index);
			NodeAtIndex.prev.next = NodeAtIndex.next;
			NodeAtIndex.next.prev = NodeAtIndex.prev;
			NodeAtIndex.next = null;
			NodeAtIndex.prev =null;
			if(size>0) {
				size = size-1;
			}
			return NodeAtIndex.data;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Remeber object cannot store null pointers");
		}
		else if(index<0||index>size-1) { //check if size > 0 and if index is in the proper range
			throw new IndexOutOfBoundsException("Index input is out of bounds!!! Check that !!!");
		}
		LLNode<E> NodeAtIndex = getNodeAtIndex(index);
		E oldValue = NodeAtIndex.data;
		NodeAtIndex.data = element;
		return oldValue;
	}   
	
	public String toString() {
		LLNode<E> NodeAtIndex;
		String listAsString = "";
		NodeAtIndex = head;
		for (int i=0; i<=size-1; i++) {
			NodeAtIndex = NodeAtIndex.next;
			listAsString = listAsString + " " + NodeAtIndex.data.toString();
		}
		return listAsString;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
