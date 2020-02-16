/**
 * O(n) time and space
 */

class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;

        int numZeroes = countZeroes(nums);
        int[] transformedArray = new int[length];
        transformedArray = putNonZeroElement(transformedArray, nums);
        transformedArray = putZeroElement(transformedArray, numZeroes);

        for (int i = 0; i < length; i++)
            nums[i] = transformedArray[i];
    }

    private int countZeroes(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                counter++;
        }
        return counter;
    }

    private int[] putNonZeroElement(int[] dest, int[] src) {
        int idx = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] != 0) {
                dest[idx] = src[i];
                idx++;
            }
        }
        return dest;
    }

    private int[] putZeroElement(int[] arr, int numZeroes) {
        int startIdx = arr.length - numZeroes;
        while (startIdx < arr.length) {
            arr[startIdx] = 0;
            startIdx++;
        }
        return arr;
    }
}

/**
 * O(n) time and O(1) space
 */

class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroPtr = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroPtr] = nums[i];
                lastNonZeroPtr++;
            }
        }

        for (int i = lastNonZeroPtr; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}