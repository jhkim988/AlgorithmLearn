// 1.3.23
// �� �Ʒ� �ڵ�� �ٷ� ���� ���������� ����� �ٸ���?
public class Exercise01_03_23 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);

		Node<Integer> x = al.head;
		Node<Integer> t = new Node<Integer>(20, null);
		x.next = t;
		t.next = x.next;
		
		// x.next�� t�� �Ʊ� ������, t.next = t�� �� ������.
	}
}
