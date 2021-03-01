
public class Exercise01_01_10 {
	// 1.1.10
	// 아래 코드의 잘못된 점을 찾으라.
	public static void main(String[] args) {
		int[] a = new int[10]; // new int [10]; 메모리 할당을 하지 않았다.
		for (int i = 0; i < 10; i++)
			a[i] = i * i;
	}
}
