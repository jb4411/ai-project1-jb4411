import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The entry point for route finding using tree searches.
 *
 * The main Search class is run as:
 * $java Search [inputFile] [outputFile]
 * [inputFile]: the name of the input file, or "-" for stdin
 * [outputFile]: the name of the output file, or "-" for stdout
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class Search {

    /**
     * The main program.
     *
     * @param args Command line arguments; inputFile outputFile (or "-" for stdin or stdout)
     * @throws FileNotFoundException if "city.dat", "edge.dat", or the input file cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: java Search inputFile outputFile");
            System.exit(0);
        } else {
            // create a Scanner for input
            Scanner in;
            if (args[0].equals("-")) {
                in = new Scanner(System.in);
            } else {
                in = OpenFile(args[0]);
            }

            // create a PrintStream for output
            PrintStream out;
            if (args[1].equals("-")) {
                out = new PrintStream(System.out);
            } else {
                out = new PrintStream(args[1]);
            }

            // open city.dat
            Scanner cities = OpenFile("city.dat");
            // open edge.dat
            Scanner edges = OpenFile("edge.dat");
            // create city graph
            CityGraph cityGraph = new CityGraph(cities, edges);

            // get start
            String start = in.nextLine().strip();
            // get goal
            String goal = in.nextLine().strip();

            // run BFS
            BFS bfs = new BFS(cityGraph);
            bfs.search(start, goal);
            outputPath(bfs.getPath(), out, "Breadth-First");

            // run DFS
            DFS dfs = new DFS(cityGraph);
            dfs.search(start, goal);
            outputPath(dfs.getPath(), out, "Depth-First");

            // run A*
            AStar aStar = new AStar(cityGraph);
            aStar.search(start, goal);
            outputPath(aStar.getPath(), out, "A*");

            // close output PrintStream
            out.close();
        }
    }

    /**
     * A static helper method for opening files.
     *
     * @param fileName the name of the file to open
     * @return a scanner for the opened file
     * @throws FileNotFoundException if the file is not found
     */
    public static Scanner OpenFile(String fileName) throws FileNotFoundException {
        Scanner in = null;
        try {
            in = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            System.exit(0);
        }
        return in;
    }

    /**
     * A static helper method for outputting the results of running a search method.
     *
     * @param path an array of CityNodes to output
     * @param out the PrintStream to send output to
     * @param searchName the name of the search method used
     */
    public static void outputPath(ArrayList<CityNode> path, PrintStream out, String searchName) {
        out.println("\n" + searchName + " Search Results: ");
        double distance = 0;
        CityNode cityNode;
        for (int i = 0; i < path.size(); i++) {
            cityNode = path.get(i);
            if (i + 1 < path.size()) {
                distance += cityNode.getDistance(path.get(i + 1));
            }
            out.println(cityNode);
        }
        out.println("That took " + (path.size() - 1) + " hops to find.");
        out.println("Total distance = " + ((int) Math.round(distance)) + " miles.\n");
    }
}
