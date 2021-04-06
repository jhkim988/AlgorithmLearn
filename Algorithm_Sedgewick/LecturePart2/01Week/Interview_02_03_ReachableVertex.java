import java.util.Arrays;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

// Q3. Reachable vertex.
// DAG: Design a linear-time algorithm to determine
// whether a DAG has a vertex that is reachable from every other vertex, and if so, find one.
// Digraph: Design a linear-time algorithm to determine
// whether a digraph has a vertex that is reachable from every other vertex, and if so, find one.

// sol)
// DAG: Use Topological Sort.
// The highest vertex is reachable from every other vertex.

// Digraph: Consider a cycle as a (big) node, Then we can make given graph DAG.
// Use Topological Sort.
// The highest vertex is reachable from every other vertex.
// If the highest vertex is big node,
// the all node of cycles are reachable from every other vertex.

// Use Proposition:
// In DAG, There is a reachable vertex iff there uniquely exits vertex with 0 out-degree.
// (=>)
// 1. Existence
// Let r be reachable vertex in DAG.
// Suppose every vertex of graph has positive out-degree(deg(v) > 0 for each v in G)
// Since deg(r) > 0, there is w such that r -> w.
// Since r is reachable, By definition w -> r some path.
// r -> w -> r is cycle. Contradiction!
// 2. Uniqueness
// Suppose g1 and g2 are vertices with positive out-degree(i.e. deg(g1) > 0 and deg(g2) > 0)
// Let r be reachable vertex. then there exist paths g1 -> r and g2 -> r.
// It means that g1 = r = g2.
// (<=)
	// Lemma. In DAG, there is sink vertex(0 out-degree vertex).
	// Suppose that every vertex of DAG has positive out-degree.
	// Traverse along out-going edge. Since the number of vertex is finite,
	// we will return to previously visited vertex.
	// It is cycle, Contradiction!
// Let r be unique vertex with zero out-degree.
// Suppose r is not reachable. Then there is some vertex u such that u !-> r.
// (u !-> r means that u cannot reach r)
// Consider two sets Vn = {g : g !-> r}, Vy = {g : g -> r}.
// Obiously r is in Vy. and there is no edge from vertex of Vn to vertex of Vy.
// Since Vn is sub-DAG, Vn has sink vertex v.
// v and r are distinct, Contradition by uniqueness.

public class Interview_02_03_ReachableVertex {
	// if distinct indices i and j have same value, then i and j are in same cycle(same big node)
	private int[] bignode;
	private boolean[] marked;
	private int reachable;
	private Digraph bigG;
	
	Interview_02_03_ReachableVertex(Digraph G) {
		reachable = -1;
		bignode = new int[G.V()];
		marked = new boolean[G.V()];
		Digraph R = G.reverse();		
		int numBigNodes = G.V();
		
		for(int v = 0; v < G.V(); v++) {
			bignode[v] = v;
		}
		
		Queue<Integer> q = new Queue<Integer>();
		for(int vtx = 0; vtx < G.V(); vtx++) {
			q.enqueue(vtx);
			marked[vtx] = true;
			while(!q.isEmpty()) {
				int v = q.dequeue();
				BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(R, v);
				for(int w : G.adj(v)) {
//					StdOut.println("for : " + v + ", " + w + ", " + bfs.hasPathTo(w));
					if (!marked[w]) q.enqueue(w);
					if (bfs.hasPathTo(w)) {
						if (w < v) { // all bignode[v] -> bignode[w]
							for(int i = 0; i < G.V(); i++)
								if (bignode[i] == bignode[v]) bignode[i] = bignode[w];
						}
						else
							for(int i = 0; i < G.V(); i++)
								if (bignode[i] == bignode[w]) bignode[i] = bignode[v];
						if (!marked[w]) {
//							StdOut.println("numBigNodes-- : " + v + ", " + w);
							numBigNodes--;
						}
					}
					marked[w] = true;
				}
			}
		}
		
//		StdOut.println("BigNode : " + Arrays.toString(bignode));
		
		this.bigG = new Digraph(numBigNodes);
		int[] preprocess = new int[G.V()];
		SET<Integer> tmp = new SET<Integer>();
		int idx = 0;
		for(int i = 0; i < G.V(); i++) {
			if (tmp.contains(bignode[i])) {
				int j;
				for(j = 0; j <= i && bignode[j] != bignode[i]; j++);
				preprocess[i] = preprocess[j];
			}
			else {
				preprocess[i] = idx++;
				tmp.add(bignode[i]);
			}
		}
		tmp = null;
		
//		StdOut.println("Preprocess : " + Arrays.toString(preprocess));
//		StdOut.println("# of Bignode : " + numBigNodes);
		for(int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {// if numBigNodes < w ?
				if (preprocess[v] != preprocess[w])
					bigG.addEdge(preprocess[v], preprocess[w]);
				}
		}
//		StdOut.println(bigG);
		// Topological Sort bigG
		Topological top = new Topological(bigG);
		int preresult = -1;
		int numOutDeg = 0;
		for(int v : top.order()) {
			preresult = v;
			if (bigG.outdegree(v) == 0)
				numOutDeg++;
		}
		
		if (numOutDeg > 1) return;
		
//		StdOut.println("preresult : " + preresult);
		
		for(int i = 0; i < G.V(); i++)
			if (preprocess[i] == preresult) {
				reachable = i;
				break;
			}
	}

	public static void main(String[] args) {
		int N1 = 5;
		Digraph G1 = new Digraph(N1);
		G1.addEdge(0, 2);
		G1.addEdge(2, 4);
		G1.addEdge(4, 3);
		G1.addEdge(3, 2);
		G1.addEdge(3, 1);
		
		StdOut.println("----------------------------------------");
		StdOut.println("Original Graph");
		StdOut.println(G1);
		StdOut.println("Big Node Graph");
		Interview_02_03_ReachableVertex reach1 = new Interview_02_03_ReachableVertex(G1);
		StdOut.println(reach1.bigG);
		StdOut.println("Reachable Vertex : " + reach1.reachable);
		
		int N2 = 13;
		Digraph G2 = new Digraph(N2);
		G2.addEdge(0, 1);
		G2.addEdge(1, 2);
		G2.addEdge(2, 6);
		G2.addEdge(2, 7);
		G2.addEdge(7, 8);
		G2.addEdge(8, 1);
		G2.addEdge(3, 4);
		G2.addEdge(4, 5);
		G2.addEdge(5, 9);
		G2.addEdge(9, 3);
		G2.addEdge(9, 7);
		G2.addEdge(10, 11);
		G2.addEdge(11, 12);
		G2.addEdge(12, 10);
		G2.addEdge(12, 5);
		
		StdOut.println("----------------------------------------");
		StdOut.println("Original Graph");
		StdOut.println(G2);
		StdOut.println("Big Node Graph");
		Interview_02_03_ReachableVertex reach2 = new Interview_02_03_ReachableVertex(G2);
		StdOut.println(reach2.bigG);
		StdOut.println("Reachable Vertex : " + reach2.reachable);
		
		int N3 = 3;
		Digraph G3 = new Digraph(N3);
		G3.addEdge(0, 1);
		G3.addEdge(0, 2);
		StdOut.println("----------------------------------------");
		StdOut.println("Original Graph");
		StdOut.println(G3);
		StdOut.println("Big Node Graph");
		Interview_02_03_ReachableVertex reach3 = new Interview_02_03_ReachableVertex(G3);
		StdOut.println(reach3.bigG);
		StdOut.println("Reachable Vertex : " + reach3.reachable);
	}
}
