import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFileReader {
    private String fileLocation;
    private static final Logger logger = Logger.getLogger(CustomerFileReader.class.getName());

    public CustomerFileReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<Customer> getCustomers() {
        List<Customer> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileLocation));
            for (String line: lines) {
                result.add(Customer.fromFileEntry(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Successfuly read " + fileLocation + " customers file, and found a total of " + result.size() + " customers");
        }
        return result;
    }
}
