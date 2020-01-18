// Source: https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
/**
 * O(n) time & space. Bottom-up solution with memoi solution
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length + 1];

        dp[0] = 0;
        dp[1] = nums[0];

        for (int houseIdx = 2; houseIdx <= nums.length; houseIdx++) {
            // compare the money if rob current house (meaning current val + dp of 2
            // positions before that)
            // with the max money of robbing until the last house (without the current one)
            dp[houseIdx] = Math.max(dp[houseIdx - 2] + nums[houseIdx - 1], dp[houseIdx - 1]);
        }
        return dp[nums.length];
    }
}

/**
 * O(n) time & O(1) space. Bottom-up without memoi solution
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int moneyTillPreviousHouse = 0;
        int moneyTill2HousesBefore = 0;

        for (int curHouseMoney : nums) {
            int tempMoney = moneyTillPreviousHouse;
            moneyTillPreviousHouse = Math.max(moneyTillPreviousHouse, moneyTill2HousesBefore + curHouseMoney);
            moneyTill2HousesBefore = tempMoney;
        }
        return moneyTillPreviousHouse;
    }
}