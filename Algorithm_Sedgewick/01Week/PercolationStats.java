import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.95;
    private final int trials;
    private final double[] result;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials<= 0)
    		throw new IllegalArgumentException();
        this.trials = trials;
        result = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation percol = new Percolation(n);
            while (!percol.percolates()) {
                int ranIdx1 = (int) Math.floor(StdRandom.uniform() * n) + 1; // 1 ~ n
                int ranIdx2 = (int) Math.floor(StdRandom.uniform() * n) + 1;
                percol.open(ranIdx1, ranIdx2);
                }
            result[i] = ((double) percol.numberOfOpenSites()) / ((double) n*n);
            }
        }

// sample mean of percolation threshold
    public double mean() {
        // double sum = 0.0;
        // for (int i = 0; i < T; ++i)
        // sum += result[i];
        // return sum / T;
        return StdStats.mean(result);
        }

    // sample standard deviation of percolation threshold
    public double stddev() {
        // double sum = 0.0;
        // double mean = mean();
        // for (int i = 0; i < T; ++i)
        // sum += (mean - result[i]) * (mean - result[i]);
        // return Math.sqrt(sum / (T - 1));
        return StdStats.stddev(result);
        }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
        }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
        }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean\t\t\t= " + ps.mean());
        StdOut.println("stddev\t\t\t= " + ps.stddev());
        StdOut.println("95% confidence interval\t= [" + ps.confidenceLo()+", " + ps.confidenceHi() + "]");
        
        }
}