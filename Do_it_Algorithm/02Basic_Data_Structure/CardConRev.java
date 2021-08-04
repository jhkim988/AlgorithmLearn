import java.util.Scanner;

class CardConRev {
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// My Try
		do {
			d[digits++] = dchar.charAt(x % r);
		} while ((x /= r) > 0);

		// Exercise Test
		// d[0] ~ d[digits - 1]까지 역순정렬 해준다.
//		for (int i = 0; i < digits/2; ++i) {
//			char temp = d[i];
//			d[i] = d[digits - 1 - i];
//			d[digits - 1 - i] = temp;
//		}
		
		return digits;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int no; // 변환하는 정수
		int cd; // 기수
		int dho; // 변환 후의 자릿수
		int retry; // 다시 한 번?
		char[] cno = new char[32]; // 변환 후 각 자리의 숫자를 넣어두는 문자의 배열

		System.out.println("10진수를 기수 변환합니다.");
		do {
			do {
				System.out.print("변환하는 음이 아닌 정수 : ");
				no = stdIn.nextInt();
			} while (no < 0);

			do {
				System.out.print("어떤 진수로 변환할까요? (2 ~ 36) : ");
				cd = stdIn.nextInt();
			} while (cd < 2 || cd > 36);
			stdIn.close();
			
			dho = cardConvR(no, cd, cno);

			System.out.print(cd + "진수로는 ");
			for (int i = dho - 1; i >= 0; --i)
//			for (int i = 0; i < dho; ++i) // Exercise Test
				System.out.print(cno[i]);
			System.out.println("입니다.");

			System.out.print("한 번 더 할까요? (1. 예 / 2. 아니오) : ");
			retry = stdIn.nextInt();
		} while (retry == 1);
	}
}