
public class RectangleIntersection {
	// Goal: Find all intersections among a set of N orthogonal rectangles.
	// Brute-Force: Check all pair of rectangles for intersection. O(N^2)
	// Design Computer become a geometric problem.
	
	// Sweep-line Algorithm + Interval Search Algorithm
	// Vertical line meets left side of rectangle
	// -> input vertical interval into Interval Search Tree.
	// Vertical line meets right side of rectangle
	// -> delete ertical interval from Interval Search Tree.
	
	// N log N + R log N
	// Use Sorting or Priority Queue. (N log N)
	// Interval Search Tree (log N)
	// Reduce Rectangle Intersection Search Problem to 1D Interval Search Problem.
}
