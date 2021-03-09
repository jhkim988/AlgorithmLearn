import edu.princeton.cs.algs4.StdOut;

// 1.3.27
// ���� ����Ʈ�� ù ��° ��带 �μ��� �޾Ƽ� ����Ʈ���� ���� ū Ű���� �����ϴ� max() �޼��带 �ۼ��϶�
// �� �� ��� Ű�� ���� ������� �����ϰ�, ����Ʈ�� ����ִ� ��� 0�� �����Ѵ�.
public class Exercise01_03_27 {
	static int max(Node<Integer> crnt) {
		if (crnt == null)
			return 0;
		int max = crnt.item;
		while(crnt != null) {
			if(max < crnt.item)
				max = crnt.item;
			crnt = crnt.next;
		}
		return max;		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0; i < 8; i++) {
			al.addFirst(i);
		}
		for(int i = 10; i >= 0; i--) {
			al.addFirst(i);
		}
		
		// print All
		for(Node<Integer> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item + " ");
		}
		StdOut.println();
		
		StdOut.println("Max : " + max(al.head));
	}
}
