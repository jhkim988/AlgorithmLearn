import edu.princeton.cs.algs4.StdOut;
// 1.3.18
// x�� ���� ����Ʈ �߰��� �ִ� ����� ����. �Ʒ��� �ڵ�� � ȿ���� �������°�?
public class Exercise01_03_18 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		Node<Integer> last = new Node<Integer>(3, null);
		Node<Integer> middle= new Node<Integer>(2, last);
		Node<Integer> first = new Node<Integer>(1, middle);
		
		first.next = first.next.next;
		al.crnt = first;
		while(al.crnt != null) {
			StdOut.println(al.crnt.item);
			al.crnt = al.crnt.next;
		}
		
	}
	
	
	
	// x�� ���� ��带 �� ���� ���� �����Ͽ�, x�� ���� ��带 �����Ѵ�.
}
