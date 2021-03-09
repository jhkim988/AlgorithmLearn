import edu.princeton.cs.algs4.StdOut;

// 1.3.20
// int �� k�� �μ��� �޾� ���� ����Ʈ�� k���� �׸���(���� �����Ѵٸ�) �����ϴ� �޼��� delete()�� �ۼ��϶�

// sol)
// ArrayList class�� method�� �߰���
public class Exercise01_03_20 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			al.addFirst(i);
		
		al.delete(5);
		
		for (Node<Integer> node = al.head; node != null; node = node.next)
			StdOut.print(node.item + " ");
		StdOut.println();
	}
}
