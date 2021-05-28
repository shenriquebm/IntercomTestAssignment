import org.json.JSONObject;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {

    private GeographicLocation location;
    private int userId;
    private String name;

    private static final Logger logger = Logger.getLogger(Customer.class.getName());

    public static Customer fromFileEntry(String line) throws InvalidGeographicLocationException {
        JSONObject customerJson = new JSONObject(line);
        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Parsing new customer line " + line);
        }
        return new Customer(
            customerJson.getDouble("latitude"),
            customerJson.getDouble("longitude"),
            customerJson.getInt("user_id"),
            customerJson.getString("name")
        );
    }

    // private constructor so we get customers only from file entry
    private Customer(double latitude, double longitude, int userId, String name) throws InvalidGeographicLocationException {
        this.location = new GeographicLocation(latitude, longitude);
        this.userId = userId;
        this.name = name;

        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Created new customer = " + this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
            "name=" + name +
            ", userId=" + userId +
            ", location='" + location + '\'' +
            '}';
    }

    public GeographicLocation getLocation() {
        return location;
    }

    public void setLocation(GeographicLocation location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return userId == customer.userId && location.equals(customer.location) && name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, userId, name);
    }
}
