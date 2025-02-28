package com.lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;


public class CosClassTest {

    private static final String FILE_PATH = "tests/cos.tests.json";

    private static final double EPSILON = 1e-6;

    @Test
    public void testCos_zeroRadians_thenOne() {
        double expected = 1.0;
        double actual = CosClass.cos(0, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_piOverThree_thenHalf() {
        double expected = 0.5;
        double actual = CosClass.cos(Math.PI / 3, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_piOverTwo_thenZero() {
        double expected = 0.0;
        double actual = CosClass.cos(Math.PI / 2, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_twoPiOverThree_thenNegativeHalf() {
        double expected = -0.5;
        double actual = CosClass.cos(2 * Math.PI / 3, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_pi_thenNegativeOne() {
        double expected = -1.0;
        double actual = CosClass.cos(Math.PI, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_threePiOverTwo_thenZero() {
        double expected = 0.0;
        double actual = CosClass.cos(3 * Math.PI / 2, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_piOverFour_thenSqrtTwoOverTwo() {
        double expected = Math.sqrt(2) / 2;
        double actual = CosClass.cos(Math.PI / 4, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_piOverSix_thenSqrtThreeOverTwo() {
        double expected = Math.sqrt(3) / 2;
        double actual = CosClass.cos(Math.PI / 6, 10);

        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testCos_symmetry() {
        for (double x = 0; x <= 2*Math.PI; x += Math.PI/6) {
            double positive = CosClass.cos(x, 10);
            double negative = CosClass.cos(-x, 10);

            assertEquals(positive, negative, EPSILON);
        }
    }

    @Test
    public void testCos_periodicityOverTwo() {
        for (double x = -Math.PI; x <= Math.PI; x += Math.PI/4) {
            double initial = CosClass.cos(x, 10);
            double multiplied = CosClass.cos(x + 2 * Math.PI, 10);

            assertEquals(initial, multiplied, EPSILON);
        }
    }

    @Test
    public void testCos_largeAngles() {
        for(int i = 20; i < 100; i += 15) {
            double expected = i % 2 == 0 ? 1.0 : -1.0;
            double actual = CosClass.cos(i*Math.PI, 15);

            assertEquals(expected, actual, EPSILON);
        }
    }

    @Test
    public void testCos_approximationStep_thenDecrease() {
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
    public void testCos_fromFile() {
        File file = new File(FILE_PATH);
        String absolutePath = file.getAbsolutePath();

        if (!file.exists()) {
            System.out.println("File not found: " + absolutePath + ". Tests count as passed.");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode testCases = objectMapper.readTree(file);

            for (JsonNode testCase : testCases) {
                double x = testCase.get("x").asDouble();
                int iterations = testCase.get("iterations").asInt();

                double expected = testCase.get("expected").asDouble();
                double actual = CosClass.cos(x, iterations);

                assertEquals(expected, actual, EPSILON);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while loading JSON tests for Cos: " + e.getMessage(), e);
        }
    }
}