import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InviteRuler {
    private static final Logger logger = Logger.getLogger(InviteRuler.class.getName());

    public static List<Customer> getSortedInvitedCustomers(List<Customer> inviteeList) {
        return getSortedInvitedCustomers(inviteeList, 100.0, InviteApplication.INTERCOM_DUBLIN_OFFICE_GEOLOCATION);
    }

    public static List<Customer> getSortedInvitedCustomers(List<Customer> inviteeList, double maxDistance, GeographicLocation destination) {
        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Ruling out any customers that are over " + maxDistance + "km away from " + destination);
        }

        return inviteeList.stream()
            .filter(customer -> customer.getLocation().distanceTo(destination) < maxDistance)
            .sorted(Comparator.comparingInt(Customer::getUserId))
            .collect(Collectors.toList());
    }
}
