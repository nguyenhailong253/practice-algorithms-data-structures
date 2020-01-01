/**
 * O(n) time O(n) space - using hashmap, with n = number of nodes
 */

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        // Storing original node as key, copied node as value
        // We want constant access to a node's clone for later
        // wiring up the .next and .random pointer
        Map<Node, Node> map = new HashMap<>();

        // Create a new pointer to iterate and do whatever processing we need
        // We can't use head directly since if we modify it, it will alter the
        // original list
        Node iterationPointer = head;

        // Firstly, populate the hashmap with copied node first, we're not
        // setting up .next and .random copies yet since all copied nodes
        // have not existed.
        while (iterationPointer != null) {
            map.put(iterationPointer, new Node(iterationPointer.val));
            iterationPointer = iterationPointer.next;
        }

        // Reset iterationPointer back to head of the list (see, this is why we
        // rarely want to use the head pointer directly)
        iterationPointer = head;

        // Secondly, once all copy nodes are there, we can easily wire up the
        // .next and .random pointers
        while (iterationPointer != null) {
            // map.get(iterationPointer): grab the copy using current pointer
            // map.get(iterationPointer).next: setting the copy's next to (*)
            // iterationPointer.next: original .next of current node
            // (*) map.get(iterationPointer.next): get copy of .next of current node
            map.get(iterationPointer).next = map.get(iterationPointer.next);

            // Similar to .next, just replace .next with .random
            map.get(iterationPointer).random = map.get(iterationPointer.random);

            iterationPointer = iterationPointer.next;
        }
        return map.get(head);
    }
}

/**
 * O(n) time & O(1) space
 */

/*
 * // Definition for a Node. class Node { int val; Node next; Node random;
 * 
 * public Node(int val) { this.val = val; this.next = null; this.random = null;
 * } }
 */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        // Create a new pointer to iterate and do whatever processing we need
        // We can't use head directly since if we modify it, it will alter the
        // original list
        Node iterationPointer = head;

        // Firstly, create copy node, change .next of original node to point to the
        // new copy. Make the new copy's .next point to original .next
        // Basically, slide the copy node in between original node and its .next node
        while (iterationPointer != null) {
            // Store the original .next somewhere because it will be overwritten
            Node originalNext = iterationPointer.next;

            // Create copy of original node
            Node copyNode = new Node(iterationPointer.val);

            // Overwrite original's .next to copy
            iterationPointer.next = copyNode;

            // Make copy.next point to original next
            copyNode.next = originalNext;

            // Move pointer forward
            iterationPointer = originalNext;
        }

        // Reset iterationPointer back to head of the list (see, this is why we
        // rarely want to use the head pointer directly)
        iterationPointer = head;

        // Secondly, wire up the .random of copy nodes since now we have access to
        // copy node (by just doing originalNode.next). Note that when we move the
        // pointer forward in this list, have to do .next.next to skip the copy node
        while (iterationPointer != null) {
            // If it's not pointing to null, then update
            if (iterationPointer.random != null) {
                iterationPointer.next.random = iterationPointer.random.next;
            }
            iterationPointer = iterationPointer.next.next;
        }

        // Reset to head
        iterationPointer = head;
        Node copyDummyHead = new Node(0); // use to mark start of list
        Node copyHead = copyDummyHead; // use to iterate over list

        // Finally, separate to copy list and original list
        while (iterationPointer != null) {
            // Save the pointer to original next somewhere
            Node originalNext = iterationPointer.next.next;

            // Make copy .next point to its real .next, which is
            // copy of originalNext, which is currently originalNext.next
            // copyHead.next = copyNode;
            copyHead.next = iterationPointer.next;

            // Make original node point to its real next
            iterationPointer.next = originalNext;

            // Move pointer forward
            iterationPointer = originalNext;
            copyHead = copyHead.next;
        }
        return copyDummyHead.next;
    }
}