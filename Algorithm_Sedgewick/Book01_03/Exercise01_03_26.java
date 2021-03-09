import edu.princeton.cs.algs4.StdOut;
// 1.3.26
// 연결 리스트와 문자열 키를 인수로 받아서 키에 해당하는 노드들을 리스트에서 모두 삭제하는 remove() 메서드를 작성하라
public class Exercise01_03_26 {
	static void remove(ArrayList<String> al, String key) {
		while(al.head.item.equals(key)) {
			al.removeFirst();
		}
		
		Node<String> crnt = al.head;
		while(crnt.next != null) {
			if(crnt.next.item.equals(key)) {
				crnt.next = crnt.next.next;
				continue;
			}
			crnt = crnt.next;
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		for(int i = 0; i < 20; i++) {
			al.addFirst((i / 2) + " ");
		}
		
		for(Node<String> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item);
		}
		
		StdOut.println("\nRemove 9");
		remove(al, "9 ");
		
		for(Node<String> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item);
		}
		
		StdOut.println("\nRemove 5");
		remove(al, "5 ");
		
		for(Node<String> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item);
		}
		
		StdOut.println("\nRemove 0");
		remove(al, "0 ");
		
		for(Node<String> crnt = al.head; crnt != null; crnt = crnt.next) {
			StdOut.print(crnt.item);
		}
	}
}
