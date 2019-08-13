package com.github.gabrielbb.stanford.algorithms;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.gabrielbb.stanford.algorithms.course2.StronglyConnectedComponents;

import org.junit.Assert;
import org.junit.Test;

public class Course2Tests {

    @Test
    public void testSCCWithHugeGraph() throws IOException, URISyntaxException {

        List<int[]> data = Files.readAllLines(Paths.get(StronglyConnectedComponents.class.getClassLoader()
                .getResource("course2/strongly_connected_components.txt").toURI())).stream().map(s -> {
                    String[] split = s.split(" ");
                    return new int[] { Integer.parseInt(split[0]), Integer.parseInt(split[1]) };
                }).collect(Collectors.toList());

        System.out.println(Arrays.toString(StronglyConnectedComponents.findTop(data, 5)));
    }

    @Test
    public void testSCC() {

        List<int[]> data = List.of(new int[] { 1, 4 }, new int[] { 2, 8 }, new int[] { 3, 6 }, new int[] { 4, 7 },
                new int[] { 5, 2 }, new int[] { 6, 9 }, new int[] { 7, 1 }, new int[] { 8, 5 }, new int[] { 8, 6 },
                new int[] { 9, 7 }, new int[] { 9, 3 });
        Assert.assertArrayEquals(new int[] { 3, 3, 3, 0, 0 }, StronglyConnectedComponents.findTop(data, 5));

        data = List.of(new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 1 }, new int[] { 3, 5 },
                new int[] { 5, 6 }, new int[] { 6, 4 }, new int[] { 4, 5 }, new int[] { 7, 6 });

        Assert.assertArrayEquals(new int[] { 3, 3, 1, 0, 0 }, StronglyConnectedComponents.findTop(data, 5));
    }
}