
public class ConnectedComponents {
	// Connected: Vertices v and w are connected if there is a path between them.
	// Goal: Preprocess graph to answer queries of the form
	// "is v connected to w?" in constant time
	
	// union-find? Not quite(Not constant time)
	// Depth-first search? Yes
	
	// relation "is connected to" is equivalance relation
	// connected component is maximal set of connected vertices.
	// we assign identifier for each vertices, storage in id[] array.
	
	// 1. initialize all vertices v as unmarked.
	// 2. For each unmarked vertex v, run DFS to identify all vertices
	// discorvered as part of the same component.
	
	private boolean[] marked;
	private int[] id;
	private int count;
	
	ConnectedComponents(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int v = 0; v < G.V() && !marked[v]; v++) {
			dfs(G, v);
			count++;
		}
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
		}
	}
	boolean connected (int v, int w) {
		// are v and w connected?
		return id[v] == id[w];
	}
	int count() {
		// # of connected components
		return count;
	}
	int id(int v) {
		// component identifier for v
		return id[v];
	}
}
