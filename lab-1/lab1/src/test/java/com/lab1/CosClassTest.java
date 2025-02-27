package com.lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class CosClassTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testCos_ZeroRadians_thenOne() {
        double expected = 1.0;
        double actual = CosClass.cos(0, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_PiOverThree_thenHalf() {
        double expected = 0.5;
        double actual = CosClass.cos(Math.PI / 3, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_PiOverTwo_thenZero() {
        double expected = 0.0;
        double actual = CosClass.cos(Math.PI / 2, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_TwoPiOverThree_thenNegativeHalf() {
        double expected = -0.5;
        double actual = CosClass.cos(2 * Math.PI / 3, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_Pi_thenNegativeOne() {
        double expected = -1.0;
        double actual = CosClass.cos(Math.PI, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_ThreePiOverTwo_thenZero() {
        double expected = 0.0;
        double actual = CosClass.cos(3 * Math.PI / 2, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_PiOverFour_thenSqrtTwoOverTwo() {
        double expected = Math.sqrt(2) / 2;
        double actual = CosClass.cos(Math.PI / 4, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_PiOverSix_thenSqrtThreeOverTwo() {
        double expected = Math.sqrt(3) / 2;
        double actual = CosClass.cos(Math.PI / 6, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_Symmetry() {
        for (double x = 0; x <= 2*Math.PI; x += Math.PI/6) {
            double positive = CosClass.cos(x, 10);
            double negative = CosClass.cos(-x, 10);

            assertEquals(positive, negative, EPSILON);
        }
    }

    @Test
    public void testCos_PeriodicityOverTwo() {
        for (double x = -Math.PI; x <= Math.PI; x += Math.PI/4) {
            double initial = CosClass.cos(x, 10);
            double multiplied = CosClass.cos(x + 2 * Math.PI, 10);

            assertEquals(initial, multiplied, EPSILON);
        }
    }

    @Test
    public void testCos_PeriodicityOverFour() {
        for (double x = -Math.PI; x <= Math.PI; x += Math.PI/4) {
            double initial = CosClass.cos(x, 10);
            double multiplied = CosClass.cos(x + 4 * Math.PI, 10);

            assertEquals(initial, multiplied, EPSILON);
        }
    }

    @Test
    public void testCos_LargeAngles() {
        for(int i = 20; i < 100; i++) {
            double expected = i % 2 == 0 ? 1.0 : -1.0;
            double actual = CosClass.cos(i*Math.PI, 15);

            assertEquals(expected, actual, EPSILON);
        }
    }

    @Test
    public void testCos_ApproximationStep_thenDecrease() {
        double actual = Math.cos(Math.PI/4);
        for (int i = 1; i <= 10; i++) {
            double approximation = CosClass.cos(Math.PI/4, i);
            double previousError = Math.abs(actual - CosClass.cos(Math.PI/4, i-1));
            double currentError = Math.abs(actual - approximation);
            
            if (i > 1) { 
                assertTrue("Error should decrease with more iterations", currentError <= previousError);
            }
        }
    }

    @Test
    public void testCos_Accuracy() {
        for (double x = -2*Math.PI; x <= 2*Math.PI; x += Math.PI/12) {
            double expected = Math.cos(x);
            double actual = CosClass.cos(x, 15);

            assertEquals(expected, actual, EPSILON);
        }
    }
}