package gr.ntua.ece;

import java.util.HashMap;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.*;
import com.google.common.collect.*;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Astar {

    private Double distance;
    private PrologSystem prolog = PrologSystem.getInstance();

    private double heuristic(Node a, Node b){
        //double result = prolog.calculateCost(a, b);
        return a.euclid((b));
        //        return  a.euclid(b);
    }

    public List<Node> find(SimpleWeightedGraph<Node, DefaultWeightedEdge> G, Node s, Node e, long BeamSize){
        HashMap<Node,Double> dist = new HashMap<>(1);
        HashMap<Node,Node> from = new HashMap<>(1);
        MinMaxPriorityQueue<NodeWithPriority> queue = MinMaxPriorityQueue.orderedBy(new NodeWithPriority()).create();
        dist.put(s,0.0);
        queue.add(new NodeWithPriority(s,0.0));
        from.put(s,s);
        Node current = null;

        while (!queue.isEmpty()) {
            current = (queue.removeFirst()).getNode();
            if(current.equals(e)) break;

            ArrayList<DefaultWeightedEdge> neighbours = new ArrayList<>();

            for (DefaultWeightedEdge edge : G.edgesOf(current)) {
                Node next = G.getEdgeTarget(edge);
                if (!prolog.canMoveFromTo(current, next)) {
                    neighbours.add(edge);
                }
            }

//            Set<DefaultWeightedEdge> neighbours = G.edgesOf(current);
            Iterator<DefaultWeightedEdge> iter = neighbours.iterator();

            while (iter.hasNext()) {
                DefaultWeightedEdge ed = iter.next();
                Node next = G.getEdgeTarget(ed);

                if (next.equals(current)) {
                    next = G.getEdgeSource(ed);
                }


                double newDistance = dist.get(current) + G.getEdgeWeight(G.getEdge(current, next));
                if (!dist.containsKey(next) || newDistance < dist.get(next)) {
                    double priority = newDistance + heuristic(next, e);
                    if (BeamSize <= queue.size() && priority < queue.peekLast().getCost()) {
                        queue.removeLast();
                        from.put(next, current);
                        dist.put(next, newDistance);
                        queue.add(new NodeWithPriority(next, priority));
                    } else if (BeamSize > queue.size()) {
                        from.put(next, current);
                        dist.put(next, newDistance);
                        queue.add(new NodeWithPriority(next, priority));
                    }
                }
            }
        }

        this.distance = (double) Math.round((dist.get(e)) * 1000000d) / 1000000d;
        List<Node> l= new LinkedList<>();
        l.add(current);
        while (from.get(current)!=current) {
            l.add(from.get(current));
            current = from.get(current);
        }
        l.add(current);
        return l;
    }

    Double getDistance() {
        return distance;
    }
}