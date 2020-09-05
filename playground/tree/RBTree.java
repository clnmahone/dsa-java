
/*
red black tree rules:

1. Every node is either red or black.
2. The root is black.
3. Every leaf (NIL) is black.
4. If a node is red, then both its children are black.
5. For each node, all simple paths from the node to descendant leaves contain the
same number of black nodes.

*/

class Test {
	public static void main(String[] args) {
		RBTree rbt = new RBTree();
		rbt.insertion(1);
		rbt.insertion(2);
		rbt.insertion(4);
		rbt.insertion(6);
		rbt.insertion(7);
		rbt.insertion(5);
		rbt.insertion(3);

		rbt.InOrder(rbt.root);

		rbt.deletion(6);
		rbt.deletion(1);
		rbt.deletion(4);

		rbt.InOrder(rbt.root);

	}

}

class RBTree {

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	private class Node {

		int val;
		Node p = t_nil, l = t_nil, r = t_nil;
		boolean color = BLACK;

		Node(int val) {this.val = val;}

	}

	// T.nil 一种特殊的节点：颜色为黑，其他属性为任意值.
	private final Node t_nil = new Node(-1);
	Node root = t_nil;

	// ====================traverse====================
	public void InOrder(Node node) {
		if (node != t_nil) {
			InOrder(node.l);
			System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.val+" Parent: "+node.p.val+"\n");
			InOrder(node.r);
		}
	}

	public void insertion(int val) {

		Node z = new Node(val);
		Node insertPoint = t_nil;
		Node current = this.root;

		while (current != t_nil) {
			// 放在遍历之前，保证insertPoint非空
			insertPoint = current;
			if (val > current.val)
				current = current.r;
			else
				current = current.l;
		}

		z.p = insertPoint;
		if (insertPoint == t_nil)
			this.root = z;
		else if (insertPoint.val < val)
			insertPoint.r = z;
		else
			insertPoint.l = z;
		z.color = RED;
		insert_fix(z);
	}

	// 核心算法
	private void insert_fix(Node z) {

		while (z.p.color == RED) {
			// newNode的爸爸是他爷爷的左孩子
			if (z.p.p.l == z.p) {
				Node uncle = z.p.p.r;
				// 叔叔为红
				if (uncle.color == RED) {
					// 变色爷爷红，爸爸叔叔黑
					z.p.p.color = RED;
					uncle.color = BLACK;
					z.p.color = BLACK;
					// next iteration
					z = z.p.p;
				}
				// 叔叔为黑，新节点为右孩子
				else {
					if (z == z.p.r) {
						z = z.p;
						rotate_left(z);
					}
					// 爷爷变红,爸爸变黑
					z.p.color = BLACK;
					z.p.p.color = RED;
					// 右旋
					rotate_right(z.p.p);
				}
			}
			// -------------------------------------------------
			// newNode的爸爸是他爷爷的右孩子，其他逻辑和上面雷同
			// ---------------------------------------------------
			else {
				Node uncle = z.p.p.l;
				// 叔叔为红
				if (uncle.color == RED) {
					// 变色爷爷红，爸爸叔叔黑
					z.p.p.color = RED;
					uncle.color = BLACK;
					z.p.color = BLACK;
					// next iteration
					z = z.p.p;
				}
				// 叔叔为黑，新节点为左孩子
				else {
					if (z == z.p.l) {
						z = z.p;
						rotate_right(z);
					}
					// 变色
					z.p.color = BLACK;
					z.p.p.color = RED;
					rotate_left(z.p.p);
				}
			}

		}
		// 考虑到newNode成为root的情况：
		// 循环终止，newNode的爸爸为黑，有可能这是newNode为root，
		// 因为root.p为sentinel T.nil,为黑。
		this.root.color = BLACK;
	}

	public void deletion(Node z) {

		if (z.l == t_nil)
			replace(z, z.r);
		else if (z.r == t_nil)
			replace(z, z.l);
		else {
			Node y = tree_min(z.r);
			if (y.p != z) {
				replace(y, y.r);
				y.r = z.r;
				z.r.p = y;
			}
			replace(z, y);
			y.l = z.l;
			z.l.p = y;
		}
	}

	public void deletion(int val) {
		Node z = this.tree_search(val);
		Node y = z;
		Node x = t_nil;
		boolean y_original_color = y.color;

		if (z.l == t_nil) {
			x = z.r;
			replace(z, z.r);
		}

		else if (z.r == t_nil) {
			x = z.l;
			replace(z, z.l);
		}

		else {
			y = tree_min(z.r);
			y_original_color = y.color;
			x = y.r;
			if (y.p == z) {
				x.p = y;
			} else {
				replace(y, y.r);
				y.r = z.r;
				z.r.p = y;
			}
			replace(z, y);
			y.l = z.l;
			z.l.p = y;
			y.color = z.color;
		}

		if (y_original_color == BLACK)
			deletion_fix(x);

	}

	void deletion_fix(Node x) {
		while (x != this.root && x.color == BLACK) {

			if (x == x.p.l) {

				Node w = x.p.r;
				// case1 w.color==RED
				if (w.color == RED) {
					w.color = BLACK;
					x.p.color = RED;
					rotate_left(x.p);
					w = x.p.r;
				}
				// case2 w为黑，w的孩子都为黑
				if (w.l.color == BLACK && w.r.color == BLACK) {
					w.color = RED;
					x = x.p;

				}
				// case3 w为黑，w的右孩子为红，左孩子为黑
				else if (w.r.color == BLACK) {
					w.l.color = BLACK;
					w.color = RED;
					rotate_right(w);
					w = x.p.r;

				}
				// 其他情况 case4 w为黑，w的右孩子为红
				w.color = x.p.color;
				x.p.color = BLACK;
				rotate_left(x.p);
				x = this.root;

			}

			// x是右孩子，镜像
			else {
				Node w = x.p.l;
				// case1 w.color==RED
				if (w.color == RED) {
					w.color = BLACK;
					x.p.color = RED;
					rotate_right(x.p);
					w = x.p.l;
				}
				// case2 w为黑，w的孩子都为黑
				if (w.r.color == BLACK && w.l.color == BLACK) {
					w.color = RED;
					x = x.p;

				}
				// case3 w为黑，w的右孩子为红，左孩子为黑
				else if (w.l.color == BLACK) {
					w.r.color = BLACK;
					w.color = RED;
					rotate_left(w);
					w = x.p.l;

				}
				// 其他情况 case4 w为黑，w的右孩子为红
				w.color = x.p.color;
				x.p.color = BLACK;
				rotate_right(x.p);
				x = this.root;
			
			}

		}
	}

	// ------------------------min----------------------------
	public Node tree_min(Node n) {
		while (n.l != t_nil)
			n = n.l;
		return n;
	}

	// ------------------------max---------------------------
	public Node tree_max(Node n) {
		while (n.r != t_nil)
			n = n.r;
		return n;
	}

	// -----------------------search---------------------------
	public Node tree_search(int key) {
		Node current = this.root;
		while (current != t_nil && current.val!=key) {
			if (current.val > key)
				current = current.l;
			else
				current = current.r;
		}
		return current;
	}


	// -------------------------RBreplace----------------------
	public void replace(Node x, Node y) {
		if (x.p == t_nil)
			this.root = y;
		else if (x == x.p.l)
			x.p.l = y;
		else
			x.p.r = y;
		// if (y != t_nil)
		y.p = x.p;
	}

	// --------------------rotate---------------------
	public void rotate_left(Node x) {
		Node y = x.r;
		x.r = y.l;
		if (y.l != t_nil)
			y.l.p = x;
		y.p = x.p;
		if (x.p == t_nil)
			this.root = y;
		if (x.p.l == x)
			x.p.l = y;
		else
			x.p.r = y;

		x.p = y;
		y.l = x;

	}

	public void rotate_right(Node x) {
		Node y = x.l;
		x.l = y.r;
		if (y.r != t_nil)
			y.r.p = x;
		y.p = x.p;
		if (x.p == t_nil)
			this.root = y;
		if (x.p.l == x)
			x.p.l = y;
		else
			x.p.r = y;
		x.p = y;
		y.r = x;
	}

}