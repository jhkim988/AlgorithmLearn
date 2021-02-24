import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Interview01_01_social_network_connectivity {
	int numMember;
	int numFriendship;
	int[] id;
	int[] sz;
	int numConnectComponent;
	int[][] log;
	
	Interview01_01_social_network_connectivity(int numMember, int numFriendship) {
		this.numMember = numMember;
		this.numFriendship = numFriendship;
		id = new int[numMember];
		sz = new int[numMember];
		numConnectComponent = numMember;
		log = new int[numFriendship][2];
		
		for (int i = 0; i < numMember; i++) {
			id[i] = i;
			sz[i] = 1;
		}
		makeLog();
	}
	int root (int p) {
		while(id[p] != p) {
			id[p] = id[id[p]]; // p 노드를 부모의 부모로 연결시킨다.
			p = id[p];
		}
		return p;
	}
	void union(int p, int q) {
		int proot = root(p);
		int qroot = root(q);
		if (proot == qroot) return;
		if (sz[proot] < sz[qroot]) {
			id[proot] = qroot;
			sz[qroot] += sz[proot]; // 트리의 높이가 아니라, 노드의 개수이다.
		} else {
			id[qroot] = proot;
			sz[proot] += sz[qroot];
		}
		numConnectComponent--; // 트리가 합병됐기 때문에 컴포넌트 개수를 빼준다.
	}
	boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	// make log file randomly
	void makeLog() {
		for (int i = 0; i < numFriendship; i++) {
			int ranIdx1 = (int) Math.floor(StdRandom.uniform() * numMember);
			int ranIdx2 = (int) Math.floor(StdRandom.uniform() * numMember);
			log[i][0] = ranIdx1;
			log[i][1] = ranIdx2;
		}
	}
	// find time
	int findTimeAllConnect() {
		int timeIdx = 0;
		while(numConnectComponent > 1) {
			StdOut.println("union : " + log[timeIdx][0] + " and "+ log[timeIdx][1]);
			StdOut.println("the number of connected component : " + numConnectComponent);
			union(log[timeIdx][0], log[timeIdx][1]);
			timeIdx++;
		}
		return timeIdx;
	}
	public static void main(String[] args) {
		// randomly formed friendship, if it already form, try again.
		int numMember = 50;
		int numFriendship = 1000;
		Interview01_01_social_network_connectivity obj = new Interview01_01_social_network_connectivity(numMember, numFriendship);
		StdOut.println("Time(Index) : " + obj.findTimeAllConnect());
	}
}

