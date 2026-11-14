import java.io.*;
import java.util.*;

public class LabScheduler {
    static class Job {
        String name; int remaining;
        Job(String n, int r) { name = n; remaining = r; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int quantum = Integer.parseInt(br.readLine().trim().split("\\s+")[1]); // "Quantum 3"
        Queue<Job> queue = new ArrayDeque<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");
            queue.add(new Job(t[0], Integer.parseInt(t[1])));
        }
        int time = 0;
        StringBuilder slices = new StringBuilder();
        List<String> finish = new ArrayList<>();
        while (!queue.isEmpty()) {
            Job j = queue.poll();
            int run = Math.min(quantum, j.remaining);
            slices.append(j.name).append('(').append(time).append('-').append(time + run).append(") ");
            time += run;
            j.remaining -= run;
            if (j.remaining > 0) queue.add(j);
            else finish.add(j.name + " " + time);
        }
        System.out.println(slices.toString().trim());
        System.out.println("finish " + String.join(", ", finish));
        // Time: O(n + T/q + n) slices, each O(1) -> O(n + T/q) where T = total time (at most T slices).
        // Space: O(n) for the queue and finish list.
    }
}