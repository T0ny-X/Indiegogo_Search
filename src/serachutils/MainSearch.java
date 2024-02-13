package serachutils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainSearch {
    private static Map<String, Map<LocalDateTime, Integer>> searchHistory = new HashMap<>();
    public static void searchWithHistory() {
        // Keyword getter
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        // Add search to history
        LocalDateTime timestamp = LocalDateTime.now();

        // Create a new HashMap for the keyword if it doesn't exist
        // Recall Structure: Map<String, !!Map<LocalDateTime, Integer>!! >
        Map<LocalDateTime, Integer> keywordMap = searchHistory.computeIfAbsent(keyword, key -> new HashMap<>());

        // Get the previous search count for this keyword (or 0 if new)
        int previousCount = searchHistory.getOrDefault(keyword, Collections.emptyMap()).getOrDefault(timestamp, 0);

        int newCount = previousCount + 1;
        // Update the search count using a lambda expression for clarity
        keywordMap.put(timestamp, newCount);

        // Call searcher with keyword (Or whatever is needed)
        JsonComponent.main(new String[]{keyword});
    }

    public static void printSearchHistorySummary() {
        if (!searchHistory.isEmpty()) {
            System.out.println("\n Search History Summary:");
            for (String keyword : searchHistory.keySet()) {
                System.out.println("- '" + keyword + "' searched " + searchHistory.get(keyword).size() + " times:");
                for (Map.Entry<LocalDateTime, Integer> entry : searchHistory.get(keyword).entrySet()) {
                    System.out.printf("  * %s (%d times)\n", entry.getKey(), entry.getValue());
                }
            }
        } else {
            System.out.println("No search history available.");
        }
    }
}
