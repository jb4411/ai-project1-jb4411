import java.util.*;

/**
 * This class represents a graph of cities.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class CityGraph {
    /** a HashMap of city names to CityNode objects */
    private HashMap<String, CityNode> cityMap;

    /**
     * Create a new CityGraph.
     *
     * @param cities a Scanner for "city.dat", a file of cities in the format: CITY STATE LATITUDE LONGITUDE
     * @param edges a Scanner for "edge.dat", a file of edges between cities
     */
    public CityGraph(Scanner cities, Scanner edges) {
        this.cityMap = new HashMap<>();
        String[] currentLine;

        // read in each city from "city.dat"
        while (cities.hasNext()) {
            currentLine = cities.nextLine().split("\\s+");
            if (Objects.equals(currentLine[0], "")) {
                continue;
            }
            this.cityMap.put(currentLine[0], new CityNode(currentLine[0], currentLine[1],
                    Double.parseDouble(currentLine[2]), Double.parseDouble(currentLine[3])));
        }
        cities.close();

        // read in each edge from "edge.dat"
        while (edges.hasNext()) {
            currentLine = edges.nextLine().split("\\s+");
            if (Objects.equals(currentLine[0], "")) {
                continue;
            }
            this.cityMap.get(currentLine[0]).addConnectedCity(this.cityMap.get(currentLine[1]));
        }
        edges.close();
    }

    /**
     * Return the CityNode object with the name passed in, if it exists.
     *
     * @param cityName the name of the city to get
     * @return the CityNode object with the name passed in
     */
    public CityNode getCity(String cityName) {
        if (!this.cityMap.containsKey(cityName)) {
            System.err.println("No such city: " + cityName);
            System.exit(0);
        }
        return this.cityMap.get(cityName);
    }
}

