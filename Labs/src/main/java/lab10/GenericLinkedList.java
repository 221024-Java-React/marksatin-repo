package lab10;

/* https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/ */
public class GenericLinkedList<T> {

	Node<T> head;

	// ADD NODE TO LIST
	public void addNode(T data) {
		// Create a new node with given data
		Node<T> newNode = new Node<>(data);
		

		// If the Linked List is empty,
		// then add the new node to the head of the list
		if (head == null) {
			head = newNode;
		}
		else {
			// Else traverse the list until you find the last node
			Node<T> last = head;
			while (last.next != null) {
				last = last.next;
			}

			// Add the new node to the end of the list
			last.next = newNode;
		}
	}
	
	// DELETE NODE IN LIST
	public void deleteNode(int index) {
		
		Node<T> current = head;
		Node<T> prev = null;
		int counter = 0;
		
		while (current != null) {
			
			// INDEX 0
			if (index == 0) {
				head = current.next;
			}
			
			// ANY OTHER INDEX IN LIST
			if (index == counter) {
				prev.next = current.next;
				System.out.println("Index " + index + " was deleted from the list.");
				break;
			} else {
				prev = current;
				current = current.next;
				counter++;
			}
		}
			
		// INDEX NOT IN LIST
		if (current == null) {
			System.out.println("Index " + index + " was not found.");
			throw new NullPointerException();
		}
	}
	
	// GET NODE DATA AT INDEX
	public Node<T> getNodeCopy(int index) {
		
		Node<T> current = head;
		Node<T> prev = null;
		int counter = 0;
		
		while (current != null) {
			
			// INDEX 0
			if (index == 0) {
				break;
			}
			
			// ANY OTHER INDEX IN LIST
			if (index == counter) {
				break;
			} else {
				prev = current;
				current = current.next;
				counter++;
			}
		}
		
		// INDEX NOT IN LIST
		if (current == null) {
			System.out.println("Index " + index + " was not found.");
			throw new NullPointerException();
		}
		
		return current;
	}
	

	// PRINT LIST
	public void printList() {
		Node<T> current = head;
	
		System.out.print("LinkedList: ");
	
		// Traverse through the LinkedList
		while (current != null) {
			// Print the data at current node
			System.out.print(current.data + " ");
	
			// Go to next node
			current = current.next;
		}
	}
}
