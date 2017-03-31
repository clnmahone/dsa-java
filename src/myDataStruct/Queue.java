package myDataStruct;

public class Queue {
	int maxSize;
	int[] queArray;
	int front;
	int rear;
	int nItems;
	
	public Queue(int s) {
		maxSize=s;
		queArray=new int[maxSize];
		front=0;
		rear=-1;
		nItems=0;
	}
	
	public void insert(int item){
		if(rear==maxSize-1)
			rear=-1;
		queArray[++rear]=item;
		nItems++;
	}
	
	public int remove(){
		int temp=queArray[front++];
		if(front==maxSize)
			front=0;
		nItems--;
		return temp;
	}
	
	public int peekFrint(){
		return queArray[front];
	}
	
	public boolean isEmpty(){
		return nItems==0;
	}
	
	public boolean isFull(){
		return nItems==maxSize;
	}
	
	public int size(){
		return nItems;
	}
}

class QueueApp{
	public static void main(String[] args) {
		Queue queue = new Queue(5);
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
		queue.remove();
		queue.remove();
		queue.remove();
		queue.insert(5);
		queue.insert(6);
		queue.insert(7);
		queue.insert(8);
		queue.insert(9);
		while(!queue.isEmpty()){
			int n = queue.remove();
			System.out.print(n);
			System.out.print(' ');
		}
	}
}
