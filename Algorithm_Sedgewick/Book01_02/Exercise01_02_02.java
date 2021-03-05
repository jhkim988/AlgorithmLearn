import java.util.Arrays;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_02_02 {
	// 1.2.2
	// 명령어 인수로 정수 N을 입력받아 N개의 구간(double 한 쌍)을 표준 입력으로부터 입력받고
	// 서로 교차하는 구간들을 출력하는 Interval1D 클라이언트 프로그램을 작성하라
	static Interval1D[] intervalGenerator(int N) { // random
		Interval1D[] intervals = new Interval1D[N];
		for (int i = 0; i < N; i++) {
			double min = StdRandom.uniform(0, (double) N);
			double max = StdRandom.uniform(0, (double) N);
			if (min > max) {
				double temp = min;
				min = max;
				max = temp;
			}
			intervals[i] = new Interval1D(min, max);
		}
		return intervals;
	}

	static Interval1D[] intervalGenerator(int N, double[] points) {
		// the number of intervals == twice of the number of points
		if (points.length != 2 * N)
			throw new IllegalArgumentException();
		Interval1D[] intervals = new Interval1D[N];
		for (int i = 0; i < N; i++)
			intervals[i] = new Interval1D(points[2 * i], points[2 * i + 1]);
		return intervals;
	}

	static double getIntersectionLength(Interval1D itv1, Interval1D itv2) {
		if (!itv1.intersects(itv2))
			return 0.0;
		if (itv1.min() < itv2.min())
			return itv1.max() - itv2.min();
		else if (itv1.min() > itv2.min())
			return itv2.max() - itv1.min();
		else
			return (itv1.max() > itv2.max()) ? itv1.max() - itv2.max() : itv2.max() - itv1.max();
	}

	static double lengthOfIntersection(Interval1D[] intervals) {
		Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);
		double sum = 0.0;
		for (int i = 0; i < intervals.length; i++) {
			for (int j = i + 1; j < intervals.length && intervals[j].intersects(intervals[i]); j++) {
				sum += getIntersectionLength(intervals[i], intervals[j]);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
//		int N = Integer.parseInt(args[0]);
//		double[] coordinates = StdIn.readAllDoubles();
//		Interval1D[] intervals = intervalGenerator(N, coordinates);

		int N = 3;
		Interval1D[] intervals = intervalGenerator(N);
		for (int i = 0; i < intervals.length; i++)
			StdOut.println(intervals[i]);
		double lenIntersection = lengthOfIntersection(intervals);
		StdOut.println("length of intersection : " + lenIntersection);
	}
}
