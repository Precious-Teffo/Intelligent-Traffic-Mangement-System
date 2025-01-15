
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class EmergencyVehicleManagement {
    // Traffic simulation object
    private TrafficSimulation trafficSimulation;

    // Emergency vehicle coordinates
    private int emergencyVehicleX;
    private int emergencyVehicleY;

    // Destination coordinates
    private int destinationX;
    private int destinationY;

    /**
     * Constructor to initialize emergency vehicle management.
     * 
     * @param trafficSimulation Traffic simulation object
     */
    public EmergencyVehicleManagement(TrafficSimulation trafficSimulation) {
        this.trafficSimulation = trafficSimulation;
    }

    /**
     * Sets the emergency vehicle coordinates.
     * 
     * @param emergencyVehicleX Emergency vehicle x-coordinate
     * @param emergencyVehicleY Emergency vehicle y-coordinate
     */
    public void setEmergencyVehicleCoordinates(int emergencyVehicleX, int emergencyVehicleY) {
        this.emergencyVehicleX = emergencyVehicleX;
        this.emergencyVehicleY = emergencyVehicleY;
    }

    /**
     * Sets the destination coordinates.
     * 
     * @param destinationX Destination x-coordinate
     * @param destinationY Destination y-coordinate
     */
    public void setDestinationCoordinates(int destinationX, int destinationY) {
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    /**
     * Manages emergency vehicle routing to ensure rapid response.
     */
    public void manageEmergencyVehicleRouting() {
        // Clear traffic to ensure rapid response
        clearTraffic();

        // Find the shortest path to the destination
        List<Node> shortestPath = findShortestPath(emergencyVehicleX, emergencyVehicleY);

        // Update the emergency vehicle coordinates
        for (Node node : shortestPath) {
            emergencyVehicleX = node.getX();
            emergencyVehicleY = node.getY();

            // Update the traffic simulation
            trafficSimulation.updateTrafficNetwork(emergencyVehicleX, emergencyVehicleY);
        }
    }

    /**
     * Clears traffic to ensure rapid response.
     */
    private void clearTraffic() {
        // Clear traffic in the surrounding area
        for (int x = emergencyVehicleX - 1; x <= emergencyVehicleX + 1; x++) {
            for (int y = emergencyVehicleY - 1; y <= emergencyVehicleY + 1; y++) {
                // Check if the coordinates are within the grid boundaries
                if (x >= 0 && x < trafficSimulation.getGridSizeX() && y >= 0 && y < trafficSimulation.getGridSizeY()) {
                    trafficSimulation.clearTraffic(x, y);
                }
            }
        }
    }

    /**
     * Finds the shortest path to the destination using A* algorithm.
     * 
     * @param startX Starting x-coordinate
     * @param startY Starting y-coordinate
     * @return Shortest path to the destination
     */
    private List<Node> findShortestPath(int startX, int startY) {
        // Create a priority queue to store nodes to be processed
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Create a set to store visited nodes
        Set<Node> visited = new HashSet<>();

        // Create a node for the starting point
        Node startNode = new Node(startX, startY, 0, heuristic(startX, startY));
        queue.add(startNode);

        while (!queue.isEmpty()) {
            // Get the node with the lowest f-score
            Node currentNode = queue.poll();

            // Check if the current node is the destination
            if (currentNode.getX() == destinationX && currentNode.getY() == destinationY) {
                // Reconstruct the shortest path
                return reconstructPath(currentNode);
            }

            // Mark the current node as visited
            visited.add(currentNode);

            // Get the neighbors of the current node
            List<Node> neighbors = getNeighbors(currentNode);

            for (Node neighbor : neighbors) {
                // Check if the neighbor has been visited
                if (!visited.contains(neighbor)) {
                    // Calculate the tentative g-score
                    int tentativeGScore = currentNode.getGScore() + 1;

                    // Check if the neighbor is already in the queue
                    if (queue.contains(neighbor)) {
                        // Check if the tentative g-score is lower than the current g-score
                        if (tentativeGScore < neighbor.getGScore()) {
                            // Update the g-score and f-score
                            neighbor.setGScore(tentativeGScore);
                            neighbor.setFScore(tentativeGScore + heuristic(neighbor.getX(), neighbor.getY()));
                        }
                    } else {
                        // Add the neighbor to the queue
                        neighbor.setGScore(tentativeGScore);
                        neighbor.setFScore(tentativeGScore + heuristic(neighbor.getX(), neighbor.getY()));
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Return an empty list if no path is found
        return Collections.emptyList();
    }

   /**
     * Reconstructs the shortest path from the destination node.
     * 
     * @param destinationNode Destination node
     * @return Shortest path to the destination
     */
    private List<Node> reconstructPath(Node destinationNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = destinationNode;

        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = currentNode.getParent();
        }

        return path;
    }

    /**
     * Gets the neighbors of a node.
     * 
     * @param node Node to get neighbors for
     * @return List of neighbors
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        // Check if the node is within the grid boundaries
        if (node.getX() > 0) {
            neighbors.add(new Node(node.getX() - 1, node.getY(), node.getGScore() + 1, heuristic(node.getX() - 1, node.getY())));
        }

        if (node.getX() < trafficSimulation.getGridSizeX() - 1) {
            neighbors.add(new Node(node.getX() + 1, node.getY(), node.getGScore() + 1, heuristic(node.getX() + 1, node.getY())));
        }

        if (node.getY() > 0) {
            neighbors.add(new Node(node.getX(), node.getY() - 1, node.getGScore() + 1, heuristic(node.getX(), node.getY() - 1)));
        }

        if (node.getY() < trafficSimulation.getGridSizeY() - 1) {
            neighbors.add(new Node(node.getX(), node.getY() + 1, node.getGScore() + 1, heuristic(node.getX(), node.getY() + 1)));
        }

        return neighbors;
    }

    /**
     * Calculates the heuristic value for a node.
     * 
     * @param x X-coordinate of the node
     * @param y Y-coordinate of the node
     * @return Heuristic value
     */
    private int heuristic(int x, int y) {
        return Math.abs(x - destinationX) + Math.abs(y - destinationY);
    }

    void manageEmergencyVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Node class to represent a node in the grid.
     */
    private class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int gScore;
        private int fScore;
        private Node parent;

        public Node(int x, int y, int gScore, int fScore) {
            this.x = x;
            this.y = y;
            this.gScore = gScore;
            this.fScore = fScore;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getGScore() {
            return gScore;
        }

        public void setGScore(int gScore) {
            this.gScore = gScore;
        }

        public int getFScore() {
            return fScore;
        }

        public void setFScore(int fScore) {
            this.fScore = fScore;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fScore, other.fScore);
        }
    }
}