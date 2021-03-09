import edu.princeton.cs.algs4.In;

// 1.3.17
// �������� 1.3.16�� Transaction�� ������� Ǯ���.
public class Exercise01_03_17 {
	public Transaction[] readAllTransactions(String name) {
		Queue<Transaction> q = new Queue<Transaction>();
		In in = new In(name);
		while (!in.isEmpty()) {
			q.enqueue(new Transaction(in.readString()));
		}
		int N = q.size();
		Transaction[] tr = new Transaction[N];
		for (int i = 0; i < N; i++) {
			tr[i] = q.dequeue();
		}
		return tr;
	}
}
