package xyz;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.EditorKit;

public class CommonAncestorBST {
	
	public static Node findDeepeastCommonAncestor(Node root, int d1, int d2){
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
	
	
	
	static int findPivot(int[] arr, int low, int high)
	{
		//1,2,3,4,5
		 if (high < low)  return -1;
		   if (high == low) return low;
		 
		   int mid = (low + high)/2;   /*low + (high - low)/2;*/
		  /* if (mid < high && arr[mid] > arr[mid + 1])
		     return mid;
		   if (mid > low && arr[mid] < arr[mid - 1])
		     return (mid-1);*/
		   
		   if(mid<high && arr[mid+1] < arr[mid])
			   return mid+1;
		   if(mid>low && arr[mid] < arr[mid-1])
			   return mid;
		   
		   
		   if (arr[low] >= arr[mid])
		     return findPivot(arr, low, mid-1);
		   else
		     return findPivot(arr, mid + 1, high);
				
				
	}
	
	
	public static int maxAmount(int[][] in)
	{
		int row = in.length;
		int col = in[0].length;
		
		//int[][] buf = new int[row][col];
		
		return maxAmountRecUtil(in, row-1, col-1);
		
		//return maxAmountUtil(in, buf, row-1, col-1);
	}
	
	private static int maxAmountRecUtil(int[][] in, int i, int j)
	{
		if(i<0 || j<0) return -1;
		
		if(i == 0 && j == 0) return in[i][j];
		
		if(i == 0) return  maxAmountRecUtil(in, i, j-1) + in[i][j];
		
		if(j == 0) return  maxAmountRecUtil(in, i-1, j) + in[i][j];
		
		return  max(maxAmountRecUtil(in, i-1, j), maxAmountRecUtil(in, i, j-1)) + in[i][j];
		
	}
	
	private static int maxAmountUtil(int[][] in, int[][] buf, int i, int j)
	{
		if(i<0 || j<0) return -1;
		
		if(i == 0 && j == 0) buf[i][j] = in[i][j];
		
		if(buf[i][j] != 0) return buf[i][j];
		
		if(i == 0) buf[i][j] =  maxAmountUtil(in, buf, i, j-1) + in[i][j];
		
		if(j == 0) buf[i][j] =  maxAmountUtil(in, buf, i-1, j) + in[i][j];
		
		buf[i][j] =  max(maxAmountUtil(in, buf, i-1, j), maxAmountUtil(in, buf, i, j-1)) + in[i][j];
		
		return buf[i][j];
	}
	
	
	public static int maxAmountDP(int[][] in, int[][] buf, int r, int c)
	{
		if(r == 0 && c == 0) buf[0][0] = in[0][0];
		
		for(int j = 1; j <= c; j++)
			buf[0][j] = in[0][j] + buf[0][j-1];
		
		for(int i = 1; i <= r; i++)
			buf[i][0] = in[i][0] + buf[i-1][0];
		
		for(int i = 1; i<=r; i++)
		{
			for(int j=1; j<= c; j++)
			{
				buf[i][j] = max(buf[i-1][j], buf[i][j-1]) + in[i][j];
			}
		}
		return buf[r][c];
	}
	
	
	private static int max(int m1, int m2) {
		return (m1>m2)?m1:m2;
	}

	public static int minCoins(int[] coins, int amount)
	{
		if(amount == 0) return 0;
		
		int min = amount; // max coins can equal to amount
		
		for(int i = 0; i < coins.length; i++)
		{
			if(amount - coins[i] >= 0)
			{
				int noOfCoins = minCoins(coins, amount-coins[i]) + 1;
				
				if(noOfCoins < min)
					min = noOfCoins;
			}
		}
		return min;
	}
	
	public static int minCoinsWithBuf(int[] coins, int amount, int[] buf)
	{
		if(amount == 0)
			buf[amount] = 0;
			
		if(buf[amount] != 0) 
			return buf[amount];
		
		
		int min = amount; // max coins can equal to amount
		
		for(int i = 0; i < coins.length; i++)
		{
			if(amount - coins[i] >= 0)
			{
				int noOfCoins = minCoinsWithBuf(coins, amount-coins[i], buf) + 1;
				
				if(noOfCoins < min)
					min = noOfCoins;
			}
		}
		
		buf[amount] = min;
		
		return buf[amount];
	}
	
	
	public static int minCoinsDP(int[] coins, int amount, int[] buf)
	{
		if(amount == 0) return 0;
		
		if(amount == 1) return 1;
		
		buf[0] = 0; buf[1] = 1;
		for(int i = 2; i <= amount; i++)
		{
			int min = amount;
			for(int j = 0; j< coins.length; j++)
			{
				if((i-coins[j] >= 0))
				{
					int c = buf[i-coins[j]]+1;
					if(c < min)
						min = c;
				}
			}
			buf[i] = min;
		}
		return buf[amount];
	}
	
	
	public static int editDistance(String s1, String s2)
	{
		int len1 = s1.length();
		int len2 = s2.length();
		
		return editDistanceUtil(s1,s2,len1-1,len2-1);
	}
	
	private static int editDistanceUtil(String s1, String s2, int i, int j)
	{
		if(i == -1 && j == -1) return 0;
		if(i == -1) return j+1;
		if(j == -1) return i+1;
		int diff = 1;
		if(s1.charAt(i) == s2.charAt(j))
			diff = 0;
		
		return min((editDistanceUtil(s1, s2, i, j-1)+1), 
					(editDistanceUtil(s1, s2, i-1, j-1) + diff), 
					(editDistanceUtil(s1, s2, i-1, j) + 1));
	}
	
	
	
	private static int min(int a, int b, int c)
	{
		int min = a;
		
		if(b < min) min = b;
		if(c < min) min = c;
		
		return min;
	}
	
	
	public static boolean placeQueens(int[][] board, int row, int N)
	{
		if(row >= N) return true;
		
		for(int c = 0; c < N; c++)
		{
			if(isValidPlace(board, row, c, N))
			{
				board[row][c] = 1;
				
				if(placeQueens(board, row+1, N))
					return true;
				
				board[row][c] = 0;
			}
			
		}
		
		return false;
	}
	
	
	private static boolean isValidPlace(int[][] board, int row, int col, int N)
	{
		
		for(int r = row; r>=0; r--)
			if(board[r][col] == 1) return false;
		
		for(int r=row, c=col; r>=0 && c>=0; r--,c--)
		{
			if(board[r][c] == 1) return false;
		}
		
		for(int r = row, c=col; r>=0 && c<N; r--, c++)
		{
			if(board[r][c] == 1) return false;
		}
		
		return true;
	}
	
	private static void resetBoard(int[][] b, int N)
	{
		for(int i = 0; i<N; i++)
			for(int j = 0; j<N; j++)
				b[i][j] = 0;
	}
	
	public static void main(String args[])
	{
		
		int[][] board = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		
		int N = board.length;
		for(int c =0; c<N; c++)
		{
			resetBoard(board,N);
			if(placeQueens(board, c, N))
			{
				for(int i=0; i<N; i++)
				{
					for(int j=0; j<N; j++)
						System.out.print(board[i][j]+" ");
					System.out.println();
				}
			}else
				System.out.println("cannot place queen.");
			System.out.println();
		}
		
		
		//System.out.println(editDistance("PLASMA", "ALTRUISM"));
		
		
		/*int amount = 40;
		int[] coins = {1,3,5,7};
		int[] buf1 = new int[amount+1];
		int[] buf2 = new int[amount+1];
		
		
		
		long time = System.nanoTime();
		System.out.println(minCoins(coins, amount));
		System.out.println("Time1: "+(System.nanoTime() - time));
		
		long time2 = System.nanoTime();
		System.out.println(minCoinsWithBuf(coins, amount, buf2));
		System.out.println("Time2: "+(System.nanoTime() - time2));
		
		
		long time3 = System.nanoTime();
		System.out.println(minCoinsDP(coins, amount, buf1));
		System.out.println("Time3: "+(System.nanoTime() - time3)); */
		
		 
		
		
		
		
		
		/*int[][] arr = {{5,2,8},{10,6,12},{11,5,3}};
		int row = arr.length;
		int col = arr[0].length;
		int[][] buf = new int[row][col];
		
		long time = System.nanoTime();
		System.out.println(maxAmount(arr));
		System.out.println("Time1: "+(System.nanoTime() - time));
		
		long time2 = System.nanoTime();
		System.out.println(maxAmountUtil(arr, buf, row-1, col-1));
		System.out.println("Time2: "+(System.nanoTime() - time2));
		
		
		long time3 = System.nanoTime();
		System.out.println(maxAmountDP(arr, buf, row-1, col-1));
		System.out.println("Time3: "+(System.nanoTime() - time3));*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		Node n1 = new Node(15);
		Node n2 = new Node(10);
		Node n3 = new Node(20);
		Node n4 = new Node(8);
		Node n5 = new Node(9);
		
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n4.right = n5;
		
		Node CA_Node = findDeepeastCommonAncestor(n1, 10,22);
		
		if(CA_Node != null) System.out.println(CA_Node.val);
		else
			System.out.println("Common Ancestor doesn't exist in BST.");
	}
}
