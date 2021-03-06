import javax.naming.LimitExceededException;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

class VisualCounter {
	private int work;
	private int workLimit;
	private int max;
	private int counter;
	private int prev;

	VisualCounter(int N, int max) {
		workLimit = N;
		this.max = max;
		work = 0;
		prev = 0;
		counter = 0;
	}

	void increment() throws LimitExceededException {
		if (work >= workLimit)
			throw new LimitExceededException();
		if (counter >= max)
			throw new LimitExceededException();
		prev = counter;
		counter++;
		work++;
		draw();
	}

	void decrement() throws LimitExceededException {
		if (work >= workLimit)
			throw new LimitExceededException();
		if (counter <= 0)
			throw new LimitExceededException();
		prev = counter;
		counter--;
		work++;
		draw();
	}

	void draw() {
		StdDraw.setXscale((double) 0, (double) workLimit);
		StdDraw.setYscale((double) -max/10, (double) max/10);
		if (work > 0)
			StdDraw.line(work - 1, prev, work, counter);
	}

	int getCount() {
		return counter;
	}
}

public class Exercise01_02_10 {
	// 1.2.10
	// ������ ���Ҹ� ��� �����ϴ� VisualCounter Ŭ������ �ۼ��϶�.
	// �����ڿ����� �� ���� �μ� N(�ִ� �۾� Ƚ��)�� max(ī���Ͱ� ���� �� �ִ� �ִ�)�� �Ѱܹް� �Ѵ�.
	// �׸��� ī������ ���� �ٲ� ������ �׷����� �׸����� �Ѵ�.
	static void randomUpDown(int N, int max, int trial) {
		VisualCounter counter = new VisualCounter(N, max);
		int t = 0;
		while (t < trial) {
			if (StdRandom.bernoulli() && counter.getCount() < max - 1)
				try {
					counter.increment();
				} catch (LimitExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if (counter.getCount() > 0)
				try {
					counter.decrement();
				} catch (LimitExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			t++;
		}
	}

	public static void main(String[] args) {
		int N = 10000;
		int max = 10000;
		int T = 10000;
		randomUpDown(N, max, T);
	}
}
