import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerFileReaderTest {
    @Test
    public void shouldParseCorrectly() throws InvalidGeographicLocationException {
        CustomerFileReader reader = new CustomerFileReader(getClass().getClassLoader().getResource("testCustomers.txt").getFile());

        List<Customer> customers = reader.getCustomers();

        Customer c1 = Customer.fromFileEntry("{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}");
        Customer c2 = Customer.fromFileEntry("{\"latitude\": \"52.966\", \"user_id\": 15, \"name\": \"Michael Ahearn\", \"longitude\": \"-6.463\"}");
        Customer c3 = Customer.fromFileEntry("{\"latitude\": \"52.366037\", \"user_id\": 16, \"name\": \"Ian Larkin\", \"longitude\": \"-8.179118\"}");

        Assertions.assertEquals(customers.get(0), c1);
        Assertions.assertEquals(customers.get(1), c2);
        Assertions.assertEquals(customers.get(2), c3);

        Assertions.assertEquals(customers.size(), 3);

    }

    @Test
    public void shouldParseFileEvenWithErrors() throws InvalidGeographicLocationException {
        CustomerFileReader reader = new CustomerFileReader(getClass().getClassLoader().getResource("someErrors.txt").getFile());

        List<Customer> customers = reader.getCustomers();

        Customer c1 = Customer.fromFileEntry("{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}");
        Customer c2 = Customer.fromFileEntry("{\"latitude\": \"52.366037\", \"user_id\": 16, \"name\": \"Ian Larkin\", \"longitude\": \"-8.179118\"}");

        Assertions.assertEquals(customers.get(0), c1);
        Assertions.assertEquals(customers.get(1), c2);

        Assertions.assertEquals(customers.size(), 2);
    }

    @Test
    public void shouldReturnEmptyListForEmptyFile() {
        CustomerFileReader reader = new CustomerFileReader(getClass().getClassLoader().getResource("empty.txt").getFile());

        List<Customer> customers = reader.getCustomers();

        Assertions.assertEquals(customers.size(), 0);

    }
}
