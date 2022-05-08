import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class represents the Depth-First Search algorithm.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class DFS {
    /** the graph of cities being searched */
    private final CityGraph cityGraph;
    /** the current path */
    private ArrayList<CityNode> path = null;

    /**
     * Create a new DFS.
     *
     * @param cityGraph the graph of cities being searched
     */
    public DFS(CityGraph cityGraph) {
        this.cityGraph = cityGraph;
    }

    /**
     * Return the current path.
     *
     * @return the current path
     */
    public ArrayList<CityNode> getPath() {
        return path;
    }

    /**
     * Use Depth-First Search to find a path between the start city passed in and the goal city passed in.
     *
     * @param start the name of the starting city
     * @param goal the name of the goal city
     */
    public void search(String start, String goal) {
        // initialize path and add the starting city
        this.path = new ArrayList<>();
        this.path.add(this.cityGraph.getCity(start));

        // create a queue to hold possible paths to search, and add the current path
        ArrayDeque<ArrayList<CityNode>> pathQueue = new ArrayDeque<>();
        pathQueue.add(this.path);

        // create a set of open nodes, and a set of closed nodes
        TreeSet<CityNode> openNodes = new TreeSet<>();
        TreeSet<CityNode> closedNodes = new TreeSet<>();

        // get the goal city
        CityNode goalNode = this.cityGraph.getCity(goal);

        CityNode currentNode;
        while (!pathQueue.isEmpty()) {
            // remove the next path from the queue
            this.path = pathQueue.removeFirst();
            // get the current CityNode
            currentNode = this.path.get(this.path.size() - 1);
            openNodes.add(currentNode);
            if (currentNode.equals(goalNode)) {
                return;
            }

            if (!closedNodes.contains(currentNode)) {
                closedNodes.add(currentNode);
                // get the set of cities connected to the current city
                TreeSet<CityNode> connectedCities = currentNode.getConnectedCities();
                // if the goal city is connected to the current city, return
                if (connectedCities.contains(goalNode)) {
                    this.path.add(goalNode);
                    return;
                }

                // generate new paths from the current city, and add them to an array
                ArrayList<ArrayList<CityNode>> newPaths = new ArrayList<>();
                for (CityNode cityNode: connectedCities) {
                    if (!openNodes.contains(cityNode) && !closedNodes.contains(cityNode)) {
                        ArrayList<CityNode> newPath = new ArrayList<>(this.path);
                        newPath.add(cityNode);
                        newPaths.add(newPath);
                        openNodes.add(cityNode);
                    }
                }

                // add each new path in the array to the beginning of the pathQueue
                for (int i = newPaths.size() - 1; i >= 0; i--) {
                    pathQueue.addFirst(newPaths.get(i));
                }
            }
        }
    }
}