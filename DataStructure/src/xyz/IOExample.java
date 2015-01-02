package xyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


class QueueEntry{
	int distance;
	int vertex;
	
	public QueueEntry(int vertex , int distance)
	{
		this.distance = distance;
		this.vertex = vertex;
	}
	
}


public class IOExample {

	
	public Map<String, Integer> getWordCount(String fileName) throws MyException
	{
		final Map<String, Integer> myMap = new HashMap<String, Integer>();
		
		new InputStreamProcessingTemplate() {
			
			@Override
			public void doProcess(BufferedReader input) throws IOException 
			{
				String sb = null;
				
				while((sb = input.readLine()) != null)
				{
					StringTokenizer st = new StringTokenizer(sb, " ");
					
					while(st.hasMoreTokens())
					{
						String tmp = st.nextToken().toLowerCase();
						
						if(myMap.containsKey(tmp))
							myMap.put(tmp, myMap.get(tmp)+1);
						else
							myMap.put(tmp, 1);
					}
					
				}
			}
		}.process(fileName);
		
		return myMap;
	}
	
	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> myMap)
	{
		
		Set<Entry<String, Integer>> set = myMap.entrySet();
		
		List<Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(set);
		
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		
		
		return list;
		
	}
	
	
	@SuppressWarnings("finally")
	public static int JavaHungry()
	{
		int x = 0;
		try{
			System.out.println("");
			x = 10/0;
			return 10;
		}catch(Exception e){
			System.out.println("sdf");
		}finally{
			System.out.println("finally");
			return 30;
		}
	}
	
	public static void JavaHungry(Object o)
	{
		System.out.println("dfa");
	}
	
	
	public static String reverse(String str)
	{
		StringBuilder sb = new StringBuilder(str.length());
		
		for(int i = str.length()-1; i>=0; i--)
		{
			sb.append(str.charAt(i));
		}
		
		return sb.toString();
		
	}
	
	
	public static String reverseRec(String str, int n)
	{
		if(n < 2) return str;
		
		return reverseRec(str.substring(1), n-1)+str.charAt(0);
	}
	
	

	public static int minimumMoves(int[] moves, int N)
	{
		
		int minMoves = -1; 
		if(N < 0) return minMoves;
		
		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++)
		{
			visited[i] = false;
		}
		
		Queue<QueueEntry> q = new LinkedList<QueueEntry>();
		
		q.add(new QueueEntry(0, 0));
		visited[0] = true;
		
		while(!q.isEmpty())
		{
			QueueEntry qe = q.poll();
			
			int v = qe.vertex;
			
			if(v == N-1)
			{
				minMoves = qe.distance;
				break;
			}
			
			for(int i = v+1; i<=(v+6) && i<N; i++)
			{
				if(!visited[i])
				{
					visited[i] = true;
					
					if(moves[i] != -1)
					{
						q.add(new QueueEntry(moves[i], qe.distance+1));
					}else
					{
						q.add(new QueueEntry(i, qe.distance+1));
					}
				}
			}
			
		}
		
		
		return minMoves;
		
	}
	

	
	public static int minimumMoves(int[] moves, int i, int N, boolean[] visited)
	{
		if(i == N-1) return 0;
		
		int min = N;
		
		for(int k = i+1; k <= i+6 && k < N; k++)
		{
			
			if(visited[k]) continue;
			
			visited[k] = true;
			
				if(moves[k] != -1)
					k = moves[k];
				
				int d = 1+minimumMoves(moves, k, N, visited);
				
				if(d<min)
					min = d;
			
		}
		
		return min;
				
	}
	
	
	public static void main(String args[])
	{
		int[] moves = new int[30];
		boolean[] visited = new boolean[30];
		for(int i = 0; i<30; i++)
		{
			moves[i] = -1;
			visited[i] = false;
		}
		

		 // Ladders
	    moves[2] = 21;
	    moves[4] = 7;
	    moves[10] = 25;
	    moves[19] = 28;
	 
	    // Snakes
	    moves[26] = 0;
	    moves[20] = 8;
	    moves[16] = 3;
	    moves[18] = 6;
	    
	    System.out.println(minimumMoves(moves, 30));
	    
	    System.out.println(minimumMoves(moves, 0, 30, visited));
	}
	
}
