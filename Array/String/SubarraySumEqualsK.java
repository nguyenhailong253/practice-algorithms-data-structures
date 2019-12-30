/**
 * O(n^2) time & O(1) space complexity
 */

class Solution {
    public int subarraySum(int[] nums, int k) {

        if (nums == null || nums.length == 0)
            return 0;

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;

            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];

                if (currentSum == k)
                    result++;
            }
        }
        return result;
    }
}

/**
 * O(n) time & space complexity
 */

class Solution {
    public int subarraySum(int[] nums, int k) {

        int result = 0;

        int sumUpFromStartToCurrentIndex = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sumUpFromStartToCurrentIndex += nums[i];

            // sum[j] - k = sum[i] => sum[j] - sum[i] = k => sum[j - (i + 1)] = k
            // sum up to j - sum up to i = k => range [i + 1, j] has sum = k
            if (map.containsKey(sumUpFromStartToCurrentIndex - k))
                result += map.get(sumUpFromStartToCurrentIndex - k);

            map.put(sumUpFromStartToCurrentIndex, map.getOrDefault(sumUpFromStartToCurrentIndex, 0) + 1);
        }
        return result;
    }
}