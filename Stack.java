// Mingyu Liu
// July 2019

package application;

public class Stack implements StackADT{

	private Node firstNode;
	private int size = 0;
	
	public Stack(Object n) {
		firstNode = new Node(n);
		size = 1;
	}
	
	public Stack() {
		
	}
	
	// Insert an element at one end of the stack called top.
	public void push(Object i) {
		if(size==0)
			firstNode = new Node(i);
		Node temp = firstNode;
		for(int j=1; j<size; j++) {
			temp = temp.nextNode();
		}
		Node node = new Node(i);
		temp.setNextNode(node);
		size++;
	} 
	
	// Remove and return the element at the top of the stack, if it is not empty.
	public Object pop() {
		if(size==1) {
			size--;
			Node temp = firstNode;
			firstNode = null;
			return temp.get();
		}
		else {
			Node temp = firstNode;
			for(int j=1; j<size-1; j++) {
				temp = temp.nextNode();
			}
			Node returned = temp.nextNode();
			temp.setNextNode(null);
			size--;
			return returned.get();
		}
	}
	
	// Return the element at the top of the stack without removing it, if the stack is not empty.
	public Object peek() {
		Node temp = firstNode;
		for(int j=1; j<size; j++) {
			temp = temp.nextNode();
		}
		return temp.get();
	}
	
	// Return the number of elements in the stack.
	public int size() {
		return size;
	}
	
	// Return true if the stack is empty, otherwise return false.
	public boolean isEmpty() {
		if(size==0)
			return true;
		return false;
	}

	// Return true if the stack is full, otherwise return false.
	public boolean isFull() {
		return false;
	}
	
	// Clear all the elements in the stack.
	public void clear() {
		firstNode = null;
		size = 0;
	}
	
}
