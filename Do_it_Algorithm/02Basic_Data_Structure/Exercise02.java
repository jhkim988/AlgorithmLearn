import java.util.Random;
import java.util.Scanner;

class Exercise02 {
	// Q1 사람 키뿐만 아니라 사람 수도 난수로 생성하도록 실습 2-5를 수정하여 프로그램을 작성하세요.
	static void q1() {
		Random rand = new Random();

		System.out.println("키의 최댓값을 구합니다.");
		int num = rand.nextInt(10) + 5;
		System.out.println("사람 수 : " + num);

		int[] height = new int[num];

		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1까지의 정수를 랜덤하게 반환한다.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("최댓값은 " + maxOf(height) + " 입니다.");
	}

	static int maxOf(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i)
			if (a[i] > max)
				max = a[i];
		return max;
	}

	// Q2 배열 요소를 역순으로 정렬하는 과정을 하나하나 나타내는 프로그램을 작성하세요.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	static void reverse_print(int[] a) {
		for (int i = 0; i < a.length / 2; ++i) {
			System.out.println("a[" + i + "] 와 a[" + (a.length - i - 1) + "]을 교환합니다.");
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
		System.out.println("역순 정렬을 마쳤습니다.");
	}

	// Q3 배열 a의 모든 요소의 합계를 구하여 반환하는 메서드를 작성하세요.
	static int sumOf(int[] a) {
		int sum = a[0];
		for (int i = 1; i < a.length; ++i)
			sum += a[i];
		return sum;
	}

	// Q4 배열 b의 모든 요소를 배열 a에 복사하는 메서드 copy를 작성하세요.
	static void copy(int[] a, int[] b) {
		a = new int[b.length];
		for (int i = 0; i < b.length; ++i)
			a[i] = b[i];
	}

	// Q5 배열 b의 모든 요소를 배열 a에 역순으로 복사하는 메서드 rcopy를 작성하세요.
	static void rcopy(int[] a, int[] b) {
		a = new int[b.length];
		for (int i = 0; i < b.length; ++i)
			a[i] = b[b.length - 1 - i];
	}
	
	// Q6 배열의 앞쪽에 아랫자리가 아니라 윗자리를 넣어두는 메서드를 작성하세요
	static int cardConv(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		do {
			d[digits++] = dchar.charAt(x%r);
		} while ((x /= r) > 0);
		
		// d[0] ~ d[digits - 1]까지 역순정렬 해준다.
		for (int i = 0; i < digits/2; ++i) {
			char temp = d[i];
			d[i] = d[digits - 1 - i];
			d[digits - 1 - i] = temp;
		}
		
		return digits;
	}
	
	// Q7 기수 변환 과정을 자세히 나타내는 프로그램을 작성하세요
	static void cardConvPrint() {
		int x;
		int r;
		
		Scanner stdIn = new Scanner(System.in);
		
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] d = new char[36];
		
		System.out.println("10진수를 기수 변환합니다.");

		do {
			System.out.print("변환하는 음이 아닌 정수 : ");
			x = stdIn.nextInt(); 
		} while (x < 0);
		
		do {
			System.out.print("어떤 진수로 변환할까요?(2-36) : ");
			r = stdIn.nextInt();
		} while (r < 2 || r > 36);
		stdIn.close();
		
		System.out.printf(" %d | %5d\n", r, x);
		System.out.println("   +---------");
		
		do {
			System.out.printf(" %d | %5d   ··· %c\n", r, (x / r), dchar.charAt(x % r));
			System.out.println("   +---------");
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		} while (x > r);
		System.out.printf("     %5d   ··· %c\n", (x / r), dchar.charAt(x % r));
		d[digits++] = dchar.charAt(x % r);
		
		System.out.print("\n2진수로 ");
		for (int i = 0; i < digits; ++i)
			System.out.print(d[digits - 1 - i]);
		System.out.println("입니다.");
		
	}
	
	// Q8 메서드 dayOfYear를 변수 i와 days 없이 while문을 사용하여 구현.
	static int dayOfYear_v2(int y, int m, int d) {
		int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int isLeap = (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0) ? 1 : 0;
		
		while (--m != 0) {
			d += mdays[isLeap][m];
		}
		return d;
	}
	
	// Q9 y년 m월 d일의 그 해 남은 일 수를 구하는 아래 메서드를 작성하세요.
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