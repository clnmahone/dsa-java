package myDataStructure;

class DisPra {
	public int distance;
	public int parentVert;

	public DisPra(int distance, int parentVert) {
		this.distance = distance;
		this.parentVert = parentVert;
	}
}

class Vert {
	public char lable;
	public boolean isInTree;

	public Vert(char lable) {
		this.lable = lable;
		this.isInTree = false;
	}
}

class Graphy {
	public final int MAX_VERTS = 20;
	public final int INFINITY = 10000;
	public Vert[] vertList;
	public int[][] adjMat;
	public int nTree;
	public int nVerts;
	public DisPra[] sPath;
	public int currentVert;
	public int startToCurrent;

	public Graphy() {
		vertList = new Vert[MAX_VERTS];
		nTree = 0;
		nVerts = 0;
		sPath = new DisPra[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMat[i][j] = INFINITY;
			}
		}
	}

	public void addVert(char lab) {
		vertList[nVerts++] = new Vert(lab);
	}

	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;
	}

	public void path() {
		int startTree = 0;
		vertList[startTree].isInTree = true;
		nTree = 1;

		for (int i = 0; i < nVerts; i++) {
			int temDis = adjMat[startTree][i];
			sPath[i] = new DisPra(temDis, startTree);//open 表 起点到其他点的距离
		}

		while (nTree < nVerts) {
			int minIndex = getMin();//找到距离起点最近的点
			int minDis = sPath[minIndex].distance;
			if (minDis == INFINITY) {
				System.out.println("There are unreachable vertices");
				break;
			} else {
				currentVert = minIndex;//把这个点作为当前节点
				startToCurrent = sPath[minIndex].distance;
			}
			vertList[currentVert].isInTree = true;
			nTree++;
			adjust_sPath();
		}
		disPlay();
		nTree = 0; // clear tree
		for (int j = 0; j < nVerts; j++)
			vertList[j].isInTree = false;
	}

	public void adjust_sPath() {
		int c = 1;
		while (c < nVerts) {//过滤掉已在树中的节点，选择边缘节点
			if (vertList[c].isInTree) {
				c++;
				continue;
			}
			int currentToFringe = adjMat[currentVert][c];
			int startToFringe = startToCurrent + currentToFringe;
			int sPathDis = sPath[c].distance;
			if (startToFringe < sPathDis) {//选择两种距离算法中最近的1.从sPatn中获取2.距离相加
				sPath[c].parentVert = currentVert;
				sPath[c].distance = startToFringe;
			}
			c++;
		}

	}

	public int getMin() {
		int minDis = INFINITY;
		int minIndex = 0;
		for (int i = 1; i < nVerts; i++) {

			if (!vertList[i].isInTree//过滤已在树中的节点
					&& sPath[i].distance < minDis ) {//选择表中最小的距离
				minDis = sPath[i].distance;
				minIndex = i;
			}
		}
		return minIndex;
	}

	public void disPlay() {
		for (int i = 0; i < nVerts; i++) {
			System.out.print(vertList[i].lable + "=");
			if (sPath[i].distance == INFINITY) {
				System.out.print("inf");
			} else {
				System.out.print(sPath[i].distance);
			}
			char parent = vertList[sPath[i].parentVert].lable;
			System.out.print("(" + parent + ") ");
		}
		System.out.println();
	}
}

class PathAPP {
	public static void main(String[] args) {
		Graphy theGraph = new Graphy();
		theGraph.addVert('A'); // 0 (start)
		theGraph.addVert('B'); // 1
		theGraph.addVert('C'); // 2
		theGraph.addVert('D'); // 3
		theGraph.addVert('E'); // 4

		theGraph.addEdge(0, 1, 50); // AB 50
		theGraph.addEdge(0, 3, 80); // AD 80
		theGraph.addEdge(1, 2, 60); // BC 60
		theGraph.addEdge(1, 3, 90); // BD 90
		theGraph.addEdge(2, 4, 40); // CE 40
		theGraph.addEdge(3, 2, 20); // DC 20
		theGraph.addEdge(3, 4, 70); // DE 70
		theGraph.addEdge(4, 1, 50); // EB 50

		System.out.println("Shortest paths");
		theGraph.path();
		; // shortest paths
		System.out.println();
	} // end main()
}
