import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Transaction;

public class MinPQ<Key> {
	void insert(Key key) {
		
	}
	Key delMin() {
		
	}
	int size() {
		
	}
	public static void main(String[] args) {
		int M = 10;
		MinPQ<Transaction> pq = new MinPQ<Transaction>();
		while(StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			Transaction item = new Transaction(line);
			pq.insert(item);
			if(pq.size() > M) // Maintain M elements.
				pq.delMin(); // delete minimum value
			// Remain elements are largest M elements.
			
			// M elements...
			//			 		time 	space
			// Sorting  		NlogN	N
			// elementaryPQ		MN		M
			// binary heap 		NlogM 	M
			// best in theory	N		M
		}
	}
}
