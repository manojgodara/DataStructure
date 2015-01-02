package dp;

public class Array {

	public static int max_sum(int[] arr, int i, int n){
		if(i >= n) return -1;
		if(i == n-1) return arr[i];
		int maxSum = arr[i];
		for(int j = i+1; j<n-j+1; j++){
			int sum = max_sum(arr, i, n);
			if(arr[i] > 0 && sum+arr[i] > maxSum){
				maxSum = sum+arr[i];
			}else if(sum > maxSum) maxSum = sum;
		}
		return maxSum;
	}
	
	public static void main(String args[]){
		int[] arr = {-2,-3,4,-1,-2,1,5,3};
		System.out.println(max_sum(arr, 0, arr.length));
	}
}
