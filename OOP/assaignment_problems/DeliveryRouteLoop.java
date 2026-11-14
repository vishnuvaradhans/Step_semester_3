import java.io.*;
import java.util.*;

class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

public class DeliveryRouteLoop {

    static String detectLoop(Node head) {
        Node slow = head, fast = head;
        boolean met = false;

        // Phase 1: slow moves 1 step, fast moves 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { met = true; break; }
        }
        if (!met) return "Loop: no";

        // Phase 2: count the nodes in the loop by walking once round it
        int length = 1;
        for (Node t = slow.next; t != slow; t = t.next) length++;

        // Phase 3: reset one pointer to head, move both 1 step -> they meet at loop start
        Node p1 = head, p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return "Loop: yes, starts at stop " + p1.data + ", length " + length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Line 1: stops (e.g. "10 20 30 40 50" or "10 -> 20 -> 30"); line 2: pos
        String stopsLine = br.readLine().replace("->", " ").replace(",", " ").trim();
        StringTokenizer st = new StringTokenizer(stopsLine);
        String posLine = br.readLine().trim();
        int pos = Integer.parseInt(posLine.replaceAll("[^0-9-]", ""));

        Node head = null, tail = null, loopTarget = null;
        int index = 0;
        while (st.hasMoreTokens()) {
            Node n = new Node(Integer.parseInt(st.nextToken()));
            if (head == null) head = n; else tail.next = n;
            tail = n;
            if (index == pos) loopTarget = n;
            index++;
        }
        if (pos != -1) tail.next = loopTarget;   // create the bug: last stop links back to index pos

        System.out.println(detectLoop(head));
    }
}

/*
 Complexity: Time O(n), additional space O(1) (only a few node references).

 Why slow and fast must meet inside the loop:
 Once both pointers are inside the loop, fast gains exactly one node on slow per step,
 so the gap between them (measured round the loop) shrinks by 1 each step.
 The gap cannot skip over 0 because it changes by only 1, so they must land on the same node.
*/