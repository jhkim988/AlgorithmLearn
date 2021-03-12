// Q1 Nuts and bolts.
// A disorganized carpenter has a mixed pile of n nuts and n bolts.
// The goal is to find the corresponding pairs of nuts and bolts.
// Each nut fits exactly one bolt and each bolt fits exactly one nut.
// By fitting a nut and a bolt together, the carpenter can see which one is bigger
// (but the carpenter cannot compare two nuts or two bolts directly).
// Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).




public class Interview06_01_NutsAndBolts {
	private class Bolt {
		private int id;

		Bolt(int id) {
			this.id = id;
		}
		
		int id() {
			return id;
		}
		
		int compare (Nut nut) {
			if(id() > nut.id()) {
				return 1;
			} else if (id() < nut.id()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	private class Nut {
		private int id;
		int id() {
			return id;
		}
		Nut(int id) {
			this.id = id;
		}
		
		int compare (Bolt bolt ) {
			if(id() > bolt.id()) {
				return 1;
			} else if (id() < bolt.id()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	
}
