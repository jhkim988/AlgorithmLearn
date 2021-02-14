class AryLinkedList<E>{
	// 포인터로 연결리스트 -> 노드 삽입 삭제를 데이터 이동 없이 수행,
	// 그러나 삽입 삭제를 수행할 때마다 노드용 객체를 위한 메모리 영역을 만들고 해제하는 과정이 필요했다.
	// 데이터 수가 크게 바뀌지 않고, 데이터 수의 최댓값을 미리 알 수 있다면 배열을 사용해 효율적으로 연결 리스트를 운용할 수 있다.
	
	// 배열 인덱스 0 1 2 3 4 5 6 7
	// 배열 요소값 D A E C B F - -
	// 커서      2 4 5 0 3 -1
	
	// 위의 방법을 이용하면 노드의 삽입, 삭제 시 요소를 옮길 필요가 없다.

	class Node<E> {
		private E data;
		private int next; // 리스트의 뒤쪽 포인터
		private int dnext; // free 리스트의 뒤쪽 포인터
		
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E>[] n;
	private int size; // 리스트의 용량(가장 큰 데이터 수)
	private int max; // 사용중인 꼬리 record
	private int head;
	private int crnt;
	private int deleted; // free 리스트의 머리 노드
	private static final int NULL = -1; // 다음 노드 없음 / 리스트가 가득 참

	public AryLinkedList(int capacity) {
		head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for(int i = 0; i < capacity; ++i)
				n[i] = new Node<E>();
			size = capacity;
		} catch(OutOfMemoryError e) {
			size = 0;
		}
	}
	
	// 다음에 삽입하는 record의 인덱스를 구함
	private int getInsertIndex() {
		if (deleted == NULL) {
			if (max < size)
				return max++;
			else 
				return NULL;
		} else {
			int rec = deleted;
			deleted = n[rec].dnext;
			return rec;
		}
	}
}