
public class BSTree {

    class Node {
        int val;
        Node p;
        Node l;
        Node r;

        Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    BSTree(Node root) {
        this.root = root;
    }

    // -------------------insertion--------------------

    public void insertion(int val) {
        Node z = new Node(val);
        Node insertPoint = null;
        Node current = this.root;
        while (current != null) {
            insertPoint = current;
            if (val < current.val)
                current = current.l;
            else
                current = current.r;
        }
        z.p = insertPoint;
        if (insertPoint == null)
            this.root = z;
        else if (val < insertPoint.val)
            insertPoint.l = z;
        else
            insertPoint.r = z;

    }

    // --------------deletion-----------------------------
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

    // ------------------------min----------------------------
    public Node tree_min(Node n) {
        while (n != null)
            n = n.l;
        return n;
    }

    // ------------------------max---------------------------
    public Node tree_max(Node n) {
        while (n != null)
            n = n.r;
        return n;
    }

    // -----------------------search---------------------------
    public Node tree_search(int key) {
        Node current = this.root;
        while (current != null) {
            if (current.val > key)
                current = current.l;
            else
                current = current.r;
        }
        return current;
    }

    // -------------------------replace----------------------
    public void replace(Node x, Node y) {
        if (x.p == null)
            this.root = y;
        else if (x == x.p.l)
            x.p.l = y;
        else
            x.p.r = y;
        if (y != null)
            y.p = x.p;
    }


    // --------------------rotate---------------------
    public void rotate_left(Node x) {
        Node y = x.r;
        x.r = y.l;
        if (y.l != null)
            y.l.p = x;
        y.p = x.p;
        if (x.p == null)
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
        if (y.r != null)
            y.r.p = x;
        y.p = x.p;
        if (x.p == null)
            this.root = y;
        if (x.p.l == x)
            x.p.l = y;
        else
            x.p.r = y;
        x.p = y;
        y.r = x;
    }

}