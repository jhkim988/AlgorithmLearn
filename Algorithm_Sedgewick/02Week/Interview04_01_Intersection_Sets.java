import java.util.Arrays;

public class Interview04_01_Intersection_Sets {
//	Q1 Intersection of two sets.
//	Given two arrays a[] and b[], each containing n distinct 2D points in the plane,
//	design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].

// sort - subquadratic
// compare - Best: n, Worst: 2n

	public static LinkedStack<Point2D> intersectionOf(Point2D[] a, Point2D[] b) {
		// 1. sort a[] and b[]. (Use Shell Sort, we can sort it in subquadratic time)
		// 2. compare in order.

		Arrays.sort(a, Point2D.Y_ORDER); // sort for some key.
		Arrays.sort(b, Point2D.Y_ORDER);

		LinkedStack<Point2D> intersection = new LinkedStack<Point2D>();

		int ptrA = 0; // pointer of a[];
		int ptrB = 0; // pointer of b[];

		while (ptrA < a.length && ptrB < b.length) { // ptrA and ptrB move a.length and b.length for each.
			while (ptrA < a.length && ptrB < b.length && Point2D.Y_ORDER.compare(a[ptrA], b[ptrB]) == 0) {
				intersection.push(a[ptrA]);
				ptrA++;
				ptrB++;
			}
			while (ptrA < a.length && ptrB < b.length && Point2D.Y_ORDER.compare(a[ptrA], b[ptrB]) < 0) {
				ptrA++;
			}
			while (ptrA < a.length && ptrB < b.length && Point2D.Y_ORDER.compare(a[ptrA], b[ptrB]) > 0) {
				ptrB++;
			}
		}
		return intersection;
	}

	public static void main(String[] args) {
		int numA = 10;
		int numB = 20;
		Point2D[] a = new Point2D[numA];
		Point2D[] b = new Point2D[numB];

		for (int i = 0; i < numA; i++) {
			a[i] = new Point2D(i * 1.0, i * 1.0);
		}
		for (int i = 0; i < numB; i++) {
			b[i] = new Point2D((i + 5) * 1.0, (i + 5) * 1.0);
		}

		LinkedStack<Point2D> intersection = intersectionOf(a, b);
		intersection.dump();
	}
}
