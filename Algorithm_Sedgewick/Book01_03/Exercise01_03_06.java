// 1.3.6
// ������ �ڵ�� Queue q�� ���ؼ� � �۾��� �����ϴ°�?
public class Exercise01_03_06 {
	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		Stack<String> stack = new Stack<String>();
		while(!q.isEmpty()) // q�� �� �� ������
			stack.push(q.dequeue()); // q�� �ִ� ������ ���ÿ� �ִ´�.
		while(!stack.isEmpty()) // ������ �� �� ������
			q.enqueue(stack.pop()); // ���ÿ� �ִ� ������ ��� ť�� �ִ´�.
		// ���ÿ� �־��� ���� ������ ����������.
	}
}
