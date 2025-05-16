/**
 * AdjListsGraph 
 *
 * @bwhite4
 * @yluo2
 * @v1
 */
import java.util.*;
import java.io.*;
import javafoundations.*;
import java.util.LinkedList;
import java.util.Queue;

public class AdjListsGraph<T> implements Graph<T>
{
    //vector of generic kind of objects
    public Vector<T> vertices; //new Vector<T>()
    //vector of linked lists ( (adj vtx, adj vtx), (adj vtx, adj vtx)
    public Vector<LinkedList<T>> arcs;//how access elements within linkededlist

    private int numVertices;
    private T start;
    private ArrayQueue traversalQueue;
    private LinkedList iter;
    private T currentVal;
    private boolean[] visited;

    /**
     * Constructor for AdjListsGraph
     */
    public AdjListsGraph()
    {
        numVertices = 0;
        vertices = new Vector<T>();
        arcs = new Vector<LinkedList<T>>();
        iter = new LinkedList<T>();
        traversalQueue = new ArrayQueue<T>();
    }

    /**
     * toString method
     */
    public String toString()
    {
        String str = "Vertices:\n";
        str += vertices.toString() + "\n";
        str += "Number of vertices:\n" + numVertices + "\n";
        str += "Edges:\n";
        for (int i = 0; i < arcs.size(); i++)
        {
            str += "from " + vertices.elementAt(i) + ": " + arcs.get(i).toString() + "\n";
            str += "Number of elements: " + arcs.get(i).size();
        }

        return str;
    }

    /** 
     * Returns the number of vertices in this graph. 
     * 
     * @return the number of vertices in this graph
     */
    public int getNumVertices()
    {
        return vertices.size();
    }

    /** 
     * Returns the number of arcs in this graph.
     * An arc between Verteces A and B exists, if a direct connection
     * from A to B exists.
     * 
     * @return the number of arcs in this graph
     */
    public int getNumArcs()
    {
        return arcs.size();
    }

    /** 
     * Returns a boolean indicating whether this graph is empty or not.
     * A graph is empty when it contains no vertice,and of course, no edges.
     *  
     *  @return true if this graph is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        if ( (getNumVertices() == 0) && (getNumArcs() == 0) )
        {
            return true;
        }
        else
            return false;
    }

    /** 
     * Returns true if an arc (direct connection) exists 
     * from the first vertex to the second, false otherwise
     * 
     * @return true if an arc exists between the first given vertex (vertex1),
     * and the second one (vertex2),false otherwise
     * 
     *  */
    public boolean isArc (T vertex1, T vertex2)
    {
        if (this.arcs.contains(vertex1) && this.arcs.contains(vertex2))
        {
            return true;
        }

        return false;
    }

    /** 
     * Returns true if an edge exists between two given vertices, i.e,
     * an arch exists from the first vertex to the second one, and an arc from
     * the second to the first vertex, false otherwise.
     *  
     * @return true if an edge exists between vertex1 and vertex2, 
     * false otherwise
     * 
     * */
    public boolean isEdge (T vertex1, T vertex2)
    {
        return (isArc (vertex1, vertex2) && isArc (vertex2, vertex1));
    }

    /** 
     * Returns true if the graph is undirected, that is, for every
     * pair of nodes i,j for which there is an arc, the opposite arc
     * is also present in the graph, false otherwise.  
     * 
     * @return true if the graph is undirected, false otherwise
     * */
    public boolean isUndirected(){
        for (int i = 0; i < arcs.size();i++){
            for (int j = 1; j < arcs.size();j++){
                T elem1 = arcs.get(i).get(0);
                T elem2 = arcs.get(i).get(j);
                if (!isEdge(elem1, elem2)){
                    return false;
                }
            }
        }
        return true;
    }

    /** 
     * Adds the given vertex to this graph
     * If the given vertex already exists, the graph does not change
     * 
     * @param The vertex to be added to this graph
     * */
    public void addVertex (T vertex)
    {
        if (!(this.vertices.contains(vertex)))
        {
            this.vertices.add(vertex);
            LinkedList l1 = new LinkedList<T>();
            this.arcs.add(l1);
            numVertices++;
        }
        else{
            System.out.println("Vertex already exist");
        }
    }

    /** 
     * Removes the given vertex from this graph.
     * If the given vertex does not exist, the graph does not change.
     * 
     * @param the vertex to be removed from this graph
     *  */
    public void removeVertex (T vertex)
    {
        if (this.vertices.contains(vertex))
        {
            arcs.remove(vertices.indexOf(vertex));
            this.vertices.remove(vertex);
            numVertices--;
        }
        else{
            System.out.println("Vertex does not exist");
        }
    }

