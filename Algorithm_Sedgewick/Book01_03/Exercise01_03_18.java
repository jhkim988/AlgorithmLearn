import edu.princeton.cs.algs4.StdOut;
// 1.3.18
// x를 연결 리스트 중간에 있는 노드라고 하자. 아래의 코드는 어떤 효과를 가져오는가?
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
	
	
	
	// x의 다음 노드를 그 다음 노드로 설정하여, x의 다음 노드를 삭제한다.
}
