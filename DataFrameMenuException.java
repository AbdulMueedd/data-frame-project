import java.io.FileWriter;
import java.io.IOException;

public class DataFrameMenuException extends Exception {
    private static final String LOG_FILE = "error_log.txt";

    public DataFrameMenuException(String message) {
        super(message);
        logError(message);
    }

    private void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(errorMessage + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
