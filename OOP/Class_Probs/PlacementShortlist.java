import java.io.*;
import java.util.*;

public class PlacementShortlist {
    static class Cand {
        String name; int total; int climb, from, to;
        Cand(String n, int[] s) {
            name = n;
            for (int x : s) total += x;
            climb = Integer.MIN_VALUE;                 // best gain over consecutive changes = max s[j]-s[i], i<j
            for (int i = 0; i < s.length; i++)
                for (int j = i + 1; j < s.length; j++)  // 6 scores -> constant work; strict > keeps earliest run
                    if (s[j] - s[i] > climb) { climb = s[j] - s[i]; from = i + 1; to = j + 1; }
        }
    }

    // Stable merge sort, total descending. O(n log n)
    static void mergeSort(Cand[] a, Cand[] tmp, int lo, int hi) {
        if (hi - lo < 2) return;
        int mid = (lo + hi) >>> 1;
        mergeSort(a, tmp, lo, mid);
        mergeSort(a, tmp, mid, hi);
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) tmp[k++] = (a[i].total >= a[j].total) ? a[i++] : a[j++]; // >= keeps stability
        while (i < mid) tmp[k++] = a[i++];
        while (j < hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, lo, a, lo, hi - lo);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Cand[] c = new Cand[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int[] s = new int[6];
            for (int k = 0; k < 6; k++) s[k] = Integer.parseInt(st.nextToken());
            c[i] = new Cand(name, s);
        }
        mergeSort(c, new Cand[n], 0, n);

        StringBuilder sb = new StringBuilder("Shortlist:\n");
        for (int i = 0; i < n; i++) {
            Cand x = c[i];
            sb.append(i + 1).append(". ").append(x.name).append(' ').append(x.total)
              .append(" | best climb ").append(x.climb >= 0 ? "+" : "").append(x.climb)
              .append(" (test ").append(x.from).append(" to ").append(x.to).append(")\n");
        }

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");
            int v = Integer.parseInt(t[1]);
            if (t[0].equalsIgnoreCase("CUTOFF")) {
                // first index with total < v (list is descending) -> O(log n)
                int lo = 0, hi = n;
                while (lo < hi) {
                    int mid = (lo + hi) >>> 1;
                    if (c[mid].total >= v) lo = mid + 1; else hi = mid;
                }
                sb.append("CUTOFF ").append(v).append(": ").append(lo).append(" candidates\n");
            } else {
                // two pointers on descending list -> O(n)
                int i = 0, j = n - 1;
                String res = "no pair";
                while (i < j) {
                    int sum = c[i].total + c[j].total;
                    if (sum == v) { res = c[i].name + " + " + c[j].name; break; }
                    if (sum > v) i++; else j--;
                }
                sb.append("PAIR ").append(v).append(": ").append(res).append('\n');
            }
        }
        System.out.print(sb);
        // Brute force replaced: sort O(n^2) -> O(n log n); CUTOFF O(n) scan -> O(log n); PAIR O(n^2) -> O(n).
    }
}