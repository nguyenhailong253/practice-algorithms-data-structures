/**
 * O(n) space and time
 */

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        // Create a hashmap with key = node, value = its index in list
        Map<ListNode, Integer> map = new HashMap<>();

        ListNode pointer = head;
        int nodeIndex = 0;

        while (pointer != null) {
            // If pointer exists in map, we have encountered it before
            // hence, a cycle is there, return that node
            if (map.containsKey(pointer))
                return pointer;

            map.put(pointer, nodeIndex);
            nodeIndex++;
            pointer = pointer.next;
        }
        return null;
    }
}

/**
 * O(n) time & O(1) space
 */

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                // Why head?
                // https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation./44281
                // The remaining nodes that slowPointer has to travel to cycle point
                // be equal to the number of nodes from head to cycle point
                // Hence, as soon as slow and fast meet, start a new pointer
                // The 2 slow pointers will meet at exact cycle point
                ListNode newSlowPointer = head;

                while (newSlowPointer != slowPointer) {
                    newSlowPointer = newSlowPointer.next;
                    slowPointer = slowPointer.next;
                }

                return newSlowPointer;
            }

        }
        return null;
    }
}