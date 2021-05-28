import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CustomerFileWriterTest {
    @TempDir
    File testTempDir;

    @Test
    public void shouldWriteOutputFile() throws IOException, InvalidGeographicLocationException {
        File outputfile = new File(testTempDir, "out.txt");
        CustomerFileWriter writer = new CustomerFileWriter(outputfile.getAbsolutePath());

        Assertions.assertEquals(outputfile.getAbsolutePath(), writer.getOutputFileName());

        Customer c1 = Customer.fromFileEntry("{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}");
        Customer c2 = Customer.fromFileEntry("{\"latitude\": \"52.966\", \"user_id\": 15, \"name\": \"Michael Ahearn\", \"longitude\": \"-6.463\"}");
        Customer c3 = Customer.fromFileEntry("{\"latitude\": \"52.366037\", \"user_id\": 16, \"name\": \"Ian Larkin\", \"longitude\": \"-8.179118\"}");

        writer.writeCustomers(Arrays.asList(c1, c2, c3));

        List<String> lines = Files.readAllLines(Paths.get(outputfile.getAbsolutePath()));

        Assertions.assertEquals(lines.size(), 3);
        Assertions.assertEquals(lines.get(0), "{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}");
        Assertions.assertEquals(lines.get(1), "{\"latitude\": \"52.966\", \"user_id\": 15, \"name\": \"Michael Ahearn\", \"longitude\": \"-6.463\"}");
        Assertions.assertEquals(lines.get(2), "{\"latitude\": \"52.366037\", \"user_id\": 16, \"name\": \"Ian Larkin\", \"longitude\": \"-8.179118\"}");

    }
}
