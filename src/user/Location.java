package user;

/**
 * Represents a location identified by latitude and longitude coordinates.
 */
public class Location {
    // variables for latitude and longitude
    protected double lat;
    protected double lon;

    /**
     * Constructs a new Location object with the specified latitude and longitude coordinates.
     * 
     * @param lat the latitude coordinate
     * @param lon the longitude coordinate
     */
    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Sets the latitude and longitude coordinates of the location.
     * 
     * @param lat the latitude coordinate
     * @param lon the longitude coordinate
     */
    public void setLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Sets the latitude and longitude coordinates of the location based on another Location object.
     * 
     * @param l the Location object containing latitude and longitude coordinates
     */
    public void setLocation(Location l) {
        this.lat = l.getLatitude();
        this.lon = l.getLongitude();
    }

    /**
     * Retrieves the latitude and longitude coordinates of the location.
     * 
     * @return an array containing latitude and longitude coordinates
     */
    public Double[] getLocation() {
        Double[] coordinates = { this.lat, this.lon };
        return coordinates;
    }

    /**
     * Retrieves the latitude coordinate of the location.
     * 
     * @return the latitude coordinate
     */
    public double getLatitude() {
        return lat;
    }

    /**
     * Retrieves the longitude coordinate of the location.
     * 
     * @return the longitude coordinate
     */
    public double getLongitude() {
        return lon;
    }
}
