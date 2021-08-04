import java.util.Random;
import java.util.Scanner;

class Exercise02 {
	// Q1 ��� Ű�Ӹ� �ƴ϶� ��� ���� ������ �����ϵ��� �ǽ� 2-5�� �����Ͽ� ���α׷��� �ۼ��ϼ���.
	static void q1() {
		Random rand = new Random();

		System.out.println("Ű�� �ִ��� ���մϴ�.");
		int num = rand.nextInt(10) + 5;
		System.out.println("��� �� : " + num);

		int[] height = new int[num];

		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1������ ������ �����ϰ� ��ȯ�Ѵ�.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("�ִ��� " + maxOf(height) + " �Դϴ�.");
	}

	static int maxOf(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i)
			if (a[i] > max)
				max = a[i];
		return max;
	}

	// Q2 �迭 ��Ҹ� �������� �����ϴ� ������ �ϳ��ϳ� ��Ÿ���� ���α׷��� �ۼ��ϼ���.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	static void reverse_print(int[] a) {
		for (int i = 0; i < a.length / 2; ++i) {
			System.out.println("a[" + i + "] �� a[" + (a.length - i - 1) + "]�� ��ȯ�մϴ�.");
			swap(a, i, a.length - 1 - i);

			for (int j = 0; j < a.length; ++j)
				System.out.print(a[j] + " ");

			System.out.println();
		}

	}

	static void q2() {
		Scanner stdIn = new Scanner(System.in);
		int num = stdIn.nextInt();
		int[] arr = new int[num];
		for (int i = 0; i < num; ++i)
			arr[i] = stdIn.nextInt();
		stdIn.close();
		reverse_print(arr);
		System.out.println("���� ������ ���ƽ��ϴ�.");
	}

	// Q3 �迭 a�� ��� ����� �հ踦 ���Ͽ� ��ȯ�ϴ� �޼��带 �ۼ��ϼ���.
	static int sumOf(int[] a) {
		int sum = a[0];
		for (int i = 1; i < a.length; ++i)
			sum += a[i];
		return sum;
	}

	// Q4 �迭 b�� ��� ��Ҹ� �迭 a�� �����ϴ� �޼��� copy�� �ۼ��ϼ���.
	static void copy(int[] a, int[] b) {
		a = new int[b.length];
		for (int i = 0; i < b.length; ++i)
			a[i] = b[i];
	}

	// Q5 �迭 b�� ��� ��Ҹ� �迭 a�� �������� �����ϴ� �޼��� rcopy�� �ۼ��ϼ���.
	static void rcopy(int[] a, int[] b) {
		a = new int[b.length];
		for (int i = 0; i < b.length; ++i)
			a[i] = b[b.length - 1 - i];
	}
	
	// Q6 �迭�� ���ʿ� �Ʒ��ڸ��� �ƴ϶� ���ڸ��� �־�δ� �޼��带 �ۼ��ϼ���
	static int cardConv(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		do {
			d[digits++] = dchar.charAt(x%r);
		} while ((x /= r) > 0);
		
		// d[0] ~ d[digits - 1]���� �������� ���ش�.
		for (int i = 0; i < digits/2; ++i) {
			char temp = d[i];
			d[i] = d[digits - 1 - i];
			d[digits - 1 - i] = temp;
		}
		
		return digits;
	}
	
	// Q7 ��� ��ȯ ������ �ڼ��� ��Ÿ���� ���α׷��� �ۼ��ϼ���
	static void cardConvPrint() {
		int x;
		int r;
		
		Scanner stdIn = new Scanner(System.in);
		
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] d = new char[36];
		
		System.out.println("10������ ��� ��ȯ�մϴ�.");

		do {
			System.out.print("��ȯ�ϴ� ���� �ƴ� ���� : ");
			x = stdIn.nextInt(); 
		} while (x < 0);
		
		do {
			System.out.print("� ������ ��ȯ�ұ��?(2-36) : ");
			r = stdIn.nextInt();
		} while (r < 2 || r > 36);
		stdIn.close();
		
		System.out.printf(" %d | %5d\n", r, x);
		System.out.println("   +---------");
		
		do {
			System.out.printf(" %d | %5d   ������ %c\n", r, (x / r), dchar.charAt(x % r));
			System.out.println("   +---------");
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		} while (x > r);
		System.out.printf("     %5d   ������ %c\n", (x / r), dchar.charAt(x % r));
		d[digits++] = dchar.charAt(x % r);
		
		System.out.print("\n2������ ");
		for (int i = 0; i < digits; ++i)
			System.out.print(d[digits - 1 - i]);
		System.out.println("�Դϴ�.");
		
	}
	
	// Q8 �޼��� dayOfYear�� ���� i�� days ���� while���� ����Ͽ� ����.
	static int dayOfYear_v2(int y, int m, int d) {
		int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int isLeap = (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0) ? 1 : 0;
		
		while (--m != 0) {
			d += mdays[isLeap][m];
		}
		return d;
	}
	
	// Q9 y�� m�� d���� �� �� ���� �� ���� ���ϴ� �Ʒ� �޼��带 �ۼ��ϼ���.
	static int leftDayOfYear(int y, int m, int d) {
		int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int isLeap = (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0) ? 1 : 0;
		
		int days = mdays[isLeap][m - 1] - d;
		for (int i = 11; i > m - 1; --i) {
			days += mdays[isLeap][i];
		}
		
		return days;
	}
	
	public static void main(String[] args) {
//		q1();
//		q2();
//		int[] a = {1, 2, 3, 4, 7};
//		System.out.println(sumOf(a));
//		cardConvPrint();
		
	}
}