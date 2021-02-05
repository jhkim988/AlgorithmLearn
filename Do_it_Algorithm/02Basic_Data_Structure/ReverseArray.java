import java.util.Scanner;

class ReverseArray{
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	static void reverse(int[] a) {
		for (int i = 0; i < a.length/2; ++i)
			swap(a, i, a.length - 1 - i);
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int num = stdIn.nextInt();
		
		int[] x = new int[num];
		
		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		stdIn.close();
		
		reverse(x);
		
		System.out.println("요소를 역순으로 정렬했습니다.");
		for (int i = 0; i < num; ++i) {
			System.out.println("x[" + i + "] = " + x[i]);
		}
	}
}