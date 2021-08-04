import java.util.Scanner;

class Digits{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int no;
		
		System.out.println("2자리 정수를 입력하세요.");
		
		do {
			System.out.println("입력 : ");
			no = stdIn.nextInt();
		} while (no < 10 || no > 99);
		stdIn.close();
		
		System.out.println("변수 no의 값은 " + no + "이(가) 되었습니다.");
		
		// 단축평가
		// no > 10 || no < 99 : 좌측 no > 10이 true 라면 우측 no < 99를 계산하지 않아도 true 이므로 계산하지 않는다.
		// no > 10 && no < 99 : 좌측 no > 10이 false 라면 우측 no < 99를 계산하지 않아도 false 이므로 계산하지 않는다.
	}
}