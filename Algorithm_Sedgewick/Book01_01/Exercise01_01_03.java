public class Exercise01_01_03 {
	// 1.1.3
	// 명령줄 인수로 3개의 정수를 받고, 그 값들이 모두 같으면 "equal" 다르면 "not equal" 문자열을 출력하는 프로그램을 작성하라
	public static void main(String[] args) {
		int input1 = Integer.parseInt(args[0]);
		int input2 = Integer.parseInt(args[1]);
		int input3 = Integer.parseInt(args[2]);
		
		if (input1 != input2) {
			System.out.println("not equal");
		}
		else if (input2 != input3) {
			System.out.println("not equal");
		} else {
			System.out.println("equal");
		}
	}
}
