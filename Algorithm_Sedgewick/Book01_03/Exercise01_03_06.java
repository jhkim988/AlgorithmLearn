// 1.3.6
// 다음의 코드는 Queue q에 대해서 어떤 작업을 수행하는가?
public class Exercise01_03_06 {
	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		Stack<String> stack = new Stack<String>();
		while(!q.isEmpty()) // q가 다 빌 때까지
			stack.push(q.dequeue()); // q에 있는 값들을 스택에 넣는다.
		while(!stack.isEmpty()) // 스택이 다 빌 때까지
			q.enqueue(stack.pop()); // 스택에 있는 값들을 모두 큐에 넣는다.
		// 스택에 넣었다 빼면 순서가 뒤집어진다.
	}
}
