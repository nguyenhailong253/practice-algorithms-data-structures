/**
 * O(n) time and space with n = number of nodes - O(n) space because of edge
 * case where tree is skew to one side
 */

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return copyTree(t2);
        if (t2 == null)
            return copyTree(t1);

        TreeNode mergedRoot = new TreeNode(t1.val + t2.val);

        mergedRoot.left = mergeTrees(t1.left, t2.left);
        mergedRoot.right = mergeTrees(t1.right, t2.right);

        return mergedRoot;
    }

    private TreeNode copyTree(TreeNode tree) {
        if (tree == null)
            return null;

        TreeNode newTree = new TreeNode(tree.val);
        newTree.left = copyTree(tree.left);
        newTree.right = copyTree(tree.right);
        return newTree;
    }
}