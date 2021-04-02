import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
	// Digraph: Set of vertices connected pairwise by directed edges.
	// in-degree: # of comming the vertex.
	// out-degree: # of leaving the vertex.
	
	// Some digraph problems
	// Path. Is there a directed path from s to t?
	// Shortest path. What is the shortest directed path from s to t?
	// Topological Sort. Can you draw a digraph so that all edges point upwards?
	// Transitive closure. For which vertices v and w is there a path from v to w?
	// PageRank. What is the importance of Web page?
	
	// API
	private final Bag<Integer>[] adj;
	private final int V;
	
	Digraph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();		
	}
	Digraph(In in) {
		this.V = in.readInt();
		adj = (Bag<Integer>[]) new Bag[V];
		int E = in.readInt();
		
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
		
		while(!in.isEmpty()) {
			addEdge(in.readInt(),in.readInt());
		}
	}
	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	Iterable<Integer> adj(int v) {
		return adj[v];
	}
	int V() {
		return V;
	}
	int E() {
		int sum = 0;
		for (int v = 0; v < V; v++)
			sum += adj[v].size();
		return sum;		
	}
	Digraph reverse() {
		
	}
	String toString() {
		
	}
	public static void main(String[] args) {
		In in = new Digraph(args[0]);
		Digraph G = new Digraph(in);
		
		for (int v = 0; v < G.V(); v++)
			for (int w : G.adj(v))
				StdOut.println(v + "->" + w);
	}
}
