package dynamicProgrammingHomework;

/*
Максимальна сума без сусідніх елементів
Вибрати елементи з масиву так, щоб не було сусідніх і сума була максимальною.
 */
public class MaxNonAdjacentSum {
    public static int maxSum(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return a[0];
        }

        dp[0] = a[0];
        dp[1] = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], a[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 7, 10};
        System.out.println(maxSum(a)); // вивід: 13
    }
}
