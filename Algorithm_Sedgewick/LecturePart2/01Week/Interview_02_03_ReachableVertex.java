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

public class Interview_02_03_ReachableVertex {
	// if distinct indices i and j have same value, then i and j are in same cycle(same big node)
	private int[] bignode;
	
	Interview_02_03_ReachableVertex(Digraph G) {
		bignode = new int[G.V()];
	}
	public static void main(String[] args) {
		
	}
}
