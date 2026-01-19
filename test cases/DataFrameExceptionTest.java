import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class DataFrameExceptionTest {
    @Test(expected = DataFrameException.class)
public void testInvalidDataFrameIndex() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.changeActiveDataFrame(10);
}

@Test(expected = DataFrameException.class)
public void testMissingDataTypesInCSVFile() throws IOException, DataFrameException {
    DataFrame df = new DataFrame();
    df.importCSV("missing_data_types.csv");
}

@Test(expected = DataFrameException.class)
public void testMismatchBetweenColumnHeadersAndDataTypes() throws IOException, DataFrameException {
    DataFrame df = new DataFrame();
    df.importCSV("mismatch_column_headers_data_types.csv");
}

@Test(expected = DataFrameException.class)
public void testMismatchBetweenDataAndColumnHeaders() throws IOException, DataFrameException {
    DataFrame df = new DataFrame();
    df.importCSV("mismatch_data_column_headers.csv");
}

@Test(expected = DataFrameException.class)
public void testInvalidDataTypeForComputation() throws DataFrameException {
    DataFrame df = new DataFrame();
    df.addColumn("column1", "invalid_type");
    df.columnAverage("column1");
}

}
