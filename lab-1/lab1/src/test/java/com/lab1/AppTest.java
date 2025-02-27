package com.lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class AppTest {

    private static final double EPSILON = 1e-6; 
    @Test    
    public void shouldAnswerWithTrue() {        
        assertTrue(true);    
    }    
    @Test    
    public void testCosSpecialAngles() {    
        assertEquals(1.0, App.cos(0, 10), EPSILON);       
        assertEquals(0.5, App.cos(Math.PI/3, 10), EPSILON);        
        assertEquals(0.0, App.cos(Math.PI/2, 10), EPSILON);        
        assertEquals(-0.5, App.cos(2*Math.PI/3, 10), EPSILON);        
        assertEquals(-1.0, App.cos(Math.PI, 10), EPSILON);        
        assertEquals(0.0, App.cos(3*Math.PI/2, 10), EPSILON);
        assertEquals(Math.sqrt(2)/2, App.cos(Math.PI/4, 10), EPSILON);
        assertEquals(Math.sqrt(3)/2, App.cos(Math.PI/6, 10), EPSILON);
    }

    @Test
    public void testCosSymmetry() {
        for (double x = 0; x <= 2*Math.PI; x += Math.PI/6) {
            assertEquals(App.cos(x, 10), App.cos(-x, 10), EPSILON);
        }
    }

    @Test
    public void testCosPeriodicity() {
        for (double x = -Math.PI; x <= Math.PI; x += Math.PI/4) {
            assertEquals(App.cos(x, 10), App.cos(x + 2*Math.PI, 10), EPSILON);
            assertEquals(App.cos(x, 10), App.cos(x + 4*Math.PI, 10), EPSILON);
        }
    }

    @Test
    public void testCosLargeAngles() {
        assertEquals(1.0, App.cos(20*Math.PI, 15), EPSILON);
        assertEquals(-1.0, App.cos(99*Math.PI, 15), EPSILON);
    }

    @Test
    public void testCosConvergence() {
        double actual = Math.cos(Math.PI/4);
        for (int i = 1; i <= 10; i++) {
            double approximation = App.cos(Math.PI/4, i);
            double previousError = Math.abs(actual - App.cos(Math.PI/4, i-1));
            double currentError = Math.abs(actual - approximation);
            
            if (i > 1) { 
                assertTrue("Error should decrease with more iterations", currentError <= previousError);
            }
        }
    }

    @Test
    public void testCosAccuracy() {
        for (double x = -2*Math.PI; x <= 2*Math.PI; x += Math.PI/12) {
            assertEquals(Math.cos(x), App.cos(x, 15), EPSILON);
        }
    }
}