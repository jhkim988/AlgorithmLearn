import edu.princeton.cs.algs4.StdOut;

// 1.3.24
// ���� ����Ʈ ��带 �μ��� �޾� �� ����� ���� ��带 �����ϴ� removeAfter()�޼��带 �ۼ��϶�
// ���� �μ��� �־��� ��尡 null�̰ų� �� ����� ���� ��尡 null�̸� �ƹ��͵� ���� �ʴ´�.

// sol)
// ArrayList�� �޼��忡 �ۼ�
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
