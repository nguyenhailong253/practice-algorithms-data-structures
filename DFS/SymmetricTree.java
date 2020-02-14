/**
 * O(n) time and O(logn) space with n = number of nodes
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isEqual(root, root);
    }

    private boolean isEqual(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode != null && rightNode != null) {
            if (leftNode.val != rightNode.val)
                return false;
        }

        if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null))
            return false;

        if (leftNode == null && rightNode == null)
            return true;

        return isEqual(leftNode.left, rightNode.right) && isEqual(leftNode.right, rightNode.left);
    }
}

/**
 * A neater solution
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null)
            return true;
        if (tree1 == null || tree2 == null)
            return false;

        return (tree1.val == tree2.val) && isMirror(tree1.left, tree2.right) && isMirror(tree1.right, tree2.left);
    }
}