import java.io.*;
import java.util.*;

class Node {
    String title;
    int day;
    Node next;
    Node(String title, int day) { this.title = title; this.day = day; }
}

public class LibraryReturns {

    static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) return head;

        // Split: slow ends at the last node of the first half
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node right = slow.next;
        slow.next = null;                       // cut the link after the middle

        Node l = sortByDueDay(head);
        Node r = sortByDueDay(right);
        return merge(l, r);
    }

    // Merge by relinking nodes; take from the left on ties => stable
    static Node merge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.day <= b.day) { tail.next = a; a = a.next; }
            else                { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) line = "";
        line = line.replace("->", " ").replace(",", " ");

        Node head = null, tail = null;
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            int c = tok.lastIndexOf(':');
            if (c < 0) continue;                // skips things like "(empty)"
            Node n = new Node(tok.substring(0, c), Integer.parseInt(tok.substring(c + 1)));
            if (head == null) head = n; else tail.next = n;
            tail = n;
        }

        head = sortByDueDay(head);

        StringBuilder sb = new StringBuilder();
        for (Node t = head; t != null; t = t.next) sb.append(t.title).append(':').append(t.day).append(" -> ");
        sb.append("null");
        System.out.println(sb);
    }
}

/*
 Complexity: Time O(n log n). Additional space O(log n) for the recursion stack
 (no arrays or copies; merging only relinks nodes). A bottom-up version would use O(1).

 Why merge sort suits linked lists better than quick sort:
 - Merging needs only sequential access and relinking, which lists do in O(1) per node;
   there is no need for the extra array that merge sort needs on arrays.
 - Quick sort relies on random access and swapping elements, which lists lack,
   and it degrades to O(n^2) on bad pivots.
 - Merge sort is naturally stable (take from the left on ties) and always O(n log n).
*/