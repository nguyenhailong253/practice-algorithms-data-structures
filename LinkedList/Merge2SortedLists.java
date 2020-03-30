// Name: Merge 2 Sorted Lists
// Level: Easy
// Link: https://leetcode.com/problems/merge-two-sorted-lists/

public class Merge2SortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tempNode = new ListNode(0); // just to hold the head
        ListNode currNode = tempNode; // the actual node that we'll use
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                currNode.next = l1;
                l1 = l1.next;
            } else {
                currNode.next = l2;
                l2 = l2.next;
            }
            currNode = currNode.next;
        }

        if (l1 != null) {
            currNode.next = l1;
            l1 = l1.next;
        }

        if (l2 != null) {
            currNode.next = l2;
            l2 = l2.next;
        }
        return tempNode.next;
    }

    public static void main(String[] args) {
    }
}
