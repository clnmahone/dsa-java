//用单链表来实现栈
package myDataStructure;

class LinkList {
	public Link first;

	public LinkList() {
		first = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int data) {
		
		Link newLink = new Link(data);
		newLink.next=first;
		first = newLink;
		
	}
	
	public int deletFirst(){
		int temp=first.data;
		first=first.next;
		return temp;
	}
	
	public Link find(int key){
		Link current = first;
		while(current.data!=key){
			if(current.next==null)
				return null;
			else
				current=current.next;
		}
		return current;
	}
	public void display(){
		Link current = first;
		while(current!=null){
			current.displayLink();
			current=current.next;
		}
		System.out.println();
	}
}

public class LinkStack {
	public LinkList linkList;
	public LinkStack() {
		linkList= new LinkList();
	}
	public void push(int data){
		linkList.insertFirst(data);
	}
	public int pop(){
		return linkList.deletFirst();
	}
	public void display(){
		System.out.println("LinkStack:top-->buttom");
		linkList.display();
		System.out.println();
	}
}

class LinkStackApp{
	public static void main(String[] args) {
		LinkStack linkStack=new LinkStack();
		linkStack.push(1);
		linkStack.push(2);
		linkStack.push(3);
		linkStack.push(4);
		
		linkStack.display();
		linkStack.pop();
		linkStack.pop();
		linkStack.pop();
		linkStack.display();
	}
}
