import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Topological;

public class WordNet {
	private RedBlackBST<String, Word> words;
	private Digraph wordNet;
	
	private class Word {
		int id;
		String synset;
		String gloss;
		
		Word(int id, String synset, String gloss) {
			this.id = id;
			this.synset = synset;
			this.gloss = gloss;
		}
	}
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		nullArg(synsets, hypernyms);
		
		In synsetsFile = new In(synsets);
		In hypernymsFile = new In(hypernyms);

		String[] sysnsetslines = synsetsFile.readAllLines();
		String[] hypernymslines = hypernymsFile.readAllLines();

		int len = sysnsetslines.length;

		wordNet = new Digraph(len);
		words = new RedBlackBST<String, Word>();
		
		for (int i = 0; i < len; i++) {
			String[] sls = sysnsetslines[i].split(",");
			String[] hls = hypernymslines[i].split(",");
			
			words.put(sls[1], new Word(Integer.parseInt(sls[0]), sls[1], sls[2]));
			
			int hlsId = Integer.parseInt(hls[0]);
			for (String idx : hls) {
				int hlsIdx = Integer.parseInt(idx);
				if (hlsIdx != hlsId)
					wordNet.addEdge(hlsId, Integer.parseInt(idx));
			}
		}
		
		// is wordnet a rooted DAG?
		isRootedDAG(wordNet);
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return words.keys();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		nullArg(word);
		return words.contains(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		nullArg(nounA, nounB);
		nonExistArg(nounA, nounB);
		SAP sap = new SAP(wordNet);
		int idA = words.get(nounA).id;
		int idB = words.get(nounB).id;
		return sap.length(idA, idB);
	}
	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		nullArg(nounA, nounB);
		nonExistArg(nounA, nounB);
		SAP sap = new SAP(wordNet);
		int idA = words.get(nounA).id;
		int idB = words.get(nounB).id;
		int anc = sap.ancestor(idA, idB);
		if (anc < 0) return "";
		
		int[] edgeToA = new int[wordNet.V()];
		int[] edgeToB = new int[wordNet.V()];
		
		Stack<Integer> stkA = new Stack<Integer>();
		Stack<Integer> stkB = new Stack<Integer>();
		bfsPath(idA, anc, stkA);
		bfsPath(idB, anc, stkB);
		
		Stack<Integer> tmp = new Stack<Integer>();
		for(int v : stkB)
			tmp.push(v);
		stkB = tmp;
		tmp = null;
		
		int lastA = -1;
		int sizeA = stkA.size();
		String result = "";
		for(int i = 0; i < sizeA - 1; i++)
			result += stkA.pop()+"-";
		
		// string concat
		
		
	}
	private void bfsPath(int start, int end, Stack<Integer> stk) {
		Queue<Integer> q = new Queue<Integer>();
		boolean[] marked = new boolean[wordNet.V()];
		int[] edgeTo = new int[wordNet.V()];
		
		q.enqueue(start);
		marked[start] = true;
		while(!q.isEmpty()) {
			int v = q.dequeue();
			for(int w : wordNet.adj(v)) {
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
					if (w == end) {
						break;
					}
				}
			}
		}
		
		for(int v = end; v != start; v = edgeTo[v])
			stk.push(v);
		stk.push(start);
	}
	private void nullArg(String... strs) {
		for(int i = 0; i < strs.length; i++)
			if (strs[i] == null) throw new IllegalArgumentException();
	}
	private void nonExistArg(String... strs) {
		for (int i = 0; i < strs.length; i++)
			if (!isNoun(strs[i])) throw new IllegalArgumentException();
	}
	private void isRootedDAG(Digraph G) {
		Topological top = new Topological(G);
		if (!top.hasOrder())
			throw new IllegalArgumentException();
		// root is reachable vertex.
		int numZeroOutDeg = 0;
		for(int v = 0; v < G.V(); v++)
			if (G.outdegree(v) == 0) numZeroOutDeg++;
		
		if (numZeroOutDeg == 0 || numZeroOutDeg > 1)
			throw new IllegalArgumentException();
	}
	
	// do unit testing of this class
	public static void main(String[] args) {

	}
}