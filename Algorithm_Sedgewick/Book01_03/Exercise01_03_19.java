import edu.princeton.cs.algs4.StdOut;

// 1.3.19
// ù ��° ��尡 first�� ���� ����Ʈ���� ������ ��带 �����ϴ� �ڵ带 �ۼ��϶�
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
