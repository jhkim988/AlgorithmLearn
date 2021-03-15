import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final ArrayList<LineSegment> segments = new ArrayList<>();

	private void checkNullEntries(Point[] pts) {
		for (int i = 0; i < pts.length; i++)
			if (pts[i] == null)
				throw new IllegalArgumentException();
	}

	private void checkDuplicateEntries(Point[] pts) {
		for (int i = 0; i < pts.length; i++) {
			for (int j = i + 1; j < pts.length; j++) {
				if (pts[i].equals(pts[j]))
					throw new IllegalArgumentException();
			}
		}
	}

	public FastCollinearPoints(Point[] pts) {
		// finds all line segments containing 4 or more points
		if (pts == null) // null arguments check
			throw new IllegalArgumentException();
		checkNullEntries(pts);
		checkDuplicateEntries(pts);
		Point[] points = pts.clone();
		Arrays.sort(points);

		for (int i = 0; i < points.length - 3; i++) {
			// Arrays.sort() method is guaranted stablity.
			Arrays.sort(points);
			Arrays.sort(points, points[i].slopeOrder());
			for (int p = 0, first = 1, last = 2; last < points.length; last++) {
				while (last < points.length
						&& Double.compare(points[p].slopeTo(points[first]), points[p].slopeTo(points[last])) == 0) {
					last++;
				}
				if (last - first >= 3 && points[p].compareTo(points[first]) < 0) {
					segments.add(new LineSegment(points[p], points[last - 1]));
				}
				first = last;
			}
		}
	}

	public int numberOfSegments() {
		// the number of line segments
		return segments.size();
	}

	public LineSegment[] segments() {
		// the line segments
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
//		In in = new In("input8.txt");
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
