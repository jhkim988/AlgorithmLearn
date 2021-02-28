import java.util.Arrays;
import java.util.Comparator;

public class Point2D {
	private final double x;
	private final double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static Comparator<Point2D> Y_ORDER = new Comparator<Point2D>() {
		public int compare(Point2D p, Point2D q) {
			if (p.y < q.y)
				return -1;
			if (p.y > q.y)
				return 1;
			return 0;
		}
	};
	
	public Comparator<Point2D> BY_POLAR_ORDER = new Comparator<Point2D>() {
		public int compare(Point2D p, Point2D q) { // this.x, this.y와의 각도를 구한다.
			double polar1 = Math.atan2(p.y - y, p.x - x);
			double polar2 = Math.atan2(q.y - y, q.x - x);
			if (polar1 < polar2) return -1;
			if (polar1 > polar2) return 1;
			return 0;
			
		}
	};

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area2 < 0)
			return -1;
		else if (area2 > 0)
			return 1;
		else
			return 0;
	}
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	public LinkedStack<Point2D> grahamScan(Point2D[] p) {
		int N = p.length;
		LinkedStack<Point2D> hull = new LinkedStack<Point2D>();
		Arrays.sort(p, Point2D.Y_ORDER);
		Arrays.sort(p, p[0].BY_POLAR_ORDER);

		hull.push(p[0]);
		hull.push(p[1]);

		for (int i = 2; i < N; i++) {
			Point2D top = hull.pop();
			while (Point2D.ccw(hull.peak(), top, p[i]) <= 0)
				;
			top = hull.pop();
			hull.push(top);
			hull.push(p[i]);
		}
		return hull;
	}
}
