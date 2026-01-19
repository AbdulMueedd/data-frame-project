import java.util.*;
import java.io.*;
// Divvy_Trips_July2013.csv
// PEdata-multiYear.csv
public class DataFrameMenu {
  private List<DataFrame> dataFrames;
  private DataFrame activeDataFrame;
  


  public DataFrameMenu() {
    this.dataFrames = new ArrayList<>(10);
    this.activeDataFrame = null;
    //this.fileName = null;
  }

  public void importCSV(String filePath) throws DataFrameException { //takes string --> void
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line = reader.readLine();
      if (line == null) { 
        throw new DataFrameException("Empty CSV file."); // if string is null, print error messsage
      }
      List<String> columnHeaders = Arrays.asList(line.split(","));
      line = reader.readLine(); // split text into smaller lines
      if (line == null) { // if line is empty, then print out error message
        throw new DataFrameException("Missing data types in CSV file.");
      }
      List<String> dataTypes = Arrays.asList(line.split(","));
      if (columnHeaders.size() != dataTypes.size()) { // if the columnHeaders are not the same size as the dataTypes, throw an exception and print out an error message
        throw new DataFrameException("Mismatch between column headers and data types.");
      }
      DataFrame dataFrame = new DataFrame();
      dataFrame.setColumnHeaders(columnHeaders);
      dataFrame.setDataTypes(dataTypes);
      
      while ((line = reader.readLine()) != null) { // iterate while there is information in the line 
        List<DataObject> row = new ArrayList<>();
        String[] values = line.split(","); // split by commas
        if (values.length != columnHeaders.size()) { //throw mismatch exception
          throw new DataFrameException("Mismatch between data and column headers.");
        }
        for (int i = 0; i < values.length; i++) { // switch based on conditionals: dataType determines the case
          DataObject element;
          switch (dataTypes.get(i)) {
            case "int": // if the dataTypes= integer, then convert to ints
              element = new IntObject(Integer.parseInt(values[i]));
              break;
            case "double": // if the dataTypes= doubles, then convert to doubles
              element = new DoubleObject(Double.parseDouble(values[i]));
              break;
            case "char": // if the dataTypes= char, then convert to chars
              element = new CharacterObject(values[i].charAt(0));
              break;
            case "String": // if the dataTypes= string, then convert to strings
              element = new StringObject(values[i]);
              break;
            default: //otherwise, throw an exception due to invalid data type for computation 
              throw new DataFrameException("Invalid data type.");
          }
          row.add(element);
        }
        dataFrame.getDataRows().add(row);
      }
      dataFrames.add(dataFrame);
      activeDataFrame = dataFrame;
    } catch (IOException e) { //if try catch fails, then throw new exception with error message
      throw new DataFrameException("Error reading CSV file: " + e.getMessage());
    }
  }

  public void changeActiveDataFrame(int index) throws DataFrameException {
    if (index < 0 || index >= dataFrames.size()) { 
      throw new DataFrameException("Invalid DataFrame index.");
    }
    this.activeDataFrame = dataFrames.get(index); 
  }

  public DataFrame getActiveDataFrame(){
    return this.activeDataFrame;
  }
  
  
  public double columnAverage(String columnName) throws DataFrameException {
    if (activeDataFrame == null) {
      throw new DataFrameException("No active DataFrame.");
    }
    return activeDataFrame.columnAverage(columnName); // if not empty, return average of the column columnName
  }
  
  public double columnMinimum(String columnName) throws DataFrameException {
    if (activeDataFrame == null) {
      throw new DataFrameException("No active DataFrame.");
    }
    return activeDataFrame.columnMinimum(columnName); // if not empty, return minimum of the column columnName
  }

  public double columnMaximum(String columnName) throws DataFrameException {
    if (activeDataFrame == null) {
      throw new DataFrameException("No active DataFrame.");
    }
    return activeDataFrame.columnMaximum(columnName); // if not empty, return maximum of the column columnName
  }

  public Map<Double, Integer> frequencyTable(String columnName) throws DataFrameException {
    if (activeDataFrame == null) {
      throw new DataFrameException("No active DataFrame.");
    }
    return activeDataFrame.frequencyTable(columnName); //// if not empty, return frequency table given the column columnName
  }

  public void dataFrameSubset(String condition) throws DataFrameException {
    if (activeDataFrame == null) {
      throw new DataFrameException("No active DataFrame.");
    }
    DataFrame subset = activeDataFrame.dataFrameSubset(condition);
    dataFrames.add(subset);
    activeDataFrame = subset; // implements code that subsets the dataFrame
  }

  public void exportToCSV() throws DataFrameMenuException {
    if (activeDataFrame == null) {
      throw new DataFrameMenuException("No active DataFrame.");
    }
    int index = dataFrames.indexOf(activeDataFrame);
    String fileName = "DataFrame" + index + ".csv"; // names the filename the index name.csv
    try {
      activeDataFrame.exportToCSV(fileName);
      System.out.println("DataFrame exported to " + fileName);
    } catch (DataFrameException e) {
      throw new DataFrameMenuException("Error exporting to CSV file: " + e.getMessage()); // if errors in exporting active dataFrame, throw dataFrameMenu exception and print error message
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("DataFrameMenu [");
    if (activeDataFrame != null) {
      sb.append("Active DataFrame: ").append(activeDataFrame);
    } else {
      sb.append("No active DataFrame");
    }
    sb.append("]");
    return sb.toString();
  }
  

  public void quit() {
    System.out.println("Stopping the program");
  }
}
