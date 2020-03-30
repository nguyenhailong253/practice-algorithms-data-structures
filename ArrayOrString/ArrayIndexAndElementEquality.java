/**
 * Pramp problem:
 * 
 * Given a sorted array arr of distinct integers, write a function
 * indexEqualsValueSearch that returns the lowest index i for which arr[i] == i.
 * Return -1 if there is no such index. Analyze the time and space complexities
 * of your solution and explain its correctness.
 * 
 * Eg:
 * 
 * input: arr = [-8, 0, 2, 5] output: 2 since arr[2] == 2
 * 
 * input: arr = [-1, 0, 3, 6] output: -1 since no index satisfies arr[i] == i
 * 
 * O(logn) time and O(1) space
 */

class Solution {

    static int indexEqualsValueSearch(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int midIdx = (start + end) / 2;

            if (arr[midIdx] - midIdx < 0)
                start = midIdx + 1;
            else if ((arr[midIdx] - midIdx == 0) && (midIdx == 0 || (arr[midIdx - 1] - (midIdx - 1) < 0)))
                return midIdx;
            else
                end = midIdx - 1;
        }
        // (arr[midIdx - 1] - (midIdx - 1) < 0) is similar to first if statement
        // it checks if current element is at the start of its sub-array
        // if it is not at the start, then there could be cases where there's a
        // duplication before itself
        // which means it is not the first match
        // then this block of code will not be entered but will go to else
        // shrink the range from right to left to narrow down the first match
        return -1;
    }
}