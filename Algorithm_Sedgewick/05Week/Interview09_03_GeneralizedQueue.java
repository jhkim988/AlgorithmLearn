import edu.princeton.cs.algs4.RedBlackBST;
// Q3 Generalized queue.
// Design a generalized queue data type
// that supports all of the following operations
// in logarithmic time (or better) in the worst case.
import edu.princeton.cs.algs4.StdOut;

// Create an empty data structure.
// Append an item to the end of the queue.
// Remove an item from the front of the queue.
// Return the ith item in the queue.
// Remove the ith item from the queue.

// sol)
// Use Red-Black Tree<Integer, TypeOfQueueValue>.
// 1. Create an empty data structure
// - Create Red-Black Tree O(1), key is integer, insert order.
// 2. Append an item from the end of the queue.
// - Append an item into Red-Black Tree, incresing key + 1.
// 3. Remove an item from the front of the queue.
// 4. Return the ith item in the queue.
// 5. Remove the ith item from the queue.
// - Use rank mathod in RedBlackBST. We can find ith(1st) element, takes log n time.
class GeneralizedQueue<T> {
	private static int id = 0;
	private RedBlackBST<Integer, T> rb;
	GeneralizedQueue() { // create an empty data structure
		rb = new RedBlackBST<Integer, T>();
	}
	
	void enqueue(T item) { // append an item from the end of the queue.
		rb.put(id++, item);
	}
	
	T dequeue() { // remove an item from the front of the queue.
		return remove(0);
	}
	T remove(int i) { // remove the ith item in the queue.
		int idx = rb.select(i);
		T tmp = rb.get(idx);
		rb.delete(idx);
		return tmp;
	}
	T look(int i) { // return the ith item from the queue.
		int idx = rb.select(i);
		return rb.get(idx);
	}
	int size() {
		return rb.size();
	}
}
public class Interview09_03_GeneralizedQueue {
	public static void main(String[] args) {
		int N = 10;
		GeneralizedQueue<Integer> gq = new GeneralizedQueue<Integer>();
		for(int i = 0; i < N; i++) {
			gq.enqueue(i);
		}
		StdOut.println("remove index 3 element - " + gq.remove(3));
		StdOut.println("look index 7 element - " + gq.look(7));
		StdOut.println("size - " + gq.size());
		N = gq.size();
		for(int i = 0; i < N; i++) {
			StdOut.print(gq.dequeue() + " ");
		}
	}
}
