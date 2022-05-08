Route Finding Using Tree Searches
CSCI 331 - Project 1
Jesse Burdick-Pless jb4411@g.rit.edu


How to run it:

The main Search class is run as:
    $java Search [inputFile] [outputFile]
        [inputFile]: the name of the input file, or "-" for stdin
        [outputFile]: the name of the output file, or "-" for stdout

After reading in the names of the starting city and the goal city from the specified input, the path between those
cites is found with Breadth-First Search, Depth-First Search, and A*. After each algorithm runs, the results are sent
to the specified output. The output includes the name of the name of the algorithm, the path found, the number of hops
in the path, and the total distance of that path in miles.