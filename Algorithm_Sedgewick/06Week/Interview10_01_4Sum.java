// Q4. 4-SUM.
// Given an array  a[] of n integers,
// the 4-SUM problem is to determine
// if there exist distinct indices i, j, k, and l
// such that a[i]+a[j]=a[k]+a[l].
// Design an algorithm for the 4-SUM problem that takes time proportional to n^2
// (under suitable technical assumptions).

// sol) Hashing by key a[i] + a[j] and value Node containing i, j field.
// If its index of hash table already occupied, Record it and Link next node.
// Make hash table: O(N^2).

public class Interview10_01_4Sum {
	// MyTry
	public static boolean FourSum(int[] arr, int M) {
		// Suppose input data is in [-M, M].
		// -2M <= a[i] + a[j] <= 2M
		// 0 <= a[i] + a[j] + 2M <= 4M
		// Hashing a[i] + a[j] + 2M
		int len = arr.length;
		int[] table = new int[4*M + 1];
		
		// initialize O(N^2)
		for(int i = 0; i < table.length; i++)
			table[i] = -1;
		
		// O(N^2)
		for(int i = 0; i < len; i++) {
			for(int j = i + 1; j < len; j++) {
				if (table[arr[i] + arr[j] + 2*M] >= 0) // occupied?
					return true;
				table[arr[i] + arr[j] + 2*M] = arr[i] + arr[j] + 2*M;
			}
		}
		return false;
	}
	
	private class FourSum {
		private class Node {
			int val; // a[i] + a[j]
			int i;
			int j; // i < j
			Node next;
			
			Node(int i, int j) {
				// check i < j
				if (i < j) {
					this.i = i;
					this.j = j;
				} else {
					this.j = i;
					this.i = j;
				}
			}
		}
		private int[] arr;
		private Node[] hashTable;
		private int n; // arr.length;
		private int m; // hashTable.length;
		private int hash(int i) {
			return i % m;
		}
		public FourSum(int arr[]) {
			this.arr = arr.clone();
			this.n = arr.length;
			this.m = this.n;
			
			for(int i = 0; i < arr.length; i++) {
				for(int j = i + 1; j < arr.length; j++) {
					Node x = hashTable[hash(this.arr[i] + this.arr[j])];
					if (x == null) hashTable[hash(this.arr[i] + this.arr[j])] = new Node(i, j);
					else {
						for(; x.next != null; x = x.next);
						x.next = new Node(i, j);
					}		
				}
			}
		}
		
	}
	public static void main(String[] args) {
		
	}
}
