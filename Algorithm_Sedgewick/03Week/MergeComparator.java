import java.util.Comparator;

public class MergeComparator {
	private static boolean less(Comparator comparator, Object a, Object b) {
		return comparator.compare(a, b) < 0;
	}

	private static void exch(Object[] a, int idx1, int idx2) {
		Object tmp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = tmp;
	}

	public static void sort(Object[] a, Comparator comparator) {
		// Insertion Sort
		int N = a.length;
		for (int i = 0; i < N; i++)
			for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
	}
}

// how to use comparator
class Student {
	// Arrays.sort(a, Student.BY_NAME);
	// Arrays.sort(a, Student.BY_SECTION);
	// In Point2D(Convex Hull), sort by polar angle.
	private String name;
	private int section;
	
	static final Comparator<Student> BY_NAME = new ByName();
	static final Comparator<Student> BY_SECTION = new BySection();
	
	private static class ByName implements Comparator<Student> {
		public int compare(Student v, Student w) {
			return v.name.compareTo(w.name);
		}
	}
	private static class BySection implements Comparator<Student> {
		public int compare(Student v, Student w) {
			return v.section - w.section;
		}
	}
}
