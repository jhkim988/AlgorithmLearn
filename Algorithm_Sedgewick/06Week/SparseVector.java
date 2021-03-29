import edu.princeton.cs.algs4.LinearProbingHashST;

public class SparseVector {
	// matrix-vector multiplication
	// a[][] * x[] = n[]
//	int N = 10;
//	double[][] a = new double[N][N];
//	double[] x = new double[N];
//	double[] b = new double[N];
//	
//	// O(N^2)
//	public double[] multi() {
//		double[] b = new double[N];
//		for(int i = 0; i < N; i++) {
//			double sum = 0.0;
//			for(int j = 0; j < N; j++)
//				sum += a[i][j] * x[j];
//			b[i] = sum;
//		}
//		return b;
//	}
	
	// In many case(in many application), matrix is sparse
	// most entities of matrix is zero.
	// Sparse matrix-vector multiplication
	
	// Vector Representations(Symbol table representation)
	// [1 .36] [5 .36] [14 .18]
	// [key value]
	// all zeros exception above...
	
	private LinearProbingHashST<Integer, Double> v;
	public SparseVector() { v = new LinearProbingHashST<Integer, Double>(); }
	public void put(int i, double x) { v.put(i, x); } // a[i] = value
	public double get(int i) {
		if (!v.contains(i)) return 0.0;
		else return v.get(i);
	}
	public Iterable<Integer> indices() { return v.keys(); }
	public double dot(double[] that) {
		double sum = 0.0;
		for(int i : indices())
			sum += that[i]*this.get(i);
		return sum;
	}
	// Similarly Sparse Matrix...
	// Each row of matrix is a sparse vector
}
