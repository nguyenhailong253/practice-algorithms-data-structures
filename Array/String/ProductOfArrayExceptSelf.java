/**
 * O(n^2) time and O(1) space, with n = length of array
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        if (length == 0 || length == 1)
            return new int[0];

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            int product = 1;

            for (int j = 0; j < length; j++) {
                if (i != j) // skip current element at i index
                    product *= nums[j];
            }
            result[i] = product;
        }
        return result;
    }
}

/**
 * O(n) time and space
 * 
 * Source: https://leetcode.com/articles/product-of-array-except-self/
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        if (length == 0 || length == 1)
            return new int[0];

        int[] result = new int[length];
        int[] leftProd = new int[length];
        int[] rightProd = new int[length];

        // 1. Calculate products of all numbers on the left of i index
        leftProd[0] = 1;
        for (int i = 1; i < length; i++)
            leftProd[i] = leftProd[i - 1] * nums[i - 1];

        // 2. Calculate products of all numbers on the right of i index
        rightProd[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--)
            rightProd[i] = rightProd[i + 1] * nums[i + 1];

        // 3. Calculate result
        for (int i = 0; i < length; i++)
            result[i] = leftProd[i] * rightProd[i];

        return result;
    }
}

/**
 * O(n) time and O(1) space
 * 
 * Source: https://leetcode.com/articles/product-of-array-except-self/
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        if (length == 0 || length == 1)
            return new int[0];

        int[] result = new int[length];

        // 1. Fill result with products of all left numbers
        result[0] = 1;
        for (int i = 1; i < length; i++)
            result[i] = result[i - 1] * nums[i - 1];

        // 2. Calculate products of all numbers on the right of i index
        int rightProd = 1; // Holds product of all numbers on the right
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * rightProd;
            rightProd = rightProd * nums[i];
        }

        return result;
    }
}