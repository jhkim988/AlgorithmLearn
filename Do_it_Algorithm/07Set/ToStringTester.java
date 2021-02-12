class A {
	
}

class B {
	int x;
	B(int x) {
		this.x = x;
	}
	
	public String toString() { // toString()을 오버라이딩
		return "B[" + x + "]";
	}
}

class ToStringTester{
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new A();
		B b1 = new B(18);
		B b2 = new B(55);
		
		// toString 메서드를 오버라이딩 하지 않으면 Object class의 toString 메서드를 상속받아 사용한다.
		// Object class의 toString 메서드는 class@hash로 이루어져 있다.
		// 해시값이란 java가 인스턴스에 대해 임의로ㅗ 부여한 값이다.(고유값)
		
		// 문자열을 출력하는 함수에 클래스형 변수를 넣으면 자동으로 toString 메서드를 호출한다.
		System.out.println("a1 = " + a1.toString());
		System.out.println("a2 = " + a2);
		System.out.println("b1 = " + b1.toString());
		System.out.println("b2 = " + b2);
	}
}