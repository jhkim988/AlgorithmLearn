import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_28 {
	// 1.1.28 중복 제거
	// Binary Search에서 화이트 리스트를 정렬한 후에 중복 키들을 제거하도록 테스트 클라이언트 코드를 수정하라

	static int rank(int key, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (arr[mid] < key)
				lo = mid + 1;
			else if (arr[mid] > key)
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}
	
	static int[] uniqueArr(int [] arr) {
		Arrays.sort(arr);
		int pointer = 0;
		int[] storage = new int[arr.length]; 
		
		for(int i = 0; i < arr.length; i++) {
			storage[pointer++] = i;
			while(i < arr.length - 1 && arr[i] == arr[i + 1]) {
				i++;
			}
		}
				
		int[] result = new int[pointer];
		for(int i = 0; i < pointer; i++) {
			result[i] = arr[storage[i]];
		}
		return result;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whiteList = in.readAllInts();
		Arrays.sort(whiteList);
		StdOut.println("White List : " + Arrays.toString(whiteList));
		whiteList = uniqueArr(whiteList);
		StdOut.println("uniqueArr : " + Arrays.toString(whiteList));
		
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, whiteList) == -1)
				StdOut.println("Not Found - " + key);
			else
				StdOut.println("Found - " + key);
		}
	}
}
