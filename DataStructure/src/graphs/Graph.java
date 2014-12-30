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

enum Color{
	WHITE,
	GRAY,
	BLACK
}


public class Graph {
	
	Vertex[] adjLists;
	Boolean directed = false;
	Color[] color;
	int[] d;
	int[] f;
	int[] p;
 	int time = 0;
	Queue<Integer> queue;
	
	public Graph (String file) throws FileNotFoundException	{
		Scanner sc = new Scanner(new File(file));
		
		if(sc.next().equals("Undirected"))
			directed = false;
		else directed = true;
		
		
		int noOfVertices = sc.nextInt();
		adjLists = new Vertex[noOfVertices];
		color = new Color[noOfVertices];
		d = new int[noOfVertices];
		f = new int[noOfVertices];
		p = new int[noOfVertices];
		queue = new LinkedList<Integer>();
		
		//read vertices
		for(int v=0; v < adjLists.length; v++)
		{
			adjLists[v] = new Vertex(sc.next(), null);
			color[v] = Color.WHITE;
			p[v] = -1;
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
			color[v] = Color.WHITE;
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
		
		color[u] = Color.GRAY;
		time = time+1;
		d[u] = time;
		
		System.out.print(" ---> "+adjLists[u].name);
		for(Neighbor nbr = adjLists[u].adjList; nbr!=null; nbr=nbr.next)
		{
			if(color[nbr.vertexNum] == Color.GRAY) System.out.println(" \n Cycle exists.");
			
			if(color[nbr.vertexNum] == Color.WHITE){
				p[nbr.vertexNum] = u;
				dfs(nbr.vertexNum);
			}
		}
		
		color[u] = Color.BLACK;
		time = time+1;
		f[u] = time;
		
	}
	
	
	void bfs(int u){
		
		queue.add(u);
		color[u] = Color.GRAY;
		
		while(!queue.isEmpty()){
			int v = (int) queue.poll();
			System.out.print(" ---> "+adjLists[v].name);
			for(Neighbor nbr = adjLists[v].adjList; nbr!= null; nbr = nbr.next)
			{
				if(color[nbr.vertexNum] == Color.WHITE){
					queue.add(nbr.vertexNum);
					color[nbr.vertexNum] = Color.GRAY;
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
			if(graph.color[u] == Color.WHITE){
				System.out.println("\n Connected Graph: ");
				graph.dfs(u);
			}
		}
		System.out.println("\n");
		
		graph.reset();
		
		System.out.print("BFS traversal: ");
		for(int u=0; u < graph.adjLists.length; u++)
		{
			if(graph.color[u] == Color.WHITE){
				System.out.println("\n Connected Graph: ");
				graph.bfs(u);
			}
		}
		System.out.println("\n");
	}
	
	
	
}
