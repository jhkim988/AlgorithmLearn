import java.util.Iterator;
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
		ts.add(p);
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
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
			return null;
		else {
			Queue<Point2D> iter = new Queue<Point2D>();
			for (Point2D p : ts)
				iter.enqueue(p);
			return new Iterable<Point2D>() {
				@Override
				public Iterator<Point2D> iterator() {
					return new Iterator<Point2D>() {
						@Override
						public boolean hasNext() {
							while (!iter.isEmpty() && !rect.contains(iter.peek()))
								iter.dequeue();
							return !iter.isEmpty();
						}

						@Override
						public Point2D next() {
							return hasNext() ? iter.dequeue() : null;
						}

					};
				}
			};
		}
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		if (ts.isEmpty())
			return null;
		Point2D near = ts.first();
		for (Point2D tmpP : ts)
			if (!tmpP.equals(p) && tmpP.distanceTo(p) < near.distanceTo(p)) {
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
	}
}
