import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataFrameImportCSVTest { // Descriptive name ending with Test

  @Test
  public void testImportCSV() throws IOException {
    DataFrame df = new DataFrame();
    df.importCSV("test.csv");

    // Assertions to verify DataFrame content
    assertEquals(3, df.getColumnHeaders().size());
    assertEquals(3, df.getDataTypes().size());
    assertEquals(3, df.getDataRows().size());

    assertEquals("Name", df.getColumnHeaders().get(0));
    assertEquals("Age", df.getColumnHeaders().get(1));
    assertEquals("City", df.getColumnHeaders().get(2));

    assertEquals("String", df.getDataTypes().get(0));
    assertEquals("int", df.getDataTypes().get(1)); // Assuming Age is stored as an integer
    assertEquals("String", df.getDataTypes().get(2));

    assertEquals("Alice", df.getDataRows().get(0).get(0));
    assertEquals("30", df.getDataRows().get(0).get(1));
    assertEquals("New York", df.getDataRows().get(0).get(2));

    assertEquals("Bob", df.getDataRows().get(1).get(0));
    assertEquals("25", df.getDataRows().get(1).get(1));
    assertEquals("Los Angeles", df.getDataRows().get(1).get(2));

    assertEquals("Charlie", df.getDataRows().get(2).get(0));
    assertEquals("35", df.getDataRows().get(2).get(1));
    assertEquals("Chicago", df.getDataRows().get(2).get(2));
  }

  @Test
public void testAddColumn() {
    DataFrame df = new DataFrame();
    df.addColumn("Gender", "String");
    assertEquals(1, df.getColumnHeaders().size());
    assertEquals(1, df.getDataTypes().size());
    assertEquals(0, df.getDataRows().size());
    assertEquals("Gender", df.getColumnHeaders().get(0));
    assertEquals("String", df.getDataTypes().get(0));
    }

    @Test
public void testAddRow() {
    DataFrame df = new DataFrame();
    df.addColumn("Name", "String");
    df.addColumn("Age", "int");
    df.addColumn("City", "String");
    df.addRow(new String[]{"Alice", "30", "New York"});
    df.addRow(new String[]{"Bob", "25", "Los Angeles"});
    df.addRow(new String[]{"Charlie", "35", "Chicago"});
    assertEquals(3, df.getDataRows().size());
    assertEquals("Alice", df.getDataRows().get(0).get(0));
    assertEquals("30", df.getDataRows().get(0).get(1));
    assertEquals("New York", df.getDataRows().get(0).get(2));
    assertEquals("Bob", df.getDataRows().get(1).get(0));
    assertEquals("25", df.getDataRows().get(1).get(1));
    assertEquals("Los Angeles", df.getDataRows().get(1).get(2));
    assertEquals("Charlie", df.getDataRows().get(2).get(0));
    assertEquals("35", df.getDataRows().get(2).get(1));
    assertEquals("Chicago", df.getDataRows().get(2).get(2));
}

@Test
public void testColumnAverage() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("Age", "int");
    df.addRow(new String[]{"30"});
    df.addRow(new String[]{"25"});
    df.addRow(new String[]{"35"});
    assertEquals(30.0, df.columnAverage("Age"), 0.001);
}

@Test
public void testColumnMinimum() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("Age", "int");
    df.addRow(new String[]{"30"});
    df.addRow(new String[]{"25"});
    df.addRow(new String[]{"35"});
    assertEquals(25, df.columnMinimum("Age"));
}

@Test
public void testColumnMaximum() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("Age", "int");
    df.addRow(new String[]{"30"});
    df.addRow(new String[]{"25"});
    df.addRow(new String[]{"35"});
    assertEquals(35, df.columnMaximum("Age"));
}

@Test
public void testFrequencyTable() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("City", "String");
    df.addRow(new String[]{"New York"});
    df.addRow(new String[]{"Los Angeles"});
    df.addRow(new String[]{"Chicago"});
    df.addRow(new String[]{"New York"});
    Map<String, Integer> freqTable = df.frequencyTable("City");
    assertEquals(new Integer(2), freqTable.get("New York"));
    assertEquals(new Integer(1), freqTable.get("Los Angeles"));
    assertEquals(new Integer(1), freqTable.get("Chicago"));
}

@Test
public void testDisplayFrame() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("Name", "String");
    df.addColumn("Age", "int");
    df.addColumn("City", "String");
    df.addRow(new String[]{"Alice", "30", "New York"});
    df.addRow(new String[]{"Bob", "25", "Los Angeles"});
    df.addRow(new String[]{"Charlie", "35", "Chicago"});
    df.displayFrame();
}
}
