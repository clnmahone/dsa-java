public class Test {
    public static void main(String[] args) {
        RBTree rbt = new RBTree(null);
        rbt.rb_insertion(1);
        rbt.rb_insertion(2);
        rbt.rb_insertion(4);
        rbt.rb_insertion(6);
        rbt.rb_insertion(7);
        rbt.rb_insertion(5);
        rbt.rb_insertion(3);

        InOrder(rbt.root);

    }

    public static void  InOrder(Node root) {
        if (root != null) {
            InOrder(root.l);
            System.out.print(root.val+" ");
            InOrder(root.l);
        }
    }


    public void InOrder2(Node root){
        
    }

}