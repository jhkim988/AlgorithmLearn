import edu.princeton.cs.algs4.StdOut;

// 1.3.20
// int 값 k를 인수로 받아 연결 리스트의 k번쨰 항목을(만약 존재한다면) 삭제하는 메서드 delete()를 작성하라

// sol)
// ArrayList class의 method로 추가함
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
