// 1.3.23
// 왜 아래 코드는 바로 위의 연습문제와 결과가 다른가?
public class Exercise01_03_23 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);

		Node<Integer> x = al.head;
		Node<Integer> t = new Node<Integer>(20, null);
		x.next = t;
		t.next = x.next;
		
		// x.next는 t가 됐기 때문에, t.next = t가 돼 버린다.
	}
}
