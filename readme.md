# Indiegogo Search

This code helps you search for keywords in a JSON file named "merged.json" and displays specific information ("fund_raised_percent" and "close_date") for matching entries.

- Go to this website for the data.
https://webrobots.io/indiegogo-dataset/


## How it works:

- Run the code.
- Enter a keyword (e.g., "robot", "fitness", "wearable").
- The code searches the JSON file for that keyword.
- If found, it prints the "fund_raised_percent" and "close_date" for the matching entries.

## Note:
- This code assumes a specific JSON format in "merged.json".
- This uses org.json
- Data is in resources folder, but the merged cache is outside.

## What's included:
- Line by line search
- Read all data by line into buffer before merge.
- Basic error handling for file reading and parsing.
- Loop for reuse.
- Search History in local drive.