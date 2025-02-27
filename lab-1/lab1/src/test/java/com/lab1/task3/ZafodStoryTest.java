package com.lab1.task3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;





public class ZafodStoryTest {
    
    private ZafodStory story;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputCapture));
        story = new ZafodStory();
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