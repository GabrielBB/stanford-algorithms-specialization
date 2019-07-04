package com.github.gabrielbb.stanford.algorithms;

import static org.junit.Assert.assertEquals;

import com.github.gabrielbb.stanford.algorithms.course1.GradeSchoolMultiplication;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Course1Tests {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testGradeSchoolMultiplication() {
        assertEquals("448", GradeSchoolMultiplication.solve("32", "14"));
        assertEquals("8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184", GradeSchoolMultiplication.solve("3141592653589793238462643383279502884197169399375105820974944592", "2718281828459045235360287471352662497757247093699959574966967627"));
        assertEquals("27498843", GradeSchoolMultiplication.solve("3247", "8469"));
    }
}
