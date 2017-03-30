package myDataStructure;

class Edge{
	public int start;
	public int end;
	public int distance;
	public Edge(int start, int end, int distance) {
		super();
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	
}

class Vertext{
	public char label;
	public boolean isInTree;
	public Vertext(char label) {
		this.label=label;
		isInTree=false;
	}
}

class PriorityQ{
	public final int SIZE=20;
	public Edge[] queArray=new Edge[SIZE];
	public int size;
	public void insert(Edge item){
		int j;
		for(j=0;j<size;j++)
			if(item.distance>=queArray[j].distance)
				break;
		
	}
}








public class mstwAPP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
