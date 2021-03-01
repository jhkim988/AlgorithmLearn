import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_12 {
	// 1.1.12
	// 아래의 코드는 어떤 값을 출력하는가
	public static void main(String[] args) {
		int[] a = new int[10];
		for(int i = 0; i < 10; i++) // 9 ~ 0
			a[i] = 9 - i;
		for(int i = 0; i < 10; i++) // a[0] = a[9], a[1] = a[8], ..., a[9] = a[0];
			a[i] = a[a[i]]; // 0 1 2 3 4 4 3 2 1 0
		for(int i = 0; i < 10; i ++)
			StdOut.println(a[i]);
	}
}
