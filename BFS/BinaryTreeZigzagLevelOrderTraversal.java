/**
 * O(n) time & space complexity, n = number of nodes
 * 
 * BFS with reversing order of list solution
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();

        List<Integer> nodesOnCurLevel = new ArrayList<>();

        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.remove();

            if (curNode != null) {
                nodesOnCurLevel.add(curNode.val);

                if (curNode.left != null)
                    queue.add(curNode.left);
                if (curNode.right != null)
                    queue.add(curNode.right);
            } else {
                result.add(nodesOnCurLevel);
                nodesOnCurLevel = new ArrayList<>();

                if (!queue.isEmpty())
                    queue.add(null);
            }
        }

        for (int i = 1; i < result.size(); i += 2)
            Collections.reverse(result.get(i));

        return result;
    }
}

/**
 * O(n) time and space with n = number of nodes
 * 
 * BFS with 2 ended list solution
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        TreeNode delimiterForNewTreeLevel = null;
        LinkedList<TreeNode> twoEndedQueue = new LinkedList<>();

        twoEndedQueue.addLast(root);
        twoEndedQueue.addLast(delimiterForNewTreeLevel);

        LinkedList<Integer> nodesOnCurLevel = new LinkedList<>();
        boolean zigzagStartLeft = true;

        while (twoEndedQueue.size() > 0) {
            // The queue will always store the nodes as
            // traversed from left to right on each level
            TreeNode curNode = twoEndedQueue.pollFirst();

            if (curNode != delimiterForNewTreeLevel) {
                if (zigzagStartLeft)
                    nodesOnCurLevel.addLast(curNode.val); // add first left element last, then next one last,.... first
                                                          // left will be pushed to first on the line
                else
                    nodesOnCurLevel.addFirst(curNode.val);

                // add child nodes from left to right, as DFS
                if (curNode.left != null)
                    twoEndedQueue.addLast(curNode.left);
                if (curNode.right != null)
                    twoEndedQueue.addLast(curNode.right);
            } else {
                // finish scan of 1 level
                result.add(nodesOnCurLevel);
                nodesOnCurLevel = new LinkedList<>();

                // if there's child node in queue, add delimiter for that level
                if (twoEndedQueue.size() > 0)
                    twoEndedQueue.addLast(delimiterForNewTreeLevel);

                // toggle zigzag direction
                zigzagStartLeft = !zigzagStartLeft;
            }
        }
        return result;
    }
}