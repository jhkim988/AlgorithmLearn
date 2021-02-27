import java.io.File;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

public class FileSorter {
	public static void main(String[] args) {
		File directory = new File(args[0]);
		File[] files = directory.listFiles();
		Insertion.sort(files);
		for(int i = 0; i < files.length; i++)
			StdOut.println(files[i].getName());
	}
	// 정렬을 위해 자료형을 비교할 때 compareTo 메서드를 호출한다.(callback)
	// 다른 언어에서는 함수 포인터, 일급함수 등으로 처리하지만, 자바에서는 interface를 이용한다.
}
