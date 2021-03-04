import java.util.Arrays;

class Matrix {
	double[][] matrix;
	int row;
	int col;

	Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.matrix = new double[row][col];
	}

	Matrix(int n) {
		this(n, n);
	}

	Matrix(double[][] doubleArr) {
		this.row = doubleArr.length;
		this.col = doubleArr[0].length;
		matrix = doubleArr;
	}

	Matrix(double[] x) {
		this.row = 1;
		this.col = x.length;
		matrix = new double[1][col];
		for (int i = 0; i < x.length; i++)
			matrix[0][i] = x[i];
	}

	static double[] vecToArray(Matrix mat) {
		if (mat.row != 1)
			throw new IllegalArgumentException();
		double[] result = new double[mat.col];
		for (int i = 0; i < result.length; i++)
			result[i] = mat.matrix[0][i];
		return result;
	}

	static double dot(Matrix vec1, Matrix vec2) {
		return dot(vecToArray(vec1), vecToArray(vec2));
	}

	static double dot(double[] x, double[] y) {
		int len = x.length;
		if (len != y.length)
			throw new IllegalArgumentException();
		double sum = 0;
		for (int i = 0; i < len; i++)
			sum += x[i] * y[i];
		return sum;
	}

	static double[][] mult(double[][] a, double[][] b) {
		int aRow = a.length;
		int aCol = a[0].length;
		int bRow = b.length;
		int bCol = b[0].length;

		if (aCol != bRow)
			throw new IllegalArgumentException();

		double[][] result = new double[aRow][bCol];
		for (int i = 0; i < aRow; i++) {
			for (int j = 0; j < bCol; j++) {
				result[i][j] = 0;
				for (int k = 0; k < aCol; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}

	static Matrix mult(Matrix A, Matrix B) {
		Matrix result = new Matrix(mult(A.matrix, B.matrix));
		return result;
	}

	static double[][] transpose(double[][] a) {
		double[][] trnsps = new double[a.length][a[0].length];
		for (int i = 0; i < a[0].length; i++)
			for (int j = 0; j < a.length; j++)
				trnsps[i][j] = a[j][i];
		return trnsps;
	}

	static Matrix transpose(Matrix mat) {
		Matrix result = new Matrix(transpose(mat.matrix));
		return result;
	}

	static double[] mult(double[][] a, double[] x) {
		return vecToArray(mult(new Matrix(a), new Matrix(x)));
	}

	static double[] mult(double[] y, double[][] a) {
		return mult(transpose(a), y);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < row; i++) {
			result += "[ ";
			for (int j = 0; j < col; j++) {
				result += matrix[i][j] + " ";
			}
			result += "]\n";
		}
		return result;
	}
}

public class Exercise01_01_33 {
	// 1.1.33 행렬 라이브러리
	// 아래의 API들로 구성되는 행렬 연산 라이브러리를 구현하라.
	// 그리고 모든 API를 테스트해 볼 수 있도록 표준입력으로부터 인수 값들을 입력받아 각 API를 호출하여 계산하는 테스트 틀라이언트도
	// 만들어보라
	public static void main(String[] args) {
		// constructor test
		Matrix mat1 = new Matrix(3, 2);
		System.out.println(mat1);

		Matrix mat2 = new Matrix(3);
		System.out.println(mat2);

		double[][] doubleArr1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 0, 1, 2 } };
		Matrix mat3 = new Matrix(doubleArr1);
		System.out.println(mat3);

		double[] singleArr1 = { 1, 2, 3, 4, 5 };
		Matrix mat4 = new Matrix(singleArr1);
		System.out.println(mat4);

		// static method
		System.out.println(Arrays.toString(Matrix.vecToArray(mat4)));
		
		double[] singleArr2 = {-1, -2, -3, -4, 5};
		Matrix mat5 = new Matrix(singleArr2);
		System.out.println(Matrix.dot(singleArr1, singleArr2));
		System.out.println(Matrix.dot(mat4, mat5));
		
		double[][] doubleArr2 = {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}};
		Matrix mat6 = new Matrix(doubleArr2);
		System.out.println(Matrix.mult(doubleArr1, doubleArr2)); // double Array... Arrays.toString() not working properly
		System.out.println(Matrix.mult(mat3, mat6));
	}
}
