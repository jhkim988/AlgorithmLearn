// 1.3.22
// x�� ���Ḯ��Ʈ�� ����� �� �Ʒ��� �ڵ�� � ȿ���� ����Ű�°�?
public class Exercise01_03_22 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);

		Node<Integer> x = al.head;
		Node<Integer> t = new Node<Integer>(20, null);

		t.next = x.next;
		x.next = t;
		
		// x�� �ڿ� t ��带 �߰��Ѵ�.
	}
}
