
public class Interview03_01_Queue_2Stack<Item> {
	private LinkedStack<Item> stkEnq = new LinkedStack<Item>();
	private LinkedStack<Item> stkDeq = new LinkedStack<Item>();
	
	public boolean isEmpty() {
		return stkEnq.isEmpty();
	}
	private void move() {
		while(!stkEnq.isEmpty()) { // 2 * size of stkEnq
			stkDeq.push(stkEnq.pop());
		}
	}
	public void enqueue(Item item) {
		stkEnq.push(item);
	}
	public Item dequeue() {
		if (stkDeq.isEmpty())
			move();
		return stkDeq.pop();
	}
}
