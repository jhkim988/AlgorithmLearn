import edu.princeton.cs.algs4.BinarySearchST;

// Q4. Web tracking.
// Suppose that you are tracking n web sites and m users and you want to support the following API:
// User visits a website.
// How many times has a given user visited a given site?
// What data structure or data structures would you use?

// sol)
// Use Symbolic Table:
// key is user name(or id), value is symbolic table with web site(url) as key and visit count as value.
// Easy to add/delete user, To get counter takes (log n)(log m) times.
// Difficult to know who has visited a particular website.

public class Interview08_04_WebTracking {
	// Use Symbol Table(Easy to add/delete to user and website)
	// Key = user name(or id)
	// Value = Symbol Table with Key - website, Value - visit count
	private class WebTracking {
		BinarySearchST<Integer, BinarySearchST<String, Integer>> wtst = new BinarySearchST<>();
		
		void addUser(int id) {
			if (!wtst.contains(id))
				wtst.put(id, new BinarySearchST<String, Integer>());
		}
		
		void visit(int id, String url) {
			if(!wtst.contains(id))
				addUser(id);
			if(!wtst.get(id).contains(url))
				wtst.get(id).put(url, 1);
			else
				wtst.get(id).put(url, wtst.get(id).get(url) + 1);
		}
		
		int getUserVisit(int id, String url) {
			if(wtst.contains(id) && wtst.get(id).contains(url))
				return wtst.get(id).get(url);
			return -1;
		}
	}
	public static void main(String[] args) {
		
	}
}
