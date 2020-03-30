import java.util.PriorityQueue;

// Name: Merge K Sorted Lists
// Level: Hard
// Link: https://leetcode.com/problems/merge-k-sorted-lists/

public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1); // just to hold the head
        ListNode currNode = dummy; // the actual node that we'll use

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (ListNode head : lists) {
            while (head != null) {
                minHeap.add(head.val);
                head = head.next;
            }
        }

        while (!minHeap.isEmpty()) {
            currNode.next = new ListNode(minHeap.remove());
            currNode = currNode.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
