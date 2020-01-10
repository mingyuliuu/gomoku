// Mingyu Liu
// July 2019

package application;

public interface StackADT {
	
	// Insert an element at one end of the stack called top.
	public void push(Object i); 
	
	// Remove and return the element at the top of the stack, if it is not empty.
	public Object pop(); 	
	
	// Return the element at the top of the stack without removing it, if the stack is not empty.
	public Object peek();
	
	// Return the number of elements in the stack.
	public int size(); 	
	
	// Return true if the stack is empty, otherwise return false.
	public boolean isEmpty(); 	
	
	// Return true if the stack is full, otherwise return false.
	public boolean isFull(); 	
	
}
