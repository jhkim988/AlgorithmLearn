//Q2
//Stack with max.
//Create a data structure that efficiently supports the stack operations (push and pop)
//and also a return-the-maximum operation.
//Assume the elements are real numbers so that you can compare them.

//Use two stacks : Main stack and Max stack.
//The main stack is a stack that stores elements pushed to "stack with max".
//When the maximum value is pushed to the Main stack, it is also pushed to the Max stack.
//In other words, the history that the maximum value is updated is recorded.
//When popping an element from the main stack,
//it checks to see if the element is at its maximum value (if it is at the top of the max stack),
//and if so, pops the max stack as well.
//That way, even if the maximum value pops out and exits,
//the next largest number can be immediately known as the top value of the Max stack.

public class Interview03_02_Stack_Max {
	private LinkedStack<Double> stkMain = new LinkedStack<Double>();
	private LinkedStack<Double> stkMax = new LinkedStack<Double>();

	public boolean isEmpty() {
		return stkMain.isEmpty();
	}

	public double max() {
		double max = stkMax.pop();
		stkMax.push(max);
		return max;
	}

	public void push(Double data) {
		if (isEmpty()) {
			stkMax.push(data);
		}

		double max = stkMax.pop(); // Whenever maximum value is updated, it is pushed to stkMax
		if (max < data) {
			stkMax.push(max);
			stkMax.push(data);
		} else
			stkMax.push(max);
		stkMain.push(data);
//		dump();
	}

	public double pop() {
		double pop = stkMain.pop();
		if (max() == pop) {
			stkMax.pop();
		}
//		dump();
		return pop;
	}

	public void dump() {
		System.out.println("Max : " + max());
		stkMain.dump();
	}

	public static void main(String[] args) {
		Interview03_02_Stack_Max stk = new Interview03_02_Stack_Max();
		for (int i = 0; i < 10; ++i) {
			stk.push(1.1);
			stk.push(2.1);
			stk.push(3.1);
			stk.push(4.1);
			stk.push(5.1);
			stk.push(6.1);
			stk.push(7.1);
			stk.pop();
			stk.pop();
			stk.pop();
			stk.pop();
		}
	}
}
