import edu.princeton.cs.algs4.StdOut;

// 1.3.4
// Ŭ���� �̸� ResizingArrayQueueOfStrings���� ���� ũ�� �迭 ����Ҹ� �̿��ϴ� ť ������ Ÿ���� �����϶�.
// �� ������ �迭 ũ�⸦ �����Ͽ� ũ�� ������ ������ �����θ� �����غ���.
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
