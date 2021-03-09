import edu.princeton.cs.algs4.StdOut;

// 1.3.19
// 첫 번째 노드가 first인 연결 리스트에서 마지막 노드를 삭제하는 코드를 작성하라
public class Exercise01_03_19 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);
		
		// print array list before remove last element.
		StdOut.println("------------------print array list before remove last element.------------------");
		for (Node<Integer> node = al.head; node != null; node = node.next)
			StdOut.print(node.item + " ");
		StdOut.println();

		Node<Integer> first = al.head;
		while(first.next.next != null) {
			first = first.next;
		}
		
		first.next = null;
		StdOut.println("------------------print array list after remove last element.------------------");
		for (Node<Integer> node = al.head; node != null; node = node.next)
			StdOut.print(node.item + " ");
		StdOut.println();
	}
}