    /** 
     * Inserts an arc between two given vertices of this graph.
     * if at least one of the vertices does not exist, the graph 
     * is not changed.
     * 
     * @param the origin of the arc to be added to this graph
     * @param the destination of the arc to be added to this graph
     * 
     *  */
    public void addArc (T vertex1, T vertex2)
    {
        if ( (this.vertices.contains(vertex1)) && (this.vertices.contains(vertex2)) )
        {
            int index = vertices.indexOf(vertex1);
            LinkedList currentList = this.arcs.get(index);
            System.out.println(currentList);
            currentList.add(vertex2);
        }
        else{
            System.out.println("Vertex already exists");
        }
    }

    /** 
     * Removes the arc between two given vertices of this graph.
     * If one of the two vertices does not exist in the graph,
     * the graph does not change.
     * 
     * @param the origin of the arc to be removed from this graph
     * @param the destination of the arc to be removed from this graph
     * 
     * */
    public void removeArc (T vertex1, T vertex2){
        if ( (this.vertices.contains(vertex1)) && (this.vertices.contains(vertex2)))
        {
            this.arcs.get(arcs.indexOf(vertex1)).remove(vertex2);
        }
        else{
            System.out.println("One of the vertices does not exist");
        }
    }

    /** 
     * Inserts the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be added to this graph
     * @param the destination of the edge to be added to this graph
     * 
     *  */
    public void addEdge (T vertex1, T vertex2){
        addArc (vertex1, vertex2);
        addArc (vertex2, vertex1);
    }

    /** 
     * Removes the edge between the two given vertices of this graph,
     * if both vertices exist, else the graph is not changed.
     * 
     * @param the origin of the edge to be removed from this graph
     * @param the destination of the edge to be removed from this graph
     * 
     */
    public void removeEdge (T vertex1, T vertex2){
        removeArc (vertex1, vertex2);
        removeArc (vertex2, vertex1);
    }

    /** 
     * Return all the vertices, in this graph, adjacent to the given vertex.
     * 
     * @param A vertex in th egraph whose successors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from the given vertex to x (vertex -> x).
     *
     * */
    public LinkedList<T> getSuccessors(T vertex){
        return arcs.get(arcs.indexOf(vertex));
    }

    /** 
     * Return all the vertices x, in this graph, that precede a given
     * vertex.
     * 
     * @param A vertex in the graph whose predecessors will be returned.
     * @return LinkedList containing all the vertices x in the graph,
     * for which an arc exists from x to the given vertex (x -> vertex).
     * 
     * */
    public LinkedList<T> getPredecessors(T vertex){
        LinkedList predecessors = new LinkedList<T>();
        for (int i = 0; i < arcs.size(); i++){
            if (arcs.get(i).contains(vertex)){
                predecessors.add(arcs.get(i).get(0));
            }
        }
        return predecessors;
    }

    /** 
     * Writes this graph into a file in the TGF format.
     * 
     * @param the name of the file where this graph will be written 
     * in the TGF format.
     */
    public void saveToTGF(String tgf_file_name)
    {   try{
            PrintWriter writer = new PrintWriter (new File(tgf_file_name));
        }catch (IOException ex) {
            System.out.println(ex); // Handle file-not-found
        }
    }

    public LinkedList<T> iteratorBFS(){
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }

        start = vertices.get(0);
        System.out.println(start);

        traversalQueue.enqueue(start);
        visited[0] = true;
        
        while (!traversalQueue.isEmpty())
        {
            iter.add(traversalQueue.first());
            int index = vertices.indexOf(traversalQueue.first());
            LinkedList currentList = arcs.get(index);
            //System.out.println(currentList);
            traversalQueue.dequeue();
            for (int vertexIndex = 0; vertexIndex < currentList.size(); vertexIndex++){
                String current = (String)currentList.get(vertexIndex);
                traversalQueue.enqueue(currentList.get(vertexIndex));
                visited[vertexIndex] = true;
            }
        }
        return iter;
    }

    // public boolean indexIsNotValid(T s){
        // boolean result = false;
        // if (vertices.contains(s)){
            // int i = vertices.indexOf(s);
            // System.out.println(i);
            // result = visited[i];
            // System.out.println("result" + result);
        // }
        // return result;
    // }
    
    public String printList()
    {
        String str = "Sorted by BFS:\n";
        for (int i = 0; i < iter.size(); i++)
        {
            str += "element " + i + ": " + iter.get(i) + "\n";
        }

        return str;
    }
    
    public static void main(String[] args){
        //make an adjacent lists
        AdjListsGraph a1 = new AdjListsGraph<String>();
        a1.addVertex("1");
        a1.addVertex("2");
        a1.addVertex("3");
        a1.addVertex("4");
        a1.addVertex("5");
        a1.addArc("1", "2");
        a1.addArc("1", "3");
        a1.addArc("2", "4");
        a1.addArc("2","5");
        System.out.println(a1);
        
        LinkedList l1 = a1.iteratorBFS();
        System.out.println(a1.printList());
        //sort by BFS

    }
}  

