import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_02_01_ver2 {
	static Point2D[] generator(int N) {
		Point2D[] points = new Point2D[N];
		for (int i = 0; i < N; i++)
			points[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
		return points;
	}

	static double minDistance(Point2D[] points) {
		double min = points[0].distanceTo(points[1]);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double distance = points[i].distanceTo(points[j]);
				if (min > distance)
					min = distance;
			}
		}
		return min;
	}

	public static void main(String[] args) {
//		int N = Integer.parseInt(args[0]);
		int N = 20;
		Point2D[] points = generator(N);
		double min = minDistance(points);
		StdOut.println("Minimum Distance - " + min);
	}
}
