import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private final LineSegment[] lsArr;

	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if (points == null)
			throw new IllegalArgumentException();

		for(int i = 0; i < points.length; i++) {
			if(points[i] == null)
				throw new IllegalArgumentException();
			for(int j = i + 1; j < points.length; j++) {
				if (points[i].equals(points[j])) {
					throw new IllegalArgumentException();					
				}
			}
		}
		
		LineSegment[] tmp = new LineSegment[points.length];
		int lsArrPtr = 0;
		int size = points.length;

		for (int i = 0; i < size; i++) { // p
			for (int j = i + 1; j < size; j++) { // q
				for (int k = j + 1; k < size; k++) { // r
					for (int l = k + 1; l < size; l++) { // s
						if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
								&& points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
							Point[] tmpPoints = new Point[4];
							tmpPoints[0] = points[i];
							tmpPoints[1] = points[j];
							tmpPoints[2] = points[k];
							tmpPoints[3] = points[l];
							
							Arrays.sort(tmpPoints);
							tmp[lsArrPtr++] = new LineSegment(tmpPoints[0], tmpPoints[3]);
						}
					}
				}
			}
		}

		lsArr = new LineSegment[lsArrPtr];
		for (int i = 0; i < lsArrPtr; i++) {
			lsArr[i] = tmp[i];
		}
	}

	public int numberOfSegments() {
		// the number of line segments
		return lsArr.length;
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
