package serachutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonComponent {

    public static void main(String[] args) {
        String keyword = args[0];

        // Search and display
        searchAndDisplay(keyword);
    }

    private static void searchAndDisplay(String keyword) {
        // Search Part
        try {
            BufferedReader reader = new BufferedReader(new FileReader("merged.json"));
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
                        boolean hasKeyword = dataObj.has("title") && dataObj.getString("title").toLowerCase().contains(keyword)
                                || dataObj.has("tagline") && dataObj.getString("tagline").toLowerCase().contains(keyword)
                                || dataObj.has("category") && dataObj.getString("category").toLowerCase().contains(keyword);


                        if (hasKeyword){
                            // Gather the data if the condition are met.
                            String title = dataObj.optString("title", "N/A");
                            String fundRaisedPercent = formatPercent(dataObj.optString("funds_raised_percent", "0"));
                            String closeDate = formatDate(dataObj.optString("close_date", "N/A"));

                            // Pretty format
                            System.out.printf(
                                    """
                                            **************************
                                            Project Title: %s
                                            Fund Raised Percent: %s
                                            Close Date: %s
                                            --------------------------
                                            %n""", title, fundRaisedPercent, closeDate);

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

    private static String formatPercent(String percent) {
        try {
            double value = Double.parseDouble(percent);
            return String.format("%.2f%%", value);
        } catch (NumberFormatException e) {
            return percent; // Return original value if parsing fails
        }
    }

    private static String formatDate(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return new SimpleDateFormat("dd/MM/yyyy").format(formatter.parse(date));
        } catch (Exception e) {
            return date; // Return original value if parsing or formatting fails
        }
    }
}
