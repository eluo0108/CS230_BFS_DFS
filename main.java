import java.util.*;

public class main {
    public static void main(String[] args) {
        String[] vertices = {"A", "B", "C", "D"};
        boolean[][] adj = {
            {false, true, true, false},   // A → B, A → C
            {false, false, true, false},  // B → C
            {false, false, false, true},  // C → D
            {false, false, false, false}  // D
        };

        // DFS
        iteratorDFS<String> dfs = new iteratorDFS<>(4, vertices, adj);
        List<String> dfsOrder = dfs.dfs(0); // Start from "A"
        System.out.println("DFS: " + dfsOrder); // Output: [A, B, C, D]

        // BFS
        iteratorBFS bfs = new iteratorBFS(4, vertices, adj);
        LinkedList<String> traversal = bfs.bfs("A");
        System.out.println("BFS: " + traversal);
    }
}