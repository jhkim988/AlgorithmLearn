
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
		if(max < data) {
			stkMax.push(max);
			stkMax.push(data);
		} else
			stkMax.push(max);
		stkMain.push(data);
	}
	public double pop() {
		double pop = stkMain.pop();
		if (max() == pop) {
			stkMax.pop();
		}
		return pop;
	}
	public void dump() {
		System.out.println("Max : " + max());
		stkMain.dump();
	}
	
	public static void main(String[] args) {
		Interview03_02_Stack_Max stk = new Interview03_02_Stack_Max();
		System.out.println("Start");
		stk.push(1.1);
		stk.dump();
		stk.push(2.1);
		stk.dump();
		stk.push(3.1);
		stk.dump();
		stk.push(4.1);
		stk.dump();
	}
}
