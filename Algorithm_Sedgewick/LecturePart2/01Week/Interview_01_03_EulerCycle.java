import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Q3. Euler cycle.
// An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge in the graph exactly one.
// Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
// Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find one.

// sol)
// proof
// (=>) Suppose graph g is Euler cycle that has vertex with odd degree.
// Let x be vertex with odd degree of g.
// Since g is Euler cycle, all edge is used exactly once by definition.
// Pair the edge entering and exiting vertex x.
// Since x has odd degree, one edge remain. contradiction.

// (<=) Use Mathematical Induction for the number of vertex.
// Let V be the number of vertex of graph.
// 1. V = 0.
// Nothing to proof.
// 2. Induction Hypothesis.
// Suppose every graph with no-odd-degree that has k(or less) vertices is Euler cycle.
// We prove that graph X with no-odd-degree that has (k+1) vertices is Euler cycle.
// As each vertex has even degree (and graph is connected), graph contains a circuit, say C.
// If C contain every edge of graph, It is Euler cycle, done
// If C does not contain some edge of graph.
// Let X' be graph obtained from X removing all the edge in C.
// X' may be disconnected, but still has even degree.
// By Induction Hypothesis, X' is Euler cycle. say its circuit C'.
// Suppose C' is started at vertex w.
// When we meet vertex w by traversing C, Follow circuie C'.
// When we finish following C', Continue C.
// It is Euler cycle.

// Linear Algorithm
// 1. Create Edge class, then check passed by edge.
// 2. Process Depth-First-Search about edge that does not pass yet.
// If you can't move(For visit node, there is no remaining node), Push last node in cycle stack. Process other way.
// This situation occurs at the starting node and at the node connected to the node where this situation occurred.
// (Consider pair of in-out edge..)
// Therefore it is part of cycle.
// 3. If you pass by all edges, Push nodes DFS path.

public class Interview_01_03_EulerCycle {
	private Stack<Integer> cycle;
	
	// record edge
	private static class Edge {
		int v;
		int w;
		boolean isUsed;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
			isUsed = false;
		}
		public int other (int vertex) {
			if (v == vertex) return w;
			else if (w == vertex) return v;
			else throw new IllegalArgumentException();
		}
		
		public String toString() {
			return v + "-" + w;
		}
	}
	
	Interview_01_03_EulerCycle(Graph G) {
		// # of edge = 0
		if (G.E() == 0) return;
		
		// if and only if condition
		for(int v = 0; v < G.V(); v++)
			if (G.degree(v) % 2 != 0) return;
		
		Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
		for(int v = 0; v < G.V(); v++)
			adj[v] = new Queue<Edge>();
		
		// enqueue edge
		for(int v = 0; v < G.V(); v++) {
			int selfLoops = 0;
			for (int w : G.adj(v)) {
				if (v == w) {
					if (selfLoops % 2 == 0) {
						Edge e = new Edge(v, w);
						adj[v].enqueue(e);
						adj[w].enqueue(e);
					}
					selfLoops++;
				}
				else if (v < w) {
					Edge e = new Edge(v, w);
					adj[v].enqueue(e);
					adj[w].enqueue(e);
				}
			}
		}
		
//		StdOut.println("Edge[]:");
//		for(int v = 0; v < G.V(); v++)
//			StdOut.println(v + ": " + adj[v]);
		
		// search
		int s = nonIsolatedVertex(G);
		Stack<Integer> stk = new Stack<Integer>();
		cycle = new Stack<Integer>();
		stk.push(s);
		while(!stk.isEmpty()) {
			int v = stk.pop();
			while(!adj[v].isEmpty()) {
				Edge edge = adj[v].dequeue();
				if (edge.isUsed) continue;
				edge.isUsed = true;
				StdOut.println("edge: " + edge);
				stk.push(v);
				v = edge.other(v);
			}
			StdOut.println("cycle: " + v);
			cycle.push(v);
		}
		
		if (cycle.size() != G.E() + 1)
			cycle = null;
	}
	Iterable<Integer> cycle() {
		return cycle;
	}
	public boolean hasEulerianCycle() {
		return cycle != null;
	}
	private static int nonIsolatedVertex(Graph G) {
		for(int v = 0; v < G.V(); v++)
			if (G.degree(v) > 0) return v;
		return -1;
	}
	public static void main(String[] args) {
//		int N = 7;
//		Graph G = new Graph(N);
//		G.addEdge(0, 1);
//		G.addEdge(0, 2);
//		G.addEdge(1, 2);
//		G.addEdge(1, 3);
//		G.addEdge(1, 4);
//		G.addEdge(2, 3);
//		G.addEdge(2, 5);
//		G.addEdge(3, 4);
//		G.addEdge(3, 5);
//		G.addEdge(4, 5);
//		G.addEdge(4, 6);
//		G.addEdge(5, 6);
//		
//		Interview_01_03_EulerCycle ec = new Interview_01_03_EulerCycle(G);
//		for(int v : ec.cycle())
//			StdOut.print(v + " ");
		
		int N2 = 6;
		Graph G2 = new Graph(N2);
		G2.addEdge(0, 1);
		G2.addEdge(0, 1);
		G2.addEdge(1, 2);
		G2.addEdge(1, 2);
		G2.addEdge(0, 3);
		G2.addEdge(0, 4);
		G2.addEdge(1, 3);
		G2.addEdge(1, 4);
		G2.addEdge(2, 3);
		G2.addEdge(2, 4);
		G2.addEdge(3, 4);
		
		Interview_01_03_EulerCycle ec2 = new Interview_01_03_EulerCycle(G2);
		StdOut.println();
		for(int v : ec2.cycle())
			StdOut.print(v + " ");
	}
}
