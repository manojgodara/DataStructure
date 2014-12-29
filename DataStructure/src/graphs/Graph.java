package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	visited[] visited;
	
	public Graph (String file) throws FileNotFoundException	{
		Scanner sc = new Scanner(new File(file));
		
		if(sc.next().equals("Undirected"))
			directed = false;
		else directed = true;
		
		adjLists = new Vertex[sc.nextInt()];
		
		//read vertices
		for(int v=0; v < adjLists.length; v++)
		{
			adjLists[v] = new Vertex(sc.next(), null);
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
		
		
		
	}
	
	
	public static void main(String args[]) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter graph input file name: ");
		
		String file = sc.nextLine();
		
		Graph graph = new Graph(file);
		graph.print();
		
	}
	
	
	
}
