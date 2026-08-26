public class Task8 {

    public static int sumOfSums(int... numbers) {
        int totalSum = 0;
        int cumulativeSum = 0;

        for (int n : numbers) {
            int triangularSum = 0;
            for (int i = 1; i <= n; i++) {
                triangularSum += i;
            }

            cumulativeSum += triangularSum;
            totalSum += triangularSum;

            System.out.println("Parameter " + n + ": sum = " + triangularSum + ", cumulative sum = " + cumulativeSum);
        }

        System.out.println("Total sum: " + totalSum);
        return totalSum;
    }

    public static void main(String[] args) {
        sumOfSums(3, 6, 8);
    }
}
