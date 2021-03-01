
public class Exercise01_01_05 {
	// 1.1.5
	// double 타입 변수 x, y가 wjdghkrgl 0과 1사이에 있으면 true를 출력하고 그렇지 않으면 flase를 출력하는 프로그램을 작성하라.
	public static void main(String[] args) {
		double x, y;
		x = 0;
		y = 0;
		
		if (x > 0 && x < 1 && y > 0 && y < 1)
			System.out.println("true");
		else
			System.out.println("false");
	}
}
