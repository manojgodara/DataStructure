package xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

class Node{
	int val;
	Node left, right, next;
	
	public Node(int val)
	{
		this.val = val;
		this.left = this.right = this.next = null;
	}
}


class LNode{
	
	int val;
	LNode next;

	public LNode(int val){
		this.val = val;
		this.next = null;
	}
	
}


class recNode {
	
	
}

class vNode{
	int key;
	int[] arr;
	int nextIndex;
	
	public vNode(int key, int size, int nextIndex)
	{
		this.key = key;
		this.arr =  new int[size];
		this.nextIndex = nextIndex;
	}
}

class Visited{
	int index = 0;
	int freq = 0;
	public Visited(int index, int freq){
		this.index = index;
		this.freq = freq;
	}
}


class Result{

	boolean allUsed = true;
	ArrayList<Integer> list = null;
	public Result(boolean b) {
		this.allUsed = b;
		this.list = new ArrayList<Integer>();
	}
}

class A implements Runnable{
	
	private int value;
	
	public A(Thread t1){
		t1 = new Thread(this, "ThreadA");
		t1.start();
	}
	
	public void run(){
		try{
			for(int i=0; i<5; i++)
			{
				System.out.println("ThreadA");
				Thread.sleep(50);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}


public class Xyz {
	
	static void classcast(Double value)
	{
		Double val = (Double)value;
		System.out.println(val);
	}
	
	static void colltest(){
		
		List<Integer> arraylist = new ArrayList<Integer>();
		List<Integer> vector = new Vector<Integer>();
		
		List<Integer> linkedlist = new LinkedList<Integer>();
		List<Integer> stack = new Stack<Integer>();
	}
/*	static int length = 0;
	static String result = null;
	static String longestStr(String str, int i){
		if(str == null) return null;
		int n = str.length();
		if(n == 0 || n == 1) return str;
		int index = str.indexOf(str.charAt(i), i+1);
		if(i+1 < n && length < index)
		{
			length = index;
		//	result = str.subSequence(beginIndex, endIndex);
		}
		
	}
	*/
	
	private static int height(Node root){
		if(root == null) return 0;
		
		int l = height(root.left);
		int r = height(root.right);
		
		return ((l>r)?l:r)+1;
	
	}
	
	private static int searchKey(vNode[] node, int d) {

		if(node == null) return -1;
		
		for(int i=0; i< node.length; i++)
		{
			if(node[i].key == d) return i;
			else node[i].key = d;
		}
		
		return -1;
	}
	
	static int index = 0; 
	
	private static void verticalTraversalUtil(Node root, int d, vNode[] node){
		if(root == null) return;
		
		if(node[index] == null)
		{
			node[index] = new vNode(d, d, d);
		}
		
		int position = searchKey(node,d);
		
		if(position == -1)
		{
			node[index].arr[node[index].nextIndex] = root.val;
			index++;
			node[index].nextIndex++;
		}else{
			
			node[position].arr[node[position].nextIndex] = root.val;
			node[position].nextIndex++;
		}
		verticalTraversalUtil(root.left, d+1, node);
		verticalTraversalUtil(root.right, d-1, node);
	}
	
	static void verticalTraversal(Node root){
		if(root == null) return;
		
		int height = height(root);
		
		vNode[] node = new vNode[height];
		int d = 0;
		verticalTraversalUtil(root, d, node);
		
		for(int i=0; i< node.length; i++)
			System.out.println(node[i].arr);
	}	
	

	static void zigzagTraversal(Node root){
		if(root == null) return;
		
		Stack<Node> currLevel, nextLevel = null;
		currLevel = new Stack<Node>();
		nextLevel = new Stack<Node>();
		
		currLevel.push(root);
		boolean lefttoright = true;
		
		while(!currLevel.isEmpty())
		{
			Node currNode = currLevel.pop();
			System.out.print(currNode.val+" ");
			if(currNode != null)
			{
				//System.out.print(currNode.val+" ");
				if(lefttoright){
					if(currNode.left != null)
						nextLevel.push(currNode.left);
					if(currNode.right != null)
						nextLevel.push(currNode.right);
				}else{
					if(currNode.right != null)
						nextLevel.push(currNode.right);
					if(currNode.left != null)
						nextLevel.push(currNode.left);
				}
			}
			if(currLevel.isEmpty())
			{
				System.out.println();
				lefttoright = !lefttoright;
				Stack<Node> tmp = currLevel;
				currLevel = nextLevel;
				nextLevel = tmp;
			}
		}
		
	}
	
	static Node prev = null, head = null;
	
	static void convertTree2DL(Node root){
		
		if(root == null) return;
		Node tmp = null;
		convertTree2DL(root.left);
		if(prev == null) 
		{
			head = root;
			prev = root;
		}else {
			tmp = root.right;
			prev.right = root;
			root.left = prev;
			root.right = head;
			head.left = root;
			prev = root;
		}
		
		convertTree2DL(tmp);
		
	}
	

	
	private static Node convert2DLLUtil(Node root){
		
		if(root == null) return null;
		
		if(root.left != null)
		{
			Node left = convert2DLLUtil(root.left);
			
			for( ; left.right != null; left = left.right);
			
			left.right = root;
			root.left = left;
		}
		
		if(root.right != null)
		{
			Node right = convert2DLLUtil(root.right);
			
			for( ; right.left != null; right = right.left);
			
			right.left = root;
			root.right = right;
		}
		
		return root;
	}
	
	
	static Node convert2DLL(Node root){
		if(root == null) return null;
		
		Node tmp = convert2DLLUtil(root);
		
		for( ;tmp.left != null; tmp = tmp.left);
		
		return tmp;
		
	}
	
	static void rearrangeList(LNode head){
		if(head == null || head.next == null || head.next.next == null) return;
		
		LNode rear = head, result = null, tmp, prev = null;
		
		for(; rear != null && rear.next != null; rear = rear.next )
		{
			if(result == null)
			{
				result = rear.next;
				rear.next = rear.next.next;
				result.next = null;
			}else{
				tmp = result;
				result = rear.next;
				rear.next = rear.next.next;
				result.next = tmp;
			}
			prev = rear;
		}

		if(rear == null)
		{
			prev.next = result;
		}else{
			rear.next = result;
		}
	}
	
	static boolean isKeyExist(int[][] a, int key){
		if(a == null) return false;
		
		int r1 = a.length;
		
		if(r1 == 0) return false;
		
		int c1 = a[0].length;
		int col = 0, row;
		
		for(row = 0, col = c1-1; row < r1 && col >= 0; )
		{
			if(a[row][col] == key) return true;
			else if(a[row][col] < key) row++;
			else col--;
		}
		
		return false;
	}
	
	
	static void removeDuplicates(LNode head){
		if(head == null) return;
		LNode n = head;
		
		if(n.next == null) return;
		
		for(; n != null; n = n.next)
		{
			deleteNode(n.next, n.val);
		}
		
	}
	
	private static void deleteNode(LNode n1, int d){
		if(n1 == null) return;
		
		if(n1.val == d)
		{
			n1 = n1.next;
		}
		for(LNode tmp = n1; tmp != null && tmp.next != null; tmp = tmp.next)
		{
			if(tmp.next.val == d)
			{
				tmp.next = tmp.next.next;
			}
		}
	}
	
	
	static void removeDuplicateHash(LNode head){
		if(head == null) return;
		
		LNode curr = head, prev = head;
		if(curr.next == null) return;
		
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for(; curr != null; curr = curr.next)
		{
			if(map.containsKey(curr.val))
			{
				prev.next = prev.next.next;
			}else{
				map.put(curr.val, true);
				prev = curr;
			}
		}
	}
	
	
	static int kthFromLast(LNode head, int k){
		if(head == null || k == 0 ) return -1;
		LNode curr = head;
		for (; k>1; k--)
		{
			if(curr == null) return -1;
			else curr = curr.next;
		}
		LNode tmp = head;
		for(; curr != null && curr.next != null; tmp = tmp.next, curr = curr.next);
		
		return tmp.val;
		
		
	}
	
	static LNode addTwoList(LNode l1, LNode l2){
		
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		
		int sum = 0, carry = 0;
		LNode l = null, head = null;
		for(; l1 != null || l2 != null; )
		{
			sum = carry;
			if(l1 != null){
				sum = sum + l1.val;
				l1 = l1.next;
			}
			if(l2 != null){
				sum = sum + l2.val;
				l2 = l2.next;
			}
			carry = sum / 10;
			
			if(l == null){
				l = new LNode(sum%10);
				head = l;
			}
			else {
				l.next = new LNode(sum%10);
				l = l.next;
			}
			sum = 0;
		}
		
		if(carry > 0)
		{
			l.next = new LNode(carry);
		}
		return head;
	}


	static void spiralTraversal(int[][] a){
		
		int r = a.length;
		int c = a[0].length;
		
		//System.out.println(c);
		
		int r1 = 0, r2 = r-1, c1 = 0, c2 = c-1;
		
		while(r1 <= r2 && c1 <= c2)
		{
			//System.out.println(r1+","+c2);
			for (int i = r1; i <= c2; i ++)
			{
				System.out.print(a[r1][i]+ " ");
			}
			 r1++;
			 
			 for(int i = r1; i<= r2 && r1 <= r2; i++)
			 {
				 System.out.print(a[i][c2]+ " ");
			 }
			 
			 c2--;
			 
			 for(int i = c2; i>=c1 && c1 <= c2; i--)
			 {
				 System.out.print(a[r2][i]+ " ");
			 }
			 
			 r2 --;
			 
			 for(int i = r2; i>=r1; i--)
			 {
				 System.out.print(a[i][c1]+ " ");
			 }
			 c1++;
			 
			
			
		}
		
	}
	
	static Node findDeepeastCommonAncestor(Node root, int d1, int d2){
		if(root == null) return null;
		
		if(!search(root, d1) || !search(root, d2)) return null;
		
		if(d1 > d2)
		{
			int tmp = d1;
			d1 = d2;
			d2 = tmp;
		}
		
		return findDeepeastCommonAncestorUtil(root, d1, d2);
	}
	
	private static Node findDeepeastCommonAncestorUtil(Node root, int d1, int d2){
		if(root == null) return null;
		
		if(root.val > d1 && root.val < d2) return root;
		
		if(root.val > d1 && root.val > d2)
		{
			if(root.left != null && root.left.val == d1) return root;

			return findDeepeastCommonAncestorUtil(root.left, d1, d2); 
		}
		
		if(root.val < d1 && root.val < d2)
		{
			if(root.right != null && root.right.val == d1) return root;

			return findDeepeastCommonAncestorUtil(root.right, d1, d2); 
		} 
		
		return null;
	}
	
	
	private static boolean search(Node root, int data) {
		
		if(root == null) return false;
		
		if(root.val == data) return true;
		
		if(root.val > data) return search(root.left, data);
		return search(root.right, data);
	}

	
	//Hello W@@rld! -- olleH d@@lrW!
	
	// M@noj -- j@onM
	
	
	
	public static String reveseString(String str){
		int len = str.length();
		if(len == 0 || len == 1) return str;
		return reveseStringUtil(str);
	}
	
	

	private static String reveseStringUtil(String str) {
	
		if(str.indexOf(" ") == -1) return reverseUtil(str);
 		
		String rs = reveseStringUtil(str.substring(str.indexOf(" ")+1));

		return reverseUtil(str.substring(0, str.indexOf(" "))) +" "+ rs;
			
	}

	public static String reverseUtil(String str) {
		
		int end = str.length()-1;
		char []a = str.toCharArray();
		reverse(a, 0, end);
		
		/*
		for(int start = 0; start < end; )
		{
			if(a[start] == '@') start++;
			else if(a[end] == '@') end--;
			else{
				char t = a[start];
				a[start++] = a[end];
				a[end--] = t;
			}
		}
		*/
		return new String(a);
	}

	private static void reverse(char[] a, int start, int end) {
		
		if(start >= end) return;
		
		if(a[start] == '@') start++;
		else if(a[end] == '@') end--;
		else{
			char t = a[start];
			a[start++] = a[end];
			a[end--] = t;
		} 
		
		reverse(a, start, end);
	}

	
	public static void generateNumbersOfFactor235( int n ){
		
		if(n <= 1) return;
		int res;
		for(int i = 2; i <= n; i++)
		{
			res = i;
			//if(i !=2 && i!=3 && i!=5 && isPrime(i)) continue;
			while(res%2 == 0 || res%3 == 0 || res%5 == 0)
			{
				if(res%2 == 0) res = res/2;
				else if(res%3 == 0) res = res/3;
				else res = res/5;
			}
			
			if(res == 1) System.out.print(i+", ");
		}
		System.out.println();
	}
	
	
	public static void stringPalindrom(String str){
		
		int len = str.length();
		
		String str1 = str.substring(0,2);
		String str2 = str.substring(2,len);
		System.out.println(str2.concat(str1));
	}
	
	
	
	
	public static void perimeteTravrsal(Node root){
		if(root == null) return;
		
		perimeterTraversalUtil(root,true,true);
	}
	
	
	private static void perimeterTraversalUtil(Node root, boolean isLeft, boolean isRight){
		
		if(root == null) return;
		
		if(isLeft || root.left == null && root.right == null)
		{
			System.out.print(root.val+"->");
		}
		
		perimeterTraversalUtil(root.left, isLeft?true:false, false);
		
		perimeterTraversalUtil(root.right, false, isRight?true:false);
		
		if(isRight  && !isLeft && !(root.left == null && root.right == null))
		{
			System.out.print(root.val+"->");
		}
		
	}
	
	
	static void printVerticalOrder(Node root){
		if(root == null) return;
		
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		
		printVerticalOrderUtil(root, 0, map);
		
		for(Integer level: map.keySet()){
			ArrayList<Integer> tmp = map.get(level);
			
			for(Integer value: tmp)
				System.out.print(value + ",");
			System.out.println();
		}
		System.out.println();
	}
	
	private static void printVerticalOrderUtil(Node root, int level,
			HashMap<Integer, ArrayList<Integer>> map) {
		
		if(root == null) return;
		
		ArrayList<Integer> list = (map.get(level) != null)? map.get(level): new ArrayList<Integer>();
		list.add(root.val);
		
		map.put(level, list);
		
		printVerticalOrderUtil(root.left, level-1, map);
		
		printVerticalOrderUtil(root.right, level+1, map);
	 
		
	}

	
	static int smallestSubArrSumGreaterthan(int[] arr, int x){
		
		int len = arr.length;
		
		if(len == 0) return -1;
		
		int sum = 0, min_diff = len-1;
		for(int i = 0; i < len; i++)
		{
			sum = sum + arr[i];
			if(sum > x)
			{
				int start;
				for(start = 0 ; start <= i && sum > x; start++)
				{
					sum = sum - arr[start];
				}
				
				start =-2;
				//System.out.println();
				if(min_diff > (i -start)){
					min_diff = i -start; 
				}
			}
		}
		return min_diff;
	}
	
	static boolean searchGivenNo(int[][] arr, int x){
		int rows = arr.length;
		if(rows == 0) return false;
		
		int cols = arr[0].length;
		
		for(int i = 0; i<rows; i++)
		{
			for(int j = cols-1; j>=0; j--)
			{
				if(arr[i][j] == x) return true;
							
				if(arr[i][j] < x) break; 
			}
		}
		return false;
	}
	
	static Node buildTree(int[] in, int[] post) throws Exception{
		int l1 = in.length;
		int l2 = post.length;
		
		if(l1 != l2) throw new Exception("Can't build tree.");
		
		return buildTreeUtil(in, post, 0, l1-1);
		
	}
	static int p = 0;
	private static Node buildTreeUtil(int[] in, int[] post, int l, int r) throws Exception{
		if(l > r) return null;
		
		int index = searchKey(in, post[p], l, r);
		
		if(index == -1) throw new Exception("Can't build tree./n key not found in inorder array.");
		
		Node root = new Node(post[p]);
		p++;
		root.left = buildTreeUtil(in, post, l, index-1);
		root.right = buildTreeUtil(in, post, index+1, r);
		
		return root;
	}
	
	private static int searchKey(int[] in, int key, int l, int r) {
		
		for(int i = l; i<=r; i++)
			if(in[i] == key)
				return i;
		
		return -1;
	}
	
	public static void treeTraversal(Node root){
		if (root == null) return;
		
		treeTraversal(root.left);
		System.out.print(root.val+"->");
		treeTraversal(root.right);
	}

	
	static Node previous = null, headList = null;
	static void treeToList(Node root){
		
		if(root == null) return;
		
		if(previous == null && root.left == null && root.right == null)
		{
			headList = previous = root;
			return;
		}
		
		treeToList(root.left);
		
		previous.right = root;
		root.left = previous;
		
		previous = root;
		Node tmp = root.right;
		
		headList.left = root;
		root.right = headList;
		
		treeToList(tmp);
		
	}
	
	
	static void greaterSumTree(Node root){
		
		if(root == null) return;
		
		int value = greaterSumTreeUtil(root, root.val);
		
		root.val = value;
		
		greaterSumTree(root.left);
		greaterSumTree(root.right);
			
	}
	
	
	private static int greaterSumTreeUtil(Node root, int x) {
		
		if(root == null) return 0;
		
		if(root.val > x){
			return root.val;
		}
		
		return greaterSumTreeUtil(root.left, x) +
				greaterSumTreeUtil(root.right, x);
		
	}
	
	static boolean isLeafPathSum(Node root, int sum){
		if(root == null) return false;
		
		int subsum = sum - root.val;
		
		if(subsum == 0 && root.left == null && root.right == null)
			return true;
		
		if(isLeafPathSum(root.left, subsum) || isLeafPathSum(root.right, subsum))
			return true;
		
		return false;
	}
	


	static void doubleTree(Node root){
		
		if(root == null) return;
		
      /*Node newnode = new Node(root.val);
		Node tmp = root.left;
		root.left = newnode;
		newnode.left = tmp;
		
		if(root.left != null)
			doubleTree(root.left.left);
		
		doubleTree(root.right);*/
		
		doubleTree(root.left);
		doubleTree(root.right);
		
		Node oldleft = root.left;
		root.left = new Node(root.val);
		root.left.left = oldleft;
		
		
	}
	
	static boolean isSumTree(Node root){
		if (root == null) return true;
		
		int x = isSumTreeUtil(root);
		if(x == -1) return false;
		return true;
	}
	
	private static int isSumTreeUtil(Node root){
		if(root == null) return 0;
		
		if(root.left == null && root.right == null) return root.val;
		
		int l = isSumTreeUtil(root.left);
		if(l == -1) return -1;
		int r = isSumTreeUtil(root.right);
		if(r == -1) return -1;
		
		int sum = l+r;
		
		if(root.val != sum) return -1;
		
		return 2*sum;
		
	}
	
	
	
	static void connectNodeAtSameLevel(Node root){
		if(root == null) return;
		
		Queue<Node> q = new LinkedList<Node>();		
		q.add(root);
		q.add(null);
		
		while(true)
		{
			Node curr = q.poll();

			if(curr != null)
			{
				curr.next = q.peek();
				if(curr.left != null)
				{
					q.add(curr.left);
				}
				if(curr.right != null)
				{
					q.add(curr.right);
				}
			}else if(!q.isEmpty()){
				q.add(null);
			}
			else{
				break;
			}
		}
	}
	
	
	static void connectNodes(Node root){
		if(root == null) return;
		if(root.left == null && root.right == null) return;
		if(root.left != null)
		{
			if(root.right != null) root.left.next = root.right;
			else{
				if(root.next == null) root.left.next = root.next;
				else{ Node tmp = root;
					while(tmp != null && tmp.next.left == null && tmp.next.right == null){
						tmp = tmp.next;
					}
					if(tmp == null) root.left.next = null;
					else if(tmp.next.left != null) root.left.next = tmp.next.left;
					else root.left.next = tmp.next.right;
				}
			}
		}
		if(root.right != null)
		{
			if(root.next == null) root.right.next = null;
			else{
				Node tmp = root.next;
				while(tmp != null && tmp.left == null && tmp.right == null){
					tmp = tmp.next;
				}
				if(tmp == null)
					root.right.next = null;
				else if(tmp.left != null) root.right.next = tmp.left;
				else root.right.next = tmp.right;
			}
		}
		connectNodes(root.left);
		connectNodes(root.right);
	}

	public static String reverseString(String str){
		if(str.indexOf(" ") == -1)
			return str;
		
		String s = reverseString(str.substring(str.indexOf(" ")).trim());
		
		return s +" "+ str.substring(0, str.indexOf(" "));
		
	}
	
	public static void permutations(char[] str, int j, int n){
		
		if(j == n)
		{
			System.out.println(str);
		}else		
		for(int i = j; i <= n; i++)
		{
			swap(str, i, j);
			permutations(str, j+1, n);
			swap(str, i, j);
		}
		
	}
	
	
	private static void swap(char[] str, int i, int j) {
		// TODO Auto-generated method stub
		char c = str[i];
		str[i] = str[j];
		str[j] = c;
	}
	
	
	static void firstRepeatedChar(String s){
		int len = s.length();
		
		char[] f = new char[256];
		int[] c = new int[256];
		int j = 0;
		for(int i = 0; i<len; i++)
		{
			if(f[s.charAt(i)] == 0){
				c[j++] = i;
			}
			f[s.charAt(i)]++;
		}
		
		for(int i = 0; i < j; i++)
		{
			if(f[s.charAt(c[i])] == 1){
				System.out.println(s.charAt(c[i]));
			}
		}
	}
	
	static Node headll, prevll = null;
	static void treeToLL(Node root){
		if(root == null) return;
		Node right, left;
		if(prevll == null)
		{
			headll = root;
			prevll = headll;
			left = root.left;
			prevll.left = null;
			right = root.right;
			root.right = null;
		}
		else{
			left = root.left;
			prevll.left = root;
			right = root.right;
			root.right = prevll;
			root.left = null;
		}
		
		treeToLL(left);
		treeToLL(right);
	}

	
	public static int nextGreatestNumber(int num){
		if(num < 10) return num;
		int count = 1;
		while(num > 0)
		{
			count *= 10;
			num /= 10;
		}
		
		return nextGreatestNumberUtil(num, count/10);
	}
	
	
	private static int nextGreatestNumberUtil(int num, int count) {
		
		if(num < 10) return num;
		
		int x = nextGreatestNumberUtil(num%count, count/10);

		if(x%10 > num/10)
		{
			
		}else{
			
		}
		
		return 0;
	}

	
	public static String runTimeEncoding(String str){
		int len = str.length();
		int count = 0;
		String res = "";
		for(int i = 0; i<len; )
		{
			char x = str.charAt(i);
			
			while(i<len && x == str.charAt(i))
			{
				count++;
				i++;
			}
			
			res = res + x + count;
			count = 0;
			
		}
		return res;
	}
	
	// ee  
	

	
	public static int longestUniqueSubstr(String str){
		
		int len = str.length();
		
		int start_index = 0, beginIndex, endIndex;
		
		int[] visited = new int[256];
		
		for(int i =0 ; i< 256; i++)
			visited[i] = -1;
		
		int max = 0, curr_len = 0;
		
		for(int i = 0; i < len; i++)
		{
			if(visited[str.charAt(i)] == -1 || visited[str.charAt(i)] < start_index)
			{
				curr_len++;

			}else{
				if(curr_len >= max){
					max = curr_len;
					beginIndex = start_index;
					endIndex = i;
					System.out.println(str.substring(beginIndex, endIndex));
				}
				
				curr_len = curr_len - (visited[str.charAt(i)] - start_index) -1;
				start_index = visited[str.charAt(i)]+1;
			}
			visited[str.charAt(i)] = i;
		}
		return max;
	}
	
	static int minimumCost(int[] a) {
		int len = a.length;
		if (len == 0)
			return -1;
		if (len == 1)
			return a[0];
		int cost = 0;
		minHeap(a);

		while (len >= 2) {
			int first = a[0];
			a[0] = a[len - 1];
			minHeapify(a, 0, len - 1);
			a[0] = a[0] + first;
			cost += a[0];
			minHeapify(a, 0, len - 1);
			len = len - 1;
		}

		return cost;

	}

	private static void minHeap(int[] a) {
		int len = a.length;

		for (int i = (len - 1) / 2; i > 0; i--) {
			minHeapify(a, i, len);
		}
	}

	private static void minHeapify(int[] a, int k, int n) {
		if (k > n)
			return;

		int left = 2 * k + 1;
		int right = 2 * k + 2;
		int min = k;
		if (left < n && a[left] < a[k]) {
			min = left;
		}

		if (right < n && a[right] < a[min]) {
			min = right;
		}

		if (k != min) {
			swap(a, k, min);
			minHeapify(a, min, k);
		}
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	
	public static void printAllPaths(Node root, int[] arr, int level, int k) {

		if (root == null)
			return;

		findPath(root, k, level, 0, arr, level);

		printAllPaths(root.left, arr, level + 1, k);

		printAllPaths(root.right, arr, level + 1, k);

	}

	private static void findPath(Node root, int k, int level, int sum,
			int[] arr, int start) {
		if (root == null)
			return;

		sum += root.val;
		arr[level] = root.val;
		if (sum == k) {
			printPath(arr, start, level);
		}

		findPath(root.left, k, level + 1, sum, arr, start);
		findPath(root.right, k, level + 1, sum, arr, start);
	}

	private static void printPath(int[] arr, int start, int end) {

		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + "->");
		}
		System.out.println();
	}

	
	public static ArrayList<String> allPermutations(String str) {
		if (str == null)
			return null;

		ArrayList<String> list = new ArrayList<String>();

		if (str.length() == 0) {
			list.add("");
			return list;
		}

		char first = str.charAt(0);

		String remaining = str.substring(1);

		ArrayList<String> words = allPermutations(remaining);
		for (String word : words) {
			for (int i = 0; i <= word.length(); i++) {
				String s = insertAt(word, i, first);

				list.add(s);
			}
		}
		return list;
	}

	private static String insertAt(String s, int index, char ch) {
		if (index < 0 || index > s.length())
			throw new IllegalArgumentException("Index out of bound.");
		String s1 = s.substring(0, index);
		String s2 = s.substring(index);
		return s1 + ch + s2;
	}

	static int min;
	static ArrayList<Boolean> tasksStatus = new ArrayList<Boolean>();
	public static boolean canArrangeRecursively(ArrayList<Integer> tasks, int time, ArrayList<Boolean> used)
	{
		if(time == 0)
			return true;
		
		for(int i = 0; i < tasks.size(); i++)
		{
			if(!used.get(i))
			{
				used.set(i, true);
				if(time >= tasks.get(i))
				{
					time -= tasks.get(i);
					if(canArrangeRecursively(tasks, time, used))
					{
						tasksStatus.clear();
						tasksStatus.addAll(used);
						return true;						
					}else
					if(time < min)
					{
						tasksStatus.clear();
						tasksStatus.addAll(used);
						min = time;
					}
					
					time += tasks.get(i);
				}
				used.set(i, false);
			}
		}
		return false;
	}
	
	
	
	static void getResult(ArrayList<Integer> result, ArrayList<Integer> tasks, ArrayList<Boolean> used)
	{
		result.clear();
		for(int i=0; i<tasksStatus.size();)
		{
			if(tasksStatus.get(i))
			{
				result.add(tasks.get(i));
				tasks.remove(i);
				used.remove(i);
				tasksStatus.remove(i);
			}else{
				i++;
			}
		}
	}
	
	
	public static LNode mergeSortList(LNode list) {
		int len = length(list);
		if (len <= 1)
			return list;

		LNode left = list, right = null;

		right = addLeftList(left, len);

		left = mergeSortList(left);
		right = mergeSortList(right);
		LNode res = merge(left, right);

		return res;
	}

	private static int length(LNode list) {
		int len = 0;
		for (LNode curr = list; curr != null; curr = curr.next)
			len++;
		return len;
	}

	private static LNode addLeftList(LNode list, int n) {
		int m = n / 2;
		LNode curr = list;
		for (int i = 0; curr != null && i < m - 1; i++)
			curr = curr.next;
		list = curr.next;
		curr.next = null;
		return list;
	}

	private static LNode merge(LNode left, LNode right) {
		LNode result = null, curr = null, tmp = null;
		while (left != null && right != null) {
			if (left.val < right.val) {
				curr = left;
				left = left.next;
			} else {
				curr = right;
				right = right.next;
			}

			if (result == null) {
				result = curr;
				tmp = curr;
			} else {
				tmp.next = curr;
				tmp = tmp.next;
			}
		}

		if (left != null)
			tmp.next = left;
		if (right != null)
			tmp.next = right;

		return result;
	}
	
	public static LNode altReverse(LNode list, int k) {

		if (list == null)
			return list;

		LNode oldHead = list;
		int i = k;
		while (list != null && i > 0) {
			list = list.next;
			i--;
		}

		LNode head = altReverse(list, k);

		int j = k;
		LNode curr = oldHead, prev = null;
		while (curr != null && j > 0 && i == 0) {
			LNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			j--;
		}
		if (i != 0)
			return oldHead;
		else {
			oldHead.next = head;
			return prev;
		}
	}

	public static final int chessGrid = 4;

	public static void placeQueens(List<Integer[]> list, Integer[] columns,
			int row) {
		if (row == chessGrid) {
			list.add(columns.clone());
		} else {
			for (int col = 0; col < chessGrid; col++) {
				if (canPlaceQueen(columns, row, col)) {
					columns[row] = col;
					placeQueens(list, columns, row + 1);
				}
			}
		}
	}

	private static boolean canPlaceQueen(Integer[] columns, int row1, int col1) {

		for (int row2 = 0; row2 < row1; row2++) {
			int col2 = columns[row2];

			if (col1 == col2)
				return false;

			if (Math.abs(col2 - col1) == Math.abs(row2 - row1))
				return false;
		}
		return true;
	}
	
	
	 
	public static boolean isValidStr(String str)
	{
		int count = 0, brackets = 0;
		for(int i = 0; i< str.length(); i++)
		{
			char ch = str.charAt(i);
			
			switch(ch)
			{
				case '+':
					count--; 
					break;
				case '-':
					count--; 
					break;
				case '*':
					count--; 
					break;
				case '/':
					count--; 
					break;
				case '(':
					brackets++; 
					break;
				case ')':
					brackets--; 
					break;
				default:
					count++; 
					break;
			}
			
			if(count > 1 || count < 0 || brackets < 0) return false;
			
		}
		
		if(brackets != 0) return false;
		
		if(count != 1) return false;
		
		return true;
		
	}
	
	
	
	

	public static void main(String args[]) throws Exception
	{
		
		System.out.println(isValidStr("(a+b"));
		
		
//		System.out.println(allPermutations("abc"));
//		System.out.println(insertAt("a", -1, 'b'));
		
		//System.out.println("hello".substring(3,10));
		//System.out.println(longestUniqueSubstr("ABDEFGABEF"));

		//int len = str.length();
		
		//t val = 
		
//		int[] a = {5,6,7,8};
//		System.out.println("minimum cost: "+minimumCost(a));
		
		//permutations("ABCD".toCharArray(),0,3);
		//firstRepeatedChar("geeksforg");
		
		//System.out.println("reverse string: "+ reverseString("my     name is manoj"));
		
		//Thread t1 = null,t2 = null;
		
		//A a = new A(t1);
		//A b = new A(t2);
		//stringPalindrom("abcdef");
		//generateNumbersOfFactor235(10);
		
/*		int[] in = {3,2,4,1,5};
		
		int[] pre = {1,2,3,4,5};
 		
		Node root = buildTree(in, pre);
	
		treeTraversal(root);
		System.out.println();
		
		int[][] arr= {{1,2,8,10},{9,11,13,15},{12,14,18,20}};
		*/
		
		//System.out.println(searchGivenNo(arr, 9));
		//System.out.println("smallest subarray: "+smallestSubArrSumGreaterthan(arr, 51));
		
		
		Node n1 = new Node(2);
		Node n2 = new Node(5);
		Node n3 = new Node(7);
		Node n4 = new Node(6);
		Node n5 = new Node(1);
		Node n6 = new Node(4);
		Node n7 = new Node(0);
//		Node n8 = new Node(8);

		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		n3.left = n6;
		n6.left = n7;
		
		//n5.left = n8;

//		int[] arr = new int[height(n1)];
//		printAllPaths(n1, arr, 0, 11);
		
		//connectNodes(n1);
		//System.out.println(n1.left.next.next);
		
		//System.out.println("******"+isSumTree(n1));
		
/*		treeTraversal(n1);
		System.out.println();
		doubleTree(n1);
		
		treeTraversal(n1);
		*/
		//System.out.println(isLeafPathSum(n1, 141));
		
		
/*		treeTraversal(n1);
		System.out.println();
		
		greaterSumTree(n1);
		
		treeTraversal(n1);
		
		System.out.println();*/
		
		//perimeteTravrsal(n1);
		
		//treeToList(n1);
//		treeToLL(n1);
//		System.out.println("List: ");
//		Node curr;
//		for(curr = headll; curr.left != null; curr = curr.left)
//			System.out.print(curr.val+"->");
//		System.out.println(curr.val);
		
		//printVerticalOrder(n1);
		
		//System.out.println(findDeepeastCommonAncestor(n1, 10,25).val);
		
		LNode l1 = new LNode(20);
		LNode l2 = new LNode(4);
		LNode l3 = new LNode(6);
		LNode l4 = new LNode(2);
		LNode l5= new LNode(7);
		LNode l6= new LNode(9);
		
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		
		//LNode listr = altReverse(l1,3);//mergeSortList(l1);
		
		
		
		LNode prev = null;
		LNode curr = l1, next;
		
		while(curr!=null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		for(LNode tmp = prev; tmp != null; tmp = tmp.next)
		{
			System.out.print(tmp.val+"->");
		}
		
		System.out.println();
		String sp = " a b    c  d";
		char[] ch = new char[sp.length()/2];
		int j = 0;
		for(int i = 0; i<sp.length(); i++)
		{
			if(sp.charAt(i) != ' ') 
			{
				ch[j++] = sp.charAt(i);
			}
		}
		
		String s = new String(ch);
		
		System.out.println(s.length());
		
		//System.out.println("****"+reverseUtil("my    n@me is"));
		//int a[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		
		//spiralTraversal(a);
		//rearrangeList(l1);
		
		//removeDuplicateHash(l1);
		
/*		LNode l = addTwoList(l1, l4);

		for(LNode curr = l; curr != null; curr = curr.next)
		{
			System.out.print(curr.val+"->");
		}
		
		//System.out.println(kthFromLast(l1, 4));
		
		int a[][] = {{2,5,6,8,9},{7,11,13},{18,20,21,25,28}};
		try{
			System.out.println(isKeyExist(a, 28));
			}catch(Exception e){
				//e.printStackTrace();
			}*/
		
		//for(LNode tmp = l1; tmp != null; tmp = tmp.next)
		//	System.out.print(tmp.val + "->");
		//verticalTraversal(n1);
		//zigzagTraversal(n1);
		//convertTree2DL(n1);
		//Node root = convert2DLL(n1);
		
		//for( ;root != null; root = root.right)
			//System.out.print(root.val+"->");
		/*
		for(Node tmp = head; tmp.right != head; tmp = tmp.right)
		{
			System.out.print(tmp.val+"->");
		}*/
		System.out.println();
	}

}
