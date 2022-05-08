import java.util.ArrayList;

/**
 * This class represents a path between cities.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class Path implements Comparable<Path> {
    /** the current path */
    private ArrayList<CityNode> path;
    /** the goal CityNode */
    private final CityNode goalNode;
    /** the path cost so far */
    private Double pathCost;
    /** the estimated total cost of this path to the goal city */
    private Double estimatedTotalCost;

    /**
     * Create a new Path.
     *
     * @param startNode the CityNode this path starts at
     * @param goalNode the goal CityNode
     */
    public Path(CityNode startNode, CityNode goalNode) {
        this.path = new ArrayList<>();
        this.path.add(startNode);
        this.goalNode = goalNode;
        this.pathCost = 0.0;
        this.estimatedTotalCost = startNode.getDistance(goalNode);
    }

    /**
     * Create a new Path from the elements passed in, and then add nextNode to this Path.
     *
     * @param path an array of CityNodes making up the path so far
     * @param goalNode the goal CityNode
     * @param pathCost the path cost so far
     * @param nextNode the CityNode to add to this Path
     */
    private Path(ArrayList<CityNode> path, CityNode goalNode, double pathCost, CityNode nextNode) {
        this.path = new ArrayList<>(path);
        this.goalNode = goalNode;
        this.pathCost = pathCost;
        this.addNode(nextNode);
    }

    /**
     * A copy constructor to create a copy of this Path and add nextNode to the copy.
     *
     * @return a copy of this Path with nextNode added
     */
    public Path getCopy(CityNode nextNode) {
        return new Path(this.path, this.goalNode, this.pathCost, nextNode);
    }

    /**
     * Add a CityNode to this Path, then update the path cost so far and the estimated total cost of this path.
     *
     * @param nextNode the CityNode to add to this Path
     */
    public void addNode(CityNode nextNode) {
        this.pathCost += this.getCurrentNode().getDistance(nextNode);
        this.estimatedTotalCost = this.pathCost + nextNode.getDistance(goalNode);
        this.path.add(nextNode);
    }

    /**
     * Return the current CityNode from this Path.
     *
     * @return the current CityNode from this Path
     */
    public CityNode getCurrentNode() {
        return this.path.get(this.path.size() - 1);
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
     * Check if the current Path reaches the goal CityNode.
     *
     * @return whether the current Path reaches the goal CityNode or not
     */
    public boolean isGoal() {
        return this.getCurrentNode().equals(this.goalNode);
    }

    /**
     * Compare this Path to the Path passed in. Returns the result of comparing the two Paths' estimated total costs.
     *
     * @param other the Path to compare this Path to
     * @return the result of comparing the two Paths' estimated total costs
     */
    @Override
    public int compareTo(Path other) {
        return this.estimatedTotalCost.compareTo(other.estimatedTotalCost);
    }
}
