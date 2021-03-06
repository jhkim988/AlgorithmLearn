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
	// 증가와 감소를 모두 지원하는 VisualCounter 클래스를 작성하라.
	// 생성자에서는 두 개의 인수 N(최대 작업 횟수)과 max(카운터가 가질 수 있는 최댓값)을 넘겨받게 한다.
	// 그리고 카운터의 값이 바뀔 때마다 그래프를 그리도록 한다.
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
