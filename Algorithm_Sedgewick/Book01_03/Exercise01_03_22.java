// 1.3.22
// x가 연결리스트의 노드일 때 아래의 코드는 어떤 효과를 일으키는가?
public class Exercise01_03_22 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);

		Node<Integer> x = al.head;
		Node<Integer> t = new Node<Integer>(20, null);

		t.next = x.next;
		x.next = t;
		
		// x의 뒤에 t 노드를 추가한다.
	}
}
