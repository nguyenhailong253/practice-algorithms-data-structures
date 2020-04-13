/**
 * Google online assessment - summer intern 2020
 * 
 * Leetcode:
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/submissions/
 * 
 * Source:
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119835/Java-O(n)-DP-Solution
 * 
 * O(n) time and O(1) space DP solution
 */

class Solution {
    public int minSwap(int[] A, int[] B) {
        int curMinSwapIfSwap = 1;
        int curMinSwapIfNotSwap = 0;

        for (int idx = 1; idx < A.length; idx++) {
            if (A[idx - 1] >= B[idx] || B[idx - 1] >= A[idx]) {
                // In this case, if a potential swap is made and the condition is met (meaning
                // the order will not be increasing anymore), then we would need to swap the
                // previous one also. Meaning current swap value will = prev swap value + 1.
                // Same thing with NoSwap value
                curMinSwapIfSwap += 1;
            } else if (A[idx] <= A[idx - 1] || B[idx] <= B[idx - 1]) {
                // In this case, we are sure that A[idx - 1] < B[idx] and B[idx - 1] < A[idx]
                // (this case has already been checked in the above if statement), so if we
                // reach this if statement, the swap will 100% work.
                // And obviously need to swap here and make sure we use NoSwap for prev case
                int prevMinSwap = curMinSwapIfNotSwap;
                curMinSwapIfNotSwap = curMinSwapIfSwap; // if we dont swap here, min swap stay the same
                curMinSwapIfSwap = prevMinSwap + 1;
            } else {
                // This is when both A and B up until this idx, still maintain increasing order
                // Can either choose to swap or not, and we need to pick the minimum one
                int prevMinSwap = Math.min(curMinSwapIfSwap, curMinSwapIfNotSwap);
                curMinSwapIfSwap = prevMinSwap + 1;
                curMinSwapIfNotSwap = prevMinSwap;
            }
        }
        return Math.min(curMinSwapIfSwap, curMinSwapIfNotSwap);
    }
}