import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	private static final String type = "manhattan";
	private class SearchNode implements Comparable<SearchNode> {
		private SearchNode prev;
		private Board board;
		private int move;
		private int dist;
		private int priority;
		
		SearchNode(Board board, SearchNode prev, int move, String type){
			this.board = board;
			this.prev = prev;
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
	private Stack<Board> iter = new Stack<Board>();
	
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	if (initial == null)
    		throw new IllegalArgumentException();
    	status = new SearchNode(initial, null, 0, type);
    	
    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> pqAlter = new MinPQ<SearchNode>();
    	SearchNode statusAlter = new SearchNode(status.board.twin(), null, 0, type);
    	SearchNode crntAlter = statusAlter;
    	
    	int move = 1;
    	int moveAlter = 1;
    	
    	for(Board bd : status.board.neighbors()) {
			pq.insert(new SearchNode(bd, status, move, type));
    	}
    	for(Board bd: crntAlter.board.neighbors()) {
			pqAlter.insert(new SearchNode(bd, crntAlter, moveAlter, type));
    	}
    	while(true) {
        	if (status.board.isGoal()) {
        		solvable = true;
        		break;
    		}
        	if(crntAlter.board.isGoal()) {
        		solvable = false;
        		minMove = -1;
        		break;
        	}
        	for(Board bd : status.board.neighbors()) {
        		if (status.prev != null && !status.prev.board.equals(bd))
        			pq.insert(new SearchNode(bd, status, move, type));
        	}
        	for(Board bd: crntAlter.board.neighbors()) {
        		if(crntAlter.prev != null && !crntAlter.prev.board.equals(bd))
        			pqAlter.insert(new SearchNode(bd, crntAlter, moveAlter, type));
        	}
        	minMove = pq.min().move;
        	move = pq.min().move + 1;
        	moveAlter = pqAlter.min().move + 1;
        	
        	status = pq.delMin();
        	crntAlter = pqAlter.delMin();
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
    	while(status != null) {
    		iter.push(status.board);
    		status = status.prev;
    	}
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
