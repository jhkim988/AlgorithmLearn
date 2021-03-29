
public class LineSegmentIntersection {
	// Brute-Force : Quadratic Time
	
	// Sweep-Line Algorithm
	// Vertical line sweeps left to right.
	// x-coordinates define events.
	// Vertical line meet
	// 1. horizontal point(left) - insert y coordinate in BST.
	// 2. horizontal point(right)- remove y coordinate from BST.
	// 3. vertical line 		 - range search in BST between two end points.
	
	// NlogN + R (R : Intersections among N orthogonal line segments.)
	// Sorting lines by x-coordinates or insert Priority Queue.
}
