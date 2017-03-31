package myDataStruct;

//define LinkNode
class LinkNode {

	public int id; // the data
	public LinkNode next; // the next

	// the constructor
	public LinkNode(int id) {
		this.id = id;
	}

	// display the node
	public void dispaly() {
		System.out.print("[" + id + "]-->");
	}

}

// the link list
public class LinkList {
	// the head node
	private LinkNode first;

	// the constructor
	public LinkList() {
		first = null;
	}

	// is empty?
	public boolean isEmpty() {
		return first == null;
	}

	// insert new node form the head node
	public void insertFirst(int id) {
		LinkNode link = new LinkNode(id);
		link.next = first;
		first = link;
	}

	// delete node from the head node
	public LinkNode deletFirst() {

		LinkNode temp = first;
		first = first.next;
		return temp;
	}

	// show the link list
	public void displayLink() {
		System.out.println("first-->end");
		LinkNode current = first;
		while (current != null) {
			current.dispaly();
			current = current.next;
		}
	}

	// find a link node
	public LinkNode find(int key) {
		LinkNode current = first;
		while (current.id != key) {
			if (current.next == null) {
				return null;
			} else {
				current = current.next;
			}
		}
		return current;
	}

	public LinkNode deleNode(int key) {
		LinkNode current = first;
		LinkNode previous = first;
		while (current.id != key) {
			if (current.next == null) {
				return null;
			} else {
				previous = current;
				current = current.next;
			}

		}
		if (current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;

	}

}

// just a test
class LinkApp {
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		linkList.insertFirst(1);
		linkList.insertFirst(2);
		linkList.insertFirst(3);
		linkList.insertFirst(4);
		linkList.displayLink();

		while (!linkList.isEmpty()) {
			LinkNode deleted = linkList.deletFirst();
			System.out.println();
			System.out.print("delete");
			deleted.dispaly();
		}

	}
}

class LinkApp2{
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		for (int i = 0; i < 5; i++) {
			linkList.insertFirst(i);
		}
		linkList.displayLink();
		LinkNode f = linkList.find(7);
		if(f==null){
			System.out.println("can't find~");
		}
		else{
			System.out.println();
			f.dispaly();
			System.out.println("finded");
		}
		LinkNode dele = linkList.deleNode(4);
		if(dele==null){
			System.out.println("can't find~");
		}
		else{
			dele.dispaly();
			System.out.println("deleted");
		}
		linkList.displayLink();
	}
}
