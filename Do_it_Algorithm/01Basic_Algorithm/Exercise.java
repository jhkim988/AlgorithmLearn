import java.util.Scanner;

class Exercise {
	// Q1 네 값의 최댓값을 구하는 max4 메서드를 작성하세요.
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

	// Q2 세 값의 최솟값을 구하는 min3 메서드를 작성하세요.
	static int min3(int a, int b, int c) {
		int min = a;
		if (min > b)
			min = b;
		if (min > c)
			min = c;
		return min;
	}

	// Q3 네 값의 최솟값을 구하는 min4 메서드를 작성하세요.
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

	// Q4 세 값의 대소 관계 13종류의 모든 조합에 대해 중앙값을 구하여 출력하는 프로그램을 작성하세요.
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

	// Q5 중앙값을 구하는 메서드는 다음과 같이 작성할 수도 있습니다. 그러나 실습 1C-1의 med3 메서드에 비해 효율이 떨어지는데,
	// 그 이유를 설명하세요.
	static int med3_v2(int a, int b, int c) {
		// 처리해야 할 연산 횟수가 많다. med3의 경우 비교 연산이 최대 3번이지만,
		// med3_v2 함수는 c를 리턴하기 위해서 8회를 해야한다.
		if ((b >= a && a >= c) || (c >= a && a >= b)) {
			return a;
		} else if ((a > b && b > c) || (c > a && a > b)) {
			return b;
		} else
			return c;
	}

	// Q6 실습 1-4에서 while문이 종료될 때 변수 i값이 n + 1이 됨을 확인하세요.
	static void q6() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n의 값 : ");
		int n = stdIn.nextInt();
		stdIn.close();

		int sum = 0;
		int i = 1;
		while (i <= n) {
			sum += i++;
		}

		System.out.println("1부터 " + n + "까지의 합은 " + sum + "입니다.");
		System.out.println("i의 값은 " + i + "입니다.");
	}

	// Q7 실습 1-5 프로그램을 참고하여 n이 7이면 '1+2+3+4+5+6+7=28'로 출력하는 프로그램을 작성하세요.
	static void q7() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n의 값 : ");
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

	// Q8 가우스 덧셈을 이용하여 정수 합을 구하는 프로그램
	static void q8() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("n의 값 : ");
		int n = stdIn.nextInt();
		stdIn.close();
		System.out.println("1부터 " + n + "까지의 합은 " + n * (n + 1) / 2 + " 입니다.");
	}

	// Q9 정수 a, b를 포함하여 그 사이의 정수의 합을 구하여 반환하는 메서드를 작성
	static int sumof(int a, int b) {
		int sum = 0;
		for (int i = a; i <= b; ++i) {
			sum += i;
		}
		return sum;
	}

	// Q10 두 변수 a, b에 정수를 입력하고 b - a를 출력하는 프로그램을 작성하세요. (단 a >= b 이면 다시 입력 받도록)
	static void q10() {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("a의 값: ");
		int a = stdIn.nextInt();

		System.out.println("b의 값: ");
		int b = stdIn.nextInt();
		while (b <= a) {
			System.out.println("a보다 큰 값을 입력하세요!");
			System.out.println("b의 값: ");
			b = stdIn.nextInt();
		}
		stdIn.close();
		System.out.println("b - a는 " + (b - a) + "입니다.");
	}

	// Q11 양의 정수를 입력하고 자릿수를 출력하는 프로그램을 작성하세요.
	static void q11() {
		Scanner stdIn = new Scanner(System.in);
		int n;
		do {
			System.out.println("양의 정수를 입력하세요.");
			n = stdIn.nextInt();
		} while (n <= 0);
		stdIn.close();

		int count = 1;
		while ((n /= 10) != 0)
			count++;
		System.out.println("그 수는 " + count + "자리입니다.");
	}

	// Q12 곱셈표 작성
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

	// Q13 덧셈표 작성
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

	// Q14 정사각형 별찍기
	static void q14() {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("사각형을 출력합니다.");
		System.out.println("단 수 : ");
		int n = stdIn.nextInt();
		stdIn.close();
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	
	// Q15 직각 이등변 삼각형을 출력하는 부분을 아래와 같은 형식의 메서드로 작성하세요(왼쪽 위, 오른쪽 위, 오른쪽 아래도)
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
	
	// Q16 n단 피라미드를 출력하는 메서드 작성
	static void spira(int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n - i; ++j)
				System.out.print(' ');
			for (int j = 0; j < 2*i+1; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
	
	// Q17 아래를 향한 n단의 숫자 피라미드 출력
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