import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class InviteApplication {

    public static final GeographicLocation INTERCOM_DUBLIN_OFFICE_GEOLOCATION = new GeographicLocation(53.339428, -6.257664);
    public static boolean verboseMode = false;

    public static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("invite_application", options);
    }

    public static void main(String[] args) throws IOException {
        Options commandLineOptions = new Options();

        commandLineOptions.addRequiredOption("i", "input_file", true, "Use given file to process customers list.");
        commandLineOptions.addRequiredOption("o", "output_file", true, "Save output to given file.");
        commandLineOptions.addOption("v", "verbose", false, "Be a little more verbose.");
        commandLineOptions.addOption("p", "print_output", false, "Prints final output on screen.");


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(commandLineOptions, args);
        } catch (ParseException e) {
            printHelp(commandLineOptions);
            return;
        }

        if (!cmd.hasOption("i") || !cmd.hasOption("o") || cmd.hasOption("h")) {
            printHelp(commandLineOptions);
            return;
        }
        if (cmd.hasOption("v")) {
            verboseMode = true;
        }

        CustomerFileReader customerFileReader = new CustomerFileReader(cmd.getOptionValue("i"));

        List<Customer> customers = customerFileReader.getCustomers();

        List<Customer> invitedCustomers = InviteRuler.getSortedInvitedCustomers(customers);

        CustomerFileWriter customerFileWriter = new CustomerFileWriter(cmd.getOptionValue("o"));

        customerFileWriter.writeCustomers(invitedCustomers);

        if (cmd.hasOption("p")) {
            System.out.println("Invited customers are:");
            for (Customer customer: invitedCustomers) {
                System.out.println(customer);
            }
        }
    }
}
