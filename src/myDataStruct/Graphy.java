package myDataStruct;
class Vertex {
	public char lable;
	public boolean wasVisited;
	public Vertex(char lable) {
		this.lable = lable;
		wasVisited = false;
	}
}

public class Graphy {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVerts;
	
	public Graphy() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMat[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char lable){
		vertexList[nVerts++] = new Vertex(lable);
	}
	
	public void addEdge(int start,int end){
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].lable);
	}
}
class  GraphyApp{
	public static void main(String[] args) {
		Graphy graphy = new Graphy();
		graphy.addVertex('a');
		graphy.addVertex('b');
		graphy.addVertex('c');
		graphy.addVertex('d');
		graphy.addEdge(1, 2);
		graphy.addEdge(2, 1);
		graphy.addEdge(2, 3);
		graphy.addEdge(3, 2);
		graphy.addEdge(1, 3);
		graphy.addEdge(3, 1);
		graphy.displayVertex(2);
	}
}