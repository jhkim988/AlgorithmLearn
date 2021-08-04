import java.util.Scanner;

class Exercise {
	// Q1 �� ���� �ִ��� ���ϴ� max4 �޼��带 �ۼ��ϼ���.
	static int max4(int a, int b, int c, int d) {
		int max = a;
		if (max < b)
			max = b;
		if (max < c)
			max = c;
		if (max < d)
			max = d;
		return max;
	}

	// Q2 �� ���� �ּڰ��� ���ϴ� min3 �޼��带 �ۼ��ϼ���.
	static int min3(int a, int b, int c) {
		int min = a;
		if (min > b)
			min = b;
		if (min > c)
			min = c;
		return min;
	}

	// Q3 �� ���� �ּڰ��� ���ϴ� min4 �޼��带 �ۼ��ϼ���.
	static int min4(int a, int b, int c, int d) {
		int min = a;
		if (min > b)
			min = b;
		if (min > c)
			min = c;
		if (min > d)
			min = d;
		return min;
	}

	// Q4 �� ���� ��� ���� 13������ ��� ���տ� ���� �߾Ӱ��� ���Ͽ� ����ϴ� ���α׷��� �ۼ��ϼ���.
	static int med3(int a, int b, int c) {
		if (a >= b) {
			if (b >= c) { // a >= b >= c
				return b;
			} else if (a <= c) { // a >= b, c > b
				return a;
			} else
				return c;
		} else if (a > c) // b > a
			return a;
		else if (b > c) // b > a, c >= a
			return c;
		else // c >= b > a
			return b;
	}

	static void q4() {
		System.out.println("med3(3, 2, 1) = " + med3(3, 2, 1)); // a > b > c
		System.out.println("med3(3, 2, 2) = " + med3(3, 2, 2)); // a > b = c
		System.out.println("med3(3, 1, 2) = " + med3(3, 1, 2)); // a > c > b
		System.out.println("med3(3, 2, 3) = " + med3(3, 2, 3)); // a = c > b
		System.out.println("med3(2, 1, 3) = " + med3(2, 1, 3)); // c > a > b
		System.out.println("med3(2, 2, 3) = " + med3(2, 2, 3)); // c > a = b
		System.out.println("med3(1, 2, 3) = " + med3(1, 2, 3)); // c > b > a
		System.out.println("med3(2, 3, 3) = " + med3(2, 3, 3)); // c = b > a
		System.out.println("med3(2, 3, 1) = " + med3(2, 3, 1)); // b > a > c
		System.out.println("med3(2, 3, 2) = " + med3(2, 3, 2)); // b > a = c
		System.out.println("med3(1, 3, 2) = " + med3(1, 3, 2)); // b > c > a
		System.out.println("med3(3, 3, 1) = " + med3(3, 3, 1)); // b = a > c
		System.out.println("med3(3, 3, 3) = " + med3(3, 3, 3)); // a = b = c
	}

	// Q5 �߾Ӱ��� ���ϴ� �޼���� ������ ���� �ۼ��� ���� �ֽ��ϴ�. �׷��� �ǽ� 1C-1�� med3 �޼��忡 ���� ȿ���� �������µ�,
	// �� ������ �����ϼ���.
	static int med3_v2(int a, int b, int c) {
		// ó���ؾ� �� ���� Ƚ���� ����. med3�� ��� �� ������ �ִ� 3��������,
		// med3_v2 �Լ��� c�� �����ϱ� ���ؼ� 8ȸ�� �ؾ��Ѵ�.
		if ((b >= a && a >= c) || (c >= a && a >= b)) {
			return a;
		} else if ((a > b && b > c) || (c > a && a > b)) {
			return b;
		} else
			return c;
	}

	// Q6 �ǽ� 1-4���� while���� ����� �� ���� i���� n + 1�� ���� Ȯ���ϼ���.
	static void q6() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();

		int sum = 0;
		int i = 1;
		while (i <= n) {
			sum += i++;
		}

		System.out.println("1���� " + n + "������ ���� " + sum + "�Դϴ�.");
		System.out.println("i�� ���� " + i + "�Դϴ�.");
	}

	// Q7 �ǽ� 1-5 ���α׷��� �����Ͽ� n�� 7�̸� '1+2+3+4+5+6+7=28'�� ����ϴ� ���α׷��� �ۼ��ϼ���.
	static void q7() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();

		int sum = 0;
		String str = "";
		for (int i = 1; i <= n; ++i) {
			if (i == n)
				str += i + " = ";
			else
				str += i + " + ";
			sum += i;
		}
		System.out.println(str + sum);
	}

	// Q8 ���콺 ������ �̿��Ͽ� ���� ���� ���ϴ� ���α׷�
	static void q8() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();
		System.out.println("1���� " + n + "������ ���� " + n * (n + 1) / 2 + " �Դϴ�.");
	}

	// Q9 ���� a, b�� �����Ͽ� �� ������ ������ ���� ���Ͽ� ��ȯ�ϴ� �޼��带 �ۼ�
	static int sumof(int a, int b) {
		int sum = 0;
		for (int i = a; i <= b; ++i) {
			sum += i;
		}
		return sum;
	}

	// Q10 �� ���� a, b�� ������ �Է��ϰ� b - a�� ����ϴ� ���α׷��� �ۼ��ϼ���. (�� a >= b �̸� �ٽ� �Է� �޵���)
	static void q10() {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("a�� ��: ");
		int a = stdIn.nextInt();

		System.out.println("b�� ��: ");
		int b = stdIn.nextInt();
		while (b <= a) {
			System.out.println("a���� ū ���� �Է��ϼ���!");
			System.out.println("b�� ��: ");
			b = stdIn.nextInt();
		}
		stdIn.close();
		System.out.println("b - a�� " + (b - a) + "�Դϴ�.");
	}

	// Q11 ���� ������ �Է��ϰ� �ڸ����� ����ϴ� ���α׷��� �ۼ��ϼ���.
	static void q11() {
		Scanner stdIn = new Scanner(System.in);
		int n;
		do {
			System.out.println("���� ������ �Է��ϼ���.");
			n = stdIn.nextInt();
		} while (n <= 0);
		stdIn.close();

		int count = 1;
		while ((n /= 10) != 0)
			count++;
		System.out.println("�� ���� " + count + "�ڸ��Դϴ�.");
	}

	// Q12 ����ǥ �ۼ�
	static void q12() {
		System.out.println("   |  1  2  3  4  5  6  7  8  9");
		System.out.println("---+-----------------------------");
		for (int i = 1; i < 10; ++i) {
			System.out.printf("%3d|", i);
			for (int j = 1; j < 10; ++j) {
				System.out.printf("%3d", i * j);
			}
			System.out.println();
		}
	}

	// Q13 ����ǥ �ۼ�
	static void q13() {
		System.out.println("   |  1  2  3  4  5  6  7  8  9");
		System.out.println("---+-----------------------------");
		for (int i = 1; i < 10; ++i) {
			System.out.printf("%3d|", i);
			for (int j = 1; j < 10; ++j) {
				System.out.printf("%3d", i + j);
			}
			System.out.println();
		}
	}

	// Q14 ���簢�� �����
	static void q14() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("�簢���� ����մϴ�.");
		System.out.println("�� �� : ");
		int n = stdIn.nextInt();
		stdIn.close();
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	
	// Q15 ���� �̵ �ﰢ���� ����ϴ� �κ��� �Ʒ��� ���� ������ �޼���� �ۼ��ϼ���(���� ��, ������ ��, ������ �Ʒ���)
	static void triangleLB(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <=i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	static void triangleLU(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n - i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	static void triangleRU(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j)
				System.out.print(' ');
			for (int j = 0; j < n - i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	static void triangleRB(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n - i - 1; ++j)
				System.out.print(' ');
			for (int j = 0; j <= i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	
	// Q16 n�� �Ƕ�̵带 ����ϴ� �޼��� �ۼ�
	static void spira(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n - i; ++j)
				System.out.print(' ');
			for (int j = 0; j < 2*i+1; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	
	// Q17 �Ʒ��� ���� n���� ���� �Ƕ�̵� ���
	static void npira(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n - i; ++j)
				System.out.print(' ');
			for (int j = 0; j < 2*i+1; ++j)
				System.out.print(i + 1);
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// Q1 ~ Q3
//		System.out.println("max4(23, 569, 8, 156) = " + max4(23, 569, 8, 156));
//		System.out.println("min3(23, 569, 8) = " + min3(23, 569, 8));
//		System.out.println("min4(23, 569, 8, 156) = " + min4(23, 569, 8, 156));

//		q4();
//		q6();
//		q7();
//		q8();
//		System.out.println(sumof(5, 10));
//		q10();
//		q11();
//		q12();
//		q13();
//		q14();
		
		// Q15
//		triangleLB(5);
//		triangleLU(5);
//		triangleRU(5);
//		triangleRB(5);
		
		// Q16
//		spira(4);
		
		// Q17
//		npira(4);
	}
}