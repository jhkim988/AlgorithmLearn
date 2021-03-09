import edu.princeton.cs.algs4.StdOut;

public class Exercise01_03_30_Loop {
	static Node reverse(Node x) {
		Node first = x;
		Node reverse = null;
		
		while(first != null) {
			Node second = first.next;
			first.next = reverse;
			reverse = first;
			first = second;
		}
		
		return reverse;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0; i < 8; i++) {
			al.addFirst(i);
		}
		for(int i = 10; i >= 0; i--) {
			al.addFirst(i);
		}
		
		// print All
		for(Node<Integer> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item + " ");
		}
		StdOut.println();
		StdOut.println("---------------reverse---------------");
		Node<Integer> reverse = reverse(al.head);
		for(Node<Integer> crnt = reverse; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item + " ");
		}
		StdOut.println();
	}
}
