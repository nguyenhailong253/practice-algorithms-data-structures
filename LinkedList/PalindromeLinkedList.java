/**
 * A very clumsy solution using Stack, O(n) time and space
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        Stack<ListNode> stack = new Stack<>();

        int listSize = getListSize(head);
        boolean isSizeEven = listSize % 2 == 0;
        int midIdx = listSize / 2;
        int lastIdxOfFirstHalf = midIdx - 1;
        int firstIdxOfSecondHalf = isSizeEven ? midIdx : midIdx + 1;

        putFirstHalfListOnStack(stack, head, lastIdxOfFirstHalf);

        return compareSecondHalfWithFirstHalf(stack, head, firstIdxOfSecondHalf);
    }

    private int getListSize(ListNode head) {
        int listSize = 0;

        ListNode tracker = head;

        while (tracker != null) {
            listSize++;
            tracker = tracker.next;
        }

        return listSize;
    }

    private void putFirstHalfListOnStack(Stack<ListNode> stack, ListNode head, int endIdx) {
        int startIdx = 0;
        ListNode tracker = head;

        while (startIdx <= endIdx) {
            stack.push(tracker);
            startIdx++;
            tracker = tracker.next;
        }
    }

    private boolean compareSecondHalfWithFirstHalf(Stack<ListNode> stack, ListNode head, int startIdx) {
        int counter = 0;
        ListNode tracker = head;

        while (counter != startIdx) {
            tracker = tracker.next;
            counter++;
        }

        while (!stack.isEmpty()) {
            ListNode popNode = stack.pop();
            if (popNode.val != tracker.val)
                return false;
            tracker = tracker.next;
        }
        return true;
    }
}

/**
 * O(n) time and O(1) space
 * 
 * Source:
 * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
 */

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow = reverseLinkedList(slow);
        fast = head;
        return compareFirstHalfAndSecondHalf(slow, fast);
    }

    private ListNode reverseLinkedList(ListNode slow) {
        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        return prev;
    }

    private boolean compareFirstHalfAndSecondHalf(ListNode slow, ListNode fast) {
        while (slow != null) {
            if (fast.val != slow.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
}