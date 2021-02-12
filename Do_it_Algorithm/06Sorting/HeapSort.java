import java.util.Scanner;

class HeapSort {
	// 힙이란?
	// 부모 자식간의 대소관계가 일정한 완전이진트리
	// 완전? - 자식을 추가할 때 왼쪽부터 추가하는 트리
	// 이진? - 자식의 최대 개수가 2인 트리

	// 힙을 배열에 저장하는 방법
	// 루트(맨 위)를 a[0]에 넣고 한 단계 아래 요소를 왼쪽에서 오른쪽으로 따라간다.
	// 인덱스의 값을 1씩 늘리면서 배열의 각 요소에 힙의 요소를 대입한다.

	// 부모는 a[(i - 1) / 2]
	// 왼쪽 자식은 a[2 * i + 1]
	// 오른쪽 자식은 a[2 * i + 2]

	// 힙 정렬은 "가장 큰 값이 루트에 위치"하는 특징을 이용한 정렬 알고리즘
	// 힙에서 가장 큰 값을 꺼낸 후에 나머지 요소를 다시 힙으로 만드는 과정을 반복하여 정렬한다.
	// 가장 큰 요소를 꺼내는 것의 시간복잡도는 O(1)
	// 다시 힙으로 만드는 것의 시간복잡도는 O(logn)
	// 따라서 힙 정렬의 시간복잡도는 O(nlogn)이다.

	// 루트를 없애고 힙 상태 유지하기
	// 1. 루트를 없앤다.
	// 2. 힙의 가장 마지막 요소(오른쪽 아래 끝에 있는 자식요소)를 루트로 옮긴다.
	// 3. 두 자식 중 큰 쪽과 swap 하는 것을 반복한다.
	// 4. 자식의 값이 작거나, 잎에 도달하면 작업이 종료된다.

	// 초기 상태의 배열이 힙이 아니라면 힙 상태로 만들어야한다.
	// 루트를 내려서 힙으로 만드는 과정은 부분 트리에서도 적용된다. 따라서 아래에서부터 위로 올라가면서 힙으로 만들 수 있다.

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
		
		for(int i = n - 1; i > 0; --i) {
			swap(a, 0, i);
			downHeap(a, 0, i - 1);
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Heap 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		heapSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}