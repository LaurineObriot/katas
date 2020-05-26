import java.util.*;
import java.math.*;

/**
 * This class is used to return both the last vertex encoutered,
 * and the excentricity of the starting vertex after a BFS
*/
class Result {
    public int length;
    public Node last;
}

/**
 * This class is representing a node of the graph.
 * neighbs is a list of the node neighbours
 * n is a unique constant id
*/
class Node {
    private ArrayList<Node> neighbs;
    private int n;

    public Node(int n){
        this.n = n;
        neighbs = new ArrayList<Node>();
    }

    public void add(Node n){
        neighbs.add(n);
    }

    public ArrayList<Node> neighbs(){
        return neighbs;
    }

    public int n(){
        return n;
    }
}

class Solution {
    /**
     * Reads the standard input and constructs the graph to study
     * @return ArrayList of the vertices of the graph
    */
    public static ArrayList<Node> readInfos()
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Node> graph = new ArrayList<Node>();
        int N, x, y;

        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            x = in.nextInt();
            y = in.nextInt();
            /*As node ids are consecutive and start from 0, when discovering a
             new node i, adds nodes to the graph until there are i+1 nodes */
            while(graph.size() <= Math.max(x,y))
                graph.add(new Node(graph.size()));
            graph.get(x).add(graph.get(y));
            graph.get(y).add(graph.get(x));
        }

        return graph;
    }

    /**
     * Runs a Breadth First Search and returns the last node encountered,
     * and the starting node excentricity
     * Complexity : O(|V|)
     * @param start starting vertex
     * @param graph graph to search
     * @return Result containing one of the most distant nodes from 'start',
     * and the distance between them
	*/
    public static Result BFS (Node start, ArrayList<Node> graph)
    {
        Result res = new Result();

        int[] depth = new int[graph.size()];
        for (int i = 0 ; i < graph.size() ; i++)
            depth[i]= -1;
        depth[start.n()] = 0;

        Stack<Node> stack = new Stack<Node>();
        stack.push(start);

        Node father, son;
        while (!stack.empty()) {
            father = stack.pop();
            Iterator<Node> sons = father.neighbs().iterator();
            while (sons.hasNext()) {
                son = sons.next();
                if (depth[son.n()] == -1) {
                    depth[son.n()] = depth[father.n()] + 1;
                    if (depth[son.n()] > res.length) {
                        res.length = depth[son.n()];
                        res.last = son;
                    }
                    stack.push(son);
                }
            }
        }
        return res;
    }

    public static void main(String args[])
    {
        ArrayList<Node> graph = readInfos();
        Result r = BFS(graph.get(0), graph);
        r = BFS(r.last, graph);
        System.out.println((r.length + 1) / 2);
    }
}
