package myDataStruct;

public class StackX {
	private int maxSize;
	private int top;
	private int[] stackArry;

	public StackX(int size) {
		maxSize = size;
		stackArry = new int[maxSize];
		top = -1;
	}

	public void push(int j) {
		stackArry[++top] = j;
	}

	public int pop() {
		return stackArry[top--];
	}

	public int peek() {
		return stackArry[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == maxSize - 1;
	}

	public int size() {
		return top + 1;
	}

	public int peekN(int n) {
		return stackArry[n];
	}

	public void displayStack() {
		System.out.println("Stack bottom-->top");
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.println(" ");
		}
	}

}

