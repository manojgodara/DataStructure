package binarytree;



class Node{
	int value;
	Node left, right;
	
	public Node(int val)
	{
		this.value = val;
		this.left = this.right = null;
	}
}

public class BinaryTree {

	/*
	 * Given an unsorted array of integers, assume the elements are inserted into BST. 
	 * Search for an element and print the path if found. 
	 * If the element is left of previous in BST then path variable is 1 else 0.
	 * 
	 */
	public static String encoding(Node root, int key, String s){
		if(root == null) return null;
		if(root.value == key) return s;
		if(root.value > key) return encoding(root.left, key, s+"1");
		else return encoding(root.right, key, s+"0");
	}
	
	
	public static void main(String args[]){
		
		Node n1 = new Node(8);
		Node n2 = new Node(2);
		Node n3 = new Node(9);
		Node n4 = new Node(1);
		Node n5 = new Node(4);
		Node n6 = new Node(12);
		Node n7 = new Node(3);
//		Node n8 = new Node(8);

		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		n3.right = n6;
		n5.left = n7;
		
		//n5.left = n8;
		String res = encoding(n1, 12, "");
		if(res!=null)
			System.out.println(res);
		else System.out.println("");
	}
}
