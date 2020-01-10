// Mingyu Liu
// July 2019

package application;

public class Node {

	private Object obj;
	private Node nextNode;
	
	// The constructor sets the current element.
	public Node(Object i) {
		obj = i;
	}
	
	// Get the current element.
	public Object get() {
		return obj;
	}
	
	// Set the current element.
	public void set(Object i) {
		obj = i;
	}

	// Get the next Node.
	public Node nextNode() {
		return nextNode;
	}
	
	// Set the next Node.
	public void setNextNode(Node n) {
		nextNode = n;
	}
	
}
