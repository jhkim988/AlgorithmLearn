import edu.princeton.cs.algs4.StdRandom;

//Q1.
//Queue with two stacks.
//Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

//Use two stacks: Enqueue stack and Dequeue stack.
//IsEmpty : It equals to Enqueue stack's.
//Enqueue : push in enqueue stack. ~ O(1)
//Dequeue : pop at dequeue stack. if Dequeue stack is empty, copy Enqueue stack.
//Therefore, Best case : ~ 1 Worst case: ~ N(the number of Enqueue stack's elements) Amortized: ~ 2

public class Interview03_01_Queue_2Stack<Item> {
	private LinkedStack<Item> stkEnq = new LinkedStack<Item>();
	private LinkedStack<Item> stkDeq = new LinkedStack<Item>();
	private int count = 0; // stack operator count

	public boolean isEmpty() {
		return stkEnq.isEmpty();
	}

	private void move() {
		while (!stkEnq.isEmpty()) { // 2 * size of stkEnq
			stkDeq.push(stkEnq.pop());
			count += 2;
		}
	}

	public void enqueue(Item item) {
		stkEnq.push(item);
		count++;
	}

	public Item dequeue() {
		if (stkDeq.isEmpty())
			move();
		count++;
		return stkDeq.pop();
	}

	public static int test(int trials) {
		Interview03_01_Queue_2Stack<Integer> que = new Interview03_01_Queue_2Stack<Integer>();
		for (int i = 0; i < trials; i++) {
			if (StdRandom.bernoulli() && !que.isEmpty()) { // dequeue
				que.dequeue();
			} else { // enqueue
				que.enqueue(1);
			}
		}
		return que.count;
	}

	public static void main(String[] args) {
		int trials = 100;
		int count = test(trials);
		System.out.println("trials : " + trials + " \tcount : " + count);
	}
}
