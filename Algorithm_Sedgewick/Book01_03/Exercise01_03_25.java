import edu.princeton.cs.algs4.StdOut;

// 1.3.25
// �� ���� ���� ����Ʈ ��带 �μ��� �޾� �� ���� �μ��� ù ��° �μ��� ������ �߰��ϴ� insertAfter() �޼��带 �ۼ��϶�
// �� �μ� �� �ϳ��� null�̸� �ƹ��͵� ���� �ʴ´�.

// sol)
// ArrayList �޼��忡 �ۼ�
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
