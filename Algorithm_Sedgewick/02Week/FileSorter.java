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
	// ������ ���� �ڷ����� ���� �� compareTo �޼��带 ȣ���Ѵ�.(callback)
	// �ٸ� ������ �Լ� ������, �ϱ��Լ� ������ ó��������, �ڹٿ����� interface�� �̿��Ѵ�.
}
