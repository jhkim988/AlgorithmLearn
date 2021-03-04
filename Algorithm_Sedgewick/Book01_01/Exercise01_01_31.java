import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_31 {
	static class Point {
		double x;
		double y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		Point rotation(double angle) {
			double rotx = Math.cos(angle) * x - Math.sin(angle) * y;
			double roty = Math.sin(angle) * x + Math.cos(angle) * y;
			Point rot = new Point(rotx, roty);
			return rot;
		}
	}

	public static Point[] drawNgon(int N) {
		int size = 5;
		Point[] pointNgon = new Point[N];
		Point startPoint = new Point(size, 0);
		for (int i = 0; i < N; i++) {
			pointNgon[i] = startPoint;
			StdDraw.filledCircle(startPoint.x, startPoint.y, 0.05);
			startPoint = startPoint.rotation(2 * Math.PI / ((double) N));
		}
		return pointNgon;
	}

	public static void randomlyDrawLine(Point p, Point q, double prob) {
		if (StdRandom.bernoulli(prob)) {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.line(p.x, p.y, q.x, q.y);
		}
	}

	public static void randomlyDrawNgonLine(Point[] pointNgon, double prob) {
		for (int i = 0; i < pointNgon.length; i++) {
			for (int j = 0; j != i && j < pointNgon.length; j++) {
				randomlyDrawLine(pointNgon[i], pointNgon[j], prob);
			}
		}
	}

	public static void main(String[] args) {
		StdDraw.setXscale(-10, 10);
		StdDraw.setYscale(-10, 10);
		int N = Integer.parseInt(args[0]);
		double p = Double.parseDouble(args[1]);
//		int N = 20;
//		double p = 0.5;
		randomlyDrawNgonLine(drawNgon(N), p);
	}
}
