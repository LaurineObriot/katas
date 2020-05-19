import java.util.*;

/**
* <b> Node is the class representing a node of the network. </b> Each node as :
* <ul>
* <li> a unique id, constant
* <li> a list of neighbour nodes
* </ul>
*/
class Node {
    private final int n;
    private LinkedList<Node> neighb;

    public Node(int n) {
        this.n = n;
        neighb = new LinkedList<Node>();
    }

    public void addNeighb(Node m) {
        neighb.add(m);
    }

    public void removeNeighb(Node m) {
        neighb.remove(m);
    }

    public Node[] neighb() {
        return neighb.toArray(new Node[neighb.size()]);
    }

    public int n() {
        return n;
    }
}

/**
 * <b> Path is the class representing a path in the network, by a list of nodes </b>
 */
class Path {
    private LinkedList<Node> p;

    public Node getNode(int i) {
        return p.get(i);
    }

    public int length() {
        return p.size();
    }

    public void addFirst(Node n) {
        p.addFirst(n);
    }

    public Path() {
        p = new LinkedList<Node>();
    }

    /** This constructor builds a shortest path in the network described by
     * nodes, between begin and end
     */
    public Path (Node begin, Node end, Node[] nodes) {
        p = new LinkedList<Node>();
        int N = nodes.length;

        /* For each node is kept a boolean equal to true if it has been visited
        and an int equal to the node it has been visited from (initially = -1) */
        boolean[] explored = new boolean[N];
        for (int i=0; i < N ;i++)
            explored[i] = false;
        explored[begin.n()] = true;

        int[] prec = new int[N];
        for (int i=0; i < N; i++)
            prec[i] = -1;


        /* A BFS is performed, starting from node begin
        and until finding the node end.*/
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(begin);
        Node n = null;

        while((q.size() != 0) && (n = q.remove()) != end) {
            for (Node m : n.neighb()) {
                if(!explored[m.n()]) {
                    explored[m.n()] = true;
                    prec[m.n()] = n.n();
                    q.add(m);
                }
            }
        }

        /* if the queue is empty before finding the node end then
        there is no path, the new path stays empty.
        Else, a shortest path is identified by backtracing.*/
        if (n == end) {
            while(n != begin) {
                this.addFirst(n);
                n = nodes[prec[n.n()]];
            }
            this.addFirst(n);
        }
    }
}


class Player {

    public static void cut(Node n1, Node n2){
        n1.removeNeighb(n2);
        n2.removeNeighb(n1);
        System.out.println(n1.n() + " " + n2.n());
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // Scanning number of nodes, links, and exits
        int N = in.nextInt();
        int L = in.nextInt();
        int E = in.nextInt();
        // Initializing an array of non-linked nodes
        Node[] nodes = new Node[N];
        for (int i = 0 ; i < N ; i++)
            nodes[i] = new Node(i);
        // Linking nodes
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt();
            int N2 = in.nextInt();
            nodes[N1].addNeighb(nodes[N2]);
            nodes[N2].addNeighb(nodes[N1]);
        }
        // Initializing an array of exit nodes
        Node[] exits = new Node[E];
        for (int i = 0; i < E; i++)
            exits[i] = nodes[in.nextInt()];

        while (true) {
            // Skynet agent position
            int SI = in.nextInt();

            /* Simple strategy : identify one shortest paths from Skynet
               agent to each exit node, thanks to a Breadth First Searches
               Then cutting the shortest, as close as possible to Skynet agent */
            Path[] shortestPaths = new Path[E];
            int minLength = Integer.MAX_VALUE;
            Path pathToCut = null;

            for (int i = 0 ; i < E ; i++) {
                shortestPaths[i] = new Path(nodes[SI], exits[i], nodes);
                int length = shortestPaths[i].length();
                if ((length != 0) && (length < minLength)) {
                    minLength = length;
                    pathToCut = shortestPaths[i];
                }
            }

            cut(pathToCut.getNode(0), pathToCut.getNode(1));
        }
    }
}
            
