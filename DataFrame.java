import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class DataFrame { 
  private List<String> columnHeaders; // headers for columns of the data frame
  private List<String> dataTypes; // data types for each column
  private List<List<DataObject>> dataRows; // list of rows, each row is a list of data element objects
 
  public DataFrame() {
    this.columnHeaders = new ArrayList<>();
    this.dataTypes = new ArrayList<>();
    this.dataRows = new ArrayList<>(25000);
  }
  
  // calculates the average column based on the input columnName provided by the user
  public double columnAverage(String columnName) throws DataFrameException { 
   // int columnIndex = columnHeaders.indexOf(columnName);
    if(columnHeaders.contains(columnName)){
      int columnIndex = columnHeaders.indexOf(columnName);
      System.out.println("----------"+ dataTypes.get(columnIndex));
    if (columnIndex == -1 || (dataTypes.get(columnIndex).equals("String")) || (dataTypes.get(columnIndex).equals("char"))) {
      throw new DataFrameException(" Invalid type of Column for average.");
      //checks for valid column and data type ^
    }

    double sum = 0;
    int count = 0;
    //System.out.println("I am here"+ columnIndex);
    for (List<DataObject> row : dataRows) {
      //System.out.println("I am here"+ columnIndex);
      DataObject element = row.get(columnIndex);
      if (element instanceof DoubleObject ) {
        sum += ((DoubleObject) element).getValue();//sums up all the values
        count++; // counter for items in the row
       // System.out.println("I am here"+ columnIndex);
      }
      else {
        sum += ((IntObject) element).getValue();//sums up all the values
        count++; // counter for items in the row
       // System.out.println("I am here int"+ count);
      }
    }
    double avg =(sum/count);
    System.out.println(avg);
    return avg; // sum /count of items = column average
    }
    else{
      throw new DataFrameException("Column Name does not exist");
    }

  }
  
  //calculates the minimum value in a specified column (must be data types of doubles)
  public double columnMinimum(String columnName) throws DataFrameException {
    if(columnHeaders.contains(columnName)){ int columnIndex = columnHeaders.indexOf(columnName);
      if (columnIndex == -1 || dataTypes.get(columnIndex).equals("String") || dataTypes.get(columnIndex).equals("char")) { 
        throw new DataFrameException("Invalid data type for finding minimum.");
        //checks for valid column and type ^
      }
      double min = Double.POSITIVE_INFINITY;
      for (List<DataObject> row : dataRows) {
        DataObject element = row.get(columnIndex);
        if (element instanceof DoubleObject) {
          min = Math.min(min, ((DoubleObject) element).getValue());
        }
        if (element instanceof IntObject) {
          min = Math.min(min, ((IntObject) element).getValue());
        }
      }
      System.out.println(min);
      return min;
    }
    else {
      throw new DataFrameException("Column Name does not exist.");
    }
   
  }

  //calculates the maximum value in a specified column (must be data types of doubles)
  public double columnMaximum(String columnName) throws DataFrameException {
    if(columnHeaders.contains(columnName)){ int columnIndex = columnHeaders.indexOf(columnName);
      if (columnIndex == -1 || dataTypes.get(columnIndex).equals("String") || dataTypes.get(columnIndex).equals("char")) { 
        throw new DataFrameException("Invalid data type for finding minimum.");
        //checks for valid column and type ^
      }
     
      double max = Double.NEGATIVE_INFINITY;
    for (List<DataObject> row : dataRows) {
      DataObject element = row.get(columnIndex);
        if (element instanceof DoubleObject) {
          max = Math.max(max, ((DoubleObject) element).getValue());
        }
        if (element instanceof IntObject) {
          max = Math.max(max, ((IntObject) element).getValue());
        }
      }
      System.out.println(max);
      return max;
    }
    else {
      throw new DataFrameException("Column Name does not exist.");
    }
   
  }

  // creates a frequency table for a specified column
  public Map<Double, Integer> frequencyTable(String columnName) throws DataFrameException {
    if(columnHeaders.contains(columnName)){ 
      int columnIndex = columnHeaders.indexOf(columnName);
      if (columnIndex == -1 || !(dataTypes.get(columnIndex).equals("double"))) {
        throw new DataFrameException("Invalid data type for creating frequency table.");
      }
      double min = Double.POSITIVE_INFINITY;
      double max = Double.NEGATIVE_INFINITY;
      for (List<DataObject> row : dataRows) {
        DataObject element = row.get(columnIndex);
        if (element instanceof DoubleObject ) {
          double value = ((DoubleObject) element).getValue();
          min = Math.min(min, value); // find the minimum value of the row
          max = Math.max(max, value); // find the maximum value of the row
        }
      }
      double interval = (max - min) / 5;
      Map<Double, Integer> frequencyTable = new HashMap<>();
      for (double i = min; i <= max; i += interval) {
        frequencyTable.put(i, 0); // initializes the frequency table
      }
      for (List<DataObject> row : dataRows) {
        DataObject element = row.get(columnIndex);
        if (element instanceof DoubleObject) {
          double value = ((DoubleObject) element).getValue();
          double key = min;
          while (key < max) {
            if (value >= key && value < key + interval) {
              frequencyTable.put(key, frequencyTable.get(key) + 1);
              break;
            }
            key += interval;
          }
        }
      }
      System.out.println("here"+frequencyTable);
      return frequencyTable;
    }
    else{
      throw new DataFrameException("Column Name does not exist.");
    }
   
  }

  public DataFrame dataFrameSubset(String condition) throws DataFrameException {
   // System.out.println("String = = = " + condition);
    String[] parts = condition.split(" ");
 
    //System.out.println(parts.length); // Uncomment this line to see individual elements
    
    if (parts.length != 3) { 
      throw new DataFrameException("Invalid condition format.");
    }
    String columnName = parts[0];
    String operator = parts[1];
    String valueStr = parts[2];
    if(columnHeaders.contains(columnName)){
      int columnIndex = columnHeaders.indexOf(columnName);
      if (columnIndex == -1) {
        throw new DataFrameException("Invalid column name.");
      }
      String dataType = dataTypes.get(columnIndex);
      DataObject value;
      switch (dataType) {
        case "int":
          value = new IntObject(Integer.parseInt(valueStr));
          break;
        case "double":
          value = new DoubleObject(Double.parseDouble(valueStr));
          break;
        case "char":
          value = new CharacterObject(valueStr.charAt(0));
          break;
        case "String":
          value = new StringObject(valueStr);
          break;
        default:
          throw new DataFrameException("User entered invalid data type.");
      }
      
      // create a subset of the data frame (option 7)
      DataFrame subset = new DataFrame();
      // copies column headers from original data frame to the subset data frame
      subset.columnHeaders = new ArrayList<>(this.columnHeaders);
      // copies data types form the original data frame over to the subset
      subset.dataTypes = new ArrayList<>(this.dataTypes);
      // this process iterates over each row of data from the original data frame 
      for (List<DataObject> row : this.dataRows) {
        DataObject element = row.get(columnIndex);
        boolean match; // variable to store if element matches
        
        //evaluates the condition based on operator provided by user using the boolean "match"
        switch (operator) {
          case "<":
            match = element.compareTo(value) < 0;
            break;
          case "==":
            match = element.getValue() == value.getValue();
            break;
          case ">":
            match = element.compareTo(value) > 0;
            break;
          case "!=":
            match = element.getValue() != value.getValue();
            break;
          default:
            throw new DataFrameException("Invalid operator.");
        }
        // if condition is true for the row, adds the row to the data frame subset
        if (match) {
          subset.dataRows.add(new ArrayList<>(row));
        }
      }
      System.out.println(subset);
      return subset; // returns the complete data frame subset
    }
    else {
      throw new DataFrameException("Column Name does not exist.");
    }
    
  }

  // exports data frame to csv
  public void exportToCSV(String fileName) throws DataFrameException {
    try (PrintWriter newPrintWriterObject = new PrintWriter(new File(fileName))) {
      StringBuilder newStringBuilderObject = new StringBuilder();
      // appends column headers to StringBuilder, comma-delimited
      for (String header : columnHeaders) {
        newStringBuilderObject.append(header);
        newStringBuilderObject.append(',');
      }
      newStringBuilderObject.append('\n');
      // appends data types to StringBuilder, comma-delimited
      for (String type : dataTypes) {
        newStringBuilderObject.append(type);
        newStringBuilderObject.append(',');
      }
      newStringBuilderObject.append('\n');
      // appends each data element to StringBuilder, comma-delimited
      for (List<DataObject> row : dataRows) {
        for (DataObject element : row) {
          newStringBuilderObject.append(element.getValue());
          newStringBuilderObject.append(',');
        }
        newStringBuilderObject.append('\n');
      }
      // writes the content from StringBuilder to the CSV file
      newPrintWriterObject.write(newStringBuilderObject.toString());
    } catch (FileNotFoundException e) {
      throw new DataFrameException("Error writing to CSV file: " + e.getMessage());
    }
  }

  public List<String> getColumnHeaders() {
     // columnHeaders getter
    return columnHeaders;
  }

  public void setColumnHeaders(List<String> columnHeaders) { 
    this.columnHeaders = columnHeaders;
  }

  public List<String> getDataTypes() { 
    return dataTypes;
  }

  public void setDataTypes(List<String> dataTypes) { 
    this.dataTypes = dataTypes;
  }

  public List<List<DataObject>> getDataRows() { 
    return dataRows;
  }

  public void addDataRow(List<DataObject> dataRow) { 
    this.dataRows.add(dataRow);
  }

  

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("DataFrame [");
    
    // Check if there are any columns
    if (columnHeaders.isEmpty()) {
      sb.append("No columns defined");
    } else {
      // Append column headers
      sb.append("Columns: ");
      for (int i = 0; i < columnHeaders.size(); i++) {
        sb.append(columnHeaders.get(i) + " (" + dataTypes.get(i) + ")");
        if (i < columnHeaders.size() - 1) {
          sb.append(", ");
        }
      }
      
      // Check if there are any rows
      if (dataRows.isEmpty()) {
        sb.append(", No rows");
      } else {
        // Append number of rows
        sb.append(", Rows: " + dataRows.size());
        
      }
    }
    
    sb.append("]");
    return sb.toString();
  }

  
}
