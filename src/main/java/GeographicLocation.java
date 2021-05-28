import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeographicLocation {
    private double longitude;
    private double latitude;
    private static final Logger logger = Logger.getLogger(GeographicLocation.class.getName());

    public GeographicLocation(double latitude, double longitude) throws InvalidGeographicLocationException {
        if (Math.abs(latitude) > 90) {
            logger.warning("Invalid latitude found. Found latitude = " + latitude);
            throw new InvalidGeographicLocationException("Invalid latitude. Received " + latitude);
        }

        if (Math.abs(longitude) > 180) {
            logger.warning("Invalid longitude found. Found longitude = " + longitude);
            throw new InvalidGeographicLocationException("Invalid longitude. Received " + longitude);
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Calculates the distance between this geographic location and some other geographic location.
     * Formula reference: https://en.wikipedia.org/wiki/Great-circle_distance#Formulae - first equation.
     * @param other Calculate the distance to this point
     * @return the estimated distance between two points.
     */
    public double distanceTo(GeographicLocation other) {
        double earthRadius = 6371.009;
        double longitudeDiff = Math.toRadians(this.longitude - other.longitude);

        double latitudeRad = Math.toRadians(this.latitude);
        double latitudeOtherRad = Math.toRadians(this.latitude);

        double distance = Math.sin(latitudeRad) * Math.sin(latitudeOtherRad);
        distance += Math.cos(latitudeRad) * Math.cos(latitudeOtherRad) * Math.cos(longitudeDiff);
        distance = earthRadius * Math.acos(distance);

        if (InviteApplication.verboseMode) {
            logger.log(Level.INFO, "Estimated distance between " + this + " and " + other + " is " + distance + "km");
        }

        return distance;
    }

    @Override
    public String toString() {
        return "GeographicLocation{" +
            "longitude=" + longitude +
            ", latitude=" + latitude +
            '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeographicLocation that = (GeographicLocation) o;
        return Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
