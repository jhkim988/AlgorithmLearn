import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_04 {
	// 1.2.4
	// �Ʒ��� �ڵ�� � ���� ����ϴ°�?
	public static void main(String[] args) {
		// java������ string�� ������ �� �������� ���������� �ʰ�, string ��ü�� ���� �����.
		// new String();���� ����� ������ �����Ѵ�.
		String string1 = "hello";
		String string2 = string1;
		string1 = "world";
		
		StdOut.println(string1); // world
		StdOut.println(string2); // hello
		
		// �迭�� ��
		int[] arr1 = {1, 2, 3};
		int[] arr2 = arr1;
		for(int i = 0; i < 3; i++) {
			arr1[i] = i + 4;
		}
		StdOut.println(Arrays.toString(arr1));
		StdOut.println(Arrays.toString(arr2));
	}
}
