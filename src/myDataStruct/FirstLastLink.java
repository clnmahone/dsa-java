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

public class FirstLastLink {
	
}
