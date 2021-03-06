import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

class Accumulator {
	private double m;
	private double s;
	private int N;

	Accumulator() {
		m = 0;
		s = 0;
		N = 0;
	}

	void addDataValue(double x) { // 나누기를 여러 번 하기 때문에 반올림 오차가 크다.
		N++;
		s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
		m = m + (x - m) / N;
	}

	double mean() {
		return m;
	}

	double var() {
		return s / (N - 1);
	}

	double stddev() {
		return Math.sqrt(this.var());
	}
}

public class Exercise01_02_18 {
	// 1.2.18 Accumulator의 분산(Variance)
	// 아래에 구현된 분산 var(), 표준편차 stddev() 메서드가 올바른 값을 계산해 내는지 검증하라
	// 테스트용 데이터들은 addDataValue()메서드에서 정의하고 있다.

	// It is SAMPLE VARIANCE!
	public static void main(String[] args) {
		double data[] = { 1.0, 2.0, 1.1, 2.1, 3.5, 7.5 };
		Accumulator acc = new Accumulator();
		for(double d : data)
			acc.addDataValue(d);

		StdOut.println(acc.mean() + " / " + StdStats.mean(data));
		StdOut.println(acc.var()+ " / " + StdStats.var(data));
		StdOut.println(acc.stddev()+ " / " + StdStats.stddev(data));

		
		double sum = 0.0;
		for(double d: data)
			sum += d;
		double mean = sum / data.length;
		sum = 0;
		for(double d: data)
			sum += (d - mean)*(d - mean);
		double var = sum/data.length;
		
		StdOut.println("mean : " + mean + " \tvar : " + var);
	
	}
}
