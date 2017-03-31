package myDataStruct;

public class TreeX {
	private TreeNode root;

	public TreeX() {
		root = null;
	}

	public TreeNode find(int key) {
		TreeNode current = root;
		while (current.iData != key) {
			if (key < current.iData) {
				current = current.leftChild;
			}else {
				current = current.rightChild;
			}
			if(current == null){
				return null;
			}
		}

		return current;
	}
	
	public void insert(int id,double dd){
		
	}
}
