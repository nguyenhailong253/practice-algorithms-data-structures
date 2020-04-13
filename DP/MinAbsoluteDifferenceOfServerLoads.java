/**
 * Google Online Assessment - Internship 2018
 * 
 * Leetcode: https://leetcode.com/discuss/interview-question/356433/
 * 
 * Source:
 * https://leetcode.com/discuss/interview-question/356433/Google-or-OA-2018-or-Min-Abs-Difference-of-Server-Loads/421229
 * 
 * O(s*n) time and space, s = sum of all numbers in given array, n = length of
 * array
 * 
 * This is a 0/1 knapsack problem, can be solved with DP. 0/1 Knapsack problem
 * always include a given maximum weight, a set of values to choose from. In
 * this case a set of values is the given array of server loads. The maximum
 * weight, however, is a bit more tricky. When you think about splitting the
 * load for servers, you would want total load to be distributed evenly, hence,
 * the intuition of summing all server loads and divide by the number server we
 * have. In this case we have 2 servers. So now instead of allocating manually
 * to each server, we using DP to find the maximum loads that a single server
 * could take that does not exceed the value of sum/numOfServer
 */

class Solution {
    public int minAbs(int[] nums) {
        int totalLoad = calculateTotalLoad(nums);

        int numOfLoad = nums.length;

        int[][] dp = new int[numOfLoad + 1][totalLoad + 1]; // + 1 to store the base case where everything is 0

        // Going row by row. Try each of the load from the given array
        for (int loadIdx = 1; loadIdx <= numOfLoad; loadIdx++) {

            // For each max load, determine the load we could add to the server
            for (int maxLoad = 1; maxLoad <= totalLoad; maxLoad++) {
                // If current load is smaller than max, it potentially could be added to this
                // server. But we want to get as close to max laod as possible, so we will use
                // Math.max to determine whether taking the load or not would benefit us
                if (nums[loadIdx - 1] <= maxLoad) {
                    int remainCapacityIfAddCurrentLoad = maxLoad - nums[loadIdx - 1];
                    // Total if add current load and any load in previous case (use already
                    // calculated values from dp table)
                    int totalIfAddCurrentLoad = nums[loadIdx - 1] + dp[loadIdx - 1][remainCapacityIfAddCurrentLoad];

                    // Find max one between: if not take current load or take current load
                    dp[loadIdx][maxLoad] = Math.max(dp[loadIdx - 1][maxLoad], totalIfAddCurrentLoad);
                } else {
                    // If the load is bigger than max load, just don't add it
                    dp[loadIdx][maxLoad] = dp[loadIdx - 1][maxLoad];
                }
            }
        }
        int maxLoadOnCurrentServer = dp[numOfLoad][totalLoad];
        int maxLoadOnOtherServer = totalLoad - maxLoadOnCurrentServer;

        return Math.abs(maxLoadOnCurrentServer - maxLoadOnOtherServer);
    }
}