package fileutils;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Recorder {

    // Similar idea as read and save in the FileMerger, just use stream since this works a little different
    public static Map<String, Map<LocalDateTime, Integer>> HistoryGetter(){
        // Type need to be consistent with Main search.
        Map<String, Map<LocalDateTime, Integer>> searchHistory;
        try (FileInputStream fis = new FileInputStream("searchHistory.ser");
             // ObjectInputStream because this is not line anymore.
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            searchHistory = (Map<String, Map<LocalDateTime, Integer>>) ois.readObject();
            System.out.println("Search history loaded from disk successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading search history, a new file is created to save the history.");
            // If loading fails, initialize a new empty map:
            searchHistory = new HashMap<>();
        }
        return searchHistory;
    }

    public static void HistorySaver(Map<String, Map<LocalDateTime, Integer>> searchHistory){
        try (FileOutputStream fos = new FileOutputStream("searchHistory.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(searchHistory);
            System.out.println("Search history saved to disk successfully.");
        } catch (IOException e) {
            System.err.println("Error saving search history, this will not be saved.");
        }
    }
}
