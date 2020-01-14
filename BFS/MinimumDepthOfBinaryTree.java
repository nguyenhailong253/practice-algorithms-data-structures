/**
 * O(n) time & space, using BFS with queue. Iterative solution
 */
class Solution {
    public int minDepth(TreeNode root) {
        // num of nodes along shortest path
        // this is just the level
        // can do bfs and if a node has no children, break and return level

        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int depth = 0;
        int maxNodesOnLevel = queue.size();
        int nodesFound = 0;

        while (!queue.isEmpty()) {
            if (nodesFound != maxNodesOnLevel) {
                TreeNode curNode = queue.remove();

                if (curNode.left == null && curNode.right == null) {
                    depth++;
                    break;
                }

                if (curNode.left != null)
                    queue.add(curNode.left);
                if (curNode.right != null)
                    queue.add(curNode.right);

                nodesFound++;
            } else {
                depth++;
                nodesFound = 0;
                maxNodesOnLevel = queue.size();
            }
        }
        return depth;
    }
}