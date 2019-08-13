package com.github.gabrielbb.stanford.algorithms.course2;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class StronglyConnectedComponents {

    public static class Graph {

        private final Map<Integer, List<Integer>> map = new HashMap<>();

        public void addEdge(int from, int to) {
            map.putIfAbsent(from, new LinkedList<>());
            map.putIfAbsent(to, new LinkedList<>());

            map.get(from).add(to);
        }

        public Stack<Integer> depthFirstSearch() {
            Stack<Integer> finishTimes = new Stack<>();

            Stack<Integer> stack = new Stack<>();
            boolean[] inStack = new boolean[map.size() + 5];
            boolean[] visited = new boolean[map.size() + 5];

            for (int vertex : map.keySet()) {
                stack.push(vertex);

                while (!stack.isEmpty()) {
                    int curr = stack.pop();

                    if (inStack[curr]) {
                        finishTimes.push(curr);
                        inStack[curr] = false;
                    } else if (!visited[curr]) {
                        inStack[curr] = true;
                        visited[curr] = true;
                        stack.push(curr);
                        map.get(curr).forEach(stack::push);
                    }
                }
            }

            return finishTimes;
        }

        public PriorityQueue<Integer> getSCCSizes(Stack<Integer> finishTimes) {

            PriorityQueue<Integer> sizes = new PriorityQueue<>(Collections.reverseOrder());
            boolean[] visited = new boolean[finishTimes.size() + 5];

            Stack<Integer> stack = new Stack<>();
            int currentCount = 0;

            while (!finishTimes.isEmpty()) {
                stack.push(finishTimes.pop());

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();

                    if (!visited[vertex]) {
                        visited[vertex] = true;
                        currentCount++;
                        map.get(vertex).forEach(stack::push);
                    }
                }

                if (currentCount > 0) {
                    sizes.add(currentCount);
                    currentCount = 0;
                }
            }

            return sizes;
        }
    }

    public static int[] findTop(List<int[]> edges, int topLimit) {

        Graph g = new Graph();
        Graph reversed = new Graph();

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
            reversed.addEdge(edge[1], edge[0]);
        }

        PriorityQueue<Integer> sizes = reversed.getSCCSizes(g.depthFirstSearch());
        int[] result = new int[topLimit];

        for (int i = 0; i < topLimit && !sizes.isEmpty(); i++) {
            result[i] = sizes.poll();
        }

        return result;
    }
}