package com.github.gabrielbb.stanford.algorithms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.gabrielbb.stanford.algorithms.course1.CountingInversions;
import com.github.gabrielbb.stanford.algorithms.course1.GradeSchoolMultiplication;
import com.github.gabrielbb.stanford.algorithms.course1.KargerContractionAlgorithm;

import org.junit.Test;

public class Course1Tests {

    @Test
    public void testKargerContractionAlgorithm() throws IOException, URISyntaxException {
        List<String> lines = Files
                .readAllLines(Paths.get(getClass().getClassLoader().getResource("karger.txt").toURI()));

        int minCut = Integer.MAX_VALUE;

        for (int i = 1; i <= 200; i++) {
            int result = KargerContractionAlgorithm.getEdgesLeftAfterContracting(getGraph(lines));
            minCut = Math.min(minCut, result);
        }

        assertEquals(minCut, 17);
    }

    private Map<String, List<String>> getGraph(List<String> lines) {
        var map = new HashMap<String, List<String>>();

        for (String line : lines) {
            String[] split = line.split("\t");
            map.put(split[0], new ArrayList<>());

            for (int i = 1; i < split.length; i++)
                if (!split[i].equals(split[0]))
                    map.get(split[0]).add(split[i]);
        }

        return map;
    }

    @Test
    public void testGradeSchoolMultiplication() {
        assertEquals("448", GradeSchoolMultiplication.solve("32", "14"));
        assertEquals(
                "8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184",
                GradeSchoolMultiplication.solve("3141592653589793238462643383279502884197169399375105820974944592",
                        "2718281828459045235360287471352662497757247093699959574966967627"));
        assertEquals("27498843", GradeSchoolMultiplication.solve("3247", "8469"));
    }

    @Test
    public void testCountingInversions() throws IOException, URISyntaxException {

        int[] array = Files
                .readAllLines(
                        Paths.get(getClass().getClassLoader().getResource("counting_inversions_test_case.txt").toURI()))
                .stream().mapToInt(Integer::parseInt).toArray();

        assertEquals(2407905288L, CountingInversions.countInversions(array));
    }
}
