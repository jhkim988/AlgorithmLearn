import edu.princeton.cs.algs4.StdOut;

// 1.3.21
// 연결리스트와 문자열 키를 인수로 리스트에 키의 존재여부를 true, false로 리턴하는 메서드 find()를 작성하라.
// 키는 노드의 item 필드와 비교한다.
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
