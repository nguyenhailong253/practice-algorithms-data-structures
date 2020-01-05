/**
 * Recursive solution - O(n) time & O(1) space with n = number of nodes
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return populateTreeWithRecursion(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode populateTreeWithRecursion(int rootIdx, int inOrderRangeStart, int inOrderRangeEnd, int[] preorder,
            int[] inorder) {
        if (rootIdx > preorder.length - 1 || inOrderRangeStart > inOrderRangeEnd)
            return null;

        int rootSubTreeVal = preorder[rootIdx];
        TreeNode root = new TreeNode(rootSubTreeVal);
        int inOrderRootIdx = 0;

        for (int i = inOrderRangeStart; i <= inOrderRangeEnd; i++) {
            if (inorder[i] == rootSubTreeVal)
                inOrderRootIdx = i;
        }

        int rootLeftSubtreeIdx = rootIdx + 1;
        int leftSubtreeLen = inOrderRootIdx - inOrderRangeStart;
        int rootRightSubtreeIdx = rootIdx + leftSubtreeLen + 1; // root of right subtree in the inorder array,current
                                                                // root idx + #left nodes
        root.left = populateTreeWithRecursion(rootLeftSubtreeIdx, inOrderRangeStart, inOrderRootIdx - 1, preorder,
                inorder);
        root.right = populateTreeWithRecursion(rootRightSubtreeIdx, inOrderRootIdx + 1, inOrderRangeEnd, preorder,
                inorder);

        return root;
    }
}

/**
 * Recursive solution - O(n) time & O(n) space complexity
 */

class Solution {

    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int nodeIdx = 0; nodeIdx < inorder.length; nodeIdx++)
            map.put(inorder[nodeIdx], nodeIdx);

        return populateTreeWithRecursion(0, 0, inorder.length - 1, preorder);
    }

    private TreeNode populateTreeWithRecursion(int rootIdx, int inOrderRangeStart, int inOrderRangeEnd,
            int[] preorder) {
        if (rootIdx > preorder.length - 1 || inOrderRangeStart > inOrderRangeEnd)
            return null;

        int rootSubTreeVal = preorder[rootIdx];
        TreeNode root = new TreeNode(rootSubTreeVal);
        int inOrderRootIdx = map.get(rootSubTreeVal);

        int rootLeftSubtreeIdx = rootIdx + 1;
        int leftSubtreeLen = inOrderRootIdx - inOrderRangeStart;
        // root of right subtree in the inorder array,current root idx + #left nodes
        int rootRightSubtreeIdx = rootIdx + leftSubtreeLen + 1;
        root.left = populateTreeWithRecursion(rootLeftSubtreeIdx, inOrderRangeStart, inOrderRootIdx - 1, preorder);
        root.right = populateTreeWithRecursion(rootRightSubtreeIdx, inOrderRootIdx + 1, inOrderRangeEnd, preorder);

        return root;
    }
}

/**
 * Iterative solution - O(n) time & O(n + logn) = O(n) space complexity
 */

class Solution {

    private Map<Integer, Integer> map = new HashMap<>();

    // In this problem inorder plays the role of 'lookup' table
    // We iterate through preorder to process each node
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0)
            return null;

        populateLookUpTable(inorder);

        Stack<TreeNode> stack = new Stack<>();

        // Create root of the main tree
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        stack.push(root);

        // The bigger loop represents depth traversal on all left subtrees, until
        // reaching a leaf
        // Then the if statement will be false and the else statement will be run
        for (int preOrderIdx = 1; preOrderIdx < preorder.length; preOrderIdx++) {
            // Grab index from the inorder array of current node
            int currentNodeVal = preorder[preOrderIdx];
            TreeNode currentNode = new TreeNode(currentNodeVal);
            int currentNodeIdxInInorder = map.get(currentNodeVal);

            // Grab the index from the inorder array of node on top of stack,
            // which is the latest node from previous iteration
            TreeNode topNodeInStack = stack.peek();
            int topNodeIdxInInorder = map.get(topNodeInStack.val);

            // If current node index is smaller than the index of top node in stack
            // This means, treating the node in stack as root of subtree, the current
            // node belongs to the leftsubtree of that root (check how inorder works)
            if (currentNodeIdxInInorder < topNodeIdxInInorder) {
                topNodeInStack.left = currentNode;
            } else {
                // If current node index is bigger, that means we have reached the leaf
                // of left subtree in previous iteration, in the current iteration we
                // are at the right node of the PARENT of the node in previous iteration
                backtrackToParentNode(stack, currentNode, currentNodeIdxInInorder);
            }
            stack.push(currentNode);
        }

        return root;
    }

    private void populateLookUpTable(int[] inorder) {
        // Key = node.val, value = index of node in inorder array
        for (int nodeIdx = 0; nodeIdx < inorder.length; nodeIdx++) {
            int inOrderNodeValue = inorder[nodeIdx];
            map.put(inOrderNodeValue, nodeIdx);
        }
    }

    private void backtrackToParentNode(Stack<TreeNode> stack, TreeNode currentNode, int currentNodeIdxInorder) {
        TreeNode parentOfCurrentNode = null;

        // Keep popping the stack until the current node is NO LONGER the right node of
        // the parent
        // (stop when it becomes the left node of some other parent node)
        // The last node being popped will be the the parent node of the current node
        while (!stack.isEmpty() && currentNodeIdxInorder > map.get(stack.peek().val)) {
            parentOfCurrentNode = stack.pop();
        }

        parentOfCurrentNode.right = currentNode;
    }
}