import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    public void shouldCreateCustomerCorrectly() throws InvalidGeographicLocationException {
        String customerString = "{\"latitude\": \"13.13\", \"user_id\": 13, \"name\": \"Customer 13\", \"longitude\": \"-13.13\"}";

        Customer customer = Customer.fromFileEntry(customerString);

        Assertions.assertEquals(customer.getName(), "Customer 13");
        Assertions.assertEquals(customer.getUserId(), 13);
        Assertions.assertEquals(customer.getLocation().getLatitude(), 13.13);
        Assertions.assertEquals(customer.getLocation().getLongitude(), -13.13);
    }

    @Test
    public void shouldFailOnInvalidInput() {
        String customerString = "\"latitude\": \"13.13\", \"user_id\": 13, \"name\": \"Customer 13\", \"longitude\": \"-13.13\"";

        Assertions.assertThrows(JSONException.class, () -> Customer.fromFileEntry(customerString));
    }
}
