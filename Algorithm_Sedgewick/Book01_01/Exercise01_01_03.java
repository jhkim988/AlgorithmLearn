public class Exercise01_01_03 {
	// 1.1.3
	// ����� �μ��� 3���� ������ �ް�, �� ������ ��� ������ "equal" �ٸ��� "not equal" ���ڿ��� ����ϴ� ���α׷��� �ۼ��϶�
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
