import java.io.*;
import java.util.*;

public class Order {
    // Static nested: a line item needs no Order object to exist.
    static class LineItem {
        final String productName; final int quantity; final double unitPrice;
        LineItem(String p, int q, double u) { productName = p; quantity = q; unitPrice = u; }
        double total() { return quantity * unitPrice; }
    }

    private LineItem[] items = new LineItem[4];
    private int size = 0;

    void add(LineItem li) {                       // amortized O(1) time
        if (size == items.length) items = Arrays.copyOf(items, size * 2);
        items[size++] = li;
    }

    // Member inner class: tied to one Order, reads its private array.
    class Cursor {
        private int pos = 0;
        boolean hasNext() { return pos < size; }
        LineItem next() { return items[pos++]; }
        void reset() { pos = 0; }
    }
    Cursor cursor() { return new Cursor(); }

    static String f(double d) { return d == Math.rint(d) ? String.valueOf((long) d) : String.valueOf(d); }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Order order = new Order();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] t = line.replace("x", " ").trim().split("\\s+"); // "Pen 3 x 10"
            order.add(new LineItem(t[0], Integer.parseInt(t[1]), Double.parseDouble(t[2])));
        }
        Cursor c = order.cursor();
        double total = 0;
        while (c.hasNext()) {
            LineItem li = c.next();
            System.out.println(li.productName + " " + f(li.total()));
            total += li.total();
        }
        System.out.println("Total " + f(total));
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
        // Complexity: add = O(1) amortized each (O(n) for n items), space O(n);
        // walking = O(n) time, O(1) extra space.
    }
}