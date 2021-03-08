// 1.3.1
// FixedCapacityStackOfStrings�� isFull()�޼��带 �߰��϶�
public class Exercise01_03_01 {
	class FixedCapacityStackOfStrings{
		int capacity;
		int cursor;
		String[] arr;
		
		FixedCapacityStackOfStrings(int capacity) {
			this.capacity = capacity;
			cursor = 0;
			arr = new String[capacity];
		}
		
		void push(String str) {
			arr[cursor++] = str;
		}
		
		String pop() {
			return arr[--cursor];
		}
		
		boolean isFull() {
			return cursor == capacity;
		}
		
		boolean isEmpty() {
			return cursor == 0;
		}
	}
}
