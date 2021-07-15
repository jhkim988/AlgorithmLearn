public class BOJ15596 {
    public static void main(String[] args) {
        BOJ15596 t = new BOJ15596();
        int N = 5;
        int[] testArr = new int[N]; 
        for (int i = 0; i < N; i++) {
            testArr[i] = i;
        }
        double result = t.sum(testArr);
        System.out.println(result);
    }

    long sum(int[] a) {
        long result = 0;
        for (int el : a) {
            result += (long) el;
        }
        return result;
    }
}
