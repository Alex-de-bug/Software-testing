package com.lab1.task3;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class UniverseClassTest {
    private Universe universe;
    
    @Mock
    private Planet mockPlanet1;
    
    @Mock
    private Planet mockPlanet2;
    
    @Mock
    private Planet mockPlanet3;
    
    @Mock
    private Community mockCommunity1;
    
    @Mock
    private Community mockCommunity2;
    
    @Mock
    private Surface mockSurface1;
    
    @Mock
    private Surface mockSurface2;
    
    @Mock
    private Surface mockSurface3;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        resetSingleton();
        universe = Universe.getInstance();
        
        when(mockPlanet1.getSurface()).thenReturn(mockSurface1);
        when(mockPlanet2.getSurface()).thenReturn(mockSurface2);
        when(mockPlanet3.getSurface()).thenReturn(mockSurface3);
        
        when(mockSurface1.isShiny()).thenReturn(true);
        when(mockSurface2.isShiny()).thenReturn(false);
        when(mockSurface3.isShiny()).thenReturn(true);
        
        when(mockCommunity1.getMemberCount()).thenReturn(5);
        when(mockCommunity2.getMemberCount()).thenReturn(3);
    }

    @After
    public void tearDown() {
        resetSingleton();
    }

    @Test
    public void testStory_universe_addAndRemovePlanet() {
        assertEquals(0, universe.getPlanets().size());
        
        universe.addPlanet(mockPlanet1);
        assertEquals(1, universe.getPlanets().size());
        
        universe.addPlanet(mockPlanet2);
        assertEquals(2, universe.getPlanets().size());
        
        universe.removePlanet(mockPlanet1);
        assertEquals(1, universe.getPlanets().size());
        
        assertSame(mockPlanet2, universe.getPlanets().get(0));
    }

    @Test
    public void testStory_universe_addAndRemoveCommunity() {
        assertEquals(0, universe.getCommunities().size());
        
        universe.addCommunity(mockCommunity1);
        assertEquals(1, universe.getCommunities().size());
        
        universe.addCommunity(mockCommunity2);
        assertEquals(2, universe.getCommunities().size());
        
        universe.removeCommunity(mockCommunity1);
        assertEquals(1, universe.getCommunities().size());
        
        assertSame(mockCommunity2, universe.getCommunities().get(0));
    }

    @Test
    public void testStory_universe_findShinyPlanets() {
        universe.addPlanet(mockPlanet1); // shiny
        universe.addPlanet(mockPlanet2); // not shiny
        universe.addPlanet(mockPlanet3); // shiny
        
        List<Planet> shinyPlanets = universe.findShinyPlanets();
        
        assertEquals(2, shinyPlanets.size());
        assertTrue(shinyPlanets.contains(mockPlanet1));
        assertFalse(shinyPlanets.contains(mockPlanet2));
        assertTrue(shinyPlanets.contains(mockPlanet3));
        
        // метод поверхности был вызван для каждой планеты
        verify(mockPlanet1).getSurface();
        verify(mockPlanet2).getSurface();
        verify(mockPlanet3).getSurface();
        
        // метод isShiny был вызван для каждой поверхности
        verify(mockSurface1).isShiny();
        verify(mockSurface2).isShiny();
        verify(mockSurface3).isShiny();
    }
    
    @Test
    public void testStory_universe_getTotalCreatureCount() {
        universe.addCommunity(mockCommunity1); 
        universe.addCommunity(mockCommunity2); 
        
        int totalCount = universe.getTotalCreatureCount();
        
        assertEquals(8, totalCount);
        
        verify(mockCommunity1).getMemberCount();
        verify(mockCommunity2).getMemberCount();
    }
    
    @Test
    public void testStory_universe_getPlanetsReturnsCopy() {
        universe.addPlanet(mockPlanet1);
        
        List<Planet> planets = universe.getPlanets();
        planets.add(mockPlanet2);
        
        assertEquals(1, universe.getPlanets().size());
    }
    
    @Test
    public void testStory_universe_getCommunitiesReturnsCopy() {
        universe.addCommunity(mockCommunity1);
        
        List<Community> communities = universe.getCommunities();
        communities.add(mockCommunity2);
        
        assertEquals(1, universe.getCommunities().size());
    }

    private void resetSingleton() {
        try {
            java.lang.reflect.Field instance = Universe.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
