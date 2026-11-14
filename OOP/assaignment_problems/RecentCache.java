import java.io.*;
import java.util.*;
import java.util.regex.*;

public class RecentCache {

    private static class Node {
        int key;
        String page;
        Node prev, next;
        Node(int key, String page) { this.key = key; this.page = page; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, null);   // dummy: most recent side
    private final Node tail = new Node(0, null);   // dummy: least recent side
    private int lastEvicted;                       // key evicted by the latest put, -1 if none
    private boolean lastUpdated;                   // true if the latest put updated an existing key

    public RecentCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void unlink(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    public String get(int key) {
        Node n = map.get(key);
        if (n == null) return "-1";
        unlink(n);
        addFront(n);                // reading makes it most recently used
        return n.page;
    }

    public void put(int key, String page) {
        lastEvicted = -1;
        lastUpdated = false;
        Node n = map.get(key);
        if (n != null) {            // update existing page
            n.page = page;
            unlink(n);
            addFront(n);
            lastUpdated = true;
            return;
        }
        if (map.size() == capacity) {   // full: remove the node just before the tail (LRU)
            Node lru = tail.prev;
            unlink(lru);
            map.remove(lru.key);
            lastEvicted = lru.key;
        }
        Node fresh = new Node(key, page);
        addFront(fresh);
        map.put(key, fresh);
    }

    public String describe() {
        StringBuilder sb = new StringBuilder("Cache (most recent first): ");
        for (Node t = head.next; t != tail; t = t.next) {
            sb.append(t.key).append('=').append(t.page);
            if (t.next != tail) sb.append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Line 1: capacity (e.g. "2" or "capacity = 2"); remaining lines: operations
        int capacity = Integer.parseInt(br.readLine().replaceAll("[^0-9]", ""));
        StringBuilder rest = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) rest.append(line).append(' ');

        RecentCache cache = new RecentCache(capacity);
        StringBuilder out = new StringBuilder();
        Matcher m = Pattern.compile("(put|get)\\s*\\(\\s*(\\d+)\\s*(?:,\\s*([^)]*))?\\)").matcher(rest);
        while (m.find()) {
            int key = Integer.parseInt(m.group(2));
            if (m.group(1).equals("get")) {
                out.append("get(").append(key).append(") -> ").append(cache.get(key)).append('\n');
            } else {
                String page = m.group(3).trim();
                cache.put(key, page);
                out.append("put(").append(key).append(',').append(page).append(')');
                if (cache.lastUpdated) out.append(" updated");
                else if (cache.lastEvicted != -1) out.append(" evicted ").append(cache.lastEvicted);
                out.append('\n');
            }
        }
        out.append(cache.describe());
        System.out.println(out);
    }
}

/*
 Complexity: get and put are O(1) time (HashMap lookup + constant pointer changes).
             Space O(capacity) for the map and the list.

 Why a singly linked list cannot remove an arbitrary node in O(1):
 To unlink a node you must change the next pointer of the node before it, but a singly linked node
 only knows its successor. Finding the predecessor needs an O(n) walk from the head.
 With prev pointers, the node itself gives access to both neighbours.
*/