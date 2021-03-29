import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
	private int[] id;
	
	QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; ++i) // 배열 초기화, O(n)
			id[i] = i;
	}

	void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		
		for (int i = 0; i < id.length; ++i) // id[p]를 모두 id[q]로 바꾼다. O(n)
			if (id[i] == pid)
				id[i] = qid;
	} // N개의 객체가 있을 때 Union을 N번 한다면? N^2시간 만큼 걸린다.

	boolean connected(int p, int q) {
		return id[p] == id[q]; // O(1)
	}
	
	// Quick Find(Eager Algorithm)
	// 정수배열을 이용하여, p와 q가 연결돼 있다면 index p와 index q가 같은 숫자를 배열원소로 갖도록 만든다.
	// 따라서 배열의 index만 확인하면, 연결돼 있는지를 알 수 있다.
	// 위의 방법으로 한다면, Union을 할 때 index p와 index q 중 어떤 배열원소값으로 컴포넌트의 값을 바꿀지 정해야한다.
	// 이런 방법은 객체가 많아지면 문제가 된다. 바꿔야 할 값이 너무 많게 된다.
	
	// 1. 배열 초기화 - 각각의 인덱스로 배열요소값을 초기화 한다. 따라서 모든 객체는 자기자신만 연결 돼 있다.
	// 2. union(4, 3);을 하면 4번 인덱스의 값을 3번 인덱스의 값과 같게 한다.
	// 3. union(3, 8);을 하면 3, 4번 인덱스의 값을 8번 인덱스의 값과 같게 한다.
	// 4. union(6, 5);을 하면 6번 인덱스의 값을 5번 인덱스의 값과 같게 한다.
	// 5. union(9, 4);을 하면, 9번 인덱스의 값을 4번 인덱스의 값과 같게 한다.
	// 6. union(2, 1);
	// 7. connected(8, 9); 이미 연결돼 있기 때문에 배열요소값이 같다. true를 리턴한다.
	// 8. connected(5, 0); 연결돼 있지 않기 때문에 false를 리턴한다.
	// 9. union(5, 0);
	// 10. union(1, 6);
	
	public static void main(String[] args) {
		// 1. 객체의 개수를 입력 받는다. 여기서 UF 객체를 생성한다.
		// 2. 어떤 객체끼리 연결 돼 있는지 알기 위해 정수를 두 쌍씩 입력 받는다.
		// 3. 연결한 객체를 출력한다.
		
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " " + q);
			}
		}
	}
}
