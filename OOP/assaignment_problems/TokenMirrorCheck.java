import java.io.*;
import java.util.*;

class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

public class TokenMirrorCheck {

    static Node reverse(Node head) {
        Node prev = null, cur = head;
        while (cur != null) {
            Node nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;   // empty or single node

        // 1. slow ends at the last node of the first half
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. reverse the second half
        Node secondHead = reverse(slow.next);

        // 3. compare node by node (the middle node of an odd list is ignored)
        boolean result = true;
        Node p1 = head, p2 = secondHead;
        while (p2 != null) {
            if (p1.data != p2.data) { result = false; break; }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. restore: reverse the second half again and reattach it
        slow.next = reverse(secondHead);
        return result;
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
            if (!tok.matches("-?\\d+")) continue;       // skips things like "(empty)"
            Node n = new Node(Integer.parseInt(tok));
            if (head == null) head = n; else tail.next = n;
            tail = n;
        }

        boolean ans = isPalindrome(head);
        System.out.println("Palindrome: " + ans);
        System.out.println();

        StringBuilder sb = new StringBuilder("List after check: ");
        for (Node t = head; t != null; t = t.next) sb.append(t.data).append(" -> ");
        sb.append("null");
        System.out.println(sb);
    }
}

/*
 Complexity: Time O(n) - one pass to find the middle, two reversals and one comparison pass.
             Additional space O(1) - only a few pointers; the list is reversed in place.

 Why an array or stack does not meet the requirement:
 Copying the n values into an array or pushing them on a stack needs O(n) extra memory,
 which is exactly what the kiosk cannot afford. Reversing the second half in place uses only links.
*/