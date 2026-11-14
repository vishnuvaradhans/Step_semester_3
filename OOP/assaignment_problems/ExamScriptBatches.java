import java.io.*;
import java.util.*;

class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

public class ExamScriptBatches {

    static Node reverseInGroups(Node head, int k) {
        Node dummy = new Node(0);          // helper anchor only; not part of the list
        dummy.next = head;
        Node prevTail = dummy;             // tail of the already-processed part

        while (true) {
            // 1. check that k nodes exist; 'after' ends up as the node following the group
            Node after = prevTail.next;
            int count = 0;
            while (after != null && count < k) {
                after = after.next;
                count++;
            }
            if (count < k) break;          // fewer than k left: keep original order

            // 2. reverse exactly k links; the first node of the group will point to 'after'
            Node groupHead = prevTail.next;
            Node prev = after, cur = groupHead;
            for (int i = 0; i < k; i++) {
                Node nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }

            // 3. connect previous tail to the new group head; old group head is the new tail
            prevTail.next = prev;
            prevTail = groupHead;
        }
        return dummy.next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Line 1: list (e.g. "1 2 3" or "1 -> 2 -> 3"; may be empty), line 2: k
        String listLine = br.readLine();
        if (listLine == null) listLine = "";
        listLine = listLine.replace("->", " ").replace(",", " ").trim();
        int k = Integer.parseInt(br.readLine().trim().replaceAll("[^0-9]", ""));

        Node head = null, tail = null;
        StringTokenizer st = new StringTokenizer(listLine);
        while (st.hasMoreTokens()) {
            Node n = new Node(Integer.parseInt(st.nextToken()));
            if (head == null) head = n; else tail.next = n;
            tail = n;
        }

        head = reverseInGroups(head, k);

        StringBuilder sb = new StringBuilder();
        for (Node t = head; t != null; t = t.next) sb.append(t.data).append(" -> ");
        sb.append("null");
        System.out.println(sb);
    }
}

/*
 Complexity: Time O(n) - each node is visited about twice per group (once to check, once to reverse).
             Additional space O(1) - only a handful of pointers.

 Stack: the solution is iterative (a while loop over groups), so there are no recursive calls
 and no O(n/k) stack space is used.
*/