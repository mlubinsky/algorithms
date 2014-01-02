package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> {
    private Map<T, Set<T>> vertices = new HashMap<>();
    
    public Set<T> getVertices() {
        return vertices.keySet();
    }
    
    public Set<T> adjacent(T node) {
        if (!vertices.containsKey(node)) {
            return new HashSet<>();
        }
        return vertices.get(node);
    }
    
    public void add(T node1, T node2) {
        if (!vertices.containsKey(node1)) {
            Set<T> newSet = new HashSet<>();
            newSet.add(node2);
            vertices.put(node1, newSet);
        } else {
            vertices.get(node1).add(node2);
        }
    }
    
    public static class PathFinder<T> {
        private Set<T> marked = new HashSet<>();
        private Map<T, T> edgeTo = new HashMap<>();
        
        public PathFinder(DirectedGraph<T> graph, T source) {
            dfs(graph, source);
        }
        
        private void dfs(DirectedGraph<T> graph, T source) {
            marked.add(source);
            for (T adj : graph.adjacent(source)) {
                if (!marked.contains(adj)) {
                    edgeTo.put(adj, source);
                    dfs(graph, adj);
                }
            }
        }
        
        public boolean hasPathTo(T target) {
            return marked.contains(target);
        }
        
        public List<T> pathTo(T target) {
            if (!hasPathTo(target)) {
                return new ArrayList<T>();
            }
            List<T> paths = new ArrayList<>();
            paths.add(target);
            T t = edgeTo.get(target);
            while (t != null) {
                paths.add(t);
                t = edgeTo.get(t);
            }
            return paths;
        }
    }
    
    public static class ShortestPath<T> {
        private Set<T> marked = new HashSet<>();
        private Map<T, T> edgeTo = new HashMap<>();
        
        public ShortestPath(DirectedGraph<T> graph, T source) {
            bfs(graph, source);
        }
        
        private void bfs(DirectedGraph<T> graph, T source) {
            T s = source;
            LinkedList<T> nodes = new LinkedList<>();
            while (true) {
                marked.add(s);
                for (T adj : graph.adjacent(s)) {
                    if (!marked.contains(adj)) {
                        nodes.add(adj);
                        marked.add(adj);
                        edgeTo.put(adj, s);
                    }
                }
                if (nodes.size() == 0) {
                    break;
                }
                s = nodes.removeFirst();
            }
        }
        
        public boolean hasPathTo(T target) {
            return marked.contains(target);
        }
        
        public List<T> pathTo(T target) {
            if (!hasPathTo(target)) {
                return new ArrayList<T>();
            }
            List<T> paths = new ArrayList<>();
            paths.add(target);
            T t = edgeTo.get(target);
            while (t != null) {
                paths.add(t);
                t = edgeTo.get(t);
            }
            return paths;
        }
    }
    
    public static class CycleDetector<T> {
        private boolean hasCycle = false;
        private Set<T> marked = new HashSet<>();
        private Map<T, Boolean> onStack = new HashMap<>();
        
        public CycleDetector(DirectedGraph<T> graph) {
            for (T t : graph.getVertices()) {
                onStack.put(t, false);
            }
            for (T t : graph.getVertices()) {
                if (!marked.contains(t)) {
                    dfs(graph, t);
                }
            }
        }
        
        private void dfs(DirectedGraph<T> graph, T source) {
            marked.add(source);
            onStack.put(source, true);
            for (T t : graph.adjacent(source)) {
                if (!marked.contains(t)) {
                    dfs(graph, t);
                } else {
                    if (onStack.get(t)) {
                        hasCycle = true;
                    }
                }
            }
            onStack.put(source, false);
        }
        
        public boolean hasCycle() {
            return hasCycle;
        }
    }
}