import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

// Wrong!
public class Solver_without_SearchNode {
	private Board status;
	private static final Comparator<Board> hamming = new Comparator<Board>() {
		public int compare(Board bd1, Board bd2) {
			if (bd1.hamming() < bd2.hamming())
				return -1;
			else if (bd1.hamming() > bd2.hamming()) {
				return 1;
			} else
				return 0;
		}
	};
	private static final Comparator<Board> manhattan = new Comparator<Board>() {
		public int compare(Board bd1, Board bd2) {
			if (bd1.manhattan() < bd2.manhattan())
				return -1;
			else if (bd1.manhattan() > bd2.manhattan()) {
				return 1;
			} else
				return 0;
		}
	};

	// find a solution to the initial board (using the A* algorithm)
	public Solver_without_SearchNode(Board initial) {
		if (initial == null)
			throw new IllegalArgumentException();
		status = initial;
	}

	// is the initial board solvable? (see below)
	public boolean isSolvable() {
		return moves() != -1;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		Iterable<Board> iter = solution();
		int result = -1;
		if (iter != null) {
			for (Board bd : iter)
				result++;
		}
		return result;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		ArrayList<Board> iter = new ArrayList<Board>();
		
		// use hamming
//		MinPQ<Board> pq = new MinPQ<Board>(4, hamming);
//		MinPQ<Board> pqAlter = new MinPQ<Board>(4, hamming);
//		
//		Board prev = status;
//		Board crnt = status;
//		
//		Board prevAlter = status.twin();
//		Board crntAlter = status.twin();
//		
//		iter.add(crnt);
//		while (true) {
//			for (Board bd : crnt.neighbors()) {
//				if (!prev.equals(bd)) // critical optimization?
//					pq.insert(bd);
//			}
//			for(Board bd : crntAlter.neighbors()) {
//				if(!prevAlter.equals(bd))
//					pqAlter.insert(bd);
//			}
//			prev = crnt;
//			crnt = pq.delMin();
//
//			prevAlter = crntAlter;
//			crntAlter = pqAlter.delMin();
//			
//			iter.add(crnt);
//			if (crnt.isGoal())
//				return iter;
//			if (crntAlter.isGoal())
//				return null;
//		}
		
		// use manhattan
		MinPQ<Board> pq = new MinPQ<Board>(4, manhattan);
		MinPQ<Board> pqAlter = new MinPQ<Board>(4, manhattan);
		
		Board prev = status;
		Board crnt = status;
		
		Board prevAlter = status.twin();
		Board crntAlter = status.twin();
		
		iter.add(crnt);
		while (true) {
			for (Board bd : crnt.neighbors()) {
				if (!prev.equals(bd)) // critical optimization?
					pq.insert(bd);
			}
			for(Board bd : crntAlter.neighbors()) {
				if(!prevAlter.equals(bd))
					pqAlter.insert(bd);
			}
			prev = crnt;
			crnt = pq.delMin();

			prevAlter = crntAlter;
			crntAlter = pqAlter.delMin();
			
			iter.add(crnt);			
			if (crnt.isGoal())
				return iter;
			if (crntAlter.isGoal())
				return null;
		}
	}

	// test client (see below)
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] tiles = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				tiles[i][j] = in.readInt();
		Board initial = new Board(tiles);

		// solve the puzzle
		Solver_without_SearchNode solver = new Solver_without_SearchNode(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}

	}
}