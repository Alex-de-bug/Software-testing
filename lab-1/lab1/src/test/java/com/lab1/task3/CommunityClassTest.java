package com.lab1.task3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CommunityClassTest {
    
    private Community community;
    private Planet testPlanet;
    private Creature creature1;
    private Creature creature2;
    private Creature creature3;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputCapture));
        
        testPlanet = new Planet("Test Planet", 6000.0, Material.ROCK, 8.0);
        community = new Community("Test Community", testPlanet);
        creature1 = new Creature("Alice", 1.7, 65.0);
        creature2 = new Creature("Bob", 1.8, 80.0);
        creature3 = new Creature("Charlie", 1.9, 85.0);
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
    

    @Test
    public void testConstructorAndBasicAccessors() {
        assertEquals("Test Community", community.getName());
        assertSame(testPlanet, community.getHabitat());
        assertEquals(0, community.getMemberCount());
        assertTrue(community.getMembers().isEmpty());
        
        Planet newPlanet = new Planet("New Planet", 5000.0, Material.GOLD, 7.0);
        community.setName("Updated Community");
        community.setHabitat(newPlanet);
        
        assertEquals("Updated Community", community.getName());
        assertSame(newPlanet, community.getHabitat());
    }
    

    @Test
    public void testMemberManagement() {
        community.addMember(creature1);
        assertEquals(1, community.getMemberCount());
        assertTrue(community.getMembers().contains(creature1));
        assertTrue(outputCapture.toString().contains("Alice has joined the Test Community community"));
        
        outputCapture.reset();
        
        community.addMember(creature2);
        community.addMember(creature3);
        assertEquals(3, community.getMemberCount());
        assertTrue(outputCapture.toString().contains("Bob has joined"));
        assertTrue(outputCapture.toString().contains("Charlie has joined"));
        
        outputCapture.reset();
        
        community.removeMember(creature2);
        assertEquals(2, community.getMemberCount());
        assertFalse(community.getMembers().contains(creature2));
        assertTrue(outputCapture.toString().contains("Bob has left the Test Community community"));
        
        outputCapture.reset();
        
        Creature nonMember = new Creature("David", 1.75, 70.0);
        community.removeMember(nonMember);
        assertEquals(2, community.getMemberCount()); 
        assertTrue(outputCapture.toString().isEmpty()); 
    }
    

    @Test
    public void testMembersListEncapsulation() {
        community.addMember(creature1);
        community.addMember(creature2);
        
        List<Creature> membersCopy = community.getMembers();
        membersCopy.add(creature3);
        
        assertEquals(2, community.getMemberCount());
        assertFalse(community.getMembers().contains(creature3));
        
        assertEquals(3, membersCopy.size());
        assertTrue(membersCopy.contains(creature3));
        
        membersCopy.clear();
        assertEquals(2, community.getMemberCount());
    }
    

    @Test
    public void testMembersOrder() {
        community.addMember(creature1); // Alice
        community.addMember(creature2); // Bob
        community.addMember(creature3); // Charlie
        
        List<Creature> members = community.getMembers();
        assertEquals(creature1, members.get(0));
        assertEquals(creature2, members.get(1));
        assertEquals(creature3, members.get(2));
    }
    

    @Test
    public void testIntegrationWithCreatureAndPlanet() {
        community.addMember(creature1);
        
        creature1.observeSurroundings(community.getHabitat());
        
        assertTrue(outputCapture.toString().contains("observes: The rocky planet Test Planet"));
        
        outputCapture.reset();
        
        Planet newPlanet = new Planet("Gold Planet", 6000.0, Material.GOLD, 9.0);
        community.setHabitat(newPlanet);
        
        creature1.observeSurroundings(community.getHabitat());
        
        assertTrue(outputCapture.toString().contains("observes: The golden planet Gold Planet"));
    }
    

    @Test
    public void testEdgeCases() {
        Community emptyNameCommunity = new Community("", testPlanet);
        assertEquals("", emptyNameCommunity.getName());
        
        community.addMember(creature1);
        outputCapture.reset();
        community.addMember(creature1);
        assertEquals(2, community.getMemberCount()); 
        
        community.removeMember(creature1); 
        assertTrue(outputCapture.toString().contains("has left"));
        outputCapture.reset();
        community.removeMember(creature1);
        assertTrue(outputCapture.toString().contains("has left"));
        outputCapture.reset();
        community.removeMember(creature1); 
        assertTrue(outputCapture.toString().isEmpty());
        
    }
}