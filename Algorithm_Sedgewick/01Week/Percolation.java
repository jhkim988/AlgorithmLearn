import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // 1. Be careful Input data: (1, 1) ~ (n, n)
    // 2. Wrong Input -> throw IllegalArgumentException

    private final int gridSize; // n, not n*n
    private boolean[][] grid;
    private int openCount = 0;
    private final WeightedQuickUnionUF uf; // top = n*n, bottom = n*n+1
    private final WeightedQuickUnionUF uf_temp; // top = n*n
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0)
    		throw new IllegalArgumentException();
    	
        gridSize = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf_temp = new WeightedQuickUnionUF(n * n + 1); // backwash chaeck
        
        // initialize
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                grid[i][j] = false;
        
        if (n != 1) {
             // top, bottom union
            for (int i = 0; i < n; ++i) {
                uf.union(i, n * n);
                uf.union((n - 1)*n + i, n * n + 1);
                uf_temp.union(i, n * n);
            }
        }
    }
    
    private int idxChanger(int row, int col) {
        return row * gridSize + col;
    }
    private void illegalArg(int row, int col) {
    	if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
//    		 System.out.print("row : " + row + "\ncol : " + col + "\n");
    		throw new IllegalArgumentException();
        }
    }
    
    private boolean correctIdx(int row, int col) {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            return false;
        return true;
        }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) { // input: row, col - 1 ~ n
    	illegalArg(row, col);
    	
        // Already open
        if (isOpen(row, col)) return;
        // Be careful - input data 1 ~ n
        grid[row - 1][col - 1] = true; // grid receives 0 ~ n-1
        openCount++;
        if (correctIdx(row - 1, col) && isOpen(row - 1, col)) {
            uf.union(idxChanger(row - 1, col - 1), idxChanger(row - 2, col - 1));
            uf_temp.union(idxChanger(row - 1, col - 1), idxChanger(row - 2, col - 1));
        }
        if (correctIdx(row + 1, col) && isOpen(row + 1, col)) {
            uf.union(idxChanger(row - 1, col - 1), idxChanger(row, col - 1));
            uf_temp.union(idxChanger(row - 1, col - 1), idxChanger(row, col - 1));
        }
        if (correctIdx(row, col - 1) && isOpen(row, col - 1)) {
            uf.union(idxChanger(row - 1, col - 1), idxChanger(row - 1, col - 2));
            uf_temp.union(idxChanger(row - 1, col - 1), idxChanger(row - 1, col - 2));
        }
        if (correctIdx(row, col + 1) && isOpen(row, col + 1)) {
            uf.union(idxChanger(row - 1, col - 1), idxChanger(row - 1, col));
            uf_temp.union(idxChanger(row - 1, col - 1), idxChanger(row - 1, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	illegalArg(row, col);
        return grid[row - 1][col - 1];
        }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	illegalArg(row, col);
    	if (gridSize == 1) return isOpen(row, col);
        return isOpen(row, col) && uf_temp.find(gridSize * gridSize) == uf_temp.find(idxChanger(row - 1, col - 1));
        }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
        }

    // does the system percolate?
    public boolean percolates() {
    	if (gridSize == 1) return isOpen(1, 1);
        return uf.find(gridSize * gridSize) == uf.find(gridSize * gridSize + 1);
        }
}