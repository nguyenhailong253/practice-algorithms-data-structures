/**
 * Leetcode: missing number; Pramp: Getting a Different number
 * 
 * O(n) time and space, n = length of given array
 */

class Solution {

    static int getDifferentNumber(int[] arr) {
        int length = arr.length;

        // Create hashset to store numbers in array
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < length; i++) {
            set.add(arr[i]);
        }

        // iterate through the arr and compare element at i index with the index itself
        for (int i = 0; i < length; i++) {
            if (!set.contains(i))
                return i;
        }

        // if after the whole arr is done iterating, return array length
        return length;
    }
}

/**
 * O(n) time and space, n = length of given array
 * 
 * Source: https://leetcode.com/articles/missing-number/
 */

class Solution {

    static int getDifferentNumber(int[] arr) {
        int length = arr.length;

        // sort the array using java sorted function
        int[] sortedArr = Arrays.copyOf(arr, length);
        Arrays.sort(sortedArr);

        // iterate through the arr and compare element at i index with the index itself
        for (int i = 0; i < length; i++) {
            if (sortedArr[i] != i)
                return i;
        }

        // if after the whole arr is done iterating, return array length
        return length;
    }
}

/**
 * O(n) time and O(1) space
 */

class Solution {
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums)
            actualSum += num;
        return expectedSum - actualSum;
    }
}