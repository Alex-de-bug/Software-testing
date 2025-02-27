package com.lab1.task3;

public class Planet {
    private String name;
    private double radius;
    private Surface surface;
    private double gravity;
    private Position position;
    private double horizonDistance;
    
    public Planet(String name, double radius, Material surfaceMaterial, double gravity) {
        this.name = name;
        this.radius = radius;
        double surfaceArea = 4 * Math.PI * radius * radius;
        this.surface = new Surface(surfaceMaterial, surfaceArea);
        this.gravity = gravity;
        this.position = new Position(0, 0, 0);
        this.horizonDistance = calculateHorizonDistance(1.7); 
    }
    
    public double calculateHorizonDistance(double observerHeight) {
        this.horizonDistance = Math.sqrt(2 * radius * observerHeight + Math.pow(observerHeight, 2));
        return this.horizonDistance;
    }
    
    public String describeView() {
        StringBuilder description = new StringBuilder();
        description.append("The ").append(surface.getMaterial().getDescription())
                   .append(" planet ").append(name).append(" extends to the horizon in all directions for about ")
                   .append(String.format("%.2f", horizonDistance)).append(" km. ");
        
        if (surface.isShiny()) {
            description.append("It shines like nothing else in the Universe.");
        }
        
        return description.toString();
    }
    
    public String describeUniqueness() {
        if (surface.getMaterial() == Material.GOLD) {
            return "The gold surface shines in a way that nothing else in the Universe can compare to - " +
                   "it's impossible to find a suitable comparison.";
        } else {
            return "The " + surface.getMaterial().getDescription() + " surface is quite distinctive.";
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public double getHorizonDistance() {
        return horizonDistance;
    }
}