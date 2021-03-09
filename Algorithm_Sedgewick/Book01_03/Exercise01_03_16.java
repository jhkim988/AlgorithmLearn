import edu.princeton.cs.algs4.In;

// 1.3.16
// 130page�� readAllInts()�� �⺻ �𵨷� �Ͽ� Date ��ü�� �����ϴ� static �޼��� readAllDates()�� �ۼ��϶�
// 123 �������� ǥ���� �������� �ִ� ������ ��¥ ǥ�� ���ڿ��� ǥ�� �Է����κ��� �޾Ƽ� ����� Date�� �迭�� �����ϰ� �϶�.

public class Exercise01_03_16 {
	public static Date[] readAllDates(String name) {
		In in = new In(name);
		Queue<Date> q = new Queue<Date>();
		while(!in.isEmpty())
			q.enqueue(new Date(in.readString())); // yyyy/mm/dd
		int N = q.size();
		Date[] dates = new Date[N];
		for(int i = 0; i < N; i++)
			dates[i] = q.dequeue();
		return dates;
	}
}
