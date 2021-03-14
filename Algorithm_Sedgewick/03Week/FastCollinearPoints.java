import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final LineSegment[] lsArr;
	private int num = 0;

	private class SlopeEnds implements Comparable<SlopeEnds> {
		public double slope;
		public Point start;
		public Point end;

		SlopeEnds(double slope, Point start, Point end) {
			this.slope = slope;
			this.start = start; // min
			this.end = end; // max
		}

		public int compareTo(SlopeEnds that) {
			if (this.slope < that.slope)
				return -1;
			else if (this.slope > that.slope)
				return 1;
			return 0;
		}
	}

	private boolean exist(SlopeEnds[] se, double slope, Point end) {
		int idx = searchOnlySlope(se, slope);
		if (idx == -1)
			return false;
		
		int tmp = idx;
		while (tmp < num && se[tmp].slope == slope) {
			if (se[tmp].end.equals(end))
				return true;
			tmp++;
		}
		tmp = idx;
		while ( tmp > -1 && se[tmp].slope == slope) {
			if (se[tmp].end.equals(end))
				return true;
			tmp--;
		}
		return false;
	}

	private int searchOnlySlope(SlopeEnds[] se, double slope) {
		// se is sorted by slope
		int lo = 0;
		int hi = num - 1;
		
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (se[mid].slope < slope)
				lo = mid + 1;
			else if (se[mid].slope > slope)
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	public FastCollinearPoints(Point[] pts) {
		// finds all line segments containing 4 or more points
		if (pts == null)
			throw new IllegalArgumentException();

		Point[] points = pts.clone();
		Arrays.sort(points);
		for (int i = 0; i < pts.length - 1; i++) {
			if (pts[i] == null)
				throw new IllegalArgumentException();
			if (pts[i] == pts[i + 1])
				throw new IllegalArgumentException();
		}
		if (pts[pts.length - 1] == null)
			throw new IllegalArgumentException();

		Point[] ysort = points.clone();
		SlopeEnds[] se = new SlopeEnds[points.length * points.length];

		for (int i = 0; i < points.length; i++) {
			Point origin = ysort[i];
			Comparator<Point> c = origin.slopeOrder();
			sort(points, c, 0, points.length - 1, se, origin); // use 3-way-partitioning - sort by slope
//			StdOut.println("origin : " + origin + "\tarray : " + Arrays.toString(points));
		}
		lsArr = new LineSegment[num];
		for (int i = 0; i < num; i++) {
			lsArr[i] = new LineSegment(se[i].start, se[i].end);
		}
	}

	private void sort(Point[] points, Comparator<Point> c, int lo, int hi, SlopeEnds[] se, Point origin) {
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
			Point start = tmp[0]; // min
			Point end = tmp[0]; // max
			for (int j = 1; j < gt - lt + 2; j++) {
				if (start.compareTo(tmp[j]) > 0)
					start = tmp[j];
				if (end.compareTo(tmp[j]) < 0)
					end = tmp[j];
			}
			
			double slope = origin.slopeTo(points[lt]);
			boolean flag = exist(se, slope, end);
//			StdOut.println("start : " + start + "\tend : " + end);
//			StdOut.println("slope : " + slope + "\tflag : " + flag);
			if (!flag) {
				se[num] = new SlopeEnds(slope, start, end);
				Arrays.sort(se, 0, num);
				num++;
			}
		}
		sort(points, c, lo, lt - 1, se, origin);
		sort(points, c, gt + 1, hi, se, origin);
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
