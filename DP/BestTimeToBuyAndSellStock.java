/**
 * O(n) time and space. Using DP and Kadane's Algo for Max SubArray
 * 
 * The key is to 'create' a virtual array of profit for every single pair i.e:
 * orginal [7, 1, 5, 3, 6, 4] => difference [0, 1-7, 5-1, 3-5, 6-3, 4-6] or [0,
 * -6, 4, -2, 3, -2] and then use Kadane's algo to find MAX of subarray in any
 * current range which will result in MAX difference between 2 certain points or
 * MAX profit
 * 
 * Source:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/Kadane's-Algorithm-Since-no-one-has-mentioned-about-this-so-far-%3A)-(In-case-if-interviewer-twists-the-input)
 * Source:
 * https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
 */
class Solution {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int maxProfit = Integer.MIN_VALUE;
        for (int stockIdx = 1; stockIdx < prices.length; stockIdx++) {
            dp[stockIdx] = Math.max(prices[stockIdx] - prices[stockIdx - 1],
                    dp[stockIdx - 1] + prices[stockIdx] - prices[stockIdx - 1]);
            maxProfit = Math.max(dp[stockIdx], maxProfit);
        }
        return maxProfit < 0 ? 0 : maxProfit;
    }
}

/**
 * O(n) time and O(1) space. Using DP with 2 variables and Kadane's Algo
 */
class Solution {
    public int maxProfit(int[] prices) {
        int curProfit = 0;
        int maxProfit = Integer.MIN_VALUE;
        for (int stockIdx = 1; stockIdx < prices.length; stockIdx++) {
            curProfit = Math.max(prices[stockIdx] - prices[stockIdx - 1],
                    curProfit + prices[stockIdx] - prices[stockIdx - 1]);
            maxProfit = Math.max(curProfit, maxProfit);
        }
        return maxProfit < 0 ? 0 : maxProfit;
    }
}

/**
 * O(n) time and O(1) space, NOT using Kadane's Algo for Max SubArray
 */
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            else
                max = Math.max(max, prices[i] - min);
        }
        return max;
    }
}

/**
 * Another representation of the same solution
 */
class Solution {
    public int maxProfit(int[] prices) {
        int minBuyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int stockIdx = 0; stockIdx < prices.length; stockIdx++) {
            if (prices[stockIdx] < minBuyPrice)
                minBuyPrice = prices[stockIdx];
            else if (prices[stockIdx] - minBuyPrice > maxProfit)
                maxProfit = prices[stockIdx] - minBuyPrice;
        }
        return maxProfit;
    }
}