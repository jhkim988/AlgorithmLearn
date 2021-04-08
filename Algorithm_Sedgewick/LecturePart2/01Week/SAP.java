import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
	private Digraph wordNet;
	private int distv[];
	private int distw[];
	private boolean markedv[];
	private boolean markedw[];
	private int dist;
	private int targetVertex;
	private boolean findFlag;
	
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
		searchAncestor(v, w);
		if (findFlag) return -1;
		return dist;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {
		searchAncestor(v, w);
		if (findFlag) return -1;
		return targetVertex;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		nullArg(v, w);
		int vresult = -1;
		int wresult = -1;
		
		for(int vtmp : v)
			vresult = vtmp;
		
		for(int wtmp : w)
			wresult = wtmp;
		
		searchAncestor(vresult, wresult);
		return dist;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such
	// path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		nullArg(v, w);
		int vresult = -1;
		int wresult = -1;
		
		for(int vtmp : v)
			vresult = vtmp;
		
		for(int wtmp : w)
			wresult = wtmp;
		
		searchAncestor(vresult, wresult);
		return targetVertex;
	}

	private void nullArg(Object... strs) {
		for (int i = 0; i < strs.length; i++)
			if (strs[i] == null)
				throw new IllegalArgumentException();
	}
	private void searchAncestor(int v, int w) {
		if (v == w) {
			dist = 0;
			return;
		}
		
		distv = new int[wordNet.V()];
		distw = new int[wordNet.V()];
		markedv = new boolean[wordNet.V()];
		markedw = new boolean[wordNet.V()];
		
		for(int i = 0; i < wordNet.V(); i++) {
			distv[i] = -1;
			distw[i] = -1;
		}
		
		targetVertex = -1;
		int vdist = 0;
		int wdist = 0;
		findFlag = false;
		
		// bfs
		Queue<Integer> qv = new Queue<Integer>();
		Queue<Integer> qw = new Queue<Integer>();
		qv.enqueue(v);
		qw.enqueue(w);
		markedv[v] = true;
		markedw[w] = true;
		
		while(!qv.isEmpty() || !qw.isEmpty()) {
			int vtmp = qv.dequeue();
			int wtmp = qw.dequeue();
			
			for(int vtmpAdj : wordNet.adj(vtmp)) {
				if (!markedv[vtmpAdj]) {
					markedv[vtmpAdj] = true;
					if (markedw[vtmpAdj]) {
						vdist++;
						targetVertex = vtmpAdj;
						findFlag = true;
						break;
					}
				}
			}
			
			
			for(int wtmpAdj : wordNet.adj(wtmp)) {
				if (!markedw[wtmpAdj]) {
					markedw[wtmpAdj] = true;
					if (markedv[wtmpAdj]) {
						wdist++;
						targetVertex = wtmpAdj;
						findFlag = true;
						break;
					}
				}
			}
			
			vdist++;
			wdist++;
			
			for(int vtmpAdj : wordNet.adj(vtmp)) {
				if (!markedv[vtmpAdj]) qv.enqueue(vtmpAdj);
			}
			for(int wtmpAdj : wordNet.adj(wtmp)) {
				if (!markedw[wtmpAdj]) qw.enqueue(wtmpAdj);
			}
			
		}
		
		dist = vdist + wdist;
	}
	
	// do unit testing of this class
	public static void main(String[] args) {
	    In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}

}