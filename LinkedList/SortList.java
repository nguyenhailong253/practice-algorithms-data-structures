// Technique: Merge sort
/**
 * O(nlogn) time & O(1) space
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode midNode = splitToHalf(head);

        ListNode leftHalf = sortList(head);
        ListNode rightHalf = sortList(midNode);

        return merge(leftHalf, rightHalf);
    }

    private ListNode splitToHalf(ListNode startNode) {
        ListNode prevNode = null;
        ListNode slowPointer = startNode;
        ListNode fastPointer = startNode;

        // Fast pointer will run twice as fast as the slow one.
        // Hence when it reaches the last node, slow pointer will
        // be at mid node

        while (fastPointer != null && fastPointer.next != null) {
            prevNode = slowPointer;

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        prevNode.next = null; // cut off list in half, will be last node of left half
        return slowPointer; // will be the first node of right half
    }

    private ListNode merge(ListNode leftHalfPointer, ListNode rightHalfPointer) {
        ListNode dummyHead = new ListNode(0);
        ListNode endOfSortedList = dummyHead;

        while (leftHalfPointer != null && rightHalfPointer != null) {

            // whichever one is smaller, will be appended to the list

            if (leftHalfPointer.val < rightHalfPointer.val) {
                endOfSortedList.next = leftHalfPointer;
                leftHalfPointer = leftHalfPointer.next;
            } else {
                endOfSortedList.next = rightHalfPointer;
                rightHalfPointer = rightHalfPointer.next;
            }

            endOfSortedList = endOfSortedList.next; // move pointer forward
        }

        // if right half reaches end of its list first, while left still have
        // 1 node left, just append it
        if (leftHalfPointer != null)
            endOfSortedList.next = leftHalfPointer;
        if (rightHalfPointer != null)
            endOfSortedList.next = rightHalfPointer;

        return dummyHead.next;
    }
}

/**
 * O(nlogn) time & O(n) space with n = number of nodes
 */

class Solution {
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        List<Integer> nodeValues = new ArrayList<>();

        while (head != null) {
            nodeValues.add(head.val);
            head = head.next;
        }

        Collections.sort(nodeValues);

        ListNode resultPointer = new ListNode(0);
        ListNode newHead = new ListNode(nodeValues.get(0));
        resultPointer.next = newHead;

        for (int i = 1; i < nodeValues.size(); i++) {
            newHead.next = new ListNode(nodeValues.get(i));
            newHead = newHead.next;
        }
        return resultPointer.next;
    }
}