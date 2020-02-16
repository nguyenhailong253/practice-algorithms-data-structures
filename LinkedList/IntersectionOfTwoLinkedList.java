/**
 * O(n) time and space
 */

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        Set<ListNode> set = new HashSet<>();

        populateSet(set, headA);

        return getIntersection(set, headB);
    }

    private void populateSet(Set<ListNode> set, ListNode head) {
        ListNode tracker = head;
        while (tracker != null) {
            set.add(tracker);
            tracker = tracker.next;
        }
    }

    private ListNode getIntersection(Set<ListNode> set, ListNode head) {
        ListNode tracker = head;
        while (tracker != null) {
            if (set.contains(tracker))
                return tracker;
            tracker = tracker.next;
        }
        return null;
    }
}

/**
 * O(n) time and O(1) space
 */

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode trackerA = headA;
        ListNode trackerB = headB;
        /**
         * When list A and B both finished being looped (1 round for list A + 1 round
         * for list B = 2), we switch the pointers to start on the other list. The first
         * pointer to reach null on round 3 would mean there are no intersection point -
         * otherwise, if there's an intersection point, they would always meet before
         * reaching either ends of both list
         */
        int maxRounds = 3;

        while (maxRounds > 0) {
            if (trackerA == null) {
                trackerA = headB;
                maxRounds--;
            }

            if (trackerB == null) {
                trackerB = headA;
                maxRounds--;
            }

            if (trackerA == trackerB)
                return trackerA;

            trackerA = trackerA.next;
            trackerB = trackerB.next;
        }

        return null;
    }
}