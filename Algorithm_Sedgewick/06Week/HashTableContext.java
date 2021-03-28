
public class HashTableContext {
	// String hashCode() in Java 1.1
	// For long strings: only examin 8-9 evenly spaced character
	// Benefit: saves time in performing arithmetic.
//	public int hashCode() {
//		int hash = 0;
//		int skip = Math.max(1,  length() / 8);
//		for(int i = 0; i < length(); i += skip)
//			hash = s[i] + (37 * hash);
//		return hash;
//	}
	// greate potential for bad collision patterns
	// ex) url...
	
	// is the uniform hashing assumption important in practice?
	// 1. Obvious situations: aircraft control, nuclear reactor, pacemaker
	// 2. Surprising situaition: denial-of-service attcks
	
	// one-way hash functions
	
	// separate chaning vs linear probing
	// separate chaining:
	// Easier to implement delete
	// Performance degrades gracefully
	// Clustering less sensitive to poorly-designed hash function.
	// Linear probing:
	// Less wasted space.
	// Better cache performance.
	
	// How to delete?
	// How to resize?
	
	// Two-probe hashing, Double hashing, Cuckoo hashing ...
	
	// Hash tables vs Balanced search trees
	// Hash tables:
	// Simpler code, No effective alternative for unordered keys
	// Faster for simple keys (a few arithmetic ops vs logN compares)
	// Better system support in Java for strings (e.g. cached hash code)
	// Balanced search trees:
	// Stronger performance guarantee.
	// Support for ordered ST operations
	// Easier to implement compareTo() correcly than equals() and hashCode()
}
