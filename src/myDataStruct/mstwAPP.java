package myDataStruct;

class Edge {
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

class Vertext {
	public char label;
	public boolean isInTree;

	public Vertext(char label) {
		this.label = label;
		isInTree = false;
	}
}

class PriorityQ {
	public final int SIZE = 20;
	public Edge[] queArray;
	public int size;

	public PriorityQ() {
		queArray = new Edge[SIZE];
		size = 0;
	}

	public void insert(Edge item) {
		int j;
		for (j = 0; j < size; j++)
			if (item.distance >= queArray[j].distance)
				break;
		for (int k = size - 1; k >= j; k--)
			queArray[k + 1] = queArray[k];
		queArray[j] = item;
		size++;
	}

	public Edge removeMin() {
		return queArray[--size];
	}

	public Edge removeN(int n) {
		for (int j = n; j < size - 1; j++)
			queArray[j] = queArray[j + 1];
		size--;
		return queArray[n];
	}

	public Edge peekMin() {
		return queArray[size - 1];
	}

	public Edge peekN(int n) {
		return queArray[n];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int find(int findDex) {
		for (int j = 0; j < size; j++)
			if (queArray[j].end == findDex)
				return j;
		return -1;

	}
}

class Graph {
	public final int MAX_VERTEXTS = 20;
	public final int INFINITY = 10000;
	public Vertext[] vertextList;
	public PriorityQ thePQ;
	public int nTree;
	public int currentVertext;
	public int nVerts;
	public int[][] adjMax;

	public Graph() {
		nVerts = 0;
		vertextList = new Vertext[MAX_VERTEXTS];
		adjMax = new int[MAX_VERTEXTS][MAX_VERTEXTS];
		for (int i = 0; i < adjMax.length; i++) {
			for (int j = 0; j < adjMax.length; j++) {
				adjMax[i][j] = INFINITY;
			}
		}
		thePQ = new PriorityQ();
	}

	public void addVertext(char label) {
		vertextList[nVerts++] = new Vertext(label);
	}

	public void addEdge(int start, int end, int distance) {
		adjMax[start][end] = distance;
		adjMax[end][start] = distance;
	}

	public void displayVertext(int n) {
		System.out.print(vertextList[n].label);
	}

	public void mstw() {
		currentVertext = 0;// ��ͼ�ĵ�һ���ڵ㿪ʼ
		while (nTree < nVerts - 1) {// ���������еĽڵ㶼������
			vertextList[currentVertext].isInTree = true;// �ѵ�ǰ�ڵ�ŵ�����
			nTree++;// ����Ԫ������һ
			for (int i = 0; i < nVerts; i++) {// ����ͼ��ÿ���ڵ�
				if (i == currentVertext)// ����������
					continue;
				if (vertextList[i].isInTree == true)// �ڵ���������
					continue;
				int distance = adjMax[currentVertext][i];
				if (distance == INFINITY)// �����뵱ǰ�ڵ�û�������Ľڵ�
					continue;
				intoPQ(i, distance);// ɸѡ��ϣ����������
			}
			if (thePQ.size == 0) {
				System.out.println(" GRAPH NOT CONNECTED");
				return;

			}
			Edge theEdge = thePQ.removeMin();// ɾ�������о�����̵ı�
			int souceVerts = theEdge.start;
			currentVertext = theEdge.end;// ����̾����β�ڵ���Ϊ��һ��
			System.out.print(vertextList[souceVerts].label);
			System.out.print(vertextList[currentVertext].label);
			System.out.print(' ');
		}
		for (int j = 0; j < nVerts; j++) // unmark vertices
			vertextList[j].isInTree = false;
	}

	public void intoPQ(int newVerts, int newDis) {// ���ȶ�������㷨
		int queIndex = thePQ.find(newVerts);
		if (queIndex != -1) {// ������β�ڵ�������
			Edge tempEdge = thePQ.peekN(queIndex);
			
			if (tempEdge.distance > newDis) {
				thePQ.removeN(queIndex);
				Edge theEdge = new Edge(currentVertext, newVerts, newDis);
				thePQ.insert(theEdge);
			}
		} else {// ������û��������ڵ�Ϊ��β�Ĺ�ϵֱ�Ӳ���
			Edge edge = new Edge(currentVertext, newVerts, newDis);
			thePQ.insert(edge);
		}
	}
}

public class mstwAPP {

	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertext('A'); // 0 (start for mst)
		theGraph.addVertext('B'); // 1
		theGraph.addVertext('C'); // 2
		theGraph.addVertext('D'); // 3
		theGraph.addVertext('E'); // 4
		theGraph.addVertext('F'); // 5

		theGraph.addEdge(0, 1, 6); // AB 6
		theGraph.addEdge(0, 3, 4); // AD 4
		theGraph.addEdge(1, 2, 10); // BC 10
		theGraph.addEdge(1, 3, 7); // BD 7
		theGraph.addEdge(1, 4, 7); // BE 7
		theGraph.addEdge(2, 3, 8); // CD 8
		theGraph.addEdge(2, 4, 5); // CE 5
		theGraph.addEdge(2, 5, 6); // CF 6
		theGraph.addEdge(3, 4, 12); // DE 12
		theGraph.addEdge(4, 5, 7); // EF 7

		System.out.print("Minimum spanning tree: ");
		theGraph.mstw(); // minimum spanning tree
		System.out.println();

	}

}
