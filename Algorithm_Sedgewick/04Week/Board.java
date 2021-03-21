import java.util.Iterator;
import java.util.ArrayList;

public class Board {
	private final int row;
	private final int col;
	private final int[][] tiles;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	this.tiles = tiles.clone();
    	col = tiles.length;
    	row = tiles[0].length;
    }
                                           
    // string representation of this board
    public String toString() {
    	String result = row + "\n";
    	for(int i = 0; i < row; i++) {
    		for(int j = 0; j <col - 1; j++) {
    			result += tiles[i][j] + " ";
    		}
    		result += "\n";
    	}
    	for(int j = 0; j < col; j++)
    		result += tiles[row - 1][j];
    	return result;
    }

    // board dimension n
    public int dimension() {
    	return row;
    }

    // number of tiles out of place
    public int hamming() {
    	int result = 0;
    	for(int i = 0; i < row*col - 1; i++) {
    		if (tiles[i/col][i%col] != (i + 1))
    			result++;
    	}
    	return result;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int result = 0;
    	for(int i = 0; i < row*col - 1; i++) {
    		int tmp = tiles[i/col][i%col];
    		result += (Math.abs(tmp%col - i%col) + Math.abs(tmp/col - i/col));
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
    	if(tmp.dimension() != tmp.dimension()) return false;
    	for(int i = 0; i < row*col; i++) {
    		if (tiles[i/col][i%col] != tmp.tiles[i/col][i%col])
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
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
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
		if (zeroIdxCol + 1 <= col - 1){
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow, zeroIdxCol + 1));
		}
		if (zeroIdxRow - 1 >= 0) {
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow - 1, zeroIdxCol));
		}
		if (zeroIdxRow + 1 <= row - 1){
			iter.add(swap(tiles, zeroIdxRow, zeroIdxCol, zeroIdxRow + 1, zeroIdxCol));
		}		
    	return iter;
    }

	private Board swap(int[][] tiles, int row1, int col1, int row2, int col2) {
		int[][] cpy = tiles.clone();
		int tmp = cpy[row1][col1];
		cpy[row1][col1] = cpy[row2][col2];
		cpy[row2][col2] = tmp;
		return new Board(cpy);
	}

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	
    }
    // unit testing (not graded)
    public static void main(String[] args) {
    	
    }

}