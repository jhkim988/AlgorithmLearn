import edu.princeton.cs.algs4.In;

// 1.3.16
// 130page의 readAllInts()를 기본 모델로 하여 Date 객체를 생성하는 static 메서드 readAllDates()를 작성하라
// 123 페이지의 표에서 보여지고 있는 포맷의 날짜 표현 문자열을 표준 입력으로부터 받아서 결과를 Date의 배열로 리턴하게 하라.

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
