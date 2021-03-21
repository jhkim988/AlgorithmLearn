import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private static final String type = "manhattan";
	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int move;
		private int dist;
		private int priority;
		
		SearchNode(Board board, int move, String type){
			this.board = board;
			this.move = move;
			if(type.equals("hamming")) {
				dist = board.hamming();
			} else { // manhattan
				dist = board.manhattan();
			}
			priority = move + dist;
		}

		@Override
		public int compareTo(Solver.SearchNode node) {
			if(priority < node.priority)
				return -1;
			else if (priority > node.priority)
				return 1;
			else
				return 0;
		}
	}
	private SearchNode status;
	private boolean solvable;
	private int minMove;
	private ArrayList<Board> iter = new ArrayList<Board>();
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	if (initial == null)
    		throw new IllegalArgumentException();
    	status = new SearchNode(initial, 0, type);
    	
    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> pqAlter = new MinPQ<SearchNode>();
    	SearchNode statusAlter = new SearchNode(status.board.twin(), 0, type);
    	
    	SearchNode prev = status;
    	SearchNode crnt = status;
    	
    	SearchNode prevAlter = statusAlter;
    	SearchNode crntAlter = statusAlter;
    	
    	int move = 1;
    	int moveAlter = 1;
    	
    	iter.add(status.board);
    	while(true) {
        	if (crnt.board.isGoal()) {
        		solvable = true;
        		break;
        		}
        	if(crntAlter.board.isGoal()) {
        		solvable = false;
        		break;
        	}
        	for(Board bd : crnt.board.neighbors()) {
        		if (!prev.board.equals(bd))
        			pq.insert(new SearchNode(bd, move, type));
        	}
        	for(Board bd: crntAlter.board.neighbors()) {
        		if(!prevAlter.board.equals(bd))
        			pqAlter.insert(new SearchNode(bd, moveAlter, type));
        	}
        	minMove = move;
        	move = pq.min().move + 1;
        	moveAlter = pqAlter.min().move + 1;
        	
        	prev = crnt;
        	crnt = pq.delMin();
        	
        	prevAlter = crntAlter;
        	crntAlter = pqAlter.delMin();
        	
        	iter.add(crnt.board);
    	}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
		return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
		return minMove;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
    	if (!solvable)
    		return null;
    	return iter;
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
		Solver solver = new Solver(initial);

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
