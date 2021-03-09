import edu.princeton.cs.algs4.StdOut;

// 1.3.28
// 앞의 연습문제를 재귀적인 방법으로 해결해보라.
public class Exercise01_03_28 {
	static int max(Node<Integer> crnt) {
//		StdOut.println("Max Function Call");
		if(crnt == null)
			return 0;
		if(crnt.next == null)
			return crnt.item;
		int m = crnt.item;
		int recur = max(crnt.next);
		
		if(m < recur)
			m = recur;
		
		return m;		
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
		
		StdOut.println("Max : " + max(al.head));
	}
}
