import edu.princeton.cs.algs4.StdOut;

// 1.3.24
// 연결 리스트 노드를 인수로 받아 그 노드의 다음 노드를 삭제하는 removeAfter()메서드를 작성하라
// 만약 인수로 주어진 노드가 null이거나 그 노드의 다음 노드가 null이면 아무것도 하지 않는다.

// sol)
// ArrayList의 메서드에 작성
public class Exercise01_03_24 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);
		
		Node<Integer> crnt = al.head;
		for(int i = 0; i < 5; i++) {
			crnt = crnt.next;
		}
		StdOut.println("Remove Node after " + crnt.item);
		al.removeAfter(crnt);
		
		for (Node<Integer> node = al.head; node != null; node = node.next)
			StdOut.print(node.item + " ");
		StdOut.println();
	}
}
