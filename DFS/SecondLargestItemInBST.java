/**
 * Source:
 * https://www.interviewcake.com/question/java/second-largest-item-in-bst
 * 
 * O(logn) time and O(1) space
 * 
 * The key here is try solving the WRONG yet simpelr problem first - finding the
 * largest element in BST
 * 
 * From that, adapt to our situation by breaking down into cases. In our
 * example, 2 cases help us form the algorithm:
 * 
 * - The largest node HAS a left subtree
 * 
 * - The largest node does NOT have a left subtree
 * 
 * When problem is starting to feel complicated, break it into cases
 */

public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode insertLeft(int leftValue) {
        this.left = new BinaryTreeNode(leftValue);
        return this.left;
    }

    public BinaryTreeNode insertRight(int rightValue) {
        this.right = new BinaryTreeNode(rightValue);
        return this.right;
    }
}

public class Solution {
    private static int findLargest(BinaryTreeNode rootNode) {
        BinaryTreeNode currentNode = rootNode;
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode.value;
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) {
        if (rootNode == null)
            return null;

        BinaryTreeNode currentNode = rootNode;
        while (true) {

            // Case 1: current node is the largest and has a left subtree
            // Hence, the second largest must be the larges of that left subtree
            if (currentNode.right == null && currentNode.left != null) {
                return findLargest(currentNode.left);
            }

            // Case 2: current noode is the parent of the largest
            // And the largest node does not have left or right subtree
            // Then return current node as it would be 2nd largest
            if (currentNode.right.right == null && currentNode.right.left == null && currentNode.right != null) {
                return currentNode.value;
            }

            // Otherwise, keep going right
            currentNode = currentNode.right;
        }
    }
}