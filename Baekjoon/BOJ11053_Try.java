import java.util.Random;
import java.util.Arrays;

public class BOJ11053_Try {
    public static void main(String[] args) {
        Random r = new Random();
        int numData = 7;
        int data[] = new int[numData];
        
        while (true) {
            for (int i = 0; i < data.length; i++) {
                data[i] = r.nextInt(5) + 1;
            }
            if (useLinSearch(data) != useBinSearch(data)) {
                for (int i = 0; i < data.length; i++) {
                    System.out.print(data[i] + " ");
                }
								System.out.print(": " + useLinSearch(data) + ", " + useBinSearch(data));
                System.out.println();
            }
        }

    }
    static int useLinSearch(int[] data) {
        int[] numIncrSubs = new int[data.length];
        numIncrSubs[0] = data[0];
        int ptr = 0;
        for (int i = 1; i < data.length; i++) {
            ptr = 0;
            while (ptr < i && numIncrSubs[ptr] != 0 && numIncrSubs[ptr] < data[i]) {
              ptr++;
            }

            if (numIncrSubs[ptr] == 0 || numIncrSubs[ptr] > data[i]) {
                numIncrSubs[ptr] = data[i];
              }
        }

				int result = -1;
				for (int i = 0; i < data.length; i++) {
					if (numIncrSubs[i] == 0) {
						result = i;
						break;
					}
				}
				result = result == -1 ? data.length : result;
				return result;
    }
    static int useBinSearch(int[] data) {
			int[] numIncrSubs = new int[data.length];
			numIncrSubs[0] = data[0];
			int ptr = 0;
			int key;
			for (int i = 1; i < data.length; i++) {
				key = Arrays.binarySearch(numIncrSubs, 0, ptr + 1, data[i]);
        key = key < 0 ? -(key + 1) : key;
				if (numIncrSubs[key] == 0 || numIncrSubs[key] > data[i]) {
					if (numIncrSubs[key] == 0) {
						ptr++;
					}
					numIncrSubs[key] = data[i];

				}
			}
			int result = -1;
      for (int i = 0; i < data.length; i++) {
        if (numIncrSubs[i] == 0) {
          result = i;
          break;
        }
      }
      result = result == -1 ? data.length : result;
			return result;
    }
}