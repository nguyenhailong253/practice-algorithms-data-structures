/**
 * Brute force: O(n^2) time & O(1) space complexity
 */

class Solution {
    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                if (currentSum >= s) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // break as soon as we find the min, as j will only increase then j-i+1 will too
                }
            }
        }
        minLength = (minLength != Integer.MAX_VALUE) ? minLength : 0;

        return minLength;
    }
}

/**
 * Sliding window: O(n) time & O(1) space
 */

class Solution {
    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int minLength = Integer.MAX_VALUE;
        int startWindowIdx = 0;
        int endWindowIdx = 0;
        int currentSum = 0;

        while (endWindowIdx < nums.length) {
            currentSum += nums[endWindowIdx];

            // While the window is still valid, shrink it
            while (currentSum >= s) {
                minLength = Math.min(minLength, endWindowIdx - startWindowIdx + 1);
                currentSum -= nums[startWindowIdx];
                startWindowIdx++;
            }
            // Otherwise keep extending
            endWindowIdx++;
        }
        minLength = (minLength != Integer.MAX_VALUE) ? minLength : 0;

        return minLength;
    }
}