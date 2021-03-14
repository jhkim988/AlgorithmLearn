import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final LineSegment[] lsArr;
	private int num = 0;
	
	public FastCollinearPoints(Point[] pts) {
		// finds all line segments containing 4 or more points
		if (pts == null)
			throw new IllegalArgumentException();

		Arrays.sort(pts);
		Point[] points = new Point[pts.length];
		for (int i = 0; i < pts.length - 1; i++) {
			if (pts[i] == null)
				throw new IllegalArgumentException();
			if(pts[i] == pts[i+1])
				throw new IllegalArgumentException();
			points[i] = pts[i];
		}
		points[pts.length - 1] = pts[pts.length - 1]; // sorted
		
		Point[] ysort = points.clone();
		Point[] start = new Point[points.length * points.length];
		Point[] end = new Point[points.length * points.length];

		for (int i = 0; i < points.length; i++) {
			Point origin = ysort[i];
			Comparator<Point> c = origin.slopeOrder();
			sort(points, c, 0, points.length - 1, start, end, origin); // use 3-way-partitioning - sort by slope
		}
		lsArr = new LineSegment[num];
		for (int i = 0; i < num; i++) {
			lsArr[i] = new LineSegment(start[i], end[i]);
		}
	}

	private void sort(Point[] points, Comparator<Point> c, int lo, int hi, Point[] start, Point[] end, Point origin) {
		if (hi <= lo)
			return;
		int lt = lo, gt = hi;
		Point v = points[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = c.compare(points[i], v);
			if (cmp < 0)
				exch(points, lt++, i++);
			else if (cmp > 0)
				exch(points, i, gt--);
			else
				i++;
		}
		int times = gt - lt + 1;
		if (times >= 3) { // same slope, more than 4
			// add to LineSegment[]
			Point[] tmp = new Point[gt - lt + 2];
			for (int j = lt; j <= gt; j++) {
				tmp[j - lt] = points[j];
			}
			tmp[gt - lt + 1] = origin;
			Point s = tmp[0];
			Point e = tmp[0];
			for (int j = 1; j < gt - lt + 2; j++) {
				if (s.compareTo(tmp[j]) > 0)
					s = tmp[j];
				if (e.compareTo(tmp[j]) < 0)
					e = tmp[j];
			}
			boolean flag = true;
			
			for (int idx = 0; idx < num; idx++) {
				if (start[idx].equals(s) && end[idx].equals(e)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				start[num] = s;
				end[num] = e;
				num++;
			}
		}
		sort(points, c, lo, lt - 1, start, end, origin);
		sort(points, c, gt + 1, hi, start, end, origin);
	}

	private void exch(Object[] points, int idx1, int idx2) {
		Object tmp = points[idx1];
		points[idx1] = points[idx2];
		points[idx2] = tmp;
	}

	public int numberOfSegments() {
		// the number of line segments
		return num;
	}

	public LineSegment[] segments() {
		// the line segments
		return lsArr.clone();
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
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
