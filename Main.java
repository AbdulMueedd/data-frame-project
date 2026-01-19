import java.util.Scanner;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    DataFrameMenu menu = new DataFrameMenu(); // from DataFrame.java 
    Scanner in = new Scanner(System.in);
   
    DataFrame newDataFrame = new DataFrame();
    System.out.println("Menu Options: "); // store menu options in an array of strings
    while (true) { 
      String[] menuOptions= {
        "1. Import CSV", 
        "2. Change Active DataFrame", 
        "3. Average The Column",
        "4. Find The Column Minimum",
        "5. Find The Column Maximum",
        "6. Frequency Table",
        "7. Subset DataFrame",
        "8. Export DataFrame",
        "9. Quit"
      };
      //display menu options using a for loop to print out the array 
      if (menu.getActiveDataFrame() == null) {
        System.out.println("No active DataFrame or no column headers defined.");
      } else {
               System.out.println(menu.getActiveDataFrame().getColumnHeaders());
      }
      if (menu.getActiveDataFrame() == null) {
       // System.out.println("No active DataFrame or no column headers defined.");
      } else {
               System.out.println(menu.getActiveDataFrame().getDataTypes());
      }
      if (menu.getActiveDataFrame() == null) {
        // System.out.println("No active DataFrame or no column headers defined.");
       } else {
       //  System.out.println(menu.size());
       }
      
    //  System.out.println(newDataFrame.getColumnHeaders());

      for (int i=0; i< menuOptions.length; i++) {
        
        System.out.println(menuOptions[i]);
      }
      System.out.print("Please Select an option: ");
      String option= in.next();
      
      try {
        switch (option){

          case "1": // if the user selects "1", have them enter the filepatch 
            System.out.print("Enter a filename with .csv: ");
            String filePath= in.next(); 
            menu.importCSV(filePath); //method importCSV() takes in the argument filepath
            break; //move on to the next option
          case "2":
            System.out.print("Enter the DataFrame index to load: ");
            int index= Integer.parseInt(in.next());
            menu.changeActiveDataFrame(index);
            break;
          case "3":
            System.out.print("Enter column name: ");
            String columnName3= in.next();
            menu.columnAverage(columnName3);
            break;
          case "4":
            System.out.print("Enter column name: ");
            String columnName4= in.next();
            menu.columnMinimum(columnName4);
            break;
          case "5":
            System.out.print("Enter column name: ");
            String columnName5= in.next();
            menu.columnMaximum(columnName5);
            break;
          case "6":
            System.out.print("Enter column name: ");
            String columnName6= in.next();
            menu.frequencyTable(columnName6);
            break;
          case "7":
            System.out.println("Enter condition: ");
            in.nextLine();
            String condition= in.nextLine();
            menu.dataFrameSubset(condition);
            break;
          case "8":
            try {
              menu.exportToCSV();
            } catch (DataFrameMenuException e) {
              System.out.println("Error: "+ e.getMessage());
            }
            break;
          case "9":
            menu.quit();
            System.exit(0);
            break;
          default:
            System.out.println("Invalid option, enter a number from 1-9");
            break;
        }
      } catch (DataFrameException e){
        System.out.println("Error: "+ e.getMessage());
      }
    }
  }
}

            


