/**
 * O(n) time & O(1) space complexity
 */

class Solution {
    public ListNode oddEvenList(ListNode head) {

        // If head is null, or list only has 1 element, or list only has 2 elements,
        // return
        if (head == null || head.next == null || head.next.next == null)
            return head;

        // The idea is to split this list into 2 lists containing all odd and even nodes
        ListNode oddDummyHead = new ListNode(0);
        ListNode evenDummyHead = new ListNode(0);

        // Dummy pointer to keep track of the odd list, in this case I make the odd list
        // as the return list
        // So by default it points to head
        // Whereas the even dummy head above, which has no even nodes yet, has no .next
        // pointer
        oddDummyHead.next = head;

        // To iterate through the list, we need 2 pointers: odd node pointer and even
        // node pointer
        ListNode oddNodePointer = head;
        ListNode evenNodePointer = head.next;

        // Note: I make the odd list my main list to be returned

        // This is the pointer to track the building progress of the even list.
        // It will start at the dummy, and as we split new even nodes from the main
        // list,
        // we move this tracker forward
        ListNode evenListTracker = evenDummyHead;

        // There are two cases:
        // - An odd node is the last of the main list
        // - An even node is the last of the main list
        while (oddNodePointer.next != null && evenNodePointer.next != null) {
            // Save the next odd node because it will be overwritten when
            // we update next non-odd node
            ListNode nextOdd = evenNodePointer.next;

            // Move pointer of even list to point to the even node after the odd node
            evenListTracker.next = evenNodePointer;

            // Skip the even node, odd pointer.next will point to next odd
            oddNodePointer.next = nextOdd;

            // Move odd pointer & even node pointer
            oddNodePointer = nextOdd;
            evenNodePointer = nextOdd.next;

            // Move even list pointer
            evenListTracker = evenListTracker.next;
        }

        // If odd node is the last node of the main list
        if (oddNodePointer.next == null) {
            // We have found all even nodes in the main list => just point the last even
            // node
            // in the even list to null
            evenListTracker.next = null;

            // Make the last odd node point to the start of the even list to merge 2 lists
            oddNodePointer.next = evenDummyHead.next;
        } else if (evenNodePointer.next == null) {
            // If even node is the last node of the main list

            // Make the odd node before this even node point to the start of the even
            // list to merge 2 lists
            oddNodePointer.next = evenDummyHead.next;

            // We still have this last even node existing in the main list,
            // => Append this last even node to the even list
            evenListTracker.next = evenNodePointer;
        }
        return oddDummyHead.next;
    }
}

/**
 * A more neat solution, same complexity
 */

class Solution {
    public ListNode oddEvenList(ListNode head) {

        if (head == null)
            return null;

        ListNode oddPointer = head;
        ListNode evenPointer = head.next;
        ListNode evenListHead = evenPointer;

        while (evenPointer != null && evenPointer.next != null) {
            // Skip even node and make odd node point to next odd node
            oddPointer.next = evenPointer.next;

            // Move odd pointer forward
            oddPointer = oddPointer.next;

            // Skip odd node and make even node point to next even node
            evenPointer.next = oddPointer.next;

            // Move even pointer forward
            evenPointer = evenPointer.next;
        }
        // Merge 2 lists
        oddPointer.next = evenListHead;

        return head;
    }
}