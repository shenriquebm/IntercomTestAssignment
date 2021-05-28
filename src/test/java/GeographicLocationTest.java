import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeographicLocationTest {

    @Test
    public void shouldGetEstimatedDistance() throws InvalidGeographicLocationException {
        GeographicLocation location1 = new GeographicLocation(33.433, -1.934);
        GeographicLocation location2 = new GeographicLocation(33.453, -1.944);
        GeographicLocation location3 = new GeographicLocation(-53.855, 17.4854);
        Assertions.assertTrue(location1.distanceTo(location2) < 1.0);
        Assertions.assertEquals(Math.floor(location1.distanceTo(location3)), 1799);
        Assertions.assertTrue(location1.distanceTo(location3) > location1.distanceTo(location2));
    }

    @Test
    public void shouldFailForInvalidGeographicLocation() {
        Assertions.assertThrows(InvalidGeographicLocationException.class,() -> new GeographicLocation(91.433, -1.934));
        Assertions.assertThrows(InvalidGeographicLocationException.class,() -> new GeographicLocation(33.453, -188.944));
    }
}
