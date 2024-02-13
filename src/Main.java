import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Merge files
        File mergedFile = new File("merged.json"); // Existence check
        if (mergedFile.exists()) {
            System.out.println("Merged file already exists!"); // Announce the existence

            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to merge files? (y/n)");
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

        // Search Part
        serachutils.JsonSearcher.main(new String[]{"Optional Arguments Here"});

    }
}