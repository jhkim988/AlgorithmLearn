import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class MyInterval2D extends Interval2D {
	Interval1D x;
	Interval1D y;
	
	MyInterval2D(Interval1D x, Interval1D y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	Interval1D getXInterval() {
		return x;
	}
	
	Interval1D getYInterval() {
		return y;
	}
	
	double getXMin() {
		return x.min();
	}
	
	double getXMax() {
		return x.max();
	}
	
	double getYMin() {
		return y.min();
	}
	
	double getYMax() {
		return y.max();
	}
	
	Point2D getLeftBottom() {
		return new Point2D(getXMin(), getYMin());
	}
	Point2D getRightBottom() {
		return new Point2D(getXMax(), getYMin());
	}
	Point2D getLeftTop() {
		return new Point2D(getXMin(), getYMax());
	}
	Point2D getRightTop() {
		return new Point2D(getXMax(), getYMax());
	}
	
	boolean contains(MyInterval2D interval) {
		if (contains(interval.getLeftBottom()) && contains(interval.getLeftTop()) && contains(interval.getRightBottom()) && contains(interval.getRightTop()))
			return true;
		return false;
	}
}

public class Exercise01_02_03 {
	// 1.2.3
	// 명령어 인수로 N, min, max를 입력 받고 N개의 랜덤 2D 구간을 생성한다.
	// 이때 구간의 너비와 높이는 1*1 사각형 안에서 min과 max 사이에 균일하게 분포해야 한다.
	// 생성된 구간들을 StdDraw를 이용해서 그림으로 그리고,
	// 서로 겹치는 구간의 개수와 다른 구간을 내부에 포함하는 구간의 개수를 출력하라
	static MyInterval2D[] getIntervals2D(int N, double min, double max) {
		MyInterval2D[] intervals = new MyInterval2D[N];
		for (int i = 0; i < N; i++) {
			double xmin = StdRandom.uniform(min, max);
			double xmax = StdRandom.uniform(min, max);
			if (xmin > xmax) {
				double tmp = xmin;
				xmin = xmax;
				xmax = tmp;
			}

			double ymin = StdRandom.uniform(min, max);
			double ymax = StdRandom.uniform(min, max);
			if (ymin > ymax) {
				double tmp = ymin;
				ymin = ymax;
				ymax = tmp;
			}
			Interval1D xItv = new Interval1D(xmin, xmax);
			Interval1D yItv = new Interval1D(ymin, ymax);
			intervals[i] = new MyInterval2D(xItv, yItv);
		}
		return intervals;
	}

	public static int intersectionCounter(MyInterval2D[] intervals) {
		int counter = 0;
		for(int i = 0; i < intervals.length; i++) {
			for(int j = 0; j <intervals.length; j++) {
				if(intervals[i].intersects(intervals[j])) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	public static int includeCounter(MyInterval2D[] intervals) {
		int counter = 0;
		for(int i = 0; i < intervals.length; i++) {
			for(int j = i + 1; j < intervals.length; j++) {
				if (intervals[i].contains(intervals[j])) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	static void drawIntervals(Interval2D[] intervals) {
		for(int i = 0; i < intervals.length; i++)
			intervals[i].draw();
	}
	
	public static void main(String[] args) {
//		int N = Integer.parseInt(args[0]);
//		double min = Double.parseDouble(args[1]);
//		double max = Double.parseDouble(args[2]);

		int N = 20;
		double min = 0.1;
		double max = 0.8;

		MyInterval2D[] intervals = getIntervals2D(N, min, max);
		drawIntervals(intervals);
		StdOut.println("Intersection Counter : " + intersectionCounter(intervals));
		StdOut.println("Include Counter : " + includeCounter(intervals));
	}
}
