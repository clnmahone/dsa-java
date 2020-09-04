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
		if (isEmpty())// ��lastָ�����һ���ڵ㣬�����Ȳ�����Ǹ��ڵ�
			last = newLink;
		newLink.next = first;// ���½ڵ�ָ��ԭ����first
		first = newLink;// �½ڵ��滻ԭ����first
	}

	public void insertLast(int data) {
		Link newLink = new Link(data);
		if (isEmpty())
			first = newLink;
		// last.next=newLink;��lastΪ�վͻᱨ��
		else
			last.next = newLink;
		last = newLink;
	}

	public int deletFirst() {
		int temp = first.data;
		if (first.next == null)// ɾ�����һ���ڵ��ʱ���
			last = null;// ��last��Ϊnull
		first = first.next;
		return temp;
	}

	// ����������ɾ������Ϊû�дӺ�ָ��ǰ��Ӧ��
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
