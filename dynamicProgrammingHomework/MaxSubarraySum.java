package dynamicProgrammingHomework;

/*
Максимальна сума неперервного підмасиву.
Знайти максимальну суму підмасиву.
 */
public class MaxSubarraySum {
    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1};
        System.out.println(maxSum(a));
    }

    public static int maxSum(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        dp[0] = a[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(a[i], dp[i - 1] + a[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
