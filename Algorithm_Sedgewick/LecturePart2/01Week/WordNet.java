import edu.princeton.cs.algs4.In;

public class WordNet {
	private Word[] words;
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
		In synsetsFile = new In(synsets);
		In hypernymsFile = new In(hypernyms);

		String[] sysnsetslines = synsetsFile.readAllLines();
		String[] hypernymslines = hypernymsFile.readAllLines();

		int len = sysnsetslines.length;

		wordnet = new Digraph(len);
		words = new Word[len];
		
		for (int i = 0; i < len; i++) {
			String[] sls = sysnsetslines[i].split(",");
			String[] hls = hypernymslines[i].split(",");
			
			words[i] = new Word(Integer.parseInt(sls[0]), sls[1], sls[2]);
			
			int hlsId = Integer.parseInt(hls[0]);
			for (String idx : hls) {
				int hlsIdx = Integer.parseInt(idx);
				if (hlsIdx != hlsId)
					wordnet.addEdge(hlsId, Integer.parseInt(idx));
			}
		}
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {

	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {

	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
	
	}
	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {

	}

	// do unit testing of this class
	public static void main(String[] args) {

	}
}