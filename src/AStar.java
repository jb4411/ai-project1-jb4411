import java.util.*;

/**
 * This class represents the A* search algorithm.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class AStar {
    /** the graph of cities being searched */
    private final CityGraph cityGraph;
    /** the current path */
    private Path path;
    /** the sorted set of paths to search */
    private TreeSet<Path> paths;

    /**
     * Create a new AStar.
     *
     * @param cityGraph the graph of cities being searched
     */
    public AStar(CityGraph cityGraph) {
        this.cityGraph = cityGraph;
        this.paths = new TreeSet<>();
    }

    /**
     * Return the current path.
     *
     * @return the current path
     */
    public ArrayList<CityNode> getPath() {
        return path.getPath();
    }

    /**
     * Use A* to find a path between the start city passed in, and the goal city passed in.
     *
     * @param start the name of the starting city
     * @param goal the name of the goal city
     */
    public void search(String start, String goal) {
        // get the goal city
        CityNode goalNode = this.cityGraph.getCity(goal);
        // initialize path to a new Path object
        this.path = new Path(this.cityGraph.getCity(start), goalNode);
        // add path to paths
        this.paths.add(this.path);

        while (!paths.isEmpty()) {
            // get the current path
            this.path = paths.first();
            if (this.path.isGoal()) {
                return;
            }

            paths.remove(this.path);
            // get the set of cities connected to the current city
            TreeSet<CityNode> connectedCities = this.path.getCurrentNode().getConnectedCities();
            // if the goal city is connected to the current city, return
            if (connectedCities.contains(goalNode)) {
                this.path.addNode(goalNode);
                return;
            }

            // generate new Path objects from the current node, and add them to paths
            for (CityNode nextNode : connectedCities) {
                paths.add(this.path.getCopy(nextNode));
            }
        }
    }
}