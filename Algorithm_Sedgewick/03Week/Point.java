import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
	private final int x;
	private final int y;
	public Point(int x, int y) {
		// constructs the point (x, y)
		this.x = x;
		this.y = y;
	}

	public void draw() {
		// draws this point
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		// draws the line segment from this point to that point
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {
		// string representation
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point that) {
		// compare two points by y-coordinates, breaking ties by x-coordinates
		if (this.y > that.y) {
			return 1;
		} else if (this.y < that.y) {
			return -1;
		} else {
			if (this.x > that.x)
				return 1;
			else if (this.x < that.x)
				return -1;
			return 0;
		}
	}

	public double slopeTo(Point that) {
		// the slope between this point and that point
		if(this.x==that.x && this.y== that.y) // itself
			return Double.NEGATIVE_INFINITY;
		if (this.y == that.y) // horizontal
			return (double) +0;
		if (this.x == that.x) // vertical
			return Double.POSITIVE_INFINITY;
		return ((double) (this.y - that.y)) / ((double) (this.x - that.x));
	}

	public Comparator<Point> slopeOrder() {
		// compare two points by slopes they make with this point
		return new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				if (slopeTo(p1) > slopeTo(p2))
					return 1;
				else if (slopeTo(p1) < slopeTo(p2))
					return -1;
				return 0;
			}
		};
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 3);
		Point p3 = new Point(2, 5);
		StdOut.println(p1);
		StdOut.println(p1.slopeTo(p2));
		StdOut.println(p1.compareTo(p2));
		Comparator<Point> slopeComp = p1.slopeOrder();
		StdOut.println(slopeComp.compare(p2, p3));
		p1.draw();
		p2.draw();
		p1.drawTo(p2);
	}
}
