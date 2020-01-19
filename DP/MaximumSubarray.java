/**
 * O(n) time & O(1) space. Kadane algo
 * 
 * Source: https://www.youtube.com/watch?v=2MmGzdiKR9Y
 */
class Solution {
    /**
     * Question to ask on every element in the array (a way of determining the sub
     * problem):What is the max sub array sum if right bound ends at index i-th =>
     * Which one of these indices has the sub array that ends at it, that has the
     * largest sum (which one perfroms the best)
     */
    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;

        /**
         * max sum of of the first i elements is either max sum of the first (i-1)
         * elements OR it is the max sum of the vector extending to the i-th element
         * That max sum up until the i-th element is either the i-th element OR the i-th
         * element + sum of (i-1) elements
         */

        for (int i = 0; i < nums.length; i++) {
            /**
             * The key here is that, there are 2 OPTIONS we can choose, every time that we
             * land on an index. These 2 options are the essence of the problem. We could
             * either
             * 
             * - Option 1: Start a new subarray at the current index (hence, nums[i]), which
             * means that the subarray at that item, ends at itself. That is the best value
             * (that single value) in this 1-element subarray. "The best I can do ending
             * here, is myself". If the current one by itself is bigger (could do more good
             * on its own) than adding itself to the previous sum, then start a new subarray
             * at itself
             * 
             * - Option 2: Can we continue the max subarray coming before us by extending to
             * the current element? When we are at (i-1)th, does i-th extends our max value
             * OR does i-th cut off the progress that we have had (and just take itself like
             * option 1). We do this by adding sum up until (i-1) with i-th
             */
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }
}