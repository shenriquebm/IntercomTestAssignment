import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFileWriter {
    private static final Logger logger = Logger.getLogger(InviteRuler.class.getName());
    private String outputFileName;

    public CustomerFileWriter(String outputFileName) throws IOException {
        this.outputFileName = outputFileName;
        createFile();
    }

    public void createFile() throws IOException {
        File outputFile = new File(outputFileName);
        boolean created = outputFile.createNewFile();

        if (InviteApplication.verboseMode) {
            if (created ) {
                logger.log(Level.INFO, "Created file " + outputFileName + ".");
            } else {
                logger.log(Level.INFO, "File " + outputFileName + " already exists. Contents will be replaced.");
            }
        }
    }

    public void writeCustomers(List<Customer> customerList) throws IOException {
        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Writing output to " + outputFileName + ".");
        }

        FileWriter writer = new FileWriter(outputFileName);
        for (Customer customer: customerList) {
            writer.write("{");
            writer.write("\"latitude\": \"" + customer.getLocation().getLatitude() + "\", ");
            writer.write("\"user_id\": " + customer.getUserId() + ", ");
            writer.write("\"name\": \"" + customer.getName() + "\", ");
            writer.write("\"longitude\": \"" + customer.getLocation().getLongitude() + "\"");
            writer.write("}\n");
        }
        writer.close();

        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Finished writing output file.");
        }
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}
