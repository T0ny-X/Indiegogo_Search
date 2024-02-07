package serachutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JsonSearcher {

    public static void main() {
        // Keyword getter
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the keyword to search: ");
        String keyword = scanner.nextLine();


        // Search Part
        try (BufferedReader reader = new BufferedReader(new FileReader("merged.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // Idea: search line by line due to the data structure, not ideal in performance wise.

                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    JSONObject obj = new JSONObject(line); // Read line as JSON Object

                    if (obj.has("data")) {
                        JSONObject dataObj = obj.getJSONObject("data"); // Deeper level sue to given structure

                        // Check for keyword in title, tagline, and category, GPT thinks search all filed gives more ideal result.
                        if (dataObj.has("title") && dataObj.getString("title").toLowerCase().contains(keyword.toLowerCase())
                                || dataObj.has("tagline") && dataObj.getString("tagline").toLowerCase().contains(keyword.toLowerCase())
                                || dataObj.has("category") && dataObj.getString("category").toLowerCase().contains(keyword.toLowerCase())) {

                            // Gather the data if the condition are met.
                            String title = dataObj.optString("title", "N/A");
                            String fundRaisedPercent = dataObj.optString("funds_raised_percent", "N/A");
                            String closeDate = dataObj.optString("close_date", "N/A");

                            // Pretty format
                            System.out.println("**************************");
                            System.out.println("Project Title: " + title);
                            System.out.println("Fund Raised Percent: " + fundRaisedPercent);
                            System.out.println("Close Date: " + closeDate);
                            System.out.println("--------------------------");
                        }
                    }

                } catch (JSONException e) {
                    System.err.println("Error parsing the JSON file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
