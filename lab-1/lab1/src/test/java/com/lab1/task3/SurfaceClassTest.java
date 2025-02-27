package com.lab1.task3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SurfaceClassTest {
    private Surface goldSurface;
    private Surface rockSurface;
    private Surface silverSurface;
    private Surface soilSurface;
    
    @Before
    public void setUp() {
        goldSurface = new Surface(Material.GOLD, 1000.0);
        rockSurface = new Surface(Material.ROCK, 500.0);
        silverSurface = new Surface(Material.SILVER, 750.0);
        soilSurface = new Surface(Material.SOIL, 250.0);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(Material.GOLD, goldSurface.getMaterial());
        assertTrue(goldSurface.getMaterial().isMetallic());
        assertEquals(1000.0, goldSurface.getArea(), 0.001);
        assertTrue(goldSurface.isVisible());
    }
    
    @Test
    public void testCalculateShineForGold() {
        double shine = goldSurface.calculateShine(1.0);
        assertEquals(0.95, shine, 0.001);
        
        assertEquals(1.9, goldSurface.calculateShine(2.0), 0.001);
        assertEquals(0.475, goldSurface.calculateShine(0.5), 0.001);
    }
    
    @Test
    public void testCalculateShineForDifferentMaterials() {
        assertEquals(0.25, rockSurface.calculateShine(1.0), 0.001); // Rock reflectivity = 0.25
        assertEquals(0.90, silverSurface.calculateShine(1.0), 0.001); // Silver reflectivity = 0.90
        assertEquals(0.15, soilSurface.calculateShine(1.0), 0.001); // Soil reflectivity = 0.15
    }
    
    @Test
    public void testGetDescriptionFormat() {
        String goldDescription = goldSurface.getDescription();
        assertTrue(goldDescription.startsWith("A golden surface"));
        assertTrue(goldDescription.contains("1000.00"));
        assertTrue(goldDescription.contains("square units"));
        
        String rockDescription = rockSurface.getDescription();
        assertTrue(rockDescription.startsWith("A rocky surface"));
        assertTrue(rockDescription.contains("500.00"));
    }
    
    @Test
    public void testIsShiny() {
        assertTrue(goldSurface.isShiny());  
        assertTrue(silverSurface.isShiny()); 
        assertFalse(rockSurface.isShiny()); 
        assertFalse(soilSurface.isShiny());  
    }
    
    @Test
    public void testDescribeExtentForGold() {
        String goldExtent = goldSurface.describeExtent();
        assertTrue(goldExtent.contains("continuous golden surface"));
        assertTrue(goldExtent.contains("extending in all directions"));
        assertTrue(goldExtent.contains("unmatched brilliance"));  
    }
    
    @Test
    public void testDescribeExtentForOtherMaterials() {
        String rockExtent = rockSurface.describeExtent();
        assertTrue(rockExtent.contains("continuous rocky surface"));
        assertTrue(rockExtent.contains("extending in all directions"));
        assertFalse(rockExtent.contains("unmatched brilliance")); 
    }
    
    @Test
    public void testSetAndGetMaterial() {
        Surface surface = new Surface(Material.GOLD, 100.0);
        assertEquals(Material.GOLD, surface.getMaterial());
        assertEquals("golden", surface.getMaterial().toString());

        surface.setMaterial(Material.SILVER);
        assertEquals(Material.SILVER, surface.getMaterial());
        assertEquals("silvery", surface.getMaterial().toString());
    }
    
    @Test
    public void testSetAndGetArea() {
        Surface surface = new Surface(Material.ROCK, 100.0);
        assertEquals(100.0, surface.getArea(), 0.001);
        
        surface.setArea(200.0);
        assertEquals(200.0, surface.getArea(), 0.001);
    }
    
    @Test
    public void testSetAndGetVisibility() {
        Surface surface = new Surface(Material.SOIL, 100.0);
        assertTrue(surface.isVisible());
        
        surface.setVisible(false);
        assertFalse(surface.isVisible());
    }
    
    @Test
    public void testWithZeroArea() {
        Surface zeroSurface = new Surface(Material.GOLD, 0);
        assertEquals(0, zeroSurface.getArea(), 0.001);
        assertEquals("A golden surface that extends for 0.00 square units", zeroSurface.getDescription());
    }
    
    @Test
    public void testWithNegativeArea() {
        Surface negativeSurface = new Surface(Material.GOLD, -100);
        assertEquals(-100, negativeSurface.getArea(), 0.001);
        assertEquals("A golden surface that extends for -100.00 square units", negativeSurface.getDescription());
    }
    
    @Test
    public void testCalculateShineWithZeroLightIntensity() {
        assertEquals(0, goldSurface.calculateShine(0), 0.001);
    }
    
    @Test
    public void testCalculateShineWithNegativeLightIntensity() {
        assertEquals(-0.95, goldSurface.calculateShine(-1.0), 0.001);
    }
}
