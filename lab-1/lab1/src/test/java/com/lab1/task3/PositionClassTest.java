package com.lab1.task3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PositionClassTest {
    
    private static final double DELTA = 0.00001;
    
    @Test
    public void testCoordinateAccessors() {
        Position position = new Position(1.0, 2.0, 3.0);
        assertEquals(1.0, position.getX(), DELTA);
        assertEquals(2.0, position.getY(), DELTA);
        assertEquals(3.0, position.getZ(), DELTA);
        
        position.setX(4.0);
        position.setY(5.0);
        position.setZ(6.0);
        assertEquals(4.0, position.getX(), DELTA);
        assertEquals(5.0, position.getY(), DELTA);
        assertEquals(6.0, position.getZ(), DELTA);
        
        position.setX(0.0);
        position.setY(0.0);
        position.setZ(0.0);
        assertEquals(0.0, position.getX(), DELTA);
        assertEquals(0.0, position.getY(), DELTA);
        assertEquals(0.0, position.getZ(), DELTA);
        
        position.setX(-10.5);
        position.setY(-20.75);
        position.setZ(-30.25);
        assertEquals(-10.5, position.getX(), DELTA);
        assertEquals(-20.75, position.getY(), DELTA);
        assertEquals(-30.25, position.getZ(), DELTA);
    }
    

    @Test
    public void testDistanceScenarios() {
        Position origin = new Position(0.0, 0.0, 0.0);
        Position point1 = new Position(3.0, 4.0, 0.0);
        Position point2 = new Position(3.0, 4.0, 12.0);
        Position point3 = new Position(-1.0, -2.0, -3.0);
        
        assertEquals(5.0, origin.distanceTo(point1), DELTA);
        assertEquals(5.0, point1.distanceTo(origin), DELTA); 

        assertEquals(13.0, origin.distanceTo(point2), DELTA);
        
        double expectedDistance = Math.sqrt(14);
        assertEquals(expectedDistance, origin.distanceTo(point3), DELTA);
        
        assertEquals(0.0, origin.distanceTo(origin), DELTA);
        assertEquals(0.0, point1.distanceTo(point1), DELTA);
        
        Position pointA = new Position(10.0, 20.0, 30.0);
        Position pointB = new Position(13.0, 24.0, 30.0);
        assertEquals(5.0, pointA.distanceTo(pointB), DELTA); 
    }
    

    @Test
    public void testToStringFormatting() {
        Position[] positions = {
            new Position(1.0, 2.0, 3.0),
            new Position(1.5, 2.5, 3.5),
            new Position(-1.0, -2.0, -3.0),
            new Position(0.0, 0.0, 0.0)
        };
        
        String[] expected = {
            "(1.0, 2.0, 3.0)",
            "(1.5, 2.5, 3.5)",
            "(-1.0, -2.0, -3.0)",
            "(0.0, 0.0, 0.0)"
        };
        
        for (int i = 0; i < positions.length; i++) {
            assertEquals(expected[i], positions[i].toString());
        }
        
        Position extremePosition = new Position(1000000.0, 0.000001, -9999.9999);
        assertEquals("(1000000.0, 1.0E-6, -9999.9999)", extremePosition.toString());
    }
    
    @Test
    public void testEdgeCases() {
        Position veryLarge1 = new Position(1.0E9, 2.0E9, 3.0E9);
        Position veryLarge2 = new Position(1.0E9 + 3.0, 2.0E9 + 4.0, 3.0E9);
        assertEquals(5.0, veryLarge1.distanceTo(veryLarge2), DELTA);
        
        Position p1 = new Position(1.0, 1.0, 1.0);
        Position p2 = new Position(1.0 + 1.0E-10, 1.0, 1.0);
        assertTrue(p1.distanceTo(p2) < 1.0E-9);
        
        Position huge1 = new Position(0.0, 0.0, 0.0);
        Position huge2 = new Position(1.0E30, 1.0E30, 1.0E30);
        double distance = huge1.distanceTo(huge2);
        assertTrue(distance > 0);
        double expected = Math.sqrt(3) * 1.0E30;
        double relativeDiff = Math.abs(distance - expected) / expected;
        assertTrue(relativeDiff < 1.0E-10);
    }
}
