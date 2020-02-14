/**
 * O(n) time and O(logn) space with n = number of nodes
 */

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }
}