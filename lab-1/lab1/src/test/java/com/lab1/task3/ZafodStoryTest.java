package com.lab1.task3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ZafodStoryTest {
    
    private ZafodStory story;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputCapture));
        story = new ZafodStory();
    }
    
    @After
    public void restoreSystemOutput() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testZafodJumpsToFeet() {
        assertFalse(story.getZafod().isStanding());
        
        story.getZafod().jumpToFeet();
        
        assertTrue(story.getZafod().isStanding());
        
        assertTrue(outputCapture.toString().contains("as lightly as a ballet dancer"));
    }
    
    @Test
    public void testGoldSurfaceDescription() {
        assertEquals(Material.GOLD, story.getGoldPlanet().getSurface().getMaterial());
        
        String uniqueness = story.getGoldPlanet().describeUniqueness();
        assertTrue(uniqueness.contains("nothing else in the Universe can compare"));
        
        String extent = story.getGoldPlanet().getSurface().describeExtent();
        assertTrue(extent.contains("extending in all directions to the horizon"));
        assertTrue(extent.contains("unmatched brilliance"));
    }
    
    @Test
    public void testFullStorySequence() {
        story.runStory();
        
        String output = outputCapture.toString();
        
        assertTrue(output.contains("jumped to their feet as lightly as a ballet dancer"));
        assertTrue(output.contains("extends to the horizon in all directions"));
        assertTrue(output.contains("shines like nothing else in the Universe"));
        assertTrue(output.contains("gasps in awe"));
    }
    
    @Test
    public void testPlanetHorizonCalculation() {
        double horizonForShortPerson = story.getGoldPlanet().calculateHorizonDistance(1.5);
        double horizonForTallPerson = story.getGoldPlanet().calculateHorizonDistance(2.0);
        
        assertTrue(horizonForTallPerson > horizonForShortPerson);
    }
}