import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
	// Vertex, Edge
	// Path: Sequence of vertices connected by edge
	// Cycle: Path whose first and last vertices are the same.
	// Connected Component
	
	// Question about Graph:
	// Path. For given two vertices s and t, Is there a path between s and t?
	// Shortest Path. What is the shortest path between s and t?
	// Cycle. Is there a cycle in the graph?
	// Euler tour. Is there a cycle that use each edge exactly once?
	// Hamilton tour. Is there a cycle that use each vertex exactly once?
	// Connectivity. Is there a way to connect all of the vertices?
	// MST. What is the best way to connect all of the vertices?
	// Biconnectivity. Is there a vertex whose removal disconnects the graph?
	// Planarity. Can you draw the graph in the plane with no crossing  edge?
	// Graph Isomorphicm. Do two adjacency lists represent the same graph?
	
	// Which of these problems are easy? difficult? intractable?
	
	// Vertex Representation
	// Use integer between 0 and V-1. -> In order to use array with length V.
	// convert between names and integers with symbol table.
	
	// Set-of-edges graph representation
	// Maintain a list of edges(linked list or array). -> inefficient. space: E,     edge v-w E, 		  iterate adj(v): E 
	// Maintain V by V boolean matrix. -> Huge memory. 				   space: V^2,   edge v-w: 1,         iterate adj(v): V
	// Adjacency-list graph representation. 						   space: E + V, edge v-w: degree(v), iterate adj(v): degree(v)
	
	private final int V;
	private Bag<Integer>[] adj;
	Graph(int V) {
		// create an empty graph with V vertices
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	Graph(In in) {
		// create a graph from input stream
		this.V = in.readInt();
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
		
		int E = in.readInt();
		for(int i = 0; i < E; i++)
			addEdge(in.readInt(), in.readInt());
	}
	void addEdge(int v, int w) {
		// add an edge v-w
		adj[v].add(w);
		adj[w].add(v);
	}
	Iterable<Integer> adj(int v) {
		// vertices adjacent to v
		return adj[v];
	}
	int V() {
		// number of vertices
		return V;
	}
	int E() {
		// number of Edges
		int count = 0;
		for(int v = 0; v < V; v++)
			for(int w : adj(v)) count++;
		return count / 2;
	}
	public String toString() {
		// string representation
		String result = "";
		for(int v = 0; v < V; v++)
			for(int w : adj(v))
				result += v + "-" + w + " ";
		return result;
	}
	public static int degree(Graph G, int v) { // degree = # of edges of vertex v
		int degree = 0;
		for (int w : G.adj(v)) degree++;
		return degree;
	}
	public static int maxDegree(Graph G) {
		int max = 0;
		for(int v = 0; v < G.V(); v++)
			if (max < degree(G, v)) max = degree(G, v);
		return max;
	}
	public static double averageDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v))
				if (v == w) count++;
		return count / 2; // each edge counted twice
	}
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		for (int v = 0; v < G.V(); v++)
			for(int w : G.adj(v))
				StdOut.println(v + " - " + w);
		
		// tinyG.txt format
		// V
		// E
		// vertex vertex
		// vertex vertex
		// ...
	}
}