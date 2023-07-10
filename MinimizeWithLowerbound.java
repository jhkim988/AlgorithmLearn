import java.util.*;

public class MinimizeWithLowerbound {
    private static class Subject {
        int totalTime;
        int numLecture;

        public Subject(int hour, int minutes, int numLecture) {
            this.totalTime = hour * 60 + minutes;
            this.numLecture = numLecture;
        }

        @Override
        public String toString() {
            return String.format("{totalTime:%d,numLecture:%d}", totalTime, numLecture);
        }
    }

    public static void main(String[] args) {
        List<Subject> subjects = List.of(
                new Subject(4, 32, 7),
                new Subject(1, 45, 3),
                new Subject(27, 20, 39),
                new Subject(9, 33, 35),
                new Subject(6, 41, 11),
                new Subject(0, 35, 2),
                new Subject(8, 23, 26),
                new Subject(4, 52, 8),
                new Subject(0, 54, 1),
                new Subject(3, 54, 9),
                new Subject(5, 14, 23),
                new Subject(91, 36, 26),
                new Subject(0, 2141, 4),
                new Subject(6, 22, 27),
                new Subject(6, 50, 30));

        System.out.println(minSubjects(subjects, 30));
    }

    private static List<Subject> minSubjects(List<Subject> list, int lim) {
        /**
         * Original Knapsack Problem:
         * dp[index][weight]
         * = Max(dp[index-1][weight], dp[index-1][weight - crntWeight] + crntValue)
         * 
         * Variation Problem: minimize with lower bound
         * choose all items, and exclude with upper bound [sumWeight - weightLim]
         * -> knapsack problem with upper bound [sumWeight - weightLim]
         * 
         * dp trace -> use 2D dp
         */
        int sumLecture = list.stream().map(s -> s.numLecture).reduce(0, (acc, el) -> acc + el);
        int sumTime = list.stream().map(s -> s.totalTime).reduce(0, (acc, el) -> acc + el);

        int len = list.size();
        int[][] dp = new int[len + 1][sumLecture - lim + 1];
        for (int i = 0; i < list.size(); i++) {
            for (int j = sumLecture - lim; j >= 0; j--) {
                if (j >= list.get(i).numLecture) {
                    dp[i + 1][j] = Integer.max(dp[i][j], dp[i][j - list.get(i).numLecture] + list.get(i).totalTime);
                } else {
                    dp[i + 1][j] = dp[i][j];
                }
            }
        }

        System.out.printf("Min time: %d\n", sumTime - dp[len][sumLecture - lim]);

        int trace = sumLecture - lim;
        List<Subject> ret = new ArrayList<>();
        for (int i = len; i > 0; i--) {
            if (dp[i][trace] != dp[i - 1][trace]) {
                trace -= list.get(i - 1).numLecture;
            } else {
                ret.add(list.get(i - 1)); // find exclusive item.
            }
        }

        return ret;
    }
}