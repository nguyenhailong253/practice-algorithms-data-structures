/**
 * O(n) time with n = number of nodes, O(1) space
 */

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode resultPointer = new ListNode(0);
        resultPointer.next = head;
        ListNode startPointer = resultPointer;
        ListNode endPointer = head;

        while (endPointer != null) {
            while (endPointer.next != null && endPointer.val == endPointer.next.val)
                endPointer = endPointer.next;

            // If they are pointing to the same thing, then the while loop above has not
            // occurred,
            // meaning there has been no duplication yet, hence move both pointers
            /**
             * dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null ^ ^ start end start.next is
             * 3 and end is at 3 also
             */
            if (startPointer.next == endPointer)
                startPointer = startPointer.next;

            /**
             * dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null ^ ^ -->^ start end->end now,
             * start.next is still node 3 (Node that has value 3 and point to another 3)
             * whereas end is now pointing to node with value 3 but POINT TO 4 => 3
             * differents node => else statement
             */
            else
                startPointer.next = endPointer.next;

            /**
             * dummy -> 1 -> 2 3 -> 3 -> 4 -> 4 -> 5 -> null ^ ^ start end ... 2
             * ------------> 4.. hence update node 2 to point to end.next, which is 4,
             * skipping all duplicated val
             */

            endPointer = endPointer.next;
        }
        return resultPointer.next;
    }
}