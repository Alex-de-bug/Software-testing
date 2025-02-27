package com.lab1.task3;

public class ZafodStory {
    private Creature zafod;
    private Planet goldPlanet;
    private Universe universe;
    
    public ZafodStory() {
        universe = Universe.getInstance();
        
        goldPlanet = new Planet("Golden Planet", 6371.0, Material.GOLD, 9.8);
        universe.addPlanet(goldPlanet);
        
        zafod = new Creature("Zafod", 1.85, 75.0);
        
        zafod.lieDown();
    }
    
    public void runStory() {
        // "Легко, как балерина, Зафод вскочил на ноги"
        zafod.jumpToFeet();
        
        // "и начал осматриваться"
        zafod.observeSurroundings(goldPlanet);
        
        // "До самого горизонта во все стороны простиралась сплошная золотая поверхность" 
        System.out.println(goldPlanet.getSurface().describeExtent());
        
        // "Она блестела, как... впрочем, этому невозможно подобрать сравнение"
        System.out.println(goldPlanet.describeUniqueness());
        
        zafod.getMouth().gasp();
        
        zafod.lookAround();
    }
    
    // Getters for testing
    public Creature getZafod() {
        return zafod;
    }
    
    public Planet getGoldPlanet() {
        return goldPlanet;
    }
}