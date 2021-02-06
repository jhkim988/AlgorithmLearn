import java.util.Scanner;

class Exercise03_01{
	// Q1. seqSearchSen 메서드를 while문이 아니라 for문을 사용하여 수정
	static int seqSearchSen(int[] a, int n, int key) {
		int i;
		a[n] = key;
		for (i = 0; i < n; ++i) {
			if (a[i] == key)
				break;
		}
		return i == n ? -1 : i;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num + 1]; // 보초를 넣을 자리, 하나 더 크게 선언한다.
		
		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		};
		
		System.out.print("검색할 값 : ");
		int ky = stdIn.nextInt();
		stdIn.close();
		int idx = seqSearchSen(x, num, ky);
		
		if (idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
	}
}