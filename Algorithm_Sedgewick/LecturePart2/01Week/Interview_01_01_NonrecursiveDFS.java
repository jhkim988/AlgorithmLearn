import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Q1 Nonrecursive depth-first search.
// Implement depth-first search in an undirected graph without using recursion.

// sol)
// Use Stack and marked boolean array.
// Start Unmarked vertex. Push vertex in stack and mark.
// While stack is not empty, Pop element from stack and Push its adjacent vertex and mark.
// Iterate it.
public class Interview_01_01_NonrecursiveDFS {
	public static void dfs(Graph g) {
		if (g.V() == 0)	return;
		
		Stack<Integer> stk = new Stack<Integer>();
		boolean[] marked = new boolean[g.V()];
		
		for (int i = 0; i < g.V() && !marked[i]; i++) {
			stk.push(i);
			marked[i] = true;
			
			while (!stk.isEmpty()) {
				int v = stk.pop();
				StdOut.print(v + " "); // do something, for each vertex.
				for (int w : g.adj(v)) {
					if (!marked[w]) {
						stk.push(w);
						marked[w] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int V = 10;
		int E = 10;
		Graph g = new Graph(V);
		for (int i = 0; i < E; i++) {
			int rand1 = StdRandom.uniform(V);
			int rand2 = StdRandom.uniform(V);
			g.addEdge(rand1, rand2);
		}
		StdOut.println(g);
		dfs(g);
	}
}
