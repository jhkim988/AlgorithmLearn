import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;

public class Board {
	private final int n;
	private final int[][] tiles;
	
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	int row = tiles.length;
    	int col = tiles[0].length;
    	this.tiles = new int[row][col];
    	for(int i = 0; i < row; i++) {
    		for(int j = 0; j < col; j++) {
    			this.tiles[i][j] = tiles[i][j];
    		}
    	}
    	n = row;
    }
                                           
    // string representation of this board
    public String toString() {
    	String result = n + "\n";
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n ; j++) {
    			result += tiles[i][j] + " ";
    		}
    		result += "\n";
    	}
    	return result;
    }

    // board dimension n
    public int dimension() {
    	return n;
    }

    // number of tiles out of place
    public int hamming() {
    	int result = 0;
    	for(int i = 0; i < n*n - 1; i++) {
    		if (tiles[i/n][i%n] != (i + 1))
    			result++;
    	}
    	return result;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int result = 0;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			int tmp = tiles[i][j];
    			if (tmp == 0)
    				continue;
    			result += Math.abs((tmp-1)/n - i) + Math.abs((tmp-1)%n - j);
    		}
    	}
    	return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	if (y == null) return false;
    	if (y.getClass() != this.getClass()) return false;
    	Board tmp = (Board) y;
    	if(dimension() != tmp.tiles.length) return false;
    	if(dimension() != tmp.tiles[0].length) return false;
    	for(int i = 0; i < n*n; i++) {
    		if (tiles[i/n][i%n] != tmp.tiles[i/n][i%n])
    			return false;
    	}
    	return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
    	ArrayList<Board> iter = new ArrayList<Board>();
		int zeroIdxRow = -1;
		int zeroIdxCol = -1;
		boolean flag = false;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (tiles[i][j] == 0) {
					flag = true;
					zeroIdxRow = i;
					zeroIdxCol = j;
					break;
				}
			}
			if (flag)
				break;
		}
		if (zeroIdxCol - 1 >= 0) {
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow, zeroIdxCol - 1));
		}
		if (zeroIdxCol + 1 <= n - 1){
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow, zeroIdxCol + 1));
		}
		if (zeroIdxRow - 1 >= 0) {
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow - 1, zeroIdxCol));
		}
		if (zeroIdxRow + 1 <= n - 1){
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow + 1, zeroIdxCol));
		}
    	return iter;
    }

	private Board swap(int[][] tiles, int row1, int col1, int row2, int col2) {
		int[][] cpy = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cpy[i][j] = tiles[i][j];
			}
		}
		
		int tmp = cpy[row1][col1];
		cpy[row1][col1] = cpy[row2][col2];
		cpy[row2][col2] = tmp;
		return new Board(cpy);
	}

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	if (tiles[0][0] != 0 && tiles[0][1] != 0)
    		return swap(tiles, 0, 0, 0, 1);
    	else
    		return swap(tiles, 1, 0, 1, 1);
    }
    // unit testing (not graded)
    public static void main(String[] args) {
    	int[][] tiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    	int[][] goalTiles = {{1, 2, 3},{4, 5, 6},{7, 8, 0}};
    	
    	Board board = new Board(tiles);
    	Board goal = new Board(goalTiles);
    	
    	StdOut.println(board);
    	StdOut.println("hamming : " + board.hamming());
    	StdOut.println("manhattan : " + board.manhattan());
    	StdOut.println("dim : " + board.dimension());
    	StdOut.println("isGoal : " + board.isGoal());
    	StdOut.println("isGoal : " + goal.isGoal());
    	
    	Iterable<Board> iter = board.neighbors();
    	for(Board b : iter) {
    		StdOut.println(b);
    	}
    }
}