import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InviteRulerTest {

    @Test
    public void shouldReturnAllCustomersWithinDistanceOrdered() throws InvalidGeographicLocationException {
        // 99.99km
        Customer c1 = Customer.fromFileEntry("{\"latitude\": \"52.729447\", \"user_id\": 7, \"name\": \"c1\", \"longitude\": \"-7.742744\"}");
        // 100.01km
        Customer c2 = Customer.fromFileEntry("{\"latitude\": \"52.725447\", \"user_id\": 4, \"name\": \"c2\", \"longitude\": \"-7.742844\"}");
        // 31.90km
        Customer c3 = Customer.fromFileEntry("{\"latitude\": \"53.729447\", \"user_id\": 2, \"name\": \"c3\", \"longitude\": \"-6.742744\"}");
        // 584.46km
        Customer c4 = Customer.fromFileEntry("{\"latitude\": \"62.725447\", \"user_id\": 8, \"name\": \"c4\", \"longitude\": \"-17.742844\"}");
        // 3.32km
        Customer c5 = Customer.fromFileEntry("{\"latitude\": \"53.249428\", \"user_id\": 1, \"name\": \"c5\", \"longitude\": \"-6.307664\"}");

        List<Customer> result = InviteRuler.getSortedInvitedCustomers(Arrays.asList(c1, c2, c3, c4, c5));
        Assertions.assertEquals(result.size(), 3);
        Assertions.assertEquals(result.get(0), c5);
        Assertions.assertEquals(result.get(1), c3);
        Assertions.assertEquals(result.get(2), c1);
    }
}
