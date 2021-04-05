import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Q1. Shortest directed cycle.
// Given a digraph G, design an efficient algorithm to find a directed cycle
// with the minimum number of edges (or report that the graph is acyclic).
// The running time of your algorithm should be at most proportional to V(E + V)
// and use space proportional to E + V,
// where V is the number of vertices and E is the number of edges.

// sol)

public class Interview_02_01_ShortestDirectedCycle {
	private Stack<Integer> cycle;
	private int length;
	
	private class BreadthFirstDirectedPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int[] distTo;
		private int s; // start
		
		public BreadthFirstDirectedPaths(Digraph G, int v) {
			s = v;
			marked = new boolean[G.V()];
			edgeTo = new int[G.V()];
			distTo = new int[G.V()];
			
			for(int i = 0; i < G.V(); i++)
				distTo[i] = Integer.MAX_VALUE;
			
			bfs(G, s);
		}
		private void bfs(Digraph G, int s) {
			Queue<Integer> q = new Queue<Integer>();
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
			while(!q.isEmpty()) {
				int v = q.dequeue();
				for(int w : G.adj(v)) {
					if (!marked[w]) {
						q.enqueue(w);
						edgeTo[w] = v;
						distTo[w] = distTo[v] + 1;
						marked[w] = true;
					}
				}
			}
		}
		public boolean hasPathTo(int w) {
			return marked[w];
		}
		public int distTo(int w) {
			return distTo[w];
		}
		public Iterable<Integer> pathTo(int w) {
			if (!hasPathTo(w)) return null;
			Stack<Integer> result = new Stack<Integer>();
			for(int i = w; i != s; i = edgeTo[i])
				result.push(w);
			return result;
		}
	}
	
	Interview_02_01_ShortestDirectedCycle (Digraph G) {
		Digraph R = G.reverse();
		length = G.V() + 1;
		
		for(int v = 0; v < G.V(); v++) { // # of loop: V
			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(R, v); // E + V
			for(int w : G.adj(v)) { // # of looop: G.degree(v)
				if(bfs.hasPathTo(w) && bfs.distTo(w) + 1 < length) {
					length = bfs.distTo(w) + 1;
					cycle = new Stack<Integer>();
					for(int x : bfs.pathTo(w))
						cycle.push(x);
					cycle.push(v);
				}
			}
		}
	}
	
	Iterable<Integer> findDirectedCycle() {
		return cycle;
	}
	public static void main(String[] args) {
		int N = 10;
		Digraph G = new Digraph(N);
		Interview_02_01_ShortestDirectedCycle sdc = new Interview_02_01_ShortestDirectedCycle(G);
		for (int v : sdc.findDirectedCycle())
			StdOut.print(v + " ");
		
	}
}
