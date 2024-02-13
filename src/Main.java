import java.io.File;
import java.util.*;
import java.time.LocalDateTime;

public class Main {
    private static Map<String, Map<LocalDateTime, Integer>> searchHistory = new HashMap<>();

    public static void main(String[] args) {

        // Merge files
        File mergedFile = new File("merged.json"); // Existence check
        if (mergedFile.exists()) {
            System.out.println("Merged file already exists!"); // Announce the existence

            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to merge files again? (y/n)");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("y")) {
                fileutils.FileMerger.main(new String[]{"Optional Arguments Here"}); // Merge the files in resources
            } else if (answer.equals("n")) {
                System.out.println("Skipped");
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } else {
            fileutils.FileMerger.main(new String[]{"Optional Arguments Here"}); // Merge the files in resources
        }

        String function = "Start";
        while (!function.equals("q")){
            System.out.printf(
                    """
                            **************************
                            Please select a function:
                            **************************
                            1: Start search
                            2: View history
                            Enter 'q' to exit.
                            --------------------------
                            %n""");
            Scanner menu = new Scanner(System.in);
            function = menu.nextLine().toLowerCase();
            if (function.equals("1")) {
                searchWithHistory();
            } else if (function.equals("2")) {
                printSearchHistorySummary();
            }
        }
        // Out of the loop
        System.out.print("Thanks for using, bye~");
    }

    private static void searchWithHistory() {
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
        serachutils.JsonSearcher.main(new String[]{keyword});
    }

    private static void printSearchHistorySummary() {
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