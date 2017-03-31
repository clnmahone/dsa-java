//用单链表来实现栈
package myDataStruct;

public class LinkStack {
	public LinkList linkList;
	public LinkStack() {
		linkList= new LinkList();
	}
	public void push(int data){
		linkList.insertFirst(data);
	}
	public LinkNode pop(){
		return linkList.deletFirst();
	}
	public void display(){
		System.out.println("LinkStack:top-->buttom");
		linkList.displayLink();
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
