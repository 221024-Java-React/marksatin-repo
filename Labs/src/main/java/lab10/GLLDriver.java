package lab10;

public class GLLDriver<T> {
	
	public static void main(String[] args) {
		
		GenericLinkedList<Object> list = new GenericLinkedList<>();


		// Insert the values
		list.addNode('A');
		list.addNode(20);
		list.addNode(30);
		list.addNode(40);
		list.addNode("Hello");
		list.addNode(60);
		list.addNode(70);
		list.addNode(80);

		// Print the LinkedList
		list.printList();
		System.out.println("\n");
		Node<Object> copy = list.getNodeCopy(4);
		System.out.println(copy.data);
		list.printList();
		
	};
	
}