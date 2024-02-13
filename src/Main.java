import serachutils.MainSearch;

import java.io.File;
import java.util.Scanner;

public class Main {

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
            switch (function) {
                case "1" -> MainSearch.searchWithHistory();
                case "2" -> MainSearch.printSearchHistorySummary();
                case "q" -> System.out.print("Exiting...");
                default -> System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
        // Out of the loop
        System.out.print("Thanks for using, bye~");
    }

}