/**
 * O(n) time and O(logn) space, with n = length of array
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode curNode = new TreeNode(nums[mid]);
        curNode.left = dfs(nums, left, mid - 1);
        curNode.right = dfs(nums, mid + 1, right);
        return curNode;
    }
}