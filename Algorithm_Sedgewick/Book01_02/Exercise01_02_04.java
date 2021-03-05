import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_04 {
	// 1.2.4
	// 아래의 코드는 어떤 값을 출력하는가?
	public static void main(String[] args) {
		// java에서는 string을 대입할 때 참조값을 복사해주지 않고, string 자체를 새로 만든다.
		// new String();으로 만들면 참조를 복사한다.
		String string1 = "hello";
		String string2 = string1;
		string1 = "world";
		
		StdOut.println(string1); // world
		StdOut.println(string2); // hello
		
		// 배열과 비교
		int[] arr1 = {1, 2, 3};
		int[] arr2 = arr1;
		for(int i = 0; i < 3; i++) {
			arr1[i] = i + 4;
		}
		StdOut.println(Arrays.toString(arr1));
		StdOut.println(Arrays.toString(arr2));
	}
}
