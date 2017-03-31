package myDataStruct;

public class TreeNode {
	public int iData;
	public double dData;
	public TreeNode leftChild;
	public TreeNode rightChild;

	public void displayNode() {
		System.out.println('{' + iData + ',' + dData + '}');
	}
}
