import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// Q2. Diameter and center of a tree.
// Given a connected graph with no cycles
// Diameter: design a linear-time algorithm to find the longest simple path in the graph.
// Center: design a linear-time algorithm to find a vertex such that its maximum distance from any other vertex is minimized.

// sol)
// Diameter:
// 1. Fix any vertex v.
// 2. Find the shortest paths from v to every other vertex.
// 3. Let w be the vertex with largest distance among shortest paths from v.
// 4. Find the shortest paths from w to every other vertex.
// 5. Let x be the vertex with largest distance among shortest paths from w.
// 6. path from w to x is diameter of graph.

// Center:
// 1. Find diameter of tree.
// 2. Find middle vertiex of diameter.

public class Interview_01_02_DiameterAndCenter {
	private static int farthest (Graph g, int v) {
		if (g.V() == 0) return -1;
		int[] edgeTo = new int[g.V()];
		return farthest(g, v, edgeTo);
	}
	private static int farthest(Graph g, int v, int[] edgeTo) {
		if (g.V() == 0)	return -1;
		int dists[] = new int[g.V()];
		boolean marked[] = new boolean[g.V()];

		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(v);
		marked[v] = true;
		int dist = 1;
		
		// linear time
		while (!q.isEmpty()) {
			int x = q.dequeue();
			for (int w : g.adj(x)) {
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					dists[w] = dist;
					if (edgeTo != null) edgeTo[w] = x;
				}
			}
			dist++;
		}
		
		// linear time
		int maxIdx = 0;
		for(int i = 0; i < dists.length; i++) {
			if (dists[maxIdx] < dists[i])
				maxIdx = i;
		}
		return maxIdx;
	}

	public static Iterable<Integer> diameter(Graph g) {
		if (g.V() == 0)	return null;		
		int[] edgeTo = new int[g.V()];
		
		int w = farthest(g, 0, edgeTo);
		int x = farthest(g, w, edgeTo);
		
		Stack<Integer> path = new Stack<Integer>();		
		for(int tmp = x; tmp != w; tmp = edgeTo[tmp]) {
			path.push(tmp);
		}
		path.push(w);
		return path;
	}

	public static int center(Graph g) {
		if (g.V() == 0) return -1;
		Iterable<Integer> path = diameter(g);
		Iterator<Integer> iter = path.iterator();
		
		int len = 0;
		for(int tmp : path) {
			len++;
		}
		for(int i = 0; i < (len/2); i++)
			iter.next();
		
		return iter.next();
	}

	public static void main(String[] args) {
		int V = 12;
		Graph g = new Graph(V);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 10);
		g.addEdge(10, 11);
		g.addEdge(1, 5);
		g.addEdge(1, 6);
		g.addEdge(2, 7);
		g.addEdge(2, 8);
		g.addEdge(7, 9);
		
		StdOut.println("farthest: " + farthest(g, 0));
		StdOut.println("diameter:");
		Iterable<Integer> path = diameter(g);
		for(int v : path)
			StdOut.print(v + " ");
		StdOut.println("\ncenter: " + center(g));
	}
}
