package com.lab1.task3;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlanetClassTest {
    
    private Planet goldPlanet;
    private Planet rockPlanet;
    private Planet waterPlanet;
    private static final double DELTA = 0.00001;
    
    @Before
    public void setUp() {
        Locale.setDefault(Locale.US);
        goldPlanet = new Planet("Gold Planet", 6371.0, Material.GOLD, 9.8);
        rockPlanet = new Planet("Rocky World", 3400.0, Material.ROCK, 3.7);
        waterPlanet = new Planet("Water World", 12000.0, Material.WATER, 10.5);
    }


    @Test
    public void testStory_planet_describeView() {
        String goldDescription = goldPlanet.describeView();
        assertTrue(goldDescription.contains("The golden planet Gold Planet extends"));
        assertTrue(goldDescription.contains("horizon in all directions"));
        assertTrue(goldDescription.contains("shines like nothing else"));
        
        String rockDescription = rockPlanet.describeView();
        assertTrue(rockDescription.contains("The rocky planet Rocky World extends"));
        assertTrue(rockDescription.contains("horizon in all directions"));
        assertFalse(rockDescription.contains("shines like nothing else"));
        
        String waterDescription = waterPlanet.describeView();
        assertTrue(waterDescription.contains("The watery planet Water World extends"));
        assertTrue(waterDescription.contains("horizon in all directions"));
        assertFalse(waterDescription.contains("shines like nothing else"));
    }
    

    @Test
    public void testStory_planet_describeUniqueness() {
        String goldUniqueness = goldPlanet.describeUniqueness();
        assertTrue(goldUniqueness.contains("nothing else in the Universe can compare"));
        assertTrue(goldUniqueness.contains("impossible to find a suitable comparison"));
        
        String rockUniqueness = rockPlanet.describeUniqueness();
        assertTrue(rockUniqueness.contains("rocky surface is quite distinctive"));
        assertFalse(rockUniqueness.contains("nothing else in the Universe can compare"));
        
        String waterUniqueness = waterPlanet.describeUniqueness();
        assertTrue(waterUniqueness.contains("watery surface is quite distinctive"));
        assertFalse(waterUniqueness.contains("nothing else in the Universe can compare"));
    }


    @Test
    public void testStory_planet_accessors() {
        assertEquals("Gold Planet", goldPlanet.getName());
        assertEquals(6371.0, goldPlanet.getRadius(), DELTA);
        assertEquals(9.8, goldPlanet.getGravity(), DELTA);
        assertNotNull(goldPlanet.getSurface());
        assertNotNull(goldPlanet.getPosition());
        assertTrue(goldPlanet.getHorizonDistance() > 0);
        
        goldPlanet.setName("Renamed Gold Planet");
        assertEquals("Renamed Gold Planet", goldPlanet.getName());
        
        goldPlanet.setRadius(7000.0);
        assertEquals(7000.0, goldPlanet.getRadius(), DELTA);
        
        goldPlanet.setGravity(10.5);
        assertEquals(10.5, goldPlanet.getGravity(), DELTA);
        
        Surface newSurface = new Surface(Material.SILVER, 1000.0);
        goldPlanet.setSurface(newSurface);
        assertSame(newSurface, goldPlanet.getSurface());
        
        Position newPosition = new Position(100.0, 200.0, 300.0);
        goldPlanet.setPosition(newPosition);
        assertSame(newPosition, goldPlanet.getPosition());
    }
    

    @Test
    public void testStory_planet_calculateHorizonDistance() {
        double horizonForShortPerson = goldPlanet.calculateHorizonDistance(1.5);
        double horizonForTallPerson = goldPlanet.calculateHorizonDistance(2.0);
        
        assertTrue(horizonForTallPerson > horizonForShortPerson);
        
        double expectedForShort = Math.sqrt(2 * 6371.0 * 1.5 + 1.5*1.5);
        assertEquals(expectedForShort, horizonForShortPerson, DELTA);
        
        goldPlanet.calculateHorizonDistance(3.0);
        double expectedForTall = Math.sqrt(2 * 6371.0 * 3.0 + 3.0*3.0);
        assertEquals(expectedForTall, goldPlanet.getHorizonDistance(), DELTA);
        
        double smallPlanetHorizon = rockPlanet.calculateHorizonDistance(1.7);
        double expectedSmallPlanet = Math.sqrt(2 * 3400.0 * 1.7 + 1.7*1.7);
        assertEquals(expectedSmallPlanet, smallPlanetHorizon, DELTA);
        assertEquals(expectedSmallPlanet, rockPlanet.getHorizonDistance(), DELTA);
    }
    

    @Test
    public void testStory_planet_describeViewAfterHorizonChange() {
        goldPlanet.calculateHorizonDistance(5.0);
        double expectedDistance = Math.sqrt(2 * 6371.0 * 5.0 + 5.0*5.0);
        
        String description = goldPlanet.describeView();
        String formattedDistance = String.format("%.2f", expectedDistance);
        
        assertTrue(description.contains(formattedDistance + " km"));
    }
    

    @Test
    public void testStory_planet_edgeCases() {
        Planet zeroGravityPlanet = new Planet("Zero-G", 1000.0, Material.IRON, 0.0);
        assertEquals(0.0, zeroGravityPlanet.getGravity(), DELTA);
        
        assertNotNull(zeroGravityPlanet.describeView());
        assertNotNull(zeroGravityPlanet.describeUniqueness());
    }
}