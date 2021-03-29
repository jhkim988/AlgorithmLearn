import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // 1. Be careful Input data: (1, 1) ~ (n, n)
    // 2. Wrong Input -> throw IllegalArgumentException

    private final int gridSize; // n, not n*n
    private byte[][] grid; // 3 bit - (open-close) (connected top) (connected bottom)
    private int openCount = 0;
    private final WeightedQuickUnionUF uf; // top = n*n, bottom = n*n+1
    private boolean isPercolated = false;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
    	
        gridSize = n;
        grid = new byte[n][n];
        uf = new WeightedQuickUnionUF(n * n);
        
        // initialize - closed
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                grid[i][j] = 0b000;
        // connected with top, connected with bottom
    	for (int i = 0; i < n; ++i) {
    		grid[0][i] = 0b010;
    		grid[n - 1][i] = 0b001;
    	}
    }
    
    private int idxChanger(int row, int col) {
        return (row - 1) * gridSize + (col - 1);
    }
    
    private void illegalArg(int row, int col) {
    	if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
            // System.out.print("row : " + row + "\ncol : " + col + "\n");
            throw new IllegalArgumentException();
        }
    }
    
    private byte ptrGrid(int ptr) {
    	return grid[ptr / gridSize][ptr % gridSize];
    }
    
    private void neighborhoodOpen(int row, int col, int sum1, int sum2) {
        if (correctIdx(row + sum1, col + sum2) && isOpen(row + sum1, col + sum2)) {
        	int root = uf.find(idxChanger(row, col));
        	int gridRoot = ptrGrid(root);
        	int rootSum = uf.find(idxChanger(row + sum1, col + sum2));
        	int gridRootSum = ptrGrid(rootSum);
        	uf.union(idxChanger(row, col), idxChanger(row + sum1, col + sum2));
        	int rootUnion = uf.find(idxChanger(row, col));
            grid[rootUnion / gridSize][rootUnion % gridSize] = (byte) (gridRoot | gridRootSum);
            
        }
    }
    
    private boolean correctIdx(int row, int col) { // input data : 1 ~ n
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            return false;
        return true;
        }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) { // input: row, col - 1 ~ n
    	illegalArg(row, col);
        // Already open
        if (isOpen(row, col)) return;
        // Be careful - input data 1 ~ n, grid receives 0 ~ n-1
        grid[row - 1][col - 1] += 0b100; // not opened.. 0XX -> 1XX
        openCount++;
       
        // union - status(grid value) of root - OR(|) operator 
        neighborhoodOpen(row, col, 1, 0);
        neighborhoodOpen(row, col, -1, 0);
        neighborhoodOpen(row, col, 0, 1);
        neighborhoodOpen(row, col, 0, -1);

        if (ptrGrid(uf.find(idxChanger(row, col))) == 0b111)
        	isPercolated = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	illegalArg(row, col);
        return (grid[row - 1][col - 1] >> 2) == 0b001;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	illegalArg(row, col);
    	if (gridSize == 1) return isOpen(row, col);
        return (ptrGrid(uf.find(idxChanger(row, col))) >> 1) == 0b011; // open and connected with top
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
    	if (gridSize == 1) return isOpen(1, 1);
        return isPercolated;
    }
}