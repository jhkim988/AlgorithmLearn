class IntStack {
	int max; // 스택의 용량, stk의 요솟수와 같다.
	int ptr; // 스택에 쌓여있는 데이터 수를 나타내는 필드, 포인터
	int[] stk; // 스택의 본체

	// 실행 시 예외 : 스택이 비어있음
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}
	
	// 실행 시 예외 : 스택이 가득 참
	public class OverflowIntStackException extends RuntimeException {
		OverflowIntStackException() {
		}
	}
	
	// 생성자
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max]; // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			max = 0;
		}
	}
	
	// 스택에 x를 push
	public int push(int x) throws OverflowIntStackException {
		if (ptr >= max)
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}
	
	// 스택에서 데이터를 pop
	public int pop() throws EmptyIntStackException {
		if (ptr <= 0)
			throw new EmptyIntStackException();
		return stk[--ptr];
	}
	
	// 스택에서 데이터를 피크(꺼내지 않고 보기만 함)
	public int peek() throws EmptyIntStackException {
		if (ptr <=0)
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}
	
	// indexOf: skt에 x와 같은 값의 데이터가 포함돼 있는지, 있으면 배열의 어디에 있는지를 조사하는 메서드
	// 꼭대기 쪽에서 바닥 쪽으로 선형검색을 수행한다. 꼭대기부터 하는 이유는, 먼저 pop되는 데이터를 찾기 위해서다.
	public int indexOf(int x) {
		for (int i = ptr - 1; i >=0; --i)
			if (stk[i] == x) return i;
		return -1;
	}
	
	// clear: 스택의 모든 요소를 삭제하는 메서드
	public void clear() {
		ptr = 0;
	}
	
	// capacity: 스택의 용량을 반환하는 메서드
	public int capacity() {
		return max;
	}
	
	// size: 스택에 현재 쌓여 있는 데이터 수를 반환하는 메서드
	public int size() {
		return ptr;
	}
	
	// isEmpty: 현재 스택이 비어있는지 검사하는 메서드
	public boolean isEmpty() {
		return ptr <= 0;
	}
	
	// isFull: 현재 스택이 가득 찼는지 검사하는 메서드
	public boolean isFull() {
		return ptr >= max;
	}
	
	// dump: 스택 안에 있는 모든 데이터를 표시하는 메서드, 바닥부터 꼭대기 순으로 표시한다.
	public void dump() {
		if (ptr <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < ptr; ++i)
				System.out.print(stk[i] + " ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}