import edu.princeton.cs.algs4.StdOut;

// 1.3.21
// ���Ḯ��Ʈ�� ���ڿ� Ű�� �μ��� ����Ʈ�� Ű�� ���翩�θ� true, false�� �����ϴ� �޼��� find()�� �ۼ��϶�.
// Ű�� ����� item �ʵ�� ���Ѵ�.
public class Exercise01_03_21 {
	public static boolean find(ArrayList<String> al, String key) {
		for(Node<String> node = al.head; node != null; node=node.next) {
			if(node.item.equals(key))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			al.addFirst(i + " ");
		}
		
		StdOut.println(find(al, "10 "));
		StdOut.println(find(al, "5 "));
	}
}
