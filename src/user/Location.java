package user;


//Should this be a class or an interface

public class Location {
    // variables for latitude and longitude
    protected double lat;
    protected double lon;

    // Constructor
    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    /*
     * getters, setters, toString?
     */
    }
    public void setLocation(double lat, double lon) {
    	this.lat = lat;
    	this.lon = lon;
    }
    
    public Double[] getLocation() {
    	Double [] e = {this.lat, this.lon};
    	return e;
    }
}
    