import java.util.*;

public class QuickSort<T extends Comparable<? super T>> implements Sort<T> {
	ArrayList<T> arrList;

	QuickSort(ArrayList<T> arrList) {
		this.arrList = arrList;
	}

	@Override
	public void sort() {
		sort(0, arrList.size() - 1);
	}

	@Override
	public ArrayList<T> get() {
		return arrList;
	}

	// public void sort(int lo, int hi) {
	// if (lo >= hi) return;
	// int pl = lo;
	// int pr = hi;
	// T pivot = arrList.get((lo+hi)>>1);
	// do {
	// while (pl <= hi && arrList.get(pl).compareTo(pivot) < 0) pl++;
	// while (lo <= pr && arrList.get(pr).compareTo(pivot) > 0) pr--;
	// if (pl <= pr) swap(pl++, pr--);
	// } while (pl <= pr);
	// if (lo < pl) sort(lo, pr);
	// if (pr < hi) sort(pl, hi);
	// }
	public void sort(int lo, int hi) {
		T pivot = arrList.get(lo);
		int pr = hi + 1;
		for (int pl = hi; pl > lo; pl--) {
			if (arrList.get(pl).compareTo(pivot) > 0) {
				pr--;
				swap(pl, pr);
			}
		}
		swap(lo, --pr);

		if (lo < pr - 1)
			sort(lo, pr - 1);
		if (pr + 1 < hi)
			sort(pr + 1, hi);
	}

	private void swap(int a, int b) {
		T tmp = arrList.get(a);
		arrList.set(a, arrList.get(b));
		arrList.set(b, tmp);
	}

	void print() {
		for (T v : arrList) {
			System.out.print(v + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int numData = 200;
		ArrayList<Integer> test = new ArrayList<>();
		for (int i = 0; i < numData; i++) {
			test.add((int) (Math.random() * 200));
		}
		QuickSort<Integer> qs = new QuickSort<>(test);
		qs.print();
		qs.sort();
		qs.print();
	}
}