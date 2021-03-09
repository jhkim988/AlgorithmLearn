import edu.princeton.cs.algs4.StdOut;

// 1.3.4
// 클래스 이름 ResizingArrayQueueOfStrings으로 고정 크기 배열 저장소를 이용하는 큐 데이터 타입을 구현하라.
// 그 다음에 배열 크기를 가변하여 크기 제약이 없도록 구현부를 개선해보라.
public class Exercise01_03_14 {
	public static void main(String[] args) {
		ResizingArrayQueue<Integer> raq = new ResizingArrayQueue<Integer>();
		for(int i = 0; i < 10; i++)
			raq.enqueue(i);
		raq.dequeue();
		raq.dequeue();
		for(int el : raq)
			StdOut.println(el);
	}
}
