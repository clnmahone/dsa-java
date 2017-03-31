package myDataStructure;

class Link {
	public int data;
	public Link next;

	public Link(int data) {
		this.data = data;
		next = null;
	}

	public void displayLink() {
		System.out.print(data + " ");
	}
}

public class FirstLastLink {
	public Link first;
	public Link last;

	public FirstLastLink() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int data) {
		Link newLink = new Link(data);
		if (isEmpty())// 让last指向最后一个节点，即最先插入的那个节点
			last = newLink;
		newLink.next = first;// 让新节点指向原来的first
		first = newLink;// 新节点替换原来的first
	}

	public void insertLast(int data) {
		Link newLink = new Link(data);
		if (isEmpty())
			first = newLink;
		// last.next=newLink;当last为空就会报错
		else
			last.next = newLink;
		last = newLink;
	}

	public int deletFirst() {
		int temp = first.data;
		if (first.next == null)// 删到最后一个节点的时候后
			last = null;// 把last置为null
		first = first.next;
		return temp;
	}

	// 不能向后逐个删除，因为没有从后指向前的应用
	public void displayFirstLastlink() {
		System.out.println("first-->last");
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
}

class FirstLastLinkApp {
	public static void main(String[] args) {
		FirstLastLink firstLastLink = new FirstLastLink();
		firstLastLink.insertFirst(1);
		firstLastLink.insertFirst(2);
		firstLastLink.insertFirst(3);
		firstLastLink.insertLast(7);
		firstLastLink.insertLast(8);
		firstLastLink.insertLast(9);
		firstLastLink.displayFirstLastlink();
		firstLastLink.deletFirst();
		firstLastLink.deletFirst();
		firstLastLink.deletFirst();
		firstLastLink.displayFirstLastlink();
	}
}
