/**
 * O(n^2) time - for each num, inner loop check up to n nums. O(1) space
 * 
 * Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        // Create a dp array to store lengthOfLIS up to a particular index
        int[] lengthOfLISUpToIndex = new int[nums.length];
        // Create variable to keep track of max length so far
        int maxLengthOfLIS = 0;

        // Outer loop through the array
        for (int outIndex = 0; outIndex < nums.length; outIndex++) {
            // dp at outer loop += 1 (to include itself)
            lengthOfLISUpToIndex[outIndex] += 1;
            // Inner loop through the array. Starting from first index up to outer loop
            // index - 1
            for (int inIndex = 0; inIndex < outIndex; inIndex++) {
                // If outer loop num > inner loop num, meaning outer num can be added to the
                // subsequence ending at inner loop
                if (nums[outIndex] > nums[inIndex]) {
                    // Update dp array at outer loop, by comparing bigger value of its current value
                    // and the value of num at inIndex plus by 1 (because this will be MAX length of
                    // subsequence ending with outer num)
                    lengthOfLISUpToIndex[outIndex] = Math.max(lengthOfLISUpToInde[inIndex] + 1,
                            lengthOfLISUpToIndex[outIndex]);
                }
            }
            // Max length so far = max(max length so far, dp at outer loop num)
            maxLengthOfLIS = Math.max(maxLengthOfLIS, lengthOfLISUpToIndex[outIndex]);
        }
        return maxLengthOfLIS;
    }
}