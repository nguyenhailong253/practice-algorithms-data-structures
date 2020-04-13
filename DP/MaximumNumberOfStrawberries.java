/**
 * Google OA 2020 Singapore - intern
 * 
 * Source: https://leetcode.com/discuss/interview-question/396646/
 * 
 * O(n * m) time and space, n = length array, m = max capacity
 */

class Solution {
    public int maxStrawberries(int maxCapacity, int[] berries) {
        int numBerryBushes = berries.length;

        int[][] dp = new int[numBerryBushes + 1][maxCapacity + 1];

        for (int bushIdx = 1; bushIdx <= numBerryBushes; bushIdx++) {
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                int currentBerryValue = berries[bushIdx];
                int remainCapacityIfPick = capacity - currentBerryValue;
                int valueIfPick = 0;
                int valueIfNotPick = 0;

                if (remainCapacityIfPick >= 0 && bushIdx - 2 >= 0) {
                    valueIfPick = currentBerryValue + dp[bushIdx - 2][remainCapacityIfPick];
                    valueIfNotPick = dp[bushIdx - 1][capacity];
                } else if (remainCapacityIfPick >= 0) {
                    valueIfPick = currentBerryValue;
                    valueIfNotPick = dp[bushIdx - 1][capacity];
                } else {
                    valueIfPick = 0;
                    valueIfNotPick = dp[bushIdx - 1][capacity];
                }
                dp[bushIdx][capacity] = Math.max(valueIfPick, valueIfNotPick);
            }
        }
        return dp[numBerryBushes][maxCapacity];
    }
}