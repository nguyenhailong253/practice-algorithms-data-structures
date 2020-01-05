/**
 * Recursive solution - O(n) time, O(1) space complexity
 */
class Solution {
    public Node connect(Node root) {

        if (root == null)
            return root;

        if (root.left != null)
            root.left.next = root.right;

        if (root.right != null && root.next != null)
            root.right.next = root.next.left;

        connect(root.left);
        connect(root.right);

        return root;
    }
}

/**
 * Iterative solution - O(n) time & O(1) space
 */
class Solution {
    public Node connect(Node root) {
        // 2 key points:
        // - Traverse horizontally
        // - .right.next = .next.left

        Node rootSubtree = root;

        // Traverse left all the way down (depth)
        while (rootSubtree != null) {

            // Parent node, will populate the .next of its children
            Node nodeInCurrentLevel = rootSubtree;

            // Traverse horizontally
            while (nodeInCurrentLevel != null) {
                // Point left node.next to right node
                if (nodeInCurrentLevel.left != null)
                    nodeInCurrentLevel.left.next = nodeInCurrentLevel.right;

                // If current node has .next, point the right node to next.left
                if (nodeInCurrentLevel.right != null && nodeInCurrentLevel.next != null)
                    nodeInCurrentLevel.right.next = nodeInCurrentLevel.next.left;

                // Move to the next node in current level
                nodeInCurrentLevel = nodeInCurrentLevel.next;
            }
            rootSubtree = rootSubtree.left;
        }
        return root;
    }
}