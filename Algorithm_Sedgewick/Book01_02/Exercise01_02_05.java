import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_05 {
	// 1.2.5
	// 아래의 코드는 어떤 값을 출력하는가?
	public static void main(String[] args) {
		String s = "Hello World";
		s.toUpperCase();
		s.substring(6, 11);
		StdOut.println(s); // Hello World
		// toUpperCase(), substring() 같은 메서드는 인스턴스를 바꾸지 않고 변환된 string을 리턴한다.
		// String 객체는 불변속성을 지니고 있다.
	}
}
