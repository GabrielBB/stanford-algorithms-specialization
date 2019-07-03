package com.github.gabrielbb.stanford.algorithms;

import static org.junit.Assert.assertEquals;

import com.github.gabrielbb.stanford.algorithms.course1.GradeSchoolMultiplication;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Course1Tests 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testGradeSchoolMultiplication()
    {
        assertEquals(448, GradeSchoolMultiplication.solve(32, 14, 1));
        assertEquals(27498843, GradeSchoolMultiplication.solve(3247, 8469, 1));
    }
}
