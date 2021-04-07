import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Topological;

public class WordNet {
	private RedBlackBST<String, Word> words;
	private Digraph wordnet;
	
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

		wordnet = new Digraph(len);
		words = new RedBlackBST<String, Word>();
		
		for (int i = 0; i < len; i++) {
			String[] sls = sysnsetslines[i].split(",");
			String[] hls = hypernymslines[i].split(",");
			
			words.put(sls[1], new Word(Integer.parseInt(sls[0]), sls[1], sls[2]));
			
			int hlsId = Integer.parseInt(hls[0]);
			for (String idx : hls) {
				int hlsIdx = Integer.parseInt(idx);
				if (hlsIdx != hlsId)
					wordnet.addEdge(hlsId, Integer.parseInt(idx));
			}
		}
		
		// is wordnet a rooted DAG?
		isRootedDAG(wordnet);
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
		
	}
	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		nullArg(nounA, nounB);
		nonExistArg(nounA, nounB);
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