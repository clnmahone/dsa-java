package myDataStructure;

class Node{
	public int data;
	public Node rightChild;
	public Node leftChild;
	
	public void displayNode(Node node){
		System.out.println(node.toString());
	}
}

class BinTree{
	public Node root;
	
	public Node find(int key){
		Node current = root; 
		while(current.data!=key){
			if(key<current.data){
				current=current.leftChild;
			}else if(key>current.data){
				current=current.rightChild;
			}
			if(current==null){
				return null;
			}
		}
		return current;
	}
	
	public void insert(int key){
		Node current = root;
		if(current==null){
			 root.data=key;
			 return;
		}
		
		while(true){
			if(key<current.data){
				if(current.leftChild==null){
					current.leftChild.data=key;
					return;
				}else{
					current=current.leftChild;
				}
			}
			if(key>=current.data){
				if(current.rightChild==null){
					current.rightChild.data=key;
					return;
				}else{
					current=current.rightChild;
				}
			}
			
		}
		
	}
	
	public void delet(int key){
		Node current = root;
		if(current==null){
			return;
		}
		if current.data=key;
	}
}



public class BinTreeApp {

}
