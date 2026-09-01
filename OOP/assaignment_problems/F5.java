import java.util.*;

public class F5 {

    static void printFilteredWordFrequency(String feedback) {

        String[] stopWords =
            {"the", "was", "and", "a", "is", "of", "in"};

        feedback = feedback.toLowerCase()
                           .replace(".", "")
                           .replace(",", "");

        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            boolean stopWord = false;

            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    stopWord = true;
                    break;
                }
            }

            if (!stopWord) {
                frequency.put(
                    word,
                    frequency.getOrDefault(word, 0) + 1
                );
            }
        }

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(frequency.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : list)
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    public static void main(String[] args) {
        printFilteredWordFrequency(
            "The mentor was great, the session was great and clear."
        );
    }
}