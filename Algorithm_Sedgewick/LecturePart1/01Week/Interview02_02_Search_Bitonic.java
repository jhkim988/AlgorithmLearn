public class Interview02_02_Search_Bitonic {
	public static int searchBinary(int a[], int start, int end, int key, boolean incr) {
		// a : sorted array (increasing or decreasing)
		// start ~ end : range of search
		// key : we want to find it in a
		// incr : true if a is increasing, false if a is decreasing.
		System.out.println("Search Binary \t- range : [" + start + ", " + end + "]");
		while (start <= end) {
			int mid = (start + end) / 2;
			if ((incr) ? (a[mid] > key) : (a[mid] < key))
				end = mid - 1;
			else if ((incr) ? (a[mid] < key) : (a[mid] > key))
				start = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int searchBitonic(int a[], int start, int end, int key) {
		// a : bitonic array
		// start ~ end : range of search
		// key : we want to fint it in a
		System.out.println("Search Bitonic \t- range : [" + start + ", " + end + "]");
		int lng = a.length;
		int mid = (start + end) / 2;
		if (a[mid] == key)
			return mid;
		if (start >= end)
			return -1;
		if ((mid + 1 < lng && a[mid + 1] > a[mid])) { // mid in increasing part
			int binaryResult = -1;
			if (a[mid] > key) {
				binaryResult = searchBinary(a, start, mid - 1, key, true);
			}
			if (binaryResult == -1) {
				return searchBitonic(a, mid + 1, end, key);
			} else {
				return binaryResult;
			}
		} else { // mid in decreasing part
			int binaryResult = -1;
			if (a[mid] > key) {
				binaryResult = searchBinary(a, mid - 1, end, key, false);
			}
			if (binaryResult == -1) {
				return searchBitonic(a, start, mid - 1, key);
			} else {
				return binaryResult;
			}
		}
	}

	public static int searchBitonic(int[] a, int key) {
		return searchBitonic(a, 0, a.length - 1, key);
	}
	public static void main(String[] args) {
		int[] a = new int[] {0, 10, 20, -1, -2, -3, -4, -5, -7, -8, -9, -10};
		int key = -6;
		int find = searchBitonic(a, key);
		System.out.println("Search in Bitonic Array - " + "\tkey : " + key + " \tindex : " +  find);
	}
}
