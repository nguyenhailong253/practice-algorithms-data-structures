/**
 * Dynamic Programming solution
 * https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int curSum = 1; curSum <= n; curSum++) {
            int minNumPerfectSquares = Integer.MAX_VALUE;
            int sqrtOfPerfectSquare = 1;

            int remainings = curSum - sqrtOfPerfectSquare * sqrtOfPerfectSquare;
            while (remainings >= 0) {
                minNumPerfectSquares = Math.min(minNumPerfectSquares, dp[remainings] + 1);
                sqrtOfPerfectSquare++;
                remainings = curSum - sqrtOfPerfectSquare * sqrtOfPerfectSquare;
            }
            dp[curSum] = minNumPerfectSquares;
        }
        return dp[n];
    }
}

/**
 * Shorter version of solution above
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int curSum = 1; curSum <= n; curSum++) {
            for (int sqrtOfPerfectSquare = 1; sqrtOfPerfectSquare
                    * sqrtOfPerfectSquare <= curSum; sqrtOfPerfectSquare++)
                dp[curSum] = Math.min(dp[curSum], dp[curSum - sqrtOfPerfectSquare * sqrtOfPerfectSquare] + 1);
        }
        return dp[n];
    }
}