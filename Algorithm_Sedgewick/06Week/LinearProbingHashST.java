
public class LinearProbingHashST<Key, Value> {
	// Open addressing
	// Hash: Map key to integer i between 0 and M-1
	// Insert: Put at table index i if free; if not try i+1, i+2, etc
	// Search: Search table index i; if occupied but no match, try i+1, i+2, etc.
	// Note - Array size M must be greater than number of key-value pairs N.
	
	// Knuth's parking problem
	// Cars arrive at one-way street with M parking spaces.
	// Each desires a random space i: if space i is taken, try i+1, i+2, etc.
	// What is mean displacement of a car?
	// Half-full: With M/2 cars, mean displacement is ~3/2
	// Full: With M cars, mean displacement is ~ sqrt(pi M / 8)
	
	// Proposition
	// Under uniform hashing assumption, the average # of probes
	// in a linear probing hash table of size M that contains N = aM keys is:
	// search hit: ~(1 + 1/(1-a))/2
	// search miss/insert: ~(1 + 1/(1-a)^2)/2
	
	// M too large - too many empty array entries
	// M too small - search time blows up.
	// Typical choice: a = N/M ~ 1/2
	
	private int M = 30001;
	private Value[] vals = (Value[]) new Object[M];
	private Key[] keys = (Key[]) new Object[M];
	// array doubling/halving(resizing) code omitted
	
	private int hash(Key key) {return (key.hashCode() & 0x7fffffff) % M;}
	
	public void put(Key key, Value val) {
		int i;
		for(i = hash(key); keys[i] != null; i = (i+1) % M) {
			if(keys[i].equals(key))
				break;
		}
		keys[i] = key;
		vals[i] = val;
	}
	
	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i = (i+1) % M) {
			if (key.equals(keys[i]))
				return vals[i];
		}
		return null;
	}
}
