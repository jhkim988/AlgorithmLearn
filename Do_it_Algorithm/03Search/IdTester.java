class Id {
	// 클래스 메서드(=static method), 클래스 변수(=static variable)은 인스턴스에 포함되지 않는 변수이다.
	// 인스턴스의 개수와 관계없이 1개만 만들어진다.
	private static int counter = 0;
	private int id;
	
	public Id() {
		id = ++counter;
	}
	
	public int getId() {
		return id;
	}
	
	public static int getCounter() {
		return counter;
	}
}

class IdTester{
	
	public static void main(String[] args) {
		Id a = new Id();
		Id b = new Id();
		
		System.out.println("a의 Id : " + a.getId());
		System.out.println("b의 Id : " + b.getId());
		System.out.println("부여한 Id의 개수 : " + Id.getCounter());
	}
}