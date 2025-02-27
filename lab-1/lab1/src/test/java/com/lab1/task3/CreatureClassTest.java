package com.lab1.task3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CreatureClassTest {
    
    private Creature zafod;
    private Planet goldPlanet;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final double DELTA = 0.00001;
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputCapture));
        
        zafod = new Creature("Zafod", 1.85, 75.0);
        goldPlanet = new Planet("Golden Planet", 6371.0, Material.GOLD, 9.8);
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
    

    @Test
    public void testCreatureCreationAndBasicAccessors() {
        assertEquals("Zafod", zafod.getName());
        assertEquals(1.85, zafod.getHeight(), DELTA);
        assertEquals(75.0, zafod.getWeight(), DELTA);
        assertFalse(zafod.isStanding());
        assertNotNull(zafod.getPosition());
        assertNotNull(zafod.getEyes());
        assertNotNull(zafod.getMouth());
        assertNotNull(zafod.getThroat());
        
        zafod.setName("Arthur");
        zafod.setHeight(1.75);
        zafod.setWeight(70.0);
        Position newPosition = new Position(10, 20, 30);
        zafod.setPosition(newPosition);
        
        assertEquals("Arthur", zafod.getName());
        assertEquals(1.75, zafod.getHeight(), DELTA);
        assertEquals(70.0, zafod.getWeight(), DELTA);
        assertSame(newPosition, zafod.getPosition());
    }
    

    @Test
    public void testMovementMethods() {
        zafod.jump();
        assertTrue(outputCapture.toString().contains("jumped up lightly like a ballet dancer"));
        
        outputCapture.reset();
        
        zafod.jumpToFeet();
        assertTrue(zafod.isStanding());
        assertTrue(outputCapture.toString().contains("jumped to their feet as lightly as a ballet dancer"));
        
        outputCapture.reset();
        
        zafod.jumpToFeet();
        assertTrue(outputCapture.toString().contains("is already standing"));
        
        outputCapture.reset();
        
        double initialPositionX = zafod.getPosition().getX();
        double initialPositionY = zafod.getPosition().getY();
        double initialPositionZ = zafod.getPosition().getZ();
        zafod.move(5, 10, 15);
        
        Position newPosition = zafod.getPosition();
        assertEquals(initialPositionX + 5, newPosition.getX(), DELTA);
        assertEquals(initialPositionY + 10, newPosition.getY(), DELTA);
        assertEquals(initialPositionZ + 15, newPosition.getZ(), DELTA);
        assertTrue(outputCapture.toString().contains("moved to position"));
        
        outputCapture.reset();
        
        zafod.lieDown();
        assertFalse(zafod.isStanding());
        assertTrue(outputCapture.toString().contains("lies down on the surface"));
        
        outputCapture.reset();
        
        zafod.lieDown();
        assertTrue(outputCapture.toString().contains("is already lying down"));
    }
    

    @Test
    public void testEyesFunctionality() {
        Creature.Eyes eyes = zafod.getEyes();
        
        assertTrue(eyes.isOpened());
        
        eyes.close();
        assertFalse(eyes.isOpened());
        assertTrue(outputCapture.toString().contains("closed their eyes"));
        
        outputCapture.reset();
        
        eyes.scan();
        assertTrue(outputCapture.toString().contains("can't see with closed eyes"));
        
        outputCapture.reset();
        
        eyes.open();
        assertTrue(eyes.isOpened());
        assertTrue(outputCapture.toString().contains("opened their eyes"));
        
        outputCapture.reset();
        
        eyes.scan();
        assertTrue(outputCapture.toString().contains("scanning the surroundings"));
    }
    

    @Test
    public void testMouthFunctionality() {
        Creature.Mouth mouth = zafod.getMouth();
        
        mouth.open();
        assertTrue(outputCapture.toString().contains("opened their mouth"));
        
        outputCapture.reset();
        
        mouth.speak("Hello, Universe!");
        assertTrue(outputCapture.toString().contains("says: \"Hello, Universe!\""));
        
        outputCapture.reset();
        
        mouth.close();
        assertTrue(outputCapture.toString().contains("closed their mouth"));
        
        outputCapture.reset();
        
        mouth.speak("Can you hear me?");
        assertTrue(outputCapture.toString().contains("tries to speak with closed mouth"));
        
        outputCapture.reset();
        
        mouth.gasp();
        assertTrue(outputCapture.toString().contains("gasps in awe"));
    }
    

    @Test
    public void testThroatFunctionality() {
        Creature.Throat throat = zafod.getThroat();
        
        throat.swallow();
        assertTrue(outputCapture.toString().contains("swallows"));
        
        outputCapture.reset();
        
        throat.makeSound("Ahem!");
        assertTrue(outputCapture.toString().contains("makes a sound: Ahem!"));
    }
    

    @Test
    public void testObserveSurroundings() {
        zafod.getEyes().close();
        outputCapture.reset();
        
        zafod.observeSurroundings(goldPlanet);
        
        String output = outputCapture.toString();
        
        assertTrue(output.contains("opened their eyes"));
        
        assertTrue(output.contains("observes: The golden planet"));
        
        assertTrue(output.contains("shines with intensity"));
        assertTrue(output.contains("unlike anything else in the Universe"));
    }
    

    @Test
    public void testZafodScenario() {
        assertFalse(zafod.isStanding());
        
        zafod.jumpToFeet();
        assertTrue(zafod.isStanding());
        
        zafod.lookAround();
        
        zafod.observeSurroundings(goldPlanet);
        
        String output = outputCapture.toString();
        assertTrue(output.contains("jumped to their feet as lightly as a ballet dancer"));
        assertTrue(output.contains("scanning the surroundings"));
        assertTrue(output.contains("golden planet"));
        assertTrue(output.contains("extends to the horizon"));
    }
}