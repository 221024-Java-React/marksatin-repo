package lab10;

public class Node<T> {

	T data;
	Node<T> next;
	
	public Node(T d) {
		data = d;
		next = null;
	}
}
