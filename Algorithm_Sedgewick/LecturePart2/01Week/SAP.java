import edu.princeton.cs.algs4.Queue;

public class SAP {
	private Digraph wordNet;
	private int distv[];
	private int distw[];
	private boolean markedv[];
	private boolean markedw[];
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		nullArg(G);
		
		wordNet = new Digraph(G.V());
		for (int v = 0; v < G.V(); v++)
			for (int w : G.adj(v))
				wordNet.addEdge(v, w);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		distv = new int[wordNet.V()];
		distw = new int[wordNet.V()];
		markedv = new boolean[wordNet.V()];
		markedw = new boolean[wordNet.V()];
		
		for(int i = 0; i < wordNet.V(); i++) {
			distv[i] = -1;
			distw[i] = -1;
		}
		

		
		// bfs
		Queue<Integer> qv = new Queue<Integer>();
		Queue<Integer> qw = new Queue<Integer>();
		qv.enqueue(v);
		qw.enqueue(w);
		while(!qv.isEmpty() || !qw.isEmpty()) {
			int vtmp = qv.dequeue();
			int wtmp = qw.dequeue();
			
			for(int vtmpAdj : wordNet.adj(vtmp)) {
				if (!markedv[vtmpAdj]) {
					qv.enqueue(vtmpAdj);
					
				}
			}
			
			for(int wtmpAdj : wordNet.adj(wtmp)) {
				if (!markedw[wtmpAdj]) {
					qw.enqueue(wtmpAdj);
					
				}
			}
		}
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {

	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		nullArg(v, w);
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such
	// path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		nullArg(v, w);
	}

	private void nullArg(Object... strs) {
		for (int i = 0; i < strs.length; i++)
			if (strs[i] == null)
				throw new IllegalArgumentException();
	}

	// do unit testing of this class
	public static void main(String[] args) {

	}
}