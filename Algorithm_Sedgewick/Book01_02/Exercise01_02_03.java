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
	// ��ɾ� �μ��� N, min, max�� �Է� �ް� N���� ���� 2D ������ �����Ѵ�.
	// �̶� ������ �ʺ�� ���̴� 1*1 �簢�� �ȿ��� min�� max ���̿� �����ϰ� �����ؾ� �Ѵ�.
	// ������ �������� StdDraw�� �̿��ؼ� �׸����� �׸���,
	// ���� ��ġ�� ������ ������ �ٸ� ������ ���ο� �����ϴ� ������ ������ ����϶�
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
