
/**
 * Write a description of class iteratorDFS here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class iteratorDFS<T> {
    private int numVertices;
    private T[] vertices;
    private boolean[][] adjMatrix;

    public iteratorDFS(int numVertices, T[] vertices, boolean[][] adjMatrix) {
        this.numVertices = numVertices;
        this.vertices = vertices;
        this.adjMatrix = adjMatrix;
    }

    public List<T> dfs(int startIndex) {
        Stack<Integer> stack = new Stack<>();
        List<T> result = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];

        if (startIndex < 0 || startIndex >= numVertices) return result;

        stack.push(startIndex);
        visited[startIndex] = true;
        result.add(vertices[startIndex]);

        while (!stack.isEmpty()) {
            int current = stack.peek();
            boolean found = false;

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[current][i] && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                    result.add(vertices[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                stack.pop();
            }
        }

        return result;
    }
}
