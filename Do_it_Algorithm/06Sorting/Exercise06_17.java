import java.util.Scanner;

class Exercise06_17 {
	// Q17. downHeap 메서드가 호출될 때마다 트리를 출력하는 프로그램을 작성하세요
	static int[] leftTree(int[] a, int n) {
		// n = 1 3 4 5 6 7 8, ...
		// parent
		// child = parent * 2 + 1
		// child = parent * 2 + 2		
		return (new int[] {1, 2, 3});
	}
	static int getLcount(int[]a, int n) {
		int lcount = 0;
		if (n >= 2) lcount++;
		return 0;
	}
	static int findDepth(int n) {
		int expo = 0;
		int prod = 1;
		while (prod <= n) {
			prod *= 2;
			expo++;
		}
		return expo;
	}

	static String printSpace(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}

	static void printHeap(int a[], int n) {
		if (n == 1) {
			System.out.println(a[0]);
			return;
		}

		if (n == 2) {
			System.out.println(printSpace(2) + a[0]);
			System.out.println(printSpace(1) + "/" + printSpace(4));
			System.out.println(a[1] + printSpace(4));
		}

		if (n == 3) {
			System.out.println(printSpace(2) + a[0]);
			System.out.println(printSpace(1) + "/" + printSpace(2) + "\\");
			System.out.println(a[1] + printSpace(4) + a[2]);
		}
		// 2^k <= n < 2^(k+1)을 만족하는 k.
		// (k + 1)층
		
	}

	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// a[left] ~ a[right]를 힙으로 만듭니다.
	// a[left] 이외에는 모두 힙이라고 가정하고 a[left]를 알맞은 위치로 옮겨 힙 상태로 만든다.
	static void downHeap(int[] a, int left, int right) {
		int temp = a[left]; // root
		int child; // 큰 값을 가진 노드
		int parent; // 부모 노드

		// right의 부모 = (right - 1) / 2
		// right의 부모 + 1 = (right + 1) / 2
		// parent < (right + 1) / 2: right의 부모(포함)까지 loop...
		for (parent = left; parent < (right + 1) / 2; parent = child) {
			int cl = parent * 2 + 1; // 왼쪽 자식
			int cr = cl + 1; // 오른쪽 자식
			child = (cr <= right && a[cr] > a[cl]) ? cr : cl; // 둘 중 큰 값을 가진 자식
			// cr <= right가 있는 이유 - 자식이 하나인 경우라면 right가 cl이 되고 cl + 1이 right보다 커질 수 있다.
			if (temp >= a[child])
				break;
			a[parent] = a[child]; // 자식이 더 크다면...
		}
		a[parent] = temp;
	}

	static void heapSort(int[] a, int n) {
		for (int i = (n - 1) / 2; i >= 0; --i) // i = (n - 1) / 2부터 하는 이유? 잎의 바로 윗단계부터 힙으로 만들면 되기 때문
			downHeap(a, i, n - 1); // a[i] ~ a[n - 1]을 힙으로 만들기

		for (int i = n - 1; i > 0; --i) {
			swap(a, 0, i);
			downHeap(a, 0, i - 1);
		}
	}

	public static void main(String[] args) {
//		Scanner stdIn = new Scanner(System.in);
//
//		System.out.println("Heap 정렬");
//		System.out.print("요솟수 : ");
//		int nx = stdIn.nextInt();
//		int[] x = new int[nx];
//
//		for (int i = 0; i < nx; ++i) {
//			System.out.print("x[" + i + "] : ");
//			x[i] = stdIn.nextInt();
//		}
//
//		stdIn.close();
//
//		heapSort(x, nx);
//
//		System.out.println("오름차순으로 정렬했습니다.");
//		for (int i = 0; i < nx; ++i)
//			System.out.println("x[" + i + "] = " + x[i]);

	}
}