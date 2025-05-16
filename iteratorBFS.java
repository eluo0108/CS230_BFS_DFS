import java.util.LinkedList;
import java.util.Queue;

public class iteratorBFS {
    private int numVertices;
    private String[] vertices;
    private boolean[][] adjMatrix;
    
    public iteratorBFS(int numVertices, String[] vertices, boolean[][] adjMatrix) {
        this.numVertices = numVertices;
        this.vertices = vertices;
        this.adjMatrix = adjMatrix;
    }

    public LinkedList<String> bfs(String start) {
        LinkedList<String> iter = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[numVertices];

        int startIndex = getIndex(start);
        if (startIndex == -1) return iter;

        queue.add(startIndex);
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();
            iter.add(vertices[currentIndex]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[currentIndex][i] && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        return iter;
    }

    private int getIndex(String s) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(s)) return i;
        }
        return -1;
    }
}

