import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Google OA 2019 - intern tokyo
 * 
 * Source: https://leetcode.com/discuss/interview-question/356378/
 */

class MinDistanceCalculator {

    public int findMinDistanceToFarthestNode(int vertices, int[][] edges) {
        // Build tree as adjacency list
        Map<Integer, List<Integer>> adjacencyList = buildAdjacencyList(edges);

        // Create array to hold distance from specific node to other node
        int[] distanceToNode = new int[vertices];

        // First BFS from random node to find furthest node from it
        int firstFurthestNode = bfs(1, adjacencyList, distanceToNode);

        // Second BFS from the furthest node above to find the furthest node from it
        int secondFurthestNode = bfs(firstFurthestNode, adjacencyList, distanceToNode);

        // Find max distance (diameter) from the array above, populated after second bfs
        int graphDiameter = findGraphDiameter(distanceToNode);

        // Find max distance to furthest node
        if (graphDiameter % 2 == 0)
            return graphDiameter / 2;
        else
            return graphDiameter / 2 + 1;
    }

    private Map<Integer, List<Integer>> buildAdjacencyList(int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            updateNeighbours(adjacencyList, node1, node2);
            updateNeighbours(adjacencyList, node2, node1);
        }
        return adjacencyList;
    }

    private void updateNeighbours(Map<Integer, List<Integer>> adjacencyList, int source, int neighbour) {
        List<Integer> neighbours = adjacencyList.getOrDefault(source, new ArrayList<>());
        neighbours.add(neighbour);
        adjacencyList.put(source, neighbours);
    }

    private int bfs(int startNode, Map<Integer, List<Integer>> adjacencyList, int[] distanceToNode) {

        // Set up variables, queue, populate distanceToNode[] with -1 to represent
        // unvisited
        int furthestNode = 1;
        int furthestDistance = Integer.MIN_VALUE;

        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(distanceToNode, -1);

        // Put start node in queue
        queue.add(startNode);
        // Set distance of the first node to 0 (0 edge to itself)
        distanceToNode[startNode - 1] = 0;

        // While queue is not empty
        while (!queue.isEmpty()) {
            // Pop node
            int curNode = queue.remove();

            // Iterate through node's neighbours
            for (int neighbour : adjacencyList.get(curNode)) {
                // If neighbour is not visited (distance not -1)
                if (distanceToNode[neighbour - 1] == -1) {
                    // Calculate distance by taking curNode distance and add 1
                    // Update distance in array as a way of marking it as visited
                    distanceToNode[neighbour - 1] = distanceToNode[curNode - 1] + 1;

                    // Add to queue to process next
                    queue.add(neighbour);

                    // If the distance is bigger than current furthest one, update
                    if (distanceToNode[neighbour - 1] > furthestDistance)
                        furthestNode = neighbour;
                }
            }
        }
        return furthestNode;
    }

    private int findGraphDiameter(int[] distanceToNode) {
        int diameter = Integer.MIN_VALUE;
        for (int distance : distanceToNode) {
            if (distance > diameter)
                diameter = distance;
        }
        return diameter;
    }
}

public class MinDistanceToTheFarthestNode {

    private static Scanner inputReader;
    private static MinDistanceCalculator calculator = new MinDistanceCalculator();

    private static int[][] buildInputs(int vertices) {
        int[][] edges = new int[vertices][2];
        for (int verticeNum = 0; verticeNum < vertices - 1; verticeNum++) {
            System.out.println("Enter edges #" + (verticeNum + 1) + ":");
            String str = inputReader.nextLine();
            String[] edge = str.split("\\s");
            System.out.println("Edges: " + str);
            for (int idx = 0; idx < edge.length; idx++) {
                int node = Integer.parseInt(edge[idx]);
                edges[verticeNum][idx] = node;
            }
        }
        return edges;
    }

    private static void readConsoleInput() {
        inputReader = new Scanner(System.in);
        // Get number of test cases
        System.out.println("Number of test cases: ");
        int tests = Integer.parseInt(inputReader.nextLine());
        // for loop test cases
        for (int testNum = 0; testNum < tests; testNum++) {
            // - build inputs
            System.out.println("Enter number of vertices: ");
            int vertices = Integer.parseInt(inputReader.nextLine());
            int[][] edges = buildInputs(vertices);

            // - call method and pass in inputs
            System.out.println(calculator.findMinDistanceToFarthestNode(vertices, edges));
            System.out.println("----------------");
        }
        inputReader.close();
    }

    private static void readTextFileInput() {
        System.out.println("Read file....");
        try {
            File file = new File("./test.txt");
            inputReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        int tests = Integer.parseInt(inputReader.nextLine());
        System.out.println("Number of test cases: " + tests);
        // for loop test cases
        for (int testNum = 0; testNum < tests; testNum++) {
            // - build inputs
            int vertices = Integer.parseInt(inputReader.nextLine());
            System.out.println("Number of vertices: " + vertices);
            int[][] edges = buildInputs(vertices);

            // - call method and pass in inputs
            System.out.println(calculator.findMinDistanceToFarthestNode(vertices, edges));
            System.out.println("----------------");
        }
        inputReader.close();
    }

    public static void main(String[] args) {

        // readConsoleInput();

        // readTextFileInput();

        int vertices = 6;
        int[][] edges = new int[][] { { 1, 4 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        int expectedResult = 2;
        int actualResult = calculator.findMinDistanceToFarthestNode(vertices, edges);
        System.out.println("Actual result: " + actualResult + ", expected result " + expectedResult + ". Result is "
                + (expectedResult == actualResult));

        vertices = 6;
        edges = new int[][] { { 1, 3 }, { 4, 5 }, { 5, 6 }, { 3, 2 }, { 3, 4 } };
        expectedResult = 2;
        actualResult = calculator.findMinDistanceToFarthestNode(vertices, edges);
        System.out.println("Actual result: " + actualResult + ", expected result " + expectedResult + ". Result is "
                + (expectedResult == actualResult));

        vertices = 2;
        edges = new int[][] { { 1, 2 } };
        expectedResult = 1;
        actualResult = calculator.findMinDistanceToFarthestNode(vertices, edges);
        System.out.println("Actual result: " + actualResult + ", expected result " + expectedResult + ". Result is "
                + (expectedResult == actualResult));

        vertices = 10;
        edges = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 8 }, { 8, 9 },
                { 9, 10 } };
        expectedResult = 5;
        actualResult = calculator.findMinDistanceToFarthestNode(vertices, edges);
        System.out.println("Actual result: " + actualResult + ", expected result " + expectedResult + ". Result is "
                + (expectedResult == actualResult));

        vertices = 10;
        edges = new int[][] { { 7, 8 }, { 7, 9 }, { 4, 5 }, { 1, 3 }, { 3, 4 }, { 6, 7 }, { 4, 6 }, { 2, 3 },
                { 9, 10 } };
        expectedResult = 3;
        actualResult = calculator.findMinDistanceToFarthestNode(vertices, edges);
        System.out.println("Actual result: " + actualResult + ", expected result " + expectedResult + ". Result is "
                + (expectedResult == actualResult));
    }
}