import edu.princeton.cs.algs4.StdOut;

// 1.3.27
// 연결 리스트의 첫 번째 노드를 인수로 받아서 리스트에서 가장 큰 키값을 리턴하는 max() 메서드를 작성하라
// 이 때 모든 키는 양의 정수라고 가정하고, 리스트가 비어있는 경우 0을 리턴한다.
public class Exercise01_03_27 {
	static int max(Node<Integer> crnt) {
		if (crnt == null)
			return 0;
		int max = crnt.item;
		while(crnt != null) {
			if(max < crnt.item)
				max = crnt.item;
			crnt = crnt.next;
		}
		return max;		
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
