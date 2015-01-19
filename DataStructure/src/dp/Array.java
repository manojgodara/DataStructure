package dp;

public class Array {

	
	/*
	 * Longest increasing subsequence
	 * LIS_rec
	 * LIS - (n2)
	 * LIS_tabulation - O(n2)
	 * 
	 * O(nlogn) WIKI solution
	 */
	
	//Recursion
	public static int LIS_rec(int[] arr, int i, int n){
		if(i >= n) return -1;
		if(i == n-1) return 1;
		
		int maxLen = 1;
		for(int j = i+1; j < n; j++){
			int max = LIS_rec(arr, j, n);
			if(arr[j] > arr[i] && max+1 > maxLen)
				maxLen = max+1;
		}
		
		return maxLen;
	}
	
	// Memoization
	public static int LIS(int[] arr, int[] buf, int i, int n){
		if(i >= n) return -1;
		if(i == n-1) 
		{
			buf[i] = 1;
			return 1;
			}
		int maxLen = 1,max;
		for(int j = i+1; j<n; j++){
			
			if(buf[j] != 0) max = buf[j];
			else
				max = LIS(arr,buf, j, n);
			if(arr[j] > arr[i] && max+1 > maxLen){
				maxLen = max+1;
			}
		}
		buf[i] = maxLen;
		return maxLen;
	}
	
	//Tabulation
	public static int LIS_tabulation(int[] arr, int[] L){
		int len = arr.length;
		
		for(int i = 1; i < len; i++){
			for(int j = 0; j < i; j++){
				if(arr[j] < arr[i] && L[j]+1 > L[i])
					L[i] = L[j]+1;
			}
		}
		int max = L[0];
		for(int i = 1; i< len; i++){
			if(L[i] > max)
				max = L[i];
		}
		return max;
	}
	
	
	
	
	
	public static void main(String args[]){
		int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println(LIS_rec(arr, 0, arr.length));
		int[] buf = new int[arr.length];
		for(int i =0; i<buf.length; i++) buf[i] = 0;
		System.out.println(LIS(arr, buf, 0, arr.length));
		for(int i =0; i<buf.length; i++) buf[i] = 1;
		System.out.println(LIS_tabulation(arr, buf));
	}
}
