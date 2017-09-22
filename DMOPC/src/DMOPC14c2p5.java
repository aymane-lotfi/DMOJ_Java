import java.util.*;

class DirectedGraph<T> implements Iterable<T> {

    private final Map<T, ArrayList<T>> mGraph = new HashMap<>();

    public boolean addNode(T node) {
        if (mGraph.containsKey(node))
            return false;

        mGraph.put(node, new ArrayList<T>());
        return true;
    }

    public void addEdge(T start, T dest) {
        mGraph.get(start).add(dest);
    }

    public ArrayList<T> edgesFrom(T node) {
        ArrayList<T> arcs = mGraph.get(node);
        return arcs;
    }

    public Iterator<T> iterator() {
        return mGraph.keySet().iterator();
    }
}

public class DMOPC14c2p5 {

    private static DirectedGraph<Integer> graph = new DirectedGraph<>();
    private static double[] probList;

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int numLake = in.nextInt();
        int numRiver = in.nextInt();

        probList = new double[numLake+1];

        for (int i=0; i<=numLake; i++) {
            graph.addNode(i);
        }

        for (int i=0; i<numRiver; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            graph.addEdge(start, end);
        }

        getProb(1, 1.0d);

        for (int i=1; i<probList.length; i++) {
            if (probList[i] != -1) {
                //System.out.println(graph.edgesFrom(i));
                if (graph.edgesFrom(i).size() == 0) {
                    System.out.println(probList[i]);
                }
            }
        }
    }

    private static void getProb(int node, double prob) {
        ArrayList<Integer> curNode = graph.edgesFrom(node);
        //System.out.println("Exploring: " + node);
        //System.out.println("Current Node: " + curNode);
        //System.out.println("Probability: " + prob);
        int size = curNode.size();
        //System.out.println("Size: " + size);
        if (size == 0) {
            //System.out.println("Added final prob " + prob);
            probList[node] += prob;
            //System.out.println("Updated node: " + probList[node]);
        } else {
            for (int i=0; i<size; i++) {
                probList[node] = -1;
                //System.out.println("Exploring prob " + (((double)(1)/(double)(size))*prob) + " at location " + curNode.get(i));
                getProb(curNode.get(i), (((double)(1)/(double)(size))*prob));
            }
        }
    }
}
