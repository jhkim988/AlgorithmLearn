import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {
	private TreeSet<Point2D> ts;

	public PointSET() {
		// construct an empty set of points
		ts = new TreeSet<Point2D>();
	}

	public boolean isEmpty() {
		// is the set empty?
		return ts.isEmpty();
	}

	public int size() {
		// number of points in the set
		return ts.size();
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null)
			throw new IllegalArgumentException();
		ts.add(p);
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		if (p == null)
			throw new IllegalArgumentException();
		if (isEmpty()) return false;
		return ts.contains(p);
	}

	public void draw() {
		// draw all points to standard draw
		for (Point2D p : ts)
			p.draw();
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		if (rect == null)
			throw new IllegalArgumentException();
		if (isEmpty())
			return null;
		Queue<Point2D> iter = new Queue<Point2D>();
		for (Point2D p : ts)
			if (rect.contains(p))
				iter.enqueue(p);
		return iter;
//		return new Iterable<Point2D>() {
//			@Override
//			public Iterator<Point2D> iterator() {
//				return new Iterator<Point2D>() {
//					@Override
//					public boolean hasNext() {
//						while (!iter.isEmpty() && !rect.contains(iter.peek()))
//							iter.dequeue();
//						return !iter.isEmpty();
//					}
//
//					@Override
//					public Point2D next() {
//						return hasNext() ? iter.dequeue() : null;
//					}
//
//				};
//			}
//		};

	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		if (p == null)
			throw new IllegalArgumentException();
		if (ts.isEmpty())
			return null;
		Point2D near = ts.first();
		for (Point2D tmpP : ts)
			if (tmpP.distanceTo(p) < near.distanceTo(p)) {
				near = tmpP;
			}
		return near;
	}

	public static void main(String[] args) {
		// unit testing of the methods (optional)
		int N = 10;
		PointSET ps = new PointSET();
		StdOut.println("Before Insert - isEmpty() = " + ps.isEmpty());
		for (Double i = 0.0; i < N; i++) {
			ps.insert(new Point2D(i, i));
		}
		StdOut.println("After Insert - isEmpty() = " + ps.isEmpty());
		StdOut.println("Size of PointSet = " + ps.size());
		StdOut.println("contains() method test:\n - ps.contains(new Point2D(2.0, 2.0)) = "
				+ ps.contains(new Point2D(2.0, 2.0)));
		StdOut.println("contains() method test:\n - ps.contains(new Point2D(1.0, 2.0)) = "
				+ ps.contains(new Point2D(1.0, 2.0)));

		RectHV rect = new RectHV(0.5, 0.5, 5.5, 5.5);
		Iterable<Point2D> iter = ps.range(rect);
		StdOut.println("Iterator Test");
		for (Point2D p : iter) {
			StdOut.println(p);
		}
		StdOut.println(
				"nearest() method test:\n - ps.nearest(new Point2D(0.5, 1.5)) = " + ps.nearest(new Point2D(0.5, 1.5)));
		ps.draw();

		// Auto Grader - test 3
//		PointSET ps2 = new PointSET();
//		Point2D A = new Point2D(0.25, 0.25);
//		Point2D B = new Point2D(1.0, 0.0);
//		Point2D C = new Point2D(0.75, 0.25);
//		Point2D D = new Point2D(0.25, 1.0);
//		Point2D E = new Point2D(0.75, 1.0);
//		Point2D F = new Point2D(0.75, 0.25);
//		Point2D G = new Point2D(0.0, 0.75);
//		Point2D H = new Point2D(0.25, 0.25);
//		Point2D I = new Point2D(0.75, 0.5);
//		Point2D J = new Point2D(0.5, 0.25);
//		Point2D query = new Point2D(0.5, 0.25);
//		ps2.insert(A);
//		ps2.insert(B);
//		ps2.insert(C);
//		ps2.insert(D);
//		ps2.insert(E);
//		ps2.insert(F);
//		ps2.insert(G);
//		ps2.insert(H);
//		ps2.insert(I);
//		ps2.insert(J);
//		StdOut.println("near : " + ps2.nearest(query));

		// Auto Grader - test 7
		PointSET ps3 = new PointSET();
		ps3.insert(new Point2D(1.0, 1.0));
		ps3.insert(new Point2D(0.0, 0.0));
		ps3.insert(new Point2D(0.0, 1.0));
		StdOut.println("nearest - " + ps3.nearest(new Point2D(0.0, 0.0)));
		StdOut.println("range");
		Iterable<Point2D> iter3 = ps3.range(new RectHV(0.0, 0.0, 0.0, 0.0));
		for (Point2D p : iter3)
			StdOut.println(p);
	}
}
