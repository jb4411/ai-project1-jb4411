import java.util.Objects;
import java.util.TreeSet;

/**
 * This class represents a city in the graph of cities.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class CityNode implements Comparable<CityNode> {
    /** the name of this city */
    private final String city;
    /** the state this city is in */
    private final String state;
    /** this city's latitude */
    private final double latitude;
    /** this city's longitude */
    private final double longitude;
    /** a set of cities this city is directly connected to */
    private TreeSet<CityNode> connectedCities;

    /**
     * Create a new CityNode.
     *
     * @param city the name of this city
     * @param state the state this city is in
     * @param latitude this city's latitude
     * @param longitude this city's longitude
     */
    public CityNode(String city, String state, double latitude, double longitude) {
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.connectedCities = new TreeSet<>();
    }

    /**
     * Return the distance in miles between this city and the city passed in.
     *
     * @param other the other city
     * @return the distance in miles between this city and the other city
     */
    public double getDistance(CityNode other) {
        double latitudes = (this.latitude - other.latitude) * (this.latitude - other.latitude);
        double longitudes = (this.longitude - other.longitude) * (this.longitude - other.longitude);
        return Math.sqrt(latitudes + longitudes) * 100;
    }

    /**
     * Add the city passed in to the set of cities directly connected to this city.
     * Then add this city the set of cities directly connected to the city passed in.
     *
     * @param other the other city
     */
    public void addConnectedCity(CityNode other) {
        this.connectedCities.add(other);
        other.connectedCities.add(this);
    }

    /**
     * Return the set of cities directly connected to this city.
     *
     * @return the set of cities directly connected to this city
     */
    public TreeSet<CityNode> getConnectedCities() {
        return connectedCities;
    }

    /**
     * Compare this city to the city passed in. Returns the result of comparing the two cities' names.
     *
     * @param other the city to compare this city to
     * @return the result of comparing the two cities' names
     */
    @Override
    public int compareTo(CityNode other) {
        return this.city.compareTo(other.city);
    }

    /**
     * Check if two CityNodes are equal.
     *
     * @param obj the object to compare with
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof CityNode)) return false;
        final CityNode o = (CityNode) obj;
        return Objects.equals(this.state, o.state) && (this.latitude == o.latitude) && (this.longitude == o.longitude);
    }

    /**
     * Return the string representation of this CityNode.
     *
     * @return the string representation of this CityNode
     */
    @Override
    public String toString() {
        return this.city;
    }
}