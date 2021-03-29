import edu.princeton.cs.algs4.StdRandom;

public class Interview02_03_egg_drop {
	public static int eggDrop0(int height, int target) {
		int numEgg = 0;
		int numToss = 0;
		int crntFloor = 1;

		// toss
		while(true) {
			numToss++;	
			if (crntFloor >= target) { // broken
				numEgg++;
				break;
			}
			crntFloor++;
		}
//		System.out.println(
//				"Find Target Floor. \tThe number of used eggs : " + numEgg + " \tThe number of toss : " + numToss);
		return numToss;
	}

	public static int[] eggDrop1(int height, int target) {
		int numEgg = 0;
		int numToss = 0;

		int ptr1 = 1;
		int ptr2 = height;

		while (ptr1 <= ptr2) {
			int crntFloor = (ptr1 + ptr2) / 2; // mid
//			System.out.println("ptr1 : " + ptr1 + " \tptr2 : " + ptr2);
//			System.out.println("Throw egg on the " + crntFloor + "Floor");
			numToss++;
			if (crntFloor < target) { // unbroken
				ptr1 = crntFloor + 1;
			} else { // broken
				ptr2 = crntFloor - 1;
				numEgg++;
			}
		}
//		System.out.println(
//				"Find Target Floor. \tThe number of used eggs : " + numEgg + " \tThe number of toss : " + numToss);
		int [] result  = new int[] {numEgg, numToss};
		return result;
	}

	public static int[] eggDrop2(int height, int target) {
		int numEgg = 0;
		int numToss = 0;
		int crntFloor = 1;
		int prevFloor = 1;
		while(true) {
//			System.out.println("Throw egg on the " + crntFloor + "floor");
			numToss++;
			if (crntFloor >= target) {
				numEgg++;
				break;
			}
			prevFloor = crntFloor;
			crntFloor *= 2;
		}
		if (crntFloor != 1) {
			int ptr1 = prevFloor;
			int ptr2 = crntFloor;
			while (ptr1 <= ptr2) {
				int crnt = (ptr1 + ptr2) / 2;
				numToss++;
//				System.out.println("Throw egg on the " + crnt + "floor");
				if (crnt < target) {
					ptr1 = crnt + 1;
				} else {
					ptr2 = crnt - 1;
					numEgg++;
				}
			}
		}
//		System.out.println(
//				"Find Target Floor. \tThe number of used eggs : " + numEgg + " \tThe number of toss : " + numToss);
		int [] result  = new int[] {numEgg, numToss};
		return result;
	}
	
	public static int eggDrop3(int height, int target) {
		int numEgg = 0;
		int numToss = 0;
		
		int unit = (int) Math.sqrt(height);
		int prevFloor = 1;
		int crntFloor = unit;
		
		while(true) {
//			System.out.println("Throw egg on the " + crntFloor + " floor");
			numToss++;
			if (crntFloor >= target) {
				numEgg++;
				break;
			}
			prevFloor = crntFloor;
			crntFloor += unit;
		}
		
		// linear search prevFloor ~ crntFloor
		int crnt = (crntFloor == unit ) ? prevFloor : prevFloor + 1;
		while(true) {
//			System.out.println("Throw egg on the " + crnt + " floor");
			numToss++;
			if (crnt >= target) {
				numEgg++;
				break;
			}
			crnt++;
		}
//		System.out.println(
//				"Find Target Floor. \tThe number of used eggs : " + numEgg + " \tThe number of toss : " + numToss);
		return numToss;
	}
	
	public static int eggDrop4(int height, int target) {
		int numEgg = 0;
		int numToss = 0;
		
		int summand = 2;
		int prevFloor = 1;
		int crntFloor = 1;
		
		while (true) {
//			System.out.println("Throw egg on the " + crntFloor + " floor");
			numToss++;
			if (crntFloor >= target) {
				numEgg++;
				break;
			}
			prevFloor = crntFloor;
			crntFloor += summand;
			summand++;
		}
		
		if(crntFloor != 1) {
			int crnt = prevFloor + 1;
			while(true) {
//				System.out.println("Throw egg on the " + crnt + " floor");
				numToss++;
				if (crnt >= target) {
					numEgg++;
					break;
				}
				crnt++;
			}
		}
//		System.out.println(
//				"Find Target Floor. \tThe number of used eggs : " + numEgg + " \tThe number of toss : " + numToss);
		return numToss;
	}
	
	private final int trials;
    private final double[][] resultEgg;
    private final double[][] resultToss;
    
    private double error(double real, double esti) {
    	return Math.abs(esti - real) / esti;
    }
    
    Interview02_03_egg_drop(int trials){
    	this.trials = trials;
    	resultToss = new double[trials][5];
    	resultEgg = new double[trials][2]; // version 1, 2 only.
    	for (int i = 0; i < trials; i++) {
    		int height = 100000;
    		int target = StdRandom.uniform(height) + 1;
    		
    		double expToss0 = target;
    		double expToss1 = Math.log(height) / Math.log(2);
    		double expToss2 = 2 * Math.log(target) / Math.log(2);
    		double expToss3 = 2 * Math.sqrt(height);
    		double expToss4 = 2 * Math.sqrt(2 * target);
    		double expEgg1 = Math.log(height) / Math.log(2);
    		double expEgg2 = Math.log(target) / Math.log(2);
    		
    		resultToss[i][0] = error(eggDrop0(height, target), expToss0);
    		resultToss[i][1] = error(eggDrop1(height, target)[1], expToss1);
    		resultToss[i][2] = error(eggDrop2(height, target)[1], expToss2);
    		resultToss[i][3] = error(eggDrop3(height, target), expToss3);
    		resultToss[i][4] = error(eggDrop4(height, target), expToss4);
    		resultEgg[i][0] = error(eggDrop1(height, target)[0], expEgg1);
    		resultEgg[i][1] = error(eggDrop2(height, target)[0], expEgg2);
    	}
    }
    private double meanDoubleArr(double result[][], int fix) {
    	double sum = 0;
    	for(int i = 0; i < trials; i++)
    		sum += result[i][fix];
    	return sum / trials;
    }
    public void errMeanPrint() {
    	for (int i = 0; i < 5; i++) {
    		if (i != 1 &&i != 2)
    			System.out.println("Egg Drop " + i + " - Error of Toss " + meanDoubleArr(resultToss, i));
    		else {
    			System.out.println("Egg Drop " + i + " - Error of Toss " + meanDoubleArr(resultToss, i) + " \tError of Egg " + meanDoubleArr(resultEgg, i - 1));
    		}
		}
    }
	
	public static void main(String[] args) {
		int trials = 100;
		Interview02_03_egg_drop run = new Interview02_03_egg_drop(trials);
		run.errMeanPrint();
	}
}
