package com.lab1.task3;

public class Planet {
    private String name;
    private double radius;
    private Surface surface;
    private double gravity;
    private Position position;
    
    public Planet(String name, double radius, Material surfaceMaterial, double gravity) {
        this.name = name;
        this.radius = radius;
        double surfaceArea = 4 * Math.PI * radius * radius;
        this.surface = new Surface(surfaceMaterial, surfaceArea);
        this.gravity = gravity;
        this.position = new Position(0, 0, 0);
    }
    
    public double calculateSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }
    
    public String describeView() {
        StringBuilder description = new StringBuilder();
        description.append("The ").append(surface.getMaterial().getDescription())
                   .append(" planet ").append(name).append(" extends to the horizon in all directions. ");
        
        if (surface.isShiny()) {
            description.append("It shines like nothing else in the Universe.");
        }
        
        return description.toString();
    }
    
    // Getters and setters
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
}