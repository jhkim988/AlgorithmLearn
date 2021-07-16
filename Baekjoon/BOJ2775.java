import java.util.Scanner;

public class BOJ2775 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int numTest = scn.nextInt();

        for (int i = 0; i < numTest; i++) {
            int floor = scn.nextInt();
            int room = scn.nextInt();

            System.out.println(numResident(floor, room));
        }
        scn.close();
    }

    static int numResident(int floor, int room) {
        if (floor == 0) {
            return room;
        } else {
            int sum = 0;
            for (int i = 1; i <= room; i++ ) {
                sum += numResident(floor - 1, i);
            }
            return sum;
        }
    }
}
