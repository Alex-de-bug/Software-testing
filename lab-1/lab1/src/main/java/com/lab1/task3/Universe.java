package com.lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class Universe {
    private static Universe instance; // Singleton pattern
    private List<Planet> planets;
    private List<Community> communities;
    
    private Universe() {
        planets = new ArrayList<>();
        communities = new ArrayList<>();
    }
    
    public static Universe getInstance() {
        if (instance == null) {
            instance = new Universe();
        }
        return instance;
    }
    
    public void addPlanet(Planet planet) {
        planets.add(planet);
    }
    
    public void removePlanet(Planet planet) {
        planets.remove(planet);
    }
    
    public void addCommunity(Community community) {
        communities.add(community);
    }
    
    public void removeCommunity(Community community) {
        communities.remove(community);
    }
    
    public List<Planet> findShinyPlanets() {
        List<Planet> shinyPlanets = new ArrayList<>();
        for (Planet planet : planets) {
            if (planet.getSurface().isShiny()) {
                shinyPlanets.add(planet);
            }
        }
        return shinyPlanets;
    }
    
    public int getTotalCreatureCount() {
        int count = 0;
        for (Community community : communities) {
            count += community.getMemberCount();
        }
        return count;
    }
    
    public List<Planet> getPlanets() {
        return new ArrayList<>(planets);
    }
    
    public List<Community> getCommunities() {
        return new ArrayList<>(communities);
    }
}