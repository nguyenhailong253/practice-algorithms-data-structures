/**
 * Source: https://www.youtube.com/watch?v=NFJ3m9a1oJQ
 * https://leetcode.com/articles/climbing-stairs/
 */

/**
 * O(n) time and space top-down DP
 */
public class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) { // base case
            return 0;
        }
        if (i == n) { // base case
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}

/**
 * O(n) time and space. DP bottom up with dp[]
 */
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int step = 2; step <= n; step++) {
            dp[step] = dp[step - 1] + dp[step - 2];
        }
        return dp[n];
    }
}

/**
 * O(n) time and O(1) space. DP bottom with 2 variables
 */
class Solution {
    public int climbStairs(int n) {
        int prevOneStep = 1;
        int prevTwoSteps = 1;

        for (int step = 2; step <= n; step++) {
            int curStep = prevOneStep + prevTwoSteps;
            prevTwoSteps = prevOneStep;
            prevOneStep = curStep;
        }
        return prevOneStep;
    }
}