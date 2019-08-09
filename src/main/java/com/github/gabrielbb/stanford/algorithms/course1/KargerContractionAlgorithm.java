package com.github.gabrielbb.stanford.algorithms.course1;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KargerContractionAlgorithm {

    public static int getEdgesLeftAfterContracting(Map<String, List<String>> graph) {

        Random r = new Random();

        while (graph.size() > 2) {

            // Get a random vertex from the map
            Map.Entry<String, List<String>> aEntry = graph.entrySet().stream().skip(r.nextInt(graph.size() - 1))
                    .findFirst().get();

            // Get a random vertex from $a adjacencty list. This is b
            String b = aEntry.getValue().stream().skip(r.nextInt(aEntry.getValue().size() - 1)).findFirst().get();

            // Merge $b with $a
            for (List<String> adjacents : graph.values()) {
                Collections.replaceAll(adjacents, b, aEntry.getKey());
            }
            // System.out.println("Contracting " + b + " into " + aEntry.getKey());
            aEntry.getValue().addAll(graph.get(b));

            // Remove self loops
            aEntry.getValue().removeIf(b2 -> b2.equals(aEntry.getKey()));

            /*
             * Remove $b from the graph because it is now merged with $a. $a is the
             * contraction result
             */
            // System.out.println("Removing " + b);
            graph.remove(b);
        }

        return graph.values().stream().findFirst().get().size();
    }
}