class A {
	
}

class B {
	int x;
	B(int x) {
		this.x = x;
	}
	
	public String toString() { // toString()�� �������̵�
		return "B[" + x + "]";
	}
}

class ToStringTester{
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new A();
		B b1 = new B(18);
		B b2 = new B(55);
		
		// toString �޼��带 �������̵� ���� ������ Object class�� toString �޼��带 ��ӹ޾� ����Ѵ�.
		// Object class�� toString �޼���� class@hash�� �̷���� �ִ�.
		// �ؽð��̶� java�� �ν��Ͻ��� ���� ���ǷΤ� �ο��� ���̴�.(������)
		
		// ���ڿ��� ����ϴ� �Լ��� Ŭ������ ������ ������ �ڵ����� toString �޼��带 ȣ���Ѵ�.
		System.out.println("a1 = " + a1.toString());
		System.out.println("a2 = " + a2);
		System.out.println("b1 = " + b1.toString());
		System.out.println("b2 = " + b2);
	}
}