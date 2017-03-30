package graphy;

public class Test {
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
