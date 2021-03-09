import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_03_15 {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		ResizingArrayQueue<String> raq = new ResizingArrayQueue<String>();

		// queue�� k�� ���Ҹ� ��� �����Ѵ�.
		while (!StdIn.isEmpty())
			if (raq.size() <= k) {
				raq.enqueue(StdIn.readString());
			} else {
				raq.dequeue();
				raq.enqueue(StdIn.readString());
			}
		StdOut.println("k - last string : " + raq.peak());
	}
}
