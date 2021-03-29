import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
	private int[] id;
	
	QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; ++i) // �迭 �ʱ�ȭ, O(n)
			id[i] = i;
	}

	void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		
		for (int i = 0; i < id.length; ++i) // id[p]�� ��� id[q]�� �ٲ۴�. O(n)
			if (id[i] == pid)
				id[i] = qid;
	} // N���� ��ü�� ���� �� Union�� N�� �Ѵٸ�? N^2�ð� ��ŭ �ɸ���.

	boolean connected(int p, int q) {
		return id[p] == id[q]; // O(1)
	}
	
	// Quick Find(Eager Algorithm)
	// �����迭�� �̿��Ͽ�, p�� q�� ����� �ִٸ� index p�� index q�� ���� ���ڸ� �迭���ҷ� ������ �����.
	// ���� �迭�� index�� Ȯ���ϸ�, ����� �ִ����� �� �� �ִ�.
	// ���� ������� �Ѵٸ�, Union�� �� �� index p�� index q �� � �迭���Ұ����� ������Ʈ�� ���� �ٲ��� ���ؾ��Ѵ�.
	// �̷� ����� ��ü�� �������� ������ �ȴ�. �ٲ�� �� ���� �ʹ� ���� �ȴ�.
	
	// 1. �迭 �ʱ�ȭ - ������ �ε����� �迭��Ұ��� �ʱ�ȭ �Ѵ�. ���� ��� ��ü�� �ڱ��ڽŸ� ���� �� �ִ�.
	// 2. union(4, 3);�� �ϸ� 4�� �ε����� ���� 3�� �ε����� ���� ���� �Ѵ�.
	// 3. union(3, 8);�� �ϸ� 3, 4�� �ε����� ���� 8�� �ε����� ���� ���� �Ѵ�.
	// 4. union(6, 5);�� �ϸ� 6�� �ε����� ���� 5�� �ε����� ���� ���� �Ѵ�.
	// 5. union(9, 4);�� �ϸ�, 9�� �ε����� ���� 4�� �ε����� ���� ���� �Ѵ�.
	// 6. union(2, 1);
	// 7. connected(8, 9); �̹� ����� �ֱ� ������ �迭��Ұ��� ����. true�� �����Ѵ�.
	// 8. connected(5, 0); ����� ���� �ʱ� ������ false�� �����Ѵ�.
	// 9. union(5, 0);
	// 10. union(1, 6);
	
	public static void main(String[] args) {
		// 1. ��ü�� ������ �Է� �޴´�. ���⼭ UF ��ü�� �����Ѵ�.
		// 2. � ��ü���� ���� �� �ִ��� �˱� ���� ������ �� �־� �Է� �޴´�.
		// 3. ������ ��ü�� ����Ѵ�.
		
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
