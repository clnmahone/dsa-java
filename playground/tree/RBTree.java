
/*
red black tree rules:

1. Every node is either red or black.
2. The root is black.
3. Every leaf (NIL) is black.
4. If a node is red, then both its children are black.
5. For each node, all simple paths from the node to descendant leaves contain the
same number of black nodes.

*/


class RBTree extends BSTree {

	enum Color {
		RED, BLACK
	}
	
	class Node {
		int val;
		Node p;
		Node l;
		Node r;
		Color color;
	
		Node(int val) {
			this.val = val;
			this.color = Color.RED;
			this.l = t_nil();
			this.r = t_nil();
			this.p = t_nil();
		}
		//T.nil 一种特殊的节点：颜色为黑，其他属性为任意值，程序中定义为null
		static Node t_nil(){
			Node t_nil = new Node(0);
			t_nil.color = Color.BLACK;
			return t_nil;
		}
	}

	Node root;

	RBTree(Node root) {
		super(root);
	}

	public void rb_insertion(int val) {

		Node newNode = new Node(val);
		Node insertPoint = Node.t_nil();
		Node current = this.root;


		//需要使用特殊的比较方法
		while (current !=  {
			// 放在遍历之前，保证insertPoint非空
			insertPoint = current;
			if (val > current.val)
				current = current.r;
			else
				current = current.l;

		}
		newNode.p = insertPoint;
		if (insertPoint == null) {
			this.root = newNode;
			Node t_nil = new Node(0);
			t_nil.color = Color.BLACK;
			this.root.p = t_nil;
		} else if (insertPoint.val < val)
			insertPoint.r = newNode;
		else
			insertPoint.l = newNode;
		RB_insert_fixup(newNode);
	}

	// 核心算法
	private void RB_insert_fixup(Node newNode) {

		while (newNode.p.color == Color.RED) {
			// newNode的爸爸是他爷爷的左孩子
			if (newNode.p.p.l == newNode.p) {
				Node uncle = newNode.p.p.r;
				// 叔叔为红
				if (uncle.color == Color.RED) {
					// 变色爷爷红，爸爸叔叔黑
					newNode.p.p.color = Color.RED;
					newNode.p.p.r.color = Color.BLACK;
					newNode.p.color = Color.BLACK;
					// next iteration
					newNode = newNode.p.p;
				}
				// 叔叔为黑，新节点为右孩子
				else if (newNode == newNode.p.r)
					rotate_left(newNode.p);
				// 爷爷变红,爸爸变黑
				newNode.p.color = Color.BLACK;
				newNode.p.p.color = Color.RED;
				// 右旋
				rotate_right(newNode.p.p);
			}
			// -------------------------------------------------
			// newNode的爸爸是他爷爷的右孩子，其他逻辑和上面雷同
			// ---------------------------------------------------
			else {
				Node uncle = newNode.p.p.l;
				// 叔叔为红
				if (uncle.color == Color.RED) {
					// 变色爷爷红，爸爸叔叔黑
					newNode.p.p.color = Color.RED;
					newNode.p.p.l.color = Color.BLACK;
					newNode.p.color = Color.BLACK;
					// next iteration
					newNode = newNode.p.p;
				}
				// 叔叔为黑，新节点为右孩子
				else if (newNode == newNode.p.l)
					rotate_right(newNode.p);
				// 变色
				newNode.p.color = Color.BLACK;
				newNode.p.p.color = Color.RED;
				rotate_left(newNode.p.p);
			}
			// 考虑到newNode成为root的情况：
			// 循环终止，newNode的爸爸为黑，有可能这是newNode为root，
			// 因为root.p为sentinel T.nil,为黑。

		}
		this.root.color = Color.BLACK;

	}

	public void deletion(Node z) {
		if (z.l == null)
			replace(z, z.r);
		else if (z.r == null)
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

	public void RB_deletion(Node z) {

		Node y = z;
		Node x = null;
		Color y_original_color = y.color;

		if (z.l == null) {
			x = z.r;
			RB_replace(z, z.r);
		}

		else if (z.r == null) {
			x = z.l;
			RB_replace(z, z.l);
		}

		else {
			y = tree_min(z.r);
			y_original_color = y.color;
			x = y.r;
			if (y.p == z) {
				x.p = y;
			} else {
				RB_replace(y, y.r);
				y.r = z.r;
				z.r.p = y;
			}
			RB_replace(z, y);
			y.l = z.l;
			z.l.p = y;
			y.color = z.color;
		}

		if (y_original_color == Color.BLACK)
			RB_deletion_fix(x);

	}

	void RB_deletion_fix(Node x) {
		while (x != this.root && x.color == Color.BLACK) {

			if (x == x.p.l) {

				Node w = x.p.r;
				// case1 w.color==RED
				if (w.color == Color.RED) {
					w.color = Color.BLACK;
					x.p.color = Color.RED;
					rotate_left(x.p);
					w = x.p.r;
				}
				// case2 w为黑，w的孩子都为黑
				if (w.l.color == Color.BLACK && w.r.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.p;

				}
				// case3 w为黑，w的右孩子为红，左孩子为黑
				else if (w.r.color == Color.BLACK) {
					w.l.color = Color.BLACK;
					w.color = Color.RED;
					rotate_right(w);
					w = x.p.r;

				}
				// 其他情况 case4 w为黑，w的右孩子为红
				w.color = x.p.color;
				x.p.color = Color.BLACK;
				rotate_left(x.p);
				x = this.root;

			}

			// x是右孩子，与上面雷同
			else {

				System.out.println("to do");

			}

		}
	}
}