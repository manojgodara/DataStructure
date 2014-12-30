package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Neighbor{
	int vertexNum;
	Neighbor next;
	
	public Neighbor(int vertexNum, Neighbor next)
	{
		this.vertexNum = vertexNum;
		this.next = next;
	}
}


class Vertex{
	String name;
	Neighbor adjList;
	
	public Vertex(String name, Neighbor adjList)
	{
		this.name = name;
		this.adjList = adjList;
	}

}


public class Graph {
	
	Vertex[] adjLists;
	Boolean directed = false;
	Boolean[] visited;
	Queue<Integer> queue;
	
	public Graph (String file) throws FileNotFoundException	{
		Scanner sc = new Scanner(new File(file));
		
		if(sc.next().equals("Undirected"))
			directed = false;
		else directed = true;
		
		
		int noOfVertices = sc.nextInt();
		adjLists = new Vertex[noOfVertices];
		visited = new Boolean[noOfVertices];
		queue = new LinkedList<Integer>();
		
		//read vertices
		for(int v=0; v < adjLists.length; v++)
		{
			adjLists[v] = new Vertex(sc.next(), null);
			visited[v] = false;
		}
		
		//read edges
		while(sc.hasNext())
		{
			int v1 = indexForName(sc.next());
			int v2 = indexForName(sc.next());
			
			adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList);
			if(!directed)
			adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList);
			
		}
	}
	
	int indexForName(String name){
		
		for(int v=0; v < adjLists.length; v++)
		{
			if(adjLists[v].name.equals(name))
				return v;
		}
		return -1;
	}
	
	void reset(){
		for(int v=0; v < adjLists.length; v++)
		{
			visited[v] = false;
		}
	}
	
	void print(){
		
		System.out.println();
		for(int v=0; v < adjLists.length; v++)
		{
			System.out.print(adjLists[v].name);
			for(Neighbor nbr = adjLists[v].adjList; nbr != null; nbr = nbr.next)
			{
				System.out.print(" ---> "+adjLists[nbr.vertexNum].name); 	
			}
			System.out.println("\n");
		}
	}
	
	
	void dfs(int u){
		
		visited[u] = true;
		System.out.print(" ---> "+adjLists[u].name);
		for(Neighbor nbr = adjLists[u].adjList; nbr!=null; nbr=nbr.next)
		{
			if(!visited[nbr.vertexNum]){
				dfs(nbr.vertexNum);
			}
		}
		
	}
	
	
	void bfs(int u){
		
		queue.add(u);
		visited[u] = true;
		
		while(!queue.isEmpty()){
			int v = (int) queue.poll();
			System.out.print(" ---> "+adjLists[v].name);
			for(Neighbor nbr = adjLists[v].adjList; nbr!= null; nbr = nbr.next)
			{
				if(!visited[nbr.vertexNum]){
					queue.add(nbr.vertexNum);
					visited[nbr.vertexNum] = true;
				}
			}
		}
	}
	
	
	public static void main(String args[]) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter graph input file name: ");
		
		String file = sc.nextLine();
		
		Graph graph = new Graph(file);
		graph.print();
		
		System.out.print("DFS traversal: ");
		for(int u=0; u < graph.adjLists.length; u++)
		{
			if(!graph.visited[u]){
				System.out.println("\n Connected Graph: ");
				graph.dfs(u);
			}
		}
		System.out.println("\n");
		
		graph.reset();
		
		System.out.print("BFS traversal: ");
		for(int u=0; u < graph.adjLists.length; u++)
		{
			if(!graph.visited[u]){
				System.out.println("\n Connected Graph: ");
				graph.bfs(u);
			}
		}
		System.out.println("\n");
	}
	
	
	
}
