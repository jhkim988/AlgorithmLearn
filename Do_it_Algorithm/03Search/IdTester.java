class Id {
	// Ŭ���� �޼���(=static method), Ŭ���� ����(=static variable)�� �ν��Ͻ��� ���Ե��� �ʴ� �����̴�.
	// �ν��Ͻ��� ������ ������� 1���� ���������.
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
		
		System.out.println("a�� Id : " + a.getId());
		System.out.println("b�� Id : " + b.getId());
		System.out.println("�ο��� Id�� ���� : " + Id.getCounter());
	}
}