package fileutils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileMerger {
    public static void main(String[] args) {
        System.out.println("Start merge all files in resources into JSON.");
        try {
            mergeFiles();
            System.out.println("Files merged successfully!");
        } catch (IOException e) {
            System.err.println("Error merging files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void mergeFiles() throws IOException {
        String resourceFolder = "../resources"; // Pre-defined the folder name "resources"
        File mergedFile = new File("merged.json"); // Initialize the desired output file name

        List<String> lines = new ArrayList<>(); // Assume the computer can handle all files

        // Read files from the resource folder
        File resourceDir = new File(resourceFolder);
        for (File file : Objects.requireNonNull(resourceDir.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                lines.addAll(reader.lines().toList());
            }
            // As suggested in class, catch cases with trace back, IDE suggests combine the two exception.
            catch (NullPointerException | IOException e) {
                System.err.println("Error reading file '" + file.getName() + "': " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Write merged lines to the new file, may not be good if not enough RAM
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
