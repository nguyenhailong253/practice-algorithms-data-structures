/**
 * O(n) time and O(logn) space with n = number of nodes
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.remove();
            TreeNode rightNode = queue.remove();

            if (leftNode == null && rightNode != null || leftNode != null && rightNode == null)
                return false;

            if (leftNode != null && rightNode != null) {
                if (leftNode.val != rightNode.val)
                    return false;

                queue.add(leftNode.left);
                queue.add(rightNode.right);
                queue.add(leftNode.right);
                queue.add(rightNode.left);
            }
        }
        return true;
    }
}

/**
 * A neater solution
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();

            if (t1 == null && t2 == null)
                continue;
            if (t1 == null || t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;

            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}