# Java DataFrame Project

A Java-based implementation of a lightweight **DataFrame** system that allows users to import CSV files, explore datasets, and perform basic analytical operations through a menu-driven command-line interface.

This project mimics core ideas found in data analysis libraries (such as pandas or R data frames) while being fully implemented from scratch in Java.

---

## Features

- Import CSV files into a custom DataFrame structure
- Store heterogeneous data types (String, Integer, Double, Character)
- Compute column statistics:
  - Average
  - Minimum values
  - Frequency tables
- Apply conditional filtering on data
- Interactive CLI menu for user-friendly operation
- Robust exception handling for invalid inputs and operations
- Unit tests to validate core functionality

---

## Project Structure

java-dataframe-project/
│
├── src/ # Main source code
│ ├── Main.java
│ ├── DataFrame.java
│ ├── DataFrameMenu.java
│ ├── DataObject.java
│ ├── StringObject.java
│ ├── IntObject.java
│ ├── DoubleObject.java
│ ├── CharacterObject.java
│ ├── DataFrameException.java
│ └── DataFrameMenuException.java
│
├── test/ # Unit tests
│ ├── DataObjectTest.java
│ ├── DataFrameTest.java
│ ├── DataFrameImportCSVTest.java
│ └── DataFrameExceptionTest.java
│
├── data/ # Sample CSV datasets
│ ├── DataFrame0.csv
│ ├── Divvy_Trips_July2013.csv
│ └── PEdata-multiYear.csv
│
├── error_log.txt # Runtime error output
└── README.md

---

## How to Compile and Run

### Compile
From the project root:
```bash
javac -d out src/*.java
Run
java -cp out Main
Usage
Launch the program
Import a CSV file
Select operations from the menu:
View column data
Compute statistics (average, minimum)
Generate frequency tables
Apply conditional filters
Handle errors gracefully when invalid inputs are provided
Error Handling
The program includes custom exception handling to manage:
Invalid column names
Unsupported data types for operations
Incorrect condition formats
Missing or unloaded DataFrames
Common runtime errors are logged in error_log.txt for debugging.
Testing
Unit tests are included to verify:
DataObject type behavior
DataFrame operations
CSV import correctness
Exception handling logic
Tests can be run through an IDE such as IntelliJ IDEA or Eclipse using a JUnit runner.
Future Improvements
Support for additional statistical operations
Export DataFrame results to CSV
Improved condition parsing
Refactoring CLI into a GUI
Performance optimization for large datasets
