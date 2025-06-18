package dynamicProgrammingHomework;

/*
Мінімальна вартість дійти до кінця масиву
Дано масив cost[], де кожен елемент — вартість кроку.
Можна йти на 1 або 2 позиції.
Можна почати з позиції 0 або 1, нічого не платячи/
Знайти мінімальну суму для досягнення кінця.
 */
public class MinCostClimb {
    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(minCost(cost)); // Output: 15
    }

    public static int minCost(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
