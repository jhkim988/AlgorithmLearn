
public class KdTree_Lecture {
	// In 2D
	// Grid Impletation:
	// 1. Divide space M * M
	// 2. Create list of points contained in each square.(Use 2D array)
	// 3. Add (x, y) to list for corresponding square.
	// 4. Range Search: examine only squares that intersect 2D range query.
	// Space-time trade off(choose M)
	// Space : M^2 + N
	// time : 1 + N/M^2 per square examined, on average
	// sqrt(N) * sqrt(N) grid -> space 2N, time 2
	
	// if points are randomly distributed, it is ideal.
	// clustering case ?
	
	// Grid, 2DTree, QuadTree, BSPTree ...
	
	// 2DTree
	// even level - left/right
	// odd level  - down/up
	// R + log N
	// circular position - worst, Take sqrt(N) time even if BST is balanced.
	
	// Nearest Neighbor Search
	// Go down 2D tree, check distance each point.
	// Typical case - log N, Worst case - N
	
	// KdTree
	// level = i (mod k), ith coordinate compare, less(left) and larger(right)
	
	// N-body simulation
	// Goal - Simulate the motion of N particles, mutually affected by gravity.
	// Brute-Force - For each particles, compute force : F = Gm1m2/r^2 (O(N^2))
}
