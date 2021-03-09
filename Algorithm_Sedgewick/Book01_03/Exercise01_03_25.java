import edu.princeton.cs.algs4.StdOut;

// 1.3.25
// 두 개의 연결 리스트 노드를 인수로 받아 두 번쨰 인수를 첫 번째 인수의 다음에 추가하는 insertAfter() 메서드를 작성하라
// 두 인수 중 하나라도 null이면 아무것도 하지 않는다.

// sol)
// ArrayList 메서드에 작성
public class Exercise01_03_25 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0 ; i < 10; i++)
			al.addFirst(i);
		
		Node<Integer> node1 = al.head;
		for(int i = 0; i < 5; i++)
			node1 = node1.next;
		
		Node<Integer> node2 = new Node<Integer>(100, null);
		StdOut.println("Insert " + node2.item + " after " + node1.item);
		al.insertAfter(node1, node2);
		
		for(Node<Integer> crnt = al.head; crnt != null; crnt = crnt.next)
			StdOut.println(crnt.item);
	}
}
