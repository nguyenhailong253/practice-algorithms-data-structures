/**
 * O(n) time and O(n) space with n = number of nodes - O(n) space because if the
 * tree is skew to one side then that's the worst case
 */
class Solution {
    private int diameter = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int treeHeight = calculateTreeHeight(root);
        return diameter;
    }

    private int calculateTreeHeight(TreeNode rootSubtree) {
        if (rootSubtree == null)
            return 0;
        int leftTreeHeight = calculateTreeHeight(rootSubtree.left);
        int rightTreeHeight = calculateTreeHeight(rootSubtree.right);

        diameter = Math.max(leftTreeHeight + rightTreeHeight, diameter);

        return 1 + Math.max(leftTreeHeight, rightTreeHeight);
    }
}